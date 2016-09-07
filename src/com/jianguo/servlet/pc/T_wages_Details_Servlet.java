package com.jianguo.servlet.pc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_user_moneyout_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_moneyout_Sql;
import com.jianguo.sql.T_user_realname_Sql;

public class T_wages_Details_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_wages_Details_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_wages_Details_Servlet?count=0
	//http://101.200.205.243:8080/T_wages_Details_Servlet?count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_wages_Details_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String id =request.getParameter("id");
		String cityId =request.getParameter("cityId");
		T_user_moneyout_Bean t_user_moneyout = T_user_moneyout_Sql.select_id(id);
		
		T_user_login_Bean t_user_login = T_user_login_Sql.select_id(t_user_moneyout.getLogin_id()+"");
	
		T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
		t_user_moneyout.setLogin_name(t_user_info.getName());
		t_user_moneyout.setLogin_nameimage(t_user_info.getName_image());
		
		T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
		
		T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
		
		if(t_user_moneyout.getType() == 0){
			t_user_moneyout.setLogin_type("Ö§¸¶±¦");
			t_user_moneyout.setLogin_hao(t_user_money.getZhifubao());
		}else if(t_user_moneyout.getType() == 1){
			t_user_moneyout.setLogin_type("ÒøÐÐ¿¨");
			t_user_moneyout.setLogin_hao(t_user_money.getKahao());
		}
		double dd = t_user_money.getMoney()+t_user_moneyout.getMoney();
		t_user_moneyout.setLogin_money(dd+"");

		request.setAttribute("tel", t_user_login.getTel());
		request.setAttribute("login_id", t_user_moneyout.getLogin_id());
		request.setAttribute("front_image", t_user_realname.getFront_image());
		request.setAttribute("behind_image", t_user_realname.getBehind_image());
		request.setAttribute("realname", t_user_realname.getRealname());
		request.setAttribute("id_number", t_user_realname.getId_number());

		
		request.setAttribute("name", t_user_moneyout.getLogin_name());
		request.setAttribute("type", t_user_moneyout.getLogin_type());
		request.setAttribute("hao", t_user_moneyout.getLogin_hao());
		request.setAttribute("login_money", t_user_moneyout.getLogin_money());
		request.setAttribute("money", t_user_moneyout.getMoney());
		request.setAttribute("id", t_user_moneyout.getId());
		request.setAttribute("cityId", cityId);
		request.getRequestDispatcher("wages_details.jsp").forward(request, response);
	}
}
