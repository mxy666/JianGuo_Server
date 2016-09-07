package com.jianguo.servlet.job;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_area_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_label_Bean;
import com.jianguo.bean.T_limit_Bean;
import com.jianguo.bean.T_type_Bean;
import com.jianguo.bean.T_welfare_Bean;
import com.jianguo.sql.T_limit_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

public class T_Job_Area_City_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_Job_Area_City_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_Job_Area_City_List_Servlet?only=F62CB92A0A1CCB54E5573BAB4734BE3A&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/T_Job_Area_City_List_Servlet?only=E4A4C26BFD881E0EED1BBA5837093EF0&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_Job_Area_City_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			List<T_city_Bean> list_t_city = T_school_Sql.select_All_city();
			List<T_city_Bean> list_t_city2 = new ArrayList<T_city_Bean>();
			for (int i = 0; i < list_t_city.size(); i++) {
				T_city_Bean t_city = list_t_city.get(i);
				List<T_area_Bean> list_t_area = T_school_Sql.select_All_area(t_city.getId()+"");
				t_city.setList_t_area(list_t_area);
				list_t_city2.add(t_city);
			}
			List<T_type_Bean> list_t_type = T_school_Sql.select_All_type();
			
			List<T_limit_Bean> list_t_limit = T_limit_Sql.select_All_limit();
			List<T_welfare_Bean> list_t_welfare = T_limit_Sql.select_All_welfare();
			List<T_label_Bean> list_t_label = T_limit_Sql.select_All_label();
			
				Map map = new HashMap();
				map.put("list_t_city2", list_t_city2);
				map.put("list_t_type", list_t_type);
				map.put("list_t_limit", list_t_limit);
				map.put("list_t_welfare", list_t_welfare);
				map.put("list_t_label", list_t_label);

				params.put("data", map);
				params.put("message", "获取地区信息成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();

			//------------------访问限制--------开始----------------------
		}else{
			params.put("message", "无效访问");
			params.put("code", "404");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------访问限制--------结束----------------------
	}
}
