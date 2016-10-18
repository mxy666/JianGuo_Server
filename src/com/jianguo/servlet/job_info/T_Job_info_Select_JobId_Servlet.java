package com.jianguo.servlet.job_info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.sql.Job_Sql;
import com.jianguo.sql.T_attent_Sql;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

public class T_Job_info_Select_JobId_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_Job_info_Select_JobId_Servlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.135/JianGuo_Server/T_Job_info_Select_JobId_Servlet?only=BCB78D5391002043797A9FE2EB575587&login_id=3&job_id=199&merchant_id=3&alike=1471335057093
	//http://101.200.205.243:8080/T_Job_info_Select_JobId_Servlet?only=936DE33AB6A94B862A79B97595359E70&tel=111118101s050625&login_id=45
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_Job_info_Select_JobId_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String job_id =request.getParameter("job_id");
		String merchant_id =request.getParameter("merchant_id");
		String alike =request.getParameter("alike");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(job_id);
			T_job_Bean t_jobs = T_job_Sql.select_id(job_id);
			t_job_info.setMode(t_jobs.getMode());
			T_merchant_Bean t_merchant = T_merchant_Sql.select_id(merchant_id);
			
			boolean b = T_attent_Sql.check_login_id_collection(login_id, job_id);
			
			if(b == true){
				t_job_info.setIs_collection("1");
			}else{
				t_job_info.setIs_collection("0");
			}
			
			if(T_enroll_Sql.check_login_id_job_id2(login_id, job_id, "12")){
				T_enroll_Sql.delete(login_id, job_id);
			}
			
			if(!T_enroll_Sql.check_login_id_job_id(login_id, job_id) || T_enroll_Sql.check_login_id_job_id2(login_id, job_id, "1")){
				t_job_info.setIs_enroll("0");
			}else{
				t_job_info.setIs_enroll("1");
			}
			
			boolean b2 = T_attent_Sql.check_login_id_follow(login_id, merchant_id);
			
			if(b2 == true ){
				t_merchant.setIs_follow("1");
			}else{
				t_merchant.setIs_follow("0");
			}
			t_merchant.setTel(t_job_info.getTel());
			
			T_city_Bean t_city = Job_Sql.selectTcity_id(t_jobs.getCity_id()+"");
			t_job_info.setAddress(t_city.getCity()+"-"+t_job_info.getAddress());
			
			if(!alike.equals("0")){
				T_job_Bean t_job = T_job_Sql.select_alike(alike);
				t_job_info.setNv_job_id(t_job.getId()+"");
				t_job_info.setNv_sum(t_job.getSum()+"");
				
//				t_job_info.setNv_count(list_t_enroll.size()+"");
				t_job_info.setNv_count(t_job.getCount()+"");
				t_job_info.setNv_user_count(t_job.getUser_count()+"");
			}
			
			Map map = new HashMap();
			map.put("t_job_info", t_job_info);
			map.put("t_merchant", t_merchant);

			params.put("data", map);
			params.put("message", "兼职详情信息录入成功");
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
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
