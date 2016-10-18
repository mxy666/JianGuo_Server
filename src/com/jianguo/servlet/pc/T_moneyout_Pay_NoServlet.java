package com.jianguo.servlet.pc;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_user_moneyout_Bean;
import com.jianguo.sql.Job_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_moneyout_Sql;
import com.jianguo.sql.T_wages_Sql;
import com.jianguo.util.Jdpush;
import com.jianguo.util.Jdpush_money;
import com.jianguo.util.Jdpushcc;
import com.jianguo.util.Jdpushcc_money;
import com.jianguo.util.Jdpusher;
import com.jianguo.util.Jdpusher_money;
import com.jianguo.util.Text_Sms;

public class T_moneyout_Pay_NoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_moneyout_Pay_NoServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_moneyout_Pay_NoServlet?count=0
	//http://101.200.205.243:8080/T_moneyout_Pay_NoServlet?count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_moneyout_Pay_NoServlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String id =request.getParameter("id");
		String login_id =request.getParameter("login_id");
		String money =request.getParameter("money");
		String remarks =request.getParameter("remarks");

		T_user_moneyout_Sql.update_status_no("2", remarks, id);
		
		T_user_money_Sql.update_money(money, login_id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ly_time = sdf.format(new java.util.Date());
		/*Jdpush.sendPush(remarks,"jianguo"+login_id);
		Jdpusher.sendPush(remarks,"jianguo"+login_id);
		Jdpushcc.sendPush(remarks,"jianguo"+login_id);*/
		
		Jdpush_money.sendPush(remarks,"jianguo"+login_id);//iOS
		Jdpusher_money.sendPush(remarks,"jianguo"+login_id);//°²×¿
		Jdpushcc_money.sendPush(remarks,"jianguo"+login_id);//iOS
		
		T_push_Sql.insert(login_id, "¾Ü¾øÖ§¸¶", "¾Ü¾øÖ§¸¶", remarks, "1","0","0","0", ly_time);
		

		
		List<T_user_moneyout_Bean> list_t_user_moneyout = T_user_moneyout_Sql.select_All();

		List<T_user_moneyout_Bean> list_t_user_moneyout2 = new ArrayList<T_user_moneyout_Bean>();
		for (int i = 0; i < list_t_user_moneyout.size(); i++) {
			T_user_moneyout_Bean t_user_moneyout = list_t_user_moneyout.get(i);
			
			T_enroll_Bean t_enroll = T_wages_Sql.select_login_id(t_user_moneyout.getLogin_id()+"");
			T_job_Bean t_job = T_job_Sql.select_id(t_enroll.getJob_id()+"");
			T_city_Bean t_city = Job_Sql.selectTcity_id(t_job.getCity_id()+"");
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
			list_t_user_moneyout2.add(t_user_moneyout);
		}

		request.setAttribute("list_t_user_moneyout", list_t_user_moneyout2);
		request.getRequestDispatcher("moneyout_list.jsp").forward(request, response);
	}
}
