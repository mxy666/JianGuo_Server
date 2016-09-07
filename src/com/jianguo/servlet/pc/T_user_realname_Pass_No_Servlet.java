package com.jianguo.servlet.pc;

import java.io.IOException;
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

public class T_user_realname_Pass_No_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_realname_Pass_No_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_realname_Pass_No_Servlet?only=3016E9490D2C47F18954E1277DCA873E&tel=18631017353&password=3
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_realname_Pass_No_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String remarks =request.getParameter("remarks");
		
		T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(login_id);
		T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);

		request.setAttribute("realname", t_user_realname.getRealname());
		request.setAttribute("id_number", t_user_realname.getId_number());
		request.setAttribute("front_image", t_user_realname.getFront_image());
		request.setAttribute("behind_image", t_user_realname.getBehind_image());
		request.setAttribute("sex", t_user_realname.getSex());
		request.setAttribute("tel", t_user_login.getTel());
		request.setAttribute("login_id", login_id);
		request.getRequestDispatcher("user_realname_look.jsp").forward(request, response);
	}
}
