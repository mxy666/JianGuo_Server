package com.jianguo.servlet.finance;

import com.jianguo.bean.T_wages_Bean;
import com.jianguo.sql.FinanceMerSql;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinanceInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FinanceInfoServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * ²ÆÎñÊ×Ò³
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---FinanceInfoServlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String merchantID=request.getParameter("merchantID");

		T_wages_Bean monthTotalmoney=FinanceMerSql.queryMonTotalMoney(merchantID);
		T_wages_Bean dayTotalmoney=FinanceMerSql.queryDayTotalMoney(merchantID);
		T_wages_Bean totalmoney=FinanceMerSql.queryTotalMoney(merchantID);
	
	}

}
