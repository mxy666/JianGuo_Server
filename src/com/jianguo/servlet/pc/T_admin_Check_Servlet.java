package com.jianguo.servlet.pc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_admin_Bean;
import com.jianguo.sql.T_admin_Sql;
import com.jianguo.util.MD5Util;

public class T_admin_Check_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_admin_Check_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_admin_Check_Servlet?count=0
	//http://101.200.205.243:8080/T_admin_Check_Servlet?count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_admin_Check_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String city =request.getParameter("city");
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		
		String str_psd = MD5Util.MD5(password);
		boolean b = T_admin_Sql.check(city,username, str_psd);
		if(b == true){
			T_admin_Bean t_adin = T_admin_Sql.select_status(username);
			int cityId=(int) T_admin_Sql.queryCityId(city);
			if(t_adin.getStatus() == 0){
				if(city.equals("boss")){
					response.sendRedirect("index_boss.html");
				}else if(city.equals("sanya")){
					response.sendRedirect("index_sanya.html");
				}else if(city.equals("haikou")){
					response.sendRedirect("index_haikou.html");
				}else if(city.equals("beijing")){
					response.sendRedirect("index_beijing.html");
				}else if(city.equals("hangzhou")){
					response.sendRedirect("index_hangzhou.html");
				}else if(city.equals("xian")){
					response.sendRedirect("index_xian.html");
				}else if(city.equals("chuna")){
					response.sendRedirect("index_chuna.html");
				}
					
				
			}else if(t_adin.getStatus() == 0){
				try {
					PrintWriter out = response.getWriter();
					out.flush();out.println("<script>");
					out.println("alert('登录中，暂不能登录');");
					out.println("history.back();");
					out.println("</script>");
					response.sendRedirect("login.html");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}else{
			try {
				PrintWriter out = response.getWriter();
				out.flush();out.println("<script>");
				out.println("alert('用户名密码错误，请重试');");
				out.println("history.back();");
				out.println("</script>");
				response.sendRedirect("login.html");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}
}
