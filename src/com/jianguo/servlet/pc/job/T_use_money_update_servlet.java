package com.jianguo.servlet.pc.job;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_use_Money_Bean;
import com.jianguo.sql.T_useMoney_Sql;

public class T_use_money_update_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_use_money_update_servlet() {
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

		System.out.println("---T_use_money_update_servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String id =request.getParameter("id");
		T_use_Money_Bean useMoney =T_useMoney_Sql.queryById(id);
		

		request.setAttribute("id", useMoney.getId());
		request.setAttribute("admin", useMoney.getAdmin());
		request.setAttribute("work_date", useMoney.getWorkDate());
		request.setAttribute("merchant", useMoney.getMerchant());
		request.setAttribute("name", useMoney.getName());
		request.setAttribute("tel", useMoney.getTel());
		request.setAttribute("hould_money", useMoney.getHouldMoney());
		request.setAttribute("moneyout", useMoney.getMoneyOut());
		request.setAttribute("moneyout_date", useMoney.getMoneyOutDate());
		request.setAttribute("remarks", useMoney.getRemarks());
		
		
		request.getRequestDispatcher("use\\money_use_update.jsp").forward(request, response);
		
	}


}
