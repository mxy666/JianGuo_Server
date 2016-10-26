package com.jianguo.servlet.enroll;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.util.Frequently;

public class T_enroll_Agree_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_enroll_Agree_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_enroll_Agree_Servlet?only=D676F8B429AC1D11E2F0BE13493190AE&login_id=2&job_id=1
	//http://101.200.205.243:8080/T_enroll_Agree_Servlet?only=C978AF1CD86BC4A761985B762A5C6DC1&job_id=24&offer=13
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_enroll_Agree_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String job_id =request.getParameter("job_id");
		String offer =request.getParameter("offer");
		String alike =request.getParameter("alike");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			T_job_Bean t_job = T_job_Sql.select_id(job_id);
			long ll = System.currentTimeMillis()/1000;
			T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_job.getId()+"");
			int ii = Integer.parseInt(t_job_info.getStart_time());
//			if(ll > ii){
//				T_enroll_Sql.update_status("8", job_id);
//				T_job_Sql.update_status("2", job_id);
//				
//				params.put("message", "工作已开始，不能操作该兼职状态");
//				params.put("code", "500");
//				PrintWriter pw = response.getWriter();
//				Gson g = new Gson();
//				String str = g.toJson(params); 
//				pw.write(str);
//				pw.flush();
//				pw.close();
//			}else{
			
			if(offer.equals("9")){
				T_job_Sql.update_status("3", job_id);
				T_enroll_Sql.update_status("9", job_id);
				T_enroll_Sql.update_statusss("9", job_id);
				if(!alike.equals("0")){
					T_job_Bean t_job11 = T_job_Sql.select_alike(alike);
						T_job_Sql.update_status("3", t_job11.getId()+"");
					T_enroll_Sql.update_status("9", t_job11.getId()+"");
					T_enroll_Sql.update_statusss("9", t_job11.getId()+"");
				}
				
				params.put("message", "兼职结束成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
				
			}else if(offer.equals("13")){
//				T_enroll_Sql.update_status("8", job_id);
//				T_job_Sql.update_status("2", job_id);
				
				T_enroll_Sql.update_status("13", job_id);
				T_job_Sql.update_status("6", job_id);
				
				if(!alike.equals("0")){
					T_job_Bean t_job11 = T_job_Sql.select_alike(alike);
					T_job_Sql.update_status("6", t_job11.getId()+"");
					T_enroll_Sql.update_status("13", job_id);
				}
				
				params.put("message", "兼职下架成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}
//			}
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
