package com.jianguo.servlet.qiniu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_banner_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

public class T_city_Select_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_city_Select_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_city_Select_Servlet?only=304222948FE3B51D9DC9207BCB505CDB
	//http://101.200.197.237:8080/T_city_Select_Servlet?only=C9B1DDF8F5CEE9C376794456D3D463E6
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_city_Select_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------
			List<T_city_Bean> list_t_city = T_school_Sql.select_All_city();

//			List<T_city_Bean> list_t_city2 = new ArrayList<T_city_Bean>();
//			for (int i = 0; i < list_t_city.size(); i++) {
//				T_city_Bean t_city = list_t_city.get(i);
//				List<T_area_Bean> list_t_area = T_school_Sql.select_All_area(t_city.getId()+"");
//				t_city.setList_t_area(list_t_area);
//
//				list_t_city2.add(t_city);
//			}

			List<T_banner_Bean> list_t_banner = T_school_Sql.select_All_banner();
			Map map = new HashMap();
			map.put("list_t_city", list_t_city);
			map.put("list_t_banner", list_t_banner);

			params.put("data", map);
			params.put("message", "获取成功");
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
