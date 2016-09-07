package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class T_userManager_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_userManager_servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	 
	   System.out.println("---T_userManager_servlet---");
	   //如果指定logger名字，则是把日志，输出到pay-log 指定的日志文件中去
	   Logger logger = Logger.getLogger("log");
	   logger.info("日志信息开始!");
	   logger.info("日志信息结束!");
	   
	  try {
		  //Integer.parseInt("a");
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String cityId=request.getParameter("cityId");
			String school="dd";
				//request.getParameter("school");
			logger.info(school);
	  } catch (Exception  e) {
		   logger.error("异常",e);
		   e.printStackTrace();
	  }
	  
	
		//request.setAttribute("pushObj", pushObj);
		//request.setAttribute("list_t_job", list_t_job);
		request.getRequestDispatcher("userManage\\user_manage.jsp").forward(request, response);
	}

}
