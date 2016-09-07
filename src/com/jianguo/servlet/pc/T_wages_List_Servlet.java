package com.jianguo.servlet.pc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.sql.T_wages_Sql;

public class T_wages_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_wages_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_wages_List_Servlet?count=0
	//http://101.200.205.243:8080/T_wages_List_Servlet?count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_moneyout_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");

		List<T_wages_Bean> list_t_wages = T_wages_Sql.select_All_login_id(login_id);
		List<T_wages_Bean> list_t_wages2 = new ArrayList<T_wages_Bean>();
		
		for (int i = 0; i < list_t_wages.size(); i++) {
			T_wages_Bean t_wages = list_t_wages.get(i);
			
			T_job_Bean t_job = T_job_Sql.select_id(t_wages.getJob_id()+"");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String sd = sdf.format(new Date(Long.parseLong(t_job.getStart_date()+"100")));
			String sd2 = sdf.format(new Date(Long.parseLong(t_job.getStop_date()+"100")));
			t_wages.setJob_start(sd);
			t_wages.setJob_stop(sd2);
			
			t_wages.setJob_image(t_job.getName_image());
			t_wages.setJob_name(t_job.getName());
			T_city_Bean t_city = T_school_Sql.select_t_city_id(t_job.getCity_id()+"");
			t_wages.setJob_city(t_city.getCity());

			list_t_wages2.add(t_wages);
		}
		
		request.setAttribute("list_t_wages", list_t_wages2);
		request.getRequestDispatcher("wages_list.jsp").forward(request, response);
	}
}
