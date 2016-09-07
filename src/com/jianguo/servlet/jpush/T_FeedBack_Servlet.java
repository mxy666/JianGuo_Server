package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;

public class T_FeedBack_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_FeedBack_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 意见反馈页面跳转
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_FeedBack_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String login_id =request.getParameter("loginId");
		//String text =request.getParameter("text");
		String text =URLDecoder.decode(request.getParameter("text"),"utf-8");


		request.setAttribute("text", text);
		request.setAttribute("login_id", login_id);
		request.getRequestDispatcher("interaction\\feedBack.jsp").forward(request, response);

	}

}
