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
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.util.Frequently;

public class T_job_List_Filter_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_List_Filter_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_List_Filter_Servlet?only=B74962DB1E2E3FF8024F181551F6A6B7&city_id=1&type_id=0&area_id=3&filter_id=0&count=0
	//http://101.200.205.243:8080/T_job_List_Filter_Servlet?only=AAD1F76F407C390F54EDCE6E2E08E1C5&city_id=3&type_id=0&area_id=3&filter_id=2&count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_List_Filter_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String city_id =request.getParameter("city_id");
		String type_id =request.getParameter("type_id");
		String area_id =request.getParameter("area_id");
		String filter_id =request.getParameter("filter_id");
		String count =request.getParameter("count");
//		System.out.println("city_id:"+city_id+"    type_id:"+type_id+"    area_id:"+area_id+"    filter_id:"+filter_id);
//		                      3---            19---          11---       2
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------
			List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();

			String ss = "";
			if(city_id.equals("010")){
				ss = "3";
			}
			if(city_id.equals("0899")){
				ss = "1";
			}
			if(city_id.equals("0898")){
				ss = "2";
			}
			if(city_id.equals("0571")){
				ss = "4";
			}
			if(city_id.equals("029")){
				ss = "5";
			}
			if(city_id.equals("027")){
				ss = "6";
			}
			if(city_id.equals("3")){
				ss = "3";
			}
			if(city_id.equals("1")){
				ss = "1";
			}
			if(city_id.equals("2")){
				ss = "2";
			}
			if(city_id.equals("4")){
				ss = "4";
			}
			if(city_id.equals("5")){
				ss = "5";
			}
			if(city_id.equals("6")){
				ss = "6";
			}
			
			long ll = System.currentTimeMillis()/1000;
			if(type_id.equals("0") && area_id.equals("0") && filter_id.equals("0")){
				list_t_job = T_job_Sql.select_filter00(ss, city_id,ll+"",ll+"", count);

			}else if(type_id.equals("0") && area_id.equals("0") && filter_id.equals("1")){
				list_t_job = T_job_Sql.select_filter11(ss,city_id, ll+"",ll+"",count);

			}else if(type_id.equals("0") && area_id.equals("0") && filter_id.equals("2")){
				list_t_job = T_job_Sql.select_filter22(ss, city_id,ll+"",ll+"",count);

			}else if(filter_id.equals("0") && (!type_id.equals("0") && !area_id.equals("0"))){
				list_t_job = T_job_Sql.select_filter000(ss,city_id,type_id,area_id,ll+"",ll+"",count);

				
			}else if(filter_id.equals("0") && (!type_id.equals("0") || !area_id.equals("0"))){
				list_t_job = T_job_Sql.select_filter0(ss,city_id,type_id,area_id,ll+"",ll+"",count);

				
			}else if(filter_id.equals("1") && !type_id.equals("0") && !area_id.equals("0")){
				list_t_job = T_job_Sql.select_filter111(ss,city_id,type_id,area_id,ll+"",ll+"",count);

				
			}else if(filter_id.equals("1") && (!type_id.equals("0") || !area_id.equals("0"))){
				list_t_job = T_job_Sql.select_filter1(ss,city_id,type_id,area_id,ll+"",ll+"",count);

				
			}else if(filter_id.equals("2") && (!type_id.equals("0") && !area_id.equals("0"))){
				list_t_job = T_job_Sql.select_filter222(ss,city_id,type_id,area_id,ll+"",ll+"",count);

				
			}else if(filter_id.equals("2") && (!type_id.equals("0") || !area_id.equals("0"))){
				list_t_job = T_job_Sql.select_filter2(ss,city_id,type_id,area_id,ll+"",ll+"",count);

//			}if(filter_id.equals("0")){
//				list_t_job = T_job_Sql.select_filter0(city_id,type_id,area_id,count);
//				System.out.println("5555555555555555555");
//			}else if(filter_id.equals("1")){
//				list_t_job = T_job_Sql.select_filter1(city_id,type_id,area_id,count);
//				System.out.println("6666666666666666666");
//			}else if(filter_id.equals("2")){
//				list_t_job = T_job_Sql.select_filter2(city_id,type_id,area_id,count);
//				System.out.println("7777777777777777777777");
			}
			
			Map map = new HashMap();
			map.put("list_t_job", list_t_job);

			params.put("data", map);
			params.put("message", "兼职信息查询成功");
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
