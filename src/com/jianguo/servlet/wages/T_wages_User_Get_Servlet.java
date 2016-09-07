package com.jianguo.servlet.wages;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
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
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_wages_Sql;
import com.jianguo.util.Frequently;

public class T_wages_User_Get_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_wages_User_Get_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_wages_User_Get_Servlet?only=E00D595EA01C3F42EB65EF0AD8563684&login_id=1&count=0
	//http://101.200.205.243:8080/T_wages_User_Get_Servlet?only=00D72856CBE27EC253DD67593D2296FE&login_id=52&count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_wages_User_Get_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String login_id =request.getParameter("login_id");
		String count =request.getParameter("count");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			List<T_wages_Bean> list_t_wages = T_wages_Sql.select_All_name(login_id,count);
			
			List<T_wages_Bean> list = new ArrayList<T_wages_Bean>();
			for (int i = 0; i < list_t_wages.size(); i++) {
				T_wages_Bean t_wages = list_t_wages.get(i);
				
				T_job_Bean t_job = T_job_Sql.select_id(t_wages.getJob_id()+"");
				t_wages.setJob_image(t_job.getName_image());
				t_wages.setJob_name(t_job.getName());
				t_wages.setJob_start(t_job.getStart_date());
				t_wages.setJob_stop(t_job.getStop_date());
			
				list.add(t_wages);
			}
			
			Map map = new HashMap();
			map.put("list_t_wages", list);

			params.put("data", map);
			params.put("message", "返回收入明细成功");
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
