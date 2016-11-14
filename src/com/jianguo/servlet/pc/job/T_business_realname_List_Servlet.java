package com.jianguo.servlet.pc.job;

import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_business_realname_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger("log");

	public T_business_realname_List_Servlet() {
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
		System.out.println("---T_business_realname_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String city_id =request.getParameter("city_id");
		String str_city = "";
		if(city_id.equals("1")){
			str_city = "三亚";
		}else if(city_id.equals("2")){
			str_city = "海口";
		}else if(city_id.equals("3")){
			str_city = "北京";
		}else if(city_id.equals("4")){
			str_city = "杭州";
		}else if(city_id.equals("5")){
			str_city = "西安";
		}else if(city_id.equals("0")){
			str_city = "boss";
		}else if(city_id.equals("6")){
			str_city = "武汉";
		}

		logger.info("商家审核查询"+str_city);
		List<T_merchant_Bean> list_business_login = T_merchant_Sql.selectAuthInfo(str_city);

		logger.info("商家审核查询结束"+str_city);

		request.setAttribute("list_business_login", list_business_login);
		request.setAttribute("cityName", str_city);
		request.getRequestDispatcher("bus_realname_list.jsp").forward(request, response);
	}
}
