package com.jianguo.servlet.pay;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pingplusplus.model.Transfer;

public class T_PayAction_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_PayAction_Servlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 微信企业支付servlet
	 *

	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_PayAction_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Date date= new Date();
		Long currentMillis= date.getTime();		
		Transfer transfer=PayForWx.transfer();	
		//String path= getServletContext().getRealPath("/")+"/jsonWx/";
	/*	String fileName="json"+currentMillis;
		Boolean flag=PayForWx.writeJson(path,transfer,fileName);*/
		//request.setAttribute("list_t_user_moneyout", list_t_user_moneyout2);
		//request.getRequestDispatcher("pay\\payForWX.jsp").forward(request, response);

	
	}



}
