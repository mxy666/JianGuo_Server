package com.jianguo.servlet.pc.html;

import com.google.gson.Gson;
import com.jianguo.bean.*;
import com.jianguo.sql.*;
import com.jianguo.util.*;
import com.qiniu.util.Auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mxy on 2016/9/22.
 * ����
 */
@WebServlet(name = "JobWebBaoMServlet")
public class JobWebBaoMServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Map params = new HashMap();

        // final String login_id =request.getParameter("login_id");
        final String job_id = request.getParameter("jobId");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String birthdate = request.getParameter("birthdate");
        final String tel = request.getParameter("tel");
        String code = request.getParameter("smscode");
        String city = request.getParameter("city");

        T_user_login_Bean user=WebJobSql.check_tel(tel);
        if(T_tel_code_Sql.check_tel_code(tel, code)){//��֤����ȷ���ܱ�����������

                if(user.getTel()==null||user.getTel().equals("")){//true����false���������У�
                    //����û�
                    addUser(tel,city,name);
                 // user=WebJobSql.check_tel(tel);

                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final String ly_time = sdf.format(new java.util.Date());

                final T_job_Bean t_job11 = T_job_Sql.select_id(job_id);
                final T_job_info_Bean t_job_onfo11 = T_job_info_Sql.select_job_id(job_id);

                if (T_enroll_Sql.check_login_id_job_id2(user.getId()+"", job_id, "12")) {
                    T_enroll_Sql.delete(user.getId()+"", job_id);
                }

                if (!T_enroll_Sql.check_login_id_job_id(user.getId()+"", job_id) || T_enroll_Sql.check_login_id_job_id2(user.getId()+"", job_id, "1")) {

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                    String ly_time2 = sdf2.format(new java.util.Date());
                    int ii = Integer.parseInt(ly_time2);

                    T_enroll_limit_Bean t_enroll_limit = T_enroll_limit_Sql.select_login_id(user.getId()+"");
                    if (t_enroll_limit.getCount() >= 5 && t_enroll_limit.getDate() == ii) {
                        PrintWriter out = response.getWriter();
                        out.flush();out.println("<script>");
                        out.println("alert('���ձ����Ѵ�����');");
                        out.println("history.back();");
                        out.println("</script>");

                    } else {
                        if (t_job11.getMerchant_id() == 28 || t_job11.getMerchant_id() == 29 || t_job11.getMerchant_id() == 30 || t_job11.getMerchant_id() == 31 || t_job11.getMerchant_id() == 32) {
                            final T_user_login_Bean t_user_login = T_user_login_Sql.select_id(user.getId()+"");
                            final T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(user.getId()+"");
                            String ss_sex = "";
                            if (t_user_resume.getSex() == 1) {
                                ss_sex = "��";
                            } else {
                                ss_sex = "Ů";
                            }
                            //��¼�̼ҷ�����
                            new Thread(new Runnable() {
                                public void run() {
                                    Text_Sms.Enroll(t_job_onfo11.getTel(), t_job11.getName(), t_user_resume.getName(), (t_user_resume.getSex() == 1) ? "��" : "Ů", t_user_login.getTel(), t_user_resume.getSchool());
                                }
                            }).start();

                            T_job_Sql.update_count(job_id);

                            new Thread(new Runnable() {
                                public void run() {
                                    Text_Sms.textdemos1212(t_user_login.getTel(), t_job_onfo11.getTel());//���û�������
                                }
                            }).start();
                        } else {

                            final T_user_login_Bean t_user_login = T_user_login_Sql.select_id(user.getId()+"");
                            new Thread(new Runnable() {
                                public void run() {
                                    Text_Sms.textdemos1(t_user_login.getTel());
                                }
                            }).start();
                        }


                        T_enroll_limit_Bean t_enroll_limit2 = T_enroll_limit_Sql.select_login_id(user.getId()+"");
                        if (t_enroll_limit2.getDate() != ii) {
                            T_enroll_limit_Sql.update_count0(ly_time2, user.getId()+"");
                        } else {
                            T_enroll_limit_Sql.update_count(ly_time2, user.getId()+"");
                        }
                        if (!T_enroll_Sql.check_login_id_job_id(user.getId()+"", job_id)) {
                            String str_max = "";
                            if (t_job11.getMax() == 0) {
                                str_max = "0";
                            } else {
                                str_max = "1";
                            }
                            T_enroll_Sql.insert(user.getId()+"", job_id, "0", ly_time, "0", str_max);

                            if (!T_job_record_Sql.check_login_id(user.getId()+"")) {
                                T_job_record_Sql.insert(user.getId()+"", "0", "0");
                            }
                        } else if (T_enroll_Sql.check_login_id_job_id2(user.getId()+"", job_id, "1")) {
                            T_enroll_Sql.update_status("0", user.getId()+"", job_id);
                        }
                        T_job_Sql.update_user_count(job_id);

                        new Thread(new Runnable() {
                            public void run() {
                                T_user_login_Bean  user1=WebJobSql.check_tel(tel);
                                Jdpush.sendPush("������" + t_job11.getName() + "�����ύ����ȴ��̼�ȷ��", "jianguo" + user1.getId()+"");
                                Jdpusher.sendPush("������" + t_job11.getName() + "�����ύ����ȴ��̼�ȷ��", "jianguo" + user1.getId()+"");
                                Jdpushcc.sendPush("������" + t_job11.getName() + "�����ύ����ȴ��̼�ȷ��", "jianguo" + user1.getId()+"");

                                T_push_Sql.insert(user1.getId()+"", t_job11.getName(), "�ѱ���", "������" + t_job11.getName() + "�����ύ����ȴ��̼�ȷ��", "0", "0", "0", "0", ly_time);

                                T_job_Bean t_job = T_job_Sql.select_id(job_id);
                                T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id() + "");
                                Jdpush_shang.sendPush("�������ġ�" + t_job.getName() + "����ְ���˱���", "jianguo" + t_merchant.getLogin_id());
                                Jdpusher_shang.sendPush("�������ġ�" + t_job.getName() + "����ְ���˱���", "jianguo" + t_merchant.getLogin_id());
                                Jdpushcc_shang.sendPush("�������ġ�" + t_job.getName() + "����ְ���˱���", "jianguo" + t_merchant.getLogin_id());

                            }
                        }).start();
                        PrintWriter out = response.getWriter();
                        out.flush();out.println("<script>");
                        out.println("alert('�����ɹ�');");
                        out.println("history.back();");
                        out.println("</script>");
                    }
//			}
                } else {
                    PrintWriter out = response.getWriter();
                    out.flush();out.println("<script>");
                    out.println("alert('����ʧ��');");
                    out.println("history.back();");
                    out.println("</script>");
                }
            }else{
                PrintWriter out = response.getWriter();
                out.flush();out.println("<script>");
                out.println("alert('��֤�벻��ȷ');");
                out.println("history.back();");
                out.println("</script>");
            }


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public void addUser(String tel,String city,String name){
        long random =(long)((Math.random()*9+1)*100000);
        String codes = random+"";
        String str_psd = MD5Util.MD5(codes);//�����ʼ��
        T_user_login_Sql.insert_tel(tel, str_psd,"1","1","0","0","0");

        T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ly_time = sdf.format(new java.util.Date());
        T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "���"+t_user_login.getId(),name, "http://v3.jianguojob.com/moren.png","","0","0","0", ly_time, ly_time);
        T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "���"+t_user_login.getId(), name,"http://v3.jianguojob.com/moren.png","","","1","0","0","","","","","");
        T_user_money_Sql.insert(t_user_login.getId()+"", "0", "8.88", "0", "0", "0", "0", "0");

        T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
        //�򵥵�token(��ţ)
        Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");
        //		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//һ��
        String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7��
        t_user_info.setQiniu(qiniu_token);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        String ly_time2 = sdf2.format(new java.util.Date());
        T_enroll_limit_Sql.insert(t_user_login.getId()+"", "0", ly_time2);

        if(city == "" || city == null){
        }else{
            if(city == "0" || city.equals("0")){
            }else{
                if(city.equals("010")){
                    T_user_login_Sql.update_city_id("����", t_user_login.getId()+"");
                }else if(city.equals("0899")){
                    T_user_login_Sql.update_city_id("����", t_user_login.getId()+"");
                }else if(city.equals("0898")){
                    T_user_login_Sql.update_city_id("����", t_user_login.getId()+"");
                }else if(city.equals("0571")){
                    T_user_login_Sql.update_city_id("����", t_user_login.getId()+"");
                }else if(city.equals("029")){
                    T_user_login_Sql.update_city_id("����", t_user_login.getId()+"");
                }else if(city.equals("027")){
                    T_user_login_Sql.update_city_id("�人", t_user_login.getId()+"");
                }
                else{
                    T_user_login_Sql.update_city_id(city, t_user_login.getId()+"");
                }
            }

        }
    }
}
