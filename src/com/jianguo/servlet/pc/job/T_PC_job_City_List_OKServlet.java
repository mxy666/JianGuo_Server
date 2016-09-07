package com.jianguo.servlet.pc.job;

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
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_user_login_Sql;

public class T_PC_job_City_List_OKServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_PC_job_City_List_OKServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_PC_job_City_List_OKServlet?city_id=1
	//http://101.200.205.243:8080/T_PC_job_City_List_OKServlet?only=13863CB6975269B4276A2682B6E32786&hot=0&count=10
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_PC_job_City_List_OKServlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String city_id =request.getParameter("city_id");

			List<T_job_Bean> list_t_job = T_job_Sql.select_all_ok(city_id);

			List<T_job_info_Bean> list_t_job_info = new ArrayList<T_job_info_Bean>();
			for (int i = 0; i < list_t_job.size(); i++) {
				T_job_Bean t_job = list_t_job.get(i);
				T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_job.getId()+"");
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String sd = sdf.format(new Date(Long.parseLong(t_job_info.getStart_time()+"100")));
				String sd2 = sdf.format(new Date(Long.parseLong(t_job_info.getStop_time()+"100")));
				t_job_info.setStart_time(sd);
				t_job_info.setStop_time(sd2);
				
				t_job_info.setJob_name(t_job.getName());
				t_job_info.setJob_image(t_job.getName_image());
				t_job_info.setJob_money(t_job.getMoney()+"");
				
				T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
				T_user_login_Bean t_user_login = T_user_login_Sql.select_id(t_merchant.getLogin_id()+"");
				t_job_info.setJob_merchant_name(t_merchant.getName());
				t_job_info.setJob_merchant_tel(t_user_login.getTel());
				
				list_t_job_info.add(t_job_info);
			}

			request.setAttribute("list_t_job_info", list_t_job_info);
			request.getRequestDispatcher("job_list_ok.jsp").forward(request, response);
	}
}
