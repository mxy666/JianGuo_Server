package com.jianguo.servlet.sms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Text_Sms;

public class T_webSms_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_webSms_Servlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * web端登录
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_webSms_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		final String phone =request.getParameter("phone");
		
		String regFlag =request.getParameter("regFlag"); 
		String fastTel=request.getParameter("fastTel");
		new Thread(new Runnable() {
			public void run() {

				String code = Text_Sms.textdemos(phone);
			}
		}).start();


		if(fastTel!=null&&fastTel.equals("fast")){

			request.setAttribute("phone", phone);
			request.getRequestDispatcher("webLogin.jsp").forward(request, response);
		}else if(fastTel!=null&&fastTel.equals("fastBaoming")){
			request.setAttribute("phone", phone);
			request.getRequestDispatcher("forWeb\\fastbaoming.jsp").forward(request, response);

		}else{
			boolean b = T_user_login_Sql.check_tel(phone);	
			
			if(b){
				PrintWriter out = response.getWriter();
				out.flush();
				out.println("<script>");
				out.println("alert('手机号已存在');");
				out.println("history.back();");
				out.println("</script>");	
			}else{
				//String code = Text_Sms.textdemos(phone);
								
				if(regFlag.equals("reg")){
					request.setAttribute("phone", phone);
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}else{
					request.setAttribute("phone", phone);
					request.getRequestDispatcher("webLogin.jsp").forward(request, response);
				}
			}
		}
		
		/*String code = Text_Sms.textdemos(phone);
		
		
		if(regFlag.equals("reg")){
			request.setAttribute("phone", phone);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}else{
			request.setAttribute("phone", phone);
			request.getRequestDispatcher("webLogin.jsp").forward(request, response);
		}*/
			
			


	}

}
