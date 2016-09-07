package com.jianguo.servlet.pc;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

public class T_moneyout_Search_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_moneyout_Search_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_moneyout_Search_Servlet?count=0
	//http://101.200.205.243:8080/T_moneyout_Search_Servlet?count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_moneyout_Search_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
//		String name =request.getParameter("name");
		String tel =request.getParameter("tel");
		String flag =request.getParameter("flag");
		
		if(tel == "" || tel == null){
			List<T_user_moneyout_Bean> list_t_user_moneyout = T_user_moneyout_Sql.select_All_ok("0");
			List<T_user_moneyout_Bean> list_t_user_moneyout3 = T_user_moneyout_Sql.select_All_ok_sum();
			
			double d = 0;
			for (int i = 0; i < list_t_user_moneyout3.size(); i++) {
				T_user_moneyout_Bean t_u_m = list_t_user_moneyout3.get(i);
				d += t_u_m.getMoney();
			}
			float scale2 = (float) d; 
			DecimalFormat fnum2 = new DecimalFormat("##0.00"); 
			String dd2=fnum2.format(scale2); 

			List<T_user_moneyout_Bean> list_t_user_moneyout2 = new ArrayList<T_user_moneyout_Bean>();
			for (int i = 0; i < list_t_user_moneyout.size(); i++) {
				T_user_moneyout_Bean t_user_moneyout = list_t_user_moneyout.get(i);
				
				T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
				t_user_moneyout.setLogin_name(t_user_info.getName());
				t_user_moneyout.setLogin_nameimage(t_user_info.getName_image());
				
				T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
				t_user_moneyout.setLogin_realname(t_user_realname.getRealname());
				t_user_moneyout.setLogin_id_number(t_user_realname.getId_number()+"");
				
				T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
				
				if(t_user_moneyout.getType() == 0){
					t_user_moneyout.setLogin_type("支付宝");
					t_user_moneyout.setLogin_hao(t_user_money.getZhifubao());
				}else if(t_user_moneyout.getType() == 1){
					t_user_moneyout.setLogin_type("银行卡");
					t_user_moneyout.setLogin_hao(t_user_money.getKahao());
				}
				double dd = t_user_money.getMoney()+t_user_moneyout.getMoney();
				t_user_moneyout.setLogin_money(dd+"");
				list_t_user_moneyout2.add(t_user_moneyout);
			}
			request.setAttribute("list_t_user_moneyout", list_t_user_moneyout);
			request.setAttribute("sum", list_t_user_moneyout3.size());
			request.setAttribute("money", dd2);
			if(flag!=null&&flag.equals("chuna")){
				request.getRequestDispatcher("moneyout_list_okchuna.jsp").forward(request, response);	
			}else{
				request.getRequestDispatcher("moneyout_list_okboss.jsp").forward(request, response);	
			}
			
		}else{
			List<T_user_moneyout_Bean> list_t_user_moneyout = null;
			T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
			list_t_user_moneyout = T_user_moneyout_Sql.select_login_id(t_user_login.getId()+"");
			
			List<T_user_moneyout_Bean> list_t_user_moneyout2 = new ArrayList<T_user_moneyout_Bean>();
			for (int i = 0; i < list_t_user_moneyout.size(); i++) {
				T_user_moneyout_Bean t_user_moneyout = list_t_user_moneyout.get(i);
				
				T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
				t_user_moneyout.setLogin_name(t_user_info.getName());
				t_user_moneyout.setLogin_nameimage(t_user_info.getName_image());
				
				T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
				t_user_moneyout.setLogin_realname(t_user_realname.getRealname());
				t_user_moneyout.setLogin_id_number(t_user_realname.getId_number()+"");
				
				T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
				
				if(t_user_moneyout.getType() == 0){
					t_user_moneyout.setLogin_type("支付宝");
					t_user_moneyout.setLogin_hao(t_user_money.getZhifubao());
				}else if(t_user_moneyout.getType() == 1){
					t_user_moneyout.setLogin_type("银行卡");
					t_user_moneyout.setLogin_hao(t_user_money.getKahao());
				}
				double dd = t_user_money.getMoney()+t_user_moneyout.getMoney();
				t_user_moneyout.setLogin_money(dd+"");
				list_t_user_moneyout2.add(t_user_moneyout);
			
			
			
			}
			List<T_user_moneyout_Bean> list_t_user_moneyout3 = T_user_moneyout_Sql.queryCount(t_user_login.getId()+"");
			
			double d = 0;
			for (int i = 0; i < list_t_user_moneyout3.size(); i++) {
				T_user_moneyout_Bean t_u_m = list_t_user_moneyout3.get(i);
				d += t_u_m.getMoney();
			}
			float scale2 = (float) d; 
			DecimalFormat fnum2 = new DecimalFormat("##0.00"); 
			String total=fnum2.format(scale2); 

			request.setAttribute("list_t_user_moneyout", list_t_user_moneyout);
			request.setAttribute("sum", list_t_user_moneyout.size()+"");
			request.setAttribute("money", total);
			if(flag!=null&&flag.equals("chuna")){
				request.getRequestDispatcher("moneyout_list_okchuna.jsp").forward(request, response);	
			}else{
				request.getRequestDispatcher("moneyout_list_okboss.jsp").forward(request, response);	
			}
			//request.getRequestDispatcher("moneyout_list_okboss.jsp").forward(request, response);
		}
		}
}
