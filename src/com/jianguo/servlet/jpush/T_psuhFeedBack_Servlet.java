package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.servlet.jpush.action.PushAction;

public class T_psuhFeedBack_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_psuhFeedBack_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_psuhFeedBack_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String login_id =request.getParameter("login_id");
		String message =request.getParameter("message");
		PrintWriter out = response.getWriter();
		out.flush();out.println("<script>");
		out.println("alert('·´À¡³É¹¦');");
		out.println("history.back();");
		out.println("</script>");	
		PushAction.pushForFeedBack(message,login_id);
		
	}

}
