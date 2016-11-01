package com.jianguo.servlet.pc.job;

import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;
import com.jianguo.util.Jdpush_realname;
import com.jianguo.util.Jdpushcc_realname;
import com.jianguo.util.Jdpusher_realname;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_bus_realname_Pass_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_bus_realname_Pass_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_realname_List_Servlet?only=3016E9490D2C47F18954E1277DCA873E&tel=18631017353&password=3
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
//		System.out.println("---T_user_realname_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String m_id =request.getParameter("m_id");
		String pass =request.getParameter("pass");
		String beizhu =request.getParameter("beizhu");
		String qita =request.getParameter("qita");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ly_time = sdf.format(new java.util.Date());
		
			if(pass.equals("0")){
				if(qita.equals("无")){
				Jdpush_realname.sendPush("您提交的实名信息被拒，"+beizhu,"jianguo"+login_id);
				Jdpusher_realname.sendPush("您提交的实名信息被拒，"+beizhu,"jianguo"+login_id);
				Jdpushcc_realname.sendPush("您提交的实名信息被拒，"+beizhu,"jianguo"+login_id);
					T_merchant_Sql.update_status(2, m_id);
				T_push_Sql.insert(login_id, "实名认证失败", "实名", "您提交的实名信息被拒，"+beizhu,"2","0","0","0", ly_time);
				}else{
					Jdpush_realname.sendPush("您提交的实名信息被拒，"+qita,"jianguo"+login_id);
					Jdpusher_realname.sendPush("您提交的实名信息被拒，"+qita,"jianguo"+login_id);
					Jdpushcc_realname.sendPush("您提交的实名信息被拒，"+qita,"jianguo"+login_id);
					T_merchant_Sql.update_status(2, m_id);
					T_push_Sql.insert(login_id, "实名认证失败", "实名", "您提交的实名信息被拒，"+qita,"2","0","0","0", ly_time);
				}
			}else if(pass.equals("1")){
				Jdpush_realname.sendPush("您提交的实名信息已通过","jianguo"+login_id);
				Jdpusher_realname.sendPush("您提交的实名信息已通过","jianguo"+login_id);
				Jdpushcc_realname.sendPush("您提交的实名信息已通过","jianguo"+login_id);
				T_merchant_Sql.update_status(3, m_id);
				T_push_Sql.insert(login_id, "实名认证通过", "实名", "您提交的实名信息已通过","2","0","0","0", ly_time);
		}
//
//		List<T_user_login_Bean> list_t_login = T_user_login_Sql.select_list("3");
//		List<T_user_realname_Bean> list_t_user_realname = new ArrayList<T_user_realname_Bean>();
//		for (int i = 0; i < list_t_login.size(); i++) {
//			T_user_login_Bean t_user_login = list_t_login.get(i);
//			T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(t_user_login.getId()+"");
//			list_t_user_realname.add(t_user_realname);
//		}
//		request.setAttribute("list_t_user_realname", list_t_user_realname);
//		request.getRequestDispatcher("user_realname_list.jsp").forward(request, response);


		List<T_merchant_Bean> list_business_login = T_merchant_Sql.selectAuthInfo();
		request.setAttribute("list_business_login", list_business_login);
		request.getRequestDispatcher("bus_realname_list.jsp").forward(request, response);


	}
}
