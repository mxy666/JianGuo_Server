package com.jianguo.servlet.pc;

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
import com.jianguo.util.PageModel;

public class T_money_use_action_Servlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 更新用户结算
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_money_use_action_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Map params =  new HashMap();
		String ids =request.getParameter("id");		
		int id = Integer.parseInt(ids);
		String admin=request.getParameter("admin");
		String work_date=request.getParameter("work_date");
		String merchant=request.getParameter("merchant");
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String hould_money=request.getParameter("hould_money");
		String moneyout=request.getParameter("moneyout");
		String moneyout_date=request.getParameter("moneyout_date");
		String remarks=request.getParameter("remarks");
		int useMoney =T_useMoney_Sql.udateUseMoney(id,admin,work_date,
				merchant,name,tel,hould_money,moneyout,moneyout_date,remarks);
		
		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel<T_use_Money_Bean> page = new PageModel<T_use_Money_Bean>();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = T_useMoney_Sql.selectAllT(page);
		request.setAttribute("page", page);
		List <T_use_Money_Bean>useMoney1 =T_useMoney_Sql.queryAllUseMoney();
		request.setAttribute("useMoney", useMoney1);
		request.getRequestDispatcher("use\\money_use_list.jsp").forward(request, response);
		
		
	}
	

}
