package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jianguo.bean.T_Money_Bean;
import com.jianguo.sql.T_UserManage_Sql;

public class T_updateMoney_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_updateMoney_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 用户钱包窗口打开
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_updateMoney_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String loginId = request.getParameter("loginId");
		
		T_Money_Bean money=new T_Money_Bean();
		money=T_UserManage_Sql.queryMoney(loginId);//余额
		
		T_Money_Bean wage=new T_Money_Bean();
		wage=T_UserManage_Sql.queryWage(loginId);//余额
		
		T_Money_Bean cash=new T_Money_Bean();
		cash=T_UserManage_Sql.queryOutCash(loginId);//余额
		
		JSONArray array1 = null; 
		
		//array1 = JSONArray.fromObject(money);
		//response.setContentType("text/html;charset=utf-8");
		/*PrintWriter out = response.getWriter();
		out.println(array1);
		out.flush();
		out.close();*/
		request.setAttribute("login_id", loginId);
		request.setAttribute("money", money.getMoney());
		request.setAttribute("cash", cash.getCash());
		request.setAttribute("wage", wage.getWage());
		request.getRequestDispatcher("userManage\\userMoney.jsp").forward(request, response);
		
	}

}
