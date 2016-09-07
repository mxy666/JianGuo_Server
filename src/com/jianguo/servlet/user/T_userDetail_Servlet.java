package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;

public class T_userDetail_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_userDetail_Servlet() {
		super();
	}

	/**
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 用户详细信息查看
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_userDetail_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String login_id =request.getParameter("loginId");
		String money =request.getParameter("money");
		String school =URLDecoder.decode(request.getParameter("school"),"utf-8");
		String cityId =URLDecoder.decode(request.getParameter("cityId"),"utf-8");
		
		T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(login_id);
		T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);

		request.setAttribute("realname", t_user_realname.getRealname());
		request.setAttribute("id_number", t_user_realname.getId_number());
		request.setAttribute("front_image", t_user_realname.getFront_image());
		request.setAttribute("behind_image", t_user_realname.getBehind_image());
		request.setAttribute("sex", t_user_realname.getSex());
		request.setAttribute("tel", t_user_login.getTel());
		request.setAttribute("login_id", login_id);
		request.setAttribute("money", money);
		request.setAttribute("school", school);
		request.setAttribute("cityId", cityId);
		request.getRequestDispatcher("userManage\\userDetail.jsp").forward(request, response);

	}

}
