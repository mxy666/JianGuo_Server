package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_Money_Bean;
import com.jianguo.sql.T_UserManage_Sql;

public class T_modifyMoney_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_modifyMoney_Servlet() {
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
	 * 
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_modifyMoney_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String loginId = request.getParameter("loginId");
		String money = request.getParameter("money");
		String remark = request.getParameter("remark");
		Boolean flag=T_UserManage_Sql.updateMoney(loginId,money,remark);
		if(flag){
			out.flush();
			out.println("<script>");
			out.println("alert('修改成功');");
			out.println("history.back();");
			out.println("</script>");
		}else{
			out.flush();
			out.println("<script>");
			out.println("alert('修改失败，请重新修改');");
			out.println("history.back();");
			out.println("</script>");
		}
	}
}
