package com.jianguo.servlet.pc;

import java.io.IOException;
import java.text.DecimalFormat;
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
import com.jianguo.bean.T_use_Money_Bean;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_user_moneyout_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.sql.T_useMoney_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_wages_Sql;
import com.jianguo.util.PageModel;

public class T_money_use_Servlet extends HttpServlet {

	public T_money_use_Servlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 *用户结算维护
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_money_use_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel<T_use_Money_Bean> page = new PageModel<T_use_Money_Bean>();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = T_useMoney_Sql.selectAllT(page);
		request.setAttribute("page", page);

		
		
		List <T_use_Money_Bean>useMoney =T_useMoney_Sql.queryAllUseMoney();
		
		request.setAttribute("useMoney", useMoney);
		request.getRequestDispatcher("use\\money_use_list.jsp").forward(request, response);
	}



}
