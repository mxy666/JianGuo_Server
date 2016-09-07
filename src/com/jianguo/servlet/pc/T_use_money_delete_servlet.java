package com.jianguo.servlet.pc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_use_Money_Bean;
import com.jianguo.sql.T_useMoney_Sql;
import com.jianguo.util.PageModel;

public class T_use_money_delete_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_use_money_delete_servlet() {
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

		System.out.println("---T_use_money_delete_servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		int flag=T_useMoney_Sql.deleteUseMoney(id);
		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel<T_use_Money_Bean> page = new PageModel<T_use_Money_Bean>();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = T_useMoney_Sql.selectAllT(page);
		request.setAttribute("page", page);
		List <T_use_Money_Bean>useMoney =T_useMoney_Sql.queryAllUseMoney();
		request.setAttribute("useMoney", useMoney);
		request.getRequestDispatcher("use\\money_use_list.jsp").forward(request, response);
	
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
