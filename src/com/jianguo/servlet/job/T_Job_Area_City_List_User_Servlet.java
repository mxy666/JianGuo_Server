package com.jianguo.servlet.job;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.Area_Bean;
import com.jianguo.bean.T_area_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_label_Bean;
import com.jianguo.bean.T_limit_Bean;
import com.jianguo.bean.T_type_Bean;
import com.jianguo.bean.T_welfare_Bean;
import com.jianguo.sql.Job_Sql;
import com.jianguo.sql.T_limit_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

import org.apache.log4j.Logger;

public class T_Job_Area_City_List_User_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_Job_Area_City_List_User_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_Job_Area_City_List_User_Servlet?only=287F75378BF5ED2142EA5792AA799C66
	//http://101.200.205.243:8080/T_Job_Area_City_List_User_Servlet?only=EE4C826A92CD35A263116D7425D55763&city_id=101
	//http://101.200.205.243:8080/T_Job_Area_City_List_User_Servlet?only=287F75378BF5ED2142EA5792AA799C66
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_Job_Area_City_List_User_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		Logger logger = Logger.getLogger("log");
		logger.info("日志信息开始!T_Job_Area_City_List_User_Servlet");
		String login_id =request.getParameter("login_id");
		String city_id =request.getParameter("city_id");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			List<T_city_Bean> list_t_city = null;
			if(city_id == "" || city_id == null){
				list_t_city = T_school_Sql.select_All_city();
			}else{
//				System.out.println("----"+city_id+"-----------");
//				String ss = "";
//				if(city_id.equals("010")){
//					ss = "3";
//				}
//				if(city_id.equals("0899")){
//					ss = "1";
//				}
//				if(city_id.equals("0898")){
//					ss = "2";
//				}
//				if(city_id.equals("0571")){
//					ss = "4";
//				}
//				if(city_id.equals("029")){
//					ss = "5";
//				}
//				if(city_id.equals("3")){
//					ss = "3";
//				}
//				if(city_id.equals("1")){
//					ss = "1";
//				}
//				if(city_id.equals("2")){
//					ss = "2";
//				}
//				if(city_id.equals("4")){
//					ss = "4";
//				}
//				if(city_id.equals("5")){
//					ss = "5";
//				}

//				list_t_city = T_school_Sql.select_All_city_id(ss);
				list_t_city = T_school_Sql.select_All_city();
			}

			List<T_city_Bean> list_t_city2 = new ArrayList<T_city_Bean>();
			for (int i = 0; i < list_t_city.size(); i++) {
				T_city_Bean t_city = list_t_city.get(i);
				List<Area_Bean> list_t_area = new ArrayList<Area_Bean>();
				List<Area_Bean> list_t_area2 = new ArrayList<Area_Bean>();
				try {
					list_t_area = Job_Sql.selectAllArea(t_city.getCode()+"");
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}

				Area_Bean t_area = new Area_Bean();;
				t_area.setId("0");
				t_area.setCity_id("0");
				t_area.setArea_name("地区不限");
				list_t_area2.add(t_area);

				for (int j = 0; j < list_t_area.size(); j++) {
					Area_Bean t_area2 = list_t_area.get(j);
					list_t_area2.add(t_area2);
				}
				t_city.setList_t_area(list_t_area2);
				list_t_city2.add(t_city);
			}

//			List<T_area_Bean> list_t_area = T_school_Sql.select_All_area("3");
//			List<T_area_Bean> list_t_area2 = new ArrayList<T_area_Bean>();
//			for (int j = 0; j < list_t_area.size(); j++) {
//				T_area_Bean t_area2 = list_t_area.get(j);
//				list_t_area2.add(t_area2);
//			}

//			T_city_Bean t_citys = new T_city_Bean();
//			t_citys.setId(0);
//			t_citys.setCity("全");
//			t_citys.setCode("");
//			t_citys.setList_t_area(list_t_area2);
//			list_t_city2.add(t_citys);

			List<T_type_Bean> list_t_type = T_school_Sql.select_All_type();
			List<T_type_Bean> list_t_type2 = new ArrayList<T_type_Bean>();

			T_type_Bean t = new T_type_Bean();
			t.setId(0);
			t.setType_name("职业不限");

			list_t_type2.add(t);
			for (int i = 0; i < list_t_type.size(); i++) {
				T_type_Bean ttt = list_t_type.get(i);
				list_t_type2.add(ttt);
			}

			List<T_limit_Bean> list_t_limit = T_limit_Sql.select_All_limit();
			List<T_welfare_Bean> list_t_welfare = T_limit_Sql.select_All_welfare();
			List<T_label_Bean> list_t_label = T_limit_Sql.select_All_label();

			Map map = new HashMap();
			map.put("list_t_city2", list_t_city2);
			map.put("list_t_type", list_t_type2);
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
