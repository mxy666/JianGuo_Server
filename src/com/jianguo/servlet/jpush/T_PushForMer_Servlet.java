package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.servlet.jpush.action.PushAction;

public class T_PushForMer_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_PushForMer_Servlet() {
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
	 * 商家推送
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_PushForMer_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		
		String phone=request.getParameter("phone");
		String message=request.getParameter("message");	
		
	
		
		request.setAttribute("phone", phone);
		request.setAttribute("message", message);
		PrintWriter out = response.getWriter();
		out.flush();out.println("<script>");
		out.println("alert('推送成功');");
		out.println("history.back();");
		out.println("</script>");
		PushAction.pushForMerchant(phone,message);

	}

}
