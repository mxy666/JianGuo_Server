package com.jianguo.servlet.pc.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_wai_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_enroll_limit_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_record_Sql;
import com.jianguo.sql.T_job_wai_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.MD5Util;

public class T_PC_User_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_PC_User_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_PC_User_Insert_Servlet?only=51EDF82FC91AD97CBBB608BCDF5AAA26&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/T_PC_User_Insert_Servlet?only=51EDF82FC91AD97CBBB608BCDF5AAA26&tel=181ssss012222s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/user_agreement.jsp
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_PC_User_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		Logger logger = Logger.getLogger("log");
		logger.info("日志信息开始!");
		logger.info("T_PC_User_Insert_Servlet!");
		   
		String tel =request.getParameter("tel");
		
		String name =request.getParameter("name");
		//String school =request.getParameter("school"); 
		String job_id =request.getParameter("job_id");
		 
		logger.info(job_id);
		 
		  try {
//		String password =request.getParameter("password");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ly_time = sdf.format(new java.util.Date());
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String ly_time2 = sdf2.format(new java.util.Date());
			
			T_job_Bean t_job11 = T_job_Sql.select_id(job_id);
			boolean b = T_user_login_Sql.check_tel(tel);
			if(b == false){
				long random =(long)((Math.random()*9+1)*100000);
				String code = random+"";
				String str_psd = MD5Util.MD5(code);

				T_user_login_Sql.insert_tel(tel, str_psd,"1","1","0","0","0");
					T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
					
					T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),name, "http://v3.jianguojob.com/moren.png","","0","0","0", ly_time, ly_time);
					T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),name, "http://v3.jianguojob.com/moren.png","无","","0","0","0","","","","","");
					T_user_money_Sql.insert(t_user_login.getId()+"", "0", "0", "0", "0", "0", "0", "0");

					T_enroll_limit_Sql.insert(t_user_login.getId()+"", "0", ly_time2);
					
					T_enroll_limit_Sql.update_count(ly_time2,t_user_login.getId()+"");
					if(!T_enroll_Sql.check_login_id_job_id(t_user_login.getId()+"", job_id)){
						String str_max = "";
						if(t_job11.getMax() == 0){
							str_max = "0";
						}else{
							str_max = "1";
						}
						T_enroll_Sql.insert(t_user_login.getId()+"", job_id, "9",ly_time,"0",str_max);
						
						if(!T_job_record_Sql.check_login_id(t_user_login.getId()+"")){
							T_job_record_Sql.insert(t_user_login.getId()+"", "1", "0");
						}
					}else if(T_enroll_Sql.check_login_id_job_id(t_user_login.getId()+"", job_id)){
						T_enroll_Sql.update_status("9",t_user_login.getId()+"", job_id);
					}
					T_job_Sql.update_count(job_id);
					T_job_Sql.update_user_count(job_id);
					
				}else{
					T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
					T_enroll_limit_Sql.update_count(ly_time2,t_user_login.getId()+"");
					if(!T_enroll_Sql.check_login_id_job_id(t_user_login.getId()+"", job_id)){
						String str_max = "";
						if(t_job11.getMax() == 0){
							str_max = "0";
						}else{
							str_max = "1";
						}
						T_enroll_Sql.insert(t_user_login.getId()+"", job_id, "9",ly_time,"0",str_max);
						
						if(!T_job_record_Sql.check_login_id(t_user_login.getId()+"")){
							T_job_record_Sql.insert(t_user_login.getId()+"", "1", "0");
						}
					}else if(T_enroll_Sql.check_login_id_job_id(t_user_login.getId()+"", job_id)){
						T_enroll_Sql.update_status("9",t_user_login.getId()+"", job_id);
					}
					T_job_Sql.update_count(job_id);
				}
			
			T_job_wai_Sql.insert(job_id, tel, name, "0", "");

			List<T_job_wai_Bean> list_t_job_wai = T_job_wai_Sql.select_job_id(job_id);

			List<T_job_wai_Bean> list_t_job_wai2 = new ArrayList<T_job_wai_Bean>();
			for (int i = 0; i < list_t_job_wai.size(); i++) {
				T_job_wai_Bean t_job_wai = list_t_job_wai.get(i);
				if(t_job_wai.getSex() == 1){
					t_job_wai.setS_sex("男");
				}else if(t_job_wai.getSex() == 0){
					t_job_wai.setS_sex("女");
				}
				list_t_job_wai2.add(t_job_wai);
			}				
				request.setAttribute("job_id", job_id);
				request.setAttribute("list_t_job_wai", list_t_job_wai2);
				request.getRequestDispatcher("job_wai_list.jsp").forward(request, response);
		  } catch (Exception  e) {
			   logger.error("异常",e);
			   e.printStackTrace();
		  }
	}
}
