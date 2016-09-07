package com.jianguo.servlet.pay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pingplusplus.model.Transfer;

public class T_payForWeXin_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_payForWeXin_Servlet() {
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
		System.out.println("---T_payForWeXin_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		 
		//request.setAttribute("list_t_user_moneyout", list_t_user_moneyout2);
		request.getRequestDispatcher("pay\\payForWX.jsp").forward(request, response);

	}


}
