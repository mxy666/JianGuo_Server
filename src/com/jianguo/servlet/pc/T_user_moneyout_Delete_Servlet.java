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

import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_user_moneyout_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_moneyout_Sql;
import com.jianguo.sql.T_wages_Sql;

public class T_user_moneyout_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_moneyout_Delete_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_moneyout_Delete_Servlet?count=0
	//http://101.200.205.243:8080/T_user_moneyout_Delete_Servlet?count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_moneyout_Delete_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String id =request.getParameter("id");
		T_user_moneyout_Sql.delete_id(id);

		List<T_user_moneyout_Bean> list_t_user_moneyout = T_user_moneyout_Sql.select_All();

		List<T_user_moneyout_Bean> list_t_user_moneyout2 = new ArrayList<T_user_moneyout_Bean>();
		for (int i = 0; i < list_t_user_moneyout.size(); i++) {
			T_user_moneyout_Bean t_user_moneyout = list_t_user_moneyout.get(i);
			
			T_enroll_Bean t_enroll = T_wages_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
			T_job_Bean t_job = T_job_Sql.select_id(t_enroll.getJob_id()+"");
			T_city_Bean t_city = T_school_Sql.select_t_city_id(t_job.getCity_id()+"");
			t_user_moneyout.setLogin_id_city(t_city.getCity());
			
			T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
			t_user_moneyout.setLogin_name(t_user_info.getName());
			t_user_moneyout.setLogin_nameimage(t_user_info.getName_image());
			
			T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
			
			if(t_user_moneyout.getType() == 0){
				t_user_moneyout.setLogin_type("Ö§¸¶±¦");
				t_user_moneyout.setLogin_hao(t_user_money.getZhifubao());
			}else if(t_user_moneyout.getType() == 1){
				t_user_moneyout.setLogin_type("ÒøÐÐ¿¨");
				t_user_moneyout.setLogin_hao(t_user_money.getKahao());
			}
			double dd = t_user_money.getMoney()+t_user_moneyout.getMoney();
			t_user_moneyout.setLogin_money(dd+"");
			list_t_user_moneyout2.add(t_user_moneyout);
		}
		
		request.setAttribute("list_t_user_moneyout", list_t_user_moneyout2);
		request.getRequestDispatcher("moneyout_list.jsp").forward(request, response);
		}
}
