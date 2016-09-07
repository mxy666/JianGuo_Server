package com.jianguo.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;

public class T_user_login_FastLogin_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_login_FastLogin_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_login_FastLogin_Servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_login_FastLogin_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String tel =request.getParameter("tel");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
		//------------------访问限制--------结束----------------------

		boolean b = T_user_login_Sql.check_tel(tel);
		if(b == true){
			T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
			T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");

			Map map = new HashMap();
			map.put("t_user_login", t_user_login);
			map.put("t_user_info", t_user_info);
			
			params.put("data", map);			
			params.put("message", "登录成功");			
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
		}else{
			params.put("message", "没有该用户，快去注册吧");
			params.put("code", "500");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
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
