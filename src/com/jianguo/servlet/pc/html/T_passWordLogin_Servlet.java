package com.jianguo.servlet.pc.html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.MD5Util;

public class T_passWordLogin_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_passWordLogin_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * web∂À’À∫≈√‹¬Îµ«¬º
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_passWordLogin_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String account =request.getParameter("account");
		String password =request.getParameter("password");
		String mD5password=MD5Util.MD5(password);
		PrintWriter out = response.getWriter();
		boolean flagTel=T_user_login_Sql.check_tel(account);
		
		if(flagTel){
			boolean flag=T_user_login_Sql.checkForAccount(account,mD5password);
			
			if(flag){
			
				request.getRequestDispatcher("login\\success.jsp").forward(request, response);
			}else{
				out.flush();
				out.println("<script>");
				out.println("alert('√‹¬Î≤ª’˝»∑');");
				out.println("history.back();");
				out.println("</script>");	
			}
			
			
		}else{			
			out.flush();out.println("<script>"); 
			out.println("alert('∏√ ÷ª˙Œ¥◊¢≤·');");
			out.println("history.back();");
			out.println("</script>");	
		}
	
		
		
	}

}
