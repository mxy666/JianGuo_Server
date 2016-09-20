package com.jianguo.merchant.wages;

import com.google.gson.Gson;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.merchant.mersql.EnrollSql;
import com.jianguo.merchant.mersql.WagesSql;
import com.jianguo.servlet.wages.T_wages_Insert_Servlet;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_record_Sql;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush;
import com.jianguo.util.Jdpushcc;
import com.jianguo.util.Jdpusher;
import com.jianguo.util.ThreadPoolManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
/**
 * Created by Administrator on 2016/9/12.
 */
@WebServlet("/LongRunningServlet")
public class PayWageServlet  extends HttpServlet {
    /**
     * @api {post} PayWageServlet/ �̼ҽ���
     * @apiName LoginServlet
     * @apiGroup wages
     * @apiParam {String} job_id ��ְjobid
     * @apiParam {String} json Users info
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  ��֤���Ѿ����ͣ���ע����գ�
     * @apiError {String} code 400
     * @apiError{String} message

     */






















    private static final long serialVersionUID = 1L;
    private List<T_wages_Bean> wageBeanFails=new ArrayList<>();
    private StringBuilder message=new StringBuilder("");
    public PayWageServlet() {
        super();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("---T_wages_Insert_Servlet---");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Map params =  new HashMap();
//		String login_id =request.getParameter("login_id");
        String job_id =request.getParameter("job_id");
        String json =request.getParameter("json");
        //------------------��������--------��ʼ----------------------
//        String only =request.getParameter("only");
        String ss_only = Frequently.daycount();
        String ss_only2 = Frequently.daycount2();
        String ss_only3 = Frequently.daycount3();
//        if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
            Gson g2 = new Gson();
            PayWageServlet.user_ss userList = g2.fromJson(json, PayWageServlet.user_ss.class);
            for (int i = 0; i < userList.getList_t_wages_Bean().size(); i++) {
                PayWageServlet.PayRunnable payRunnable=new PayWageServlet.PayRunnable();
                payRunnable.setWagesBean(userList.getList_t_wages_Bean().get(i));
                ThreadPoolManager.newInstance().addExecuteTask(payRunnable);
                if (userList.getList_t_wages_Bean().size()-1==i) {
                    params.put("message", message.append("�ɹ�"));
                    params.put("sum", wageBeanFails.size());
                    params.put("code", "200");
                    PrintWriter pw = response.getWriter();
                    Gson g = new Gson();
                    String str = g.toJson(params);
                    pw.write(str);
                    pw.flush();
                    pw.close();
                }
            }
            List<T_enroll_Bean> list_t_enroll2 = null;
            try {
                list_t_enroll2 = EnrollSql.select_job_id_status_countnew(job_id, "9");
            } catch (SQLException e) {
                e.printStackTrace();
                message.append(e.getMessage());
            }
            if(list_t_enroll2.size() == 0){
                T_job_Sql.update_status("5", job_id);
            }

//        }else{
//            params.put("message", "��Ч����");
//            params.put("code", "404");
//            PrintWriter pw = response.getWriter();
//            Gson g = new Gson();
//            String str = g.toJson(params);
//            pw.write(str);
//            pw.flush();
//            pw.close();
//        }
        //------------------��������--------����----------------------
    }
    public class PayRunnable implements Runnable{
        private T_wages_Bean wagesBean;
        public void setWagesBean(T_wages_Bean wagesBean) {
            this.wagesBean = wagesBean;
        }
        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ly_time = sdf.format(new java.util.Date());
            T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(wagesBean.getLogin_id()+"");
            double ddd = t_user_money.getMoney()+ wagesBean.getReal_money();
            System.out.println(t_user_money.getMoney());
            System.out.println(wagesBean.getReal_money());
            System.out.println(ddd);
            float scale = (float) ddd;
            DecimalFormat fnum = new DecimalFormat("##0.00");
            String dd=fnum.format(scale);
            float scale2 = (float) wagesBean.getReal_money();
            DecimalFormat fnum2 = new DecimalFormat("##0.00");
            String dd2=fnum2.format(scale2);
            //�ж��Ƿ�����
            try {
                if(!WagesSql.check(wagesBean.getLogin_id()+"", wagesBean.getJob_id()+"")){
                    //���빤�ʱ�
                    WagesSql.insert(wagesBean.getLogin_id()+"", wagesBean.getJob_id()+"", wagesBean.getHould_money()+"", dd2+"", wagesBean.getRemarks(), ly_time);
                    //���¹���״̬,��һ�������ǽ������״̬���ڶ��������ǹ���״̬�����
                    EnrollSql.update_state("1","12",wagesBean.getLogin_id()+"", wagesBean.getJob_id()+"");
                    //����Ǯ������
                    T_user_money_Sql.update_moneyss(dd, wagesBean.getLogin_id()+"");
                    //������ɹ�����¼
                    T_job_record_Sql.update_complete(wagesBean.getLogin_id()+"");
                    //��ѯ������Ϣ���ڷ�����Ϣ
                    T_job_Bean t_job = T_job_Sql.select_id(wagesBean.getJob_id()+"");
                    Jdpush.sendPush("���ʵ��ˣ��˻����յ�"+dd2+"Ԫ,��"+t_job.getName()+"����ְ�Ĺ���","jianguo"+wagesBean.getLogin_id()+"");
                    Jdpusher.sendPush("���ʵ��ˣ��˻����յ�"+dd2+"Ԫ,��"+t_job.getName()+"����ְ�Ĺ���","jianguo"+wagesBean.getLogin_id()+"");
                    Jdpushcc.sendPush("���ʵ��ˣ��˻����յ�"+dd2+"Ԫ,��"+t_job.getName()+"����ְ�Ĺ���","jianguo"+wagesBean.getLogin_id()+"");
                    T_push_Sql.insert(wagesBean.getLogin_id()+"", t_job.getName(), "���ʵ���", "���ʵ��ˣ��˻����յ�"+dd2+"Ԫ,��"+t_job.getName()+"����ְ�Ĺ���", "1","0","0","0", ly_time);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                message.append("����ʧ���û�loginid="+wagesBean.getLogin_id()+"ԭ��"+e.getMessage()+":");
                wageBeanFails.add(wagesBean);
            }

        }
    }
    private class user_ss{
        private List<T_wages_Bean> list_t_wages_Bean;
        public void setList_t_wages_Bean(List<T_wages_Bean> list_t_wages_Bean) {
            this.list_t_wages_Bean = list_t_wages_Bean;
        }

        public List<T_wages_Bean> getList_t_wages_Bean() {
            return list_t_wages_Bean;
        }
    }
}
