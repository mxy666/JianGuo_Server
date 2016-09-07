package com.jianguo.servlet.pc.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;
import com.jianguo.sql.T_user_resume_Sql;

public class T_job_wai_OKList_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_wai_OKList_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_wai_OKList_Servlet?only=E374361DAB8120FCE148EDAC7E174C8F&hot=0
	//http://101.200.205.243:8080/T_job_wai_OKList_Servlet?only=13863CB6975269B4276A2682B6E32786&hot=0&count=10
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_wai_OKList_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String job_id =request.getParameter("job_id");

		List<T_enroll_Bean> list_t_enroll = T_enroll_Sql.select_job_id_s(job_id);

		List<T_user_realname_Bean> list_t_user_realname = new ArrayList<T_user_realname_Bean>();
		for (int i = 0; i < list_t_enroll.size(); i++) {
			T_enroll_Bean t_enroll = list_t_enroll.get(i);
			T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_enroll.getLogin_id()+"");
			
			T_user_login_Bean t_user_login = T_user_login_Sql.select_id(t_enroll.getLogin_id()+"");
			t_user_realname.setTel(t_user_login.getTel());
			
			T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(t_enroll.getLogin_id()+"");
			t_user_realname.setSchool(t_user_resume.getSchool());
			
			list_t_user_realname.add(t_user_realname);
		}
		
			request.setAttribute("job_id", job_id);
			request.setAttribute("list_t_user_realname", list_t_user_realname);
			request.getRequestDispatcher("job_wai_ok_list.jsp").forward(request, response);
	}
}
