package com.jianguo.servlet.wages;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_record_Sql;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_wages_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush_money;
import com.jianguo.util.Jdpushcc_money;
import com.jianguo.util.Jdpusher_money;
import com.jianguo.util.Text_Sms;

public class T_wages_Insert_ChangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_wages_Insert_ChangServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_wages_Insert_ChangServlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		Gson gson = new Gson();
		String job_id =request.getParameter("job_id");
		String json =request.getParameter("json");
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ly_time = sdf.format(new java.util.Date());
			user_ss list2 = gson.fromJson(json, user_ss.class);
			for (int i = 0; i < list2.getList_t_wages_Bean().size(); i++) {
				T_wages_Bean t = list2.getList_t_wages_Bean().get(i);
				T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(t.getLogin_id()+"");
				double ddd = t_user_money.getMoney()+ t.getReal_money();
				float scale = (float) ddd;
				DecimalFormat fnum = new DecimalFormat("##0.00"); 
				String dd=fnum.format(scale); 
				float scale2 = (float) t.getReal_money();
				DecimalFormat fnum2 = new DecimalFormat("##0.00");
				String dd2=fnum2.format(scale2); 
				if(!T_wages_Sql.check2(t.getLogin_id()+"", t.getJob_id()+"",ly_time)){
					T_wages_Sql.insert(t.getLogin_id()+"", t.getJob_id()+"", t.getHould_money()+"", dd2+"", t.getRemarks(), ly_time);
					T_enroll_Sql.update_state("1",t.getLogin_id()+"", t.getJob_id()+"");
				if(!T_user_money_Sql.check_login_id(t.getLogin_id()+"")){
					T_user_money_Sql.insert(t.getLogin_id()+"", "0", "0", "0", "0", "0", "0", "0");
				}
					T_user_money_Sql.update_moneyss(dd, t.getLogin_id()+"");
					T_enroll_Sql.update_status2("12", t.getLogin_id()+"",t.getJob_id()+"");

					T_job_record_Sql.update_complete(t.getLogin_id()+"");
					
					T_job_Bean t_job = T_job_Sql.select_id(t.getJob_id()+"");
					T_user_login_Bean t_user_login = T_user_login_Sql.select_id(t.getLogin_id()+"");
					Text_Sms.textdemos4(t_user_login.getTel(),t_job.getName(),dd2+"");//结算短信
					T_job_Sql.update_count_jian(t_job.getId()+"");
					
					Jdpush_money.sendPush("工资到账，账户已收到"+dd2+"元,【"+t_job.getName()+"】兼职的工资","jianguo"+t.getLogin_id()+"");
					Jdpusher_money.sendPush("工资到账，账户已收到"+dd2+"元,【"+t_job.getName()+"】兼职的工资","jianguo"+t.getLogin_id()+"");
					Jdpushcc_money.sendPush("工资到账，账户已收到"+dd2+"元,【"+t_job.getName()+"】兼职的工资","jianguo"+t.getLogin_id()+"");
					T_push_Sql.insert(t.getLogin_id()+"", t_job.getName(), "工资到账", "工资到账，账户已收到"+dd2+"元,【"+t_job.getName()+"】兼职的工资", "1","0","0","0", ly_time);
				}
			}
			List<T_enroll_Bean> list_t_enroll2 = T_enroll_Sql.select_job_id_status_countnew(job_id, "9");
			params.put("message", "工资结算成功");
			params.put("sum", list_t_enroll2.size());
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			String str = gson.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
			//------------------访问限制--------开始----------------------
		}else{
			params.put("message", "无效访问");
			params.put("code", "404");
			PrintWriter pw = response.getWriter();
			String str = gson.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------访问限制--------结束----------------------
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
