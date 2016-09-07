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
import com.jianguo.bean.T_userChat_Bean;
import com.jianguo.sql.T_UserChat_Sql;
import com.jianguo.util.Frequently;

public class T_UserChat_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_UserChat_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}
		
			/**2016/8/11   mxy
			 * 用户聊天查询
			 * http://localhost:8080/JianGuo_Server/T_UserChat_Servlet?login_id=3&only=9F03ED15EECA1BC41BDDA634705699AA
			 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_UserChat_Servlet ---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String login_id =request.getParameter("login_id");
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------
			
	
			T_userChat_Bean userInfo = T_UserChat_Sql.queryByLoginID(login_id);
			
			
		
			//Map map = new HashMap();
			//map.put("userInfo", userInfo);

			params.put("data", userInfo);
			params.put("message", "用户查询成功");
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
