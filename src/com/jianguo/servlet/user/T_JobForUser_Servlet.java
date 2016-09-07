package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jianguo.bean.T_Money_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_UserManage_Sql;

public class T_JobForUser_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_JobForUser_Servlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * ºÊ÷∞≤È—Ø
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_JobForUser_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String loginId = request.getParameter("loginId");
		
		List<T_job_Bean> job=new ArrayList<T_job_Bean>();
		
		//List<T_Money_Bean> money=new ArrayList<T_Money_Bean>();
		job=T_UserManage_Sql.queryJob(loginId);
		//money=T_UserManage_Sql.queryMoney(loginId);
	
		//user=T_UserManage_Sql.queryAll(name,tel);
		JSONArray array = null; 
		
		array = JSONArray.fromObject(job);
		//array1=JSONArray.fromObject(money);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(array);
		out.flush();
		out.close();
	}

}
