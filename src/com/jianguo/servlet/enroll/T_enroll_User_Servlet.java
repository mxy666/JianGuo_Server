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
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.util.Frequently;

public class T_enroll_User_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_enroll_User_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_enroll_User_Servlet?only=AEEB31108665D33C9ED84D8DF1185E3E&login_id=1&count=0
	//http://101.200.205.243:8080/T_enroll_User_Servlet?only=9848E9FF99388A0B96100FBB8FABA812&login_id=20&count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_enroll_User_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String login_id =request.getParameter("login_id");
		String type =request.getParameter("type");
		String count =request.getParameter("count");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			List<T_enroll_Bean> list_t_enroll = null;
			
//			if(type.equals("0")){
//			list_t_enroll = T_enroll_Sql.select_login_id_status1(login_id, "0","1","2",count);
//			}else if(type.equals("1")){
//				list_t_enroll = T_enroll_Sql.select_login_id_status2(login_id, "3","4","5","6","7","8",count);
//			}else if(type.equals("2")){
//				list_t_enroll = T_enroll_Sql.select_login_id_status3(login_id, "9","10","11","12",count);
//			}
			list_t_enroll = T_enroll_Sql.select_login_id_status4(login_id,count);

			List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();
			for (int i = 0; i < list_t_enroll.size(); i++) {
				T_enroll_Bean t_enroll = list_t_enroll.get(i);
				T_job_Bean t_job = T_job_Sql.select_id(t_enroll.getJob_id()+"");
				
				T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_enroll.getJob_id()+"");
				t_job.setInfo_start_time(t_job_info.getStart_time());
				t_job.setInfo_stop_time(t_job_info.getStop_time());
				
				t_job.setUser_status(t_enroll.getStatus()+"");
				list_t_job.add(t_job);
			}
			
			Map map = new HashMap();
			map.put("list_t_job", list_t_job);
			
			params.put("data", map);
			params.put("message", "报名兼职查询成功");
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
