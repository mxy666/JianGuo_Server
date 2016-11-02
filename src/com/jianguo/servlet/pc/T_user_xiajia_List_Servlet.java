package com.jianguo.servlet.pc;

import com.google.gson.Gson;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_user_xiajia_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_xiajia_List_Servlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String bus_name = request.getParameter("bus_name");
		String job_name = request.getParameter("job_name");


//		Map params =  new HashMap();
//
		List<Map<String,String>> xiaJia = T_job_Sql.searchXiaJia(bus_name,job_name);
//
//		List<T_user_realname_Bean> list_t_user_realname = new ArrayList<T_user_realname_Bean>();
//		for (int i = 0; i < list_t_login.size(); i++) {
//			T_user_login_Bean t_user_login = list_t_login.get(i);
//			T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_user_login.getId()+"");
//
//			t_user_realname.setTel(t_user_login.getTel());
//			list_t_user_realname.add(t_user_realname);
//		}
		PrintWriter writer = response.getWriter();
		Gson gson=new Gson();
		String json=gson.toJson(xiaJia);
		writer.write(json);

//		request.setAttribute("xiaJia", xiaJia);
//		request.getRequestDispatcher("user_realname_list.jsp").forward(request, response);
	}
}
