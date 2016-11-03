package com.jianguo.servlet.pc.job;

import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_business_realname_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_business_realname_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://v3.jianguojob.com:8080/T_user_realname_List_Servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_business_realname_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		List<T_merchant_Bean> list_business_login = T_merchant_Sql.selectAuthInfo();
		request.setAttribute("list_business_login", list_business_login);
		request.getRequestDispatcher("bus_realname_list.jsp").forward(request, response);
	}
}
