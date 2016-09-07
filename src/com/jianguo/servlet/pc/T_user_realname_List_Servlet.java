package com.jianguo.servlet.pc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;

public class T_user_realname_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_realname_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://v3.jianguojob.com:8080/T_user_realname_List_Servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_realname_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		List<T_user_login_Bean> list_t_login = T_user_login_Sql.select_list("3");
		
		List<T_user_realname_Bean> list_t_user_realname = new ArrayList<T_user_realname_Bean>();
		for (int i = 0; i < list_t_login.size(); i++) {
			T_user_login_Bean t_user_login = list_t_login.get(i);
			T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_user_login.getId()+"");
			
			t_user_realname.setTel(t_user_login.getTel());
			list_t_user_realname.add(t_user_realname);
		}
		
		request.setAttribute("list_t_user_realname", list_t_user_realname);
		request.getRequestDispatcher("user_realname_list.jsp").forward(request, response);
	}
}
