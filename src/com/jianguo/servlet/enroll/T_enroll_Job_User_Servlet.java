package com.jianguo.servlet.enroll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;

public class T_enroll_Job_User_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_enroll_Job_User_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}
	
	//http://192.168.1.132/JianGuo_Server/T_enroll_Job_User_Servlet?only=C115261C65717AF2C44CA17ED00C4089&job_id=1178&nv_job_id=0&count=0
	//http://101.200.205.243:8080/T_enroll_Job_User_Servlet?only=C115261C65717AF2C44CA17ED00C4089&job_id=1178&nv_job_id=0&count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_enroll_Job_User_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		Map params2 =  new HashMap();
		
		String job_id =request.getParameter("job_id");
		String nv_job_id =request.getParameter("nv_job_id");
		String count =request.getParameter("count");
		
		params2 = request.getParameterMap();
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			List<T_enroll_Bean> list_t_enroll = null;
			List<T_enroll_Bean> list_t_enroll2 = null;
			
			//if(nv_job_id.equals("0")){
				list_t_enroll = T_enroll_Sql.select_job_id_statuss(job_id, "9","10","5",count);
				list_t_enroll2 = T_enroll_Sql.select_job_id_status_IDs(job_id, "9","10");
				/*}else{
				list_t_enroll = T_enroll_Sql.select_job_id_status_nvs(job_id, nv_job_id, "9","10", count);
				list_t_enroll2 = T_enroll_Sql.select_job_id_status_nv_IDs(job_id, nv_job_id, "9","10");
			}*/
			System.out.println(list_t_enroll.size()+"------------");
			List<T_user_info_Bean> list_t_user_info = new ArrayList<T_user_info_Bean>();
			for (int i = 0; i < list_t_enroll.size(); i++) {
				T_enroll_Bean t_enroll = list_t_enroll.get(i);
				
				T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_enroll.getLogin_id()+"");
				
				List<T_enroll_Bean> list_t_enroll_4 = T_enroll_Sql.select_login_id_status(t_enroll.getLogin_id()+"", "5");
				t_user_info.setComplete_job(list_t_enroll_4.size());
				
				List<T_enroll_Bean> list_t_enroll_1 = T_enroll_Sql.select_login_id_status(t_enroll.getLogin_id()+"", "1");
				t_user_info.setCancel_job(list_t_enroll_1.size());
				
				T_enroll_Bean t_enroll2 = T_enroll_Sql.select_login_id_job_id(t_enroll.getLogin_id()+"", job_id);
				t_user_info.setTime_job(t_enroll2.getLogin_time());
				t_user_info.setUser_status(t_enroll2.getStatus());
				t_user_info.setRemarks_job("");

				T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(t_enroll.getLogin_id()+"");
				t_user_info.setSex_resume(t_user_resume.getSex());
				t_user_info.setIntoschool_date_resume(t_user_resume.getIntoschool_date());
				
				T_user_login_Bean t_user_login = T_user_login_Sql.select_id(t_enroll.getLogin_id()+"");
				t_user_info.setTel(t_user_login.getTel());
				if(t_user_login.getStatus() == 2){
					t_user_info.setRealname(2);
				}else{
					t_user_info.setRealname(0);
				}
				
				T_job_Bean t_job = T_job_Sql.select_id(job_id);
				t_user_info.setHould_money(t_job.getMoney()+"");
				t_user_info.setReal_money(t_job.getMoney()+"");
				
				t_user_info.setJob_id(t_enroll.getJob_id()+"");

				list_t_user_info.add(t_user_info);
			}
			
			Map map = new HashMap();
			map.put("list_t_user_info", list_t_user_info);
			map.put("user_sum", list_t_enroll2.size());

			params.put("data", map);
			params.put("message", "报名用户查询成功");
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
			pw.close();

			//------------------访问限制--------开始----------------------
		}else{
			params.put("message", "无效访问");
			params.put("code", "404");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------访问限制--------结束----------------------
	}
}
