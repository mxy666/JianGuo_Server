package com.jianguo.servlet.attent;

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
import com.jianguo.bean.T_attent_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.sql.T_attent_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.util.Frequently;

public class T_attent_Select_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_attent_Select_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_attent_Select_Servlet?only=8CF00E188D0E38579336EBDBA916FBBA&login_id=44
	//http://101.200.205.243:8080/T_attent_Select_Servlet?only=8CF00E188D0E38579336EBDBA916FBBA&login_id=52
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_attent_Select_Servlet---");
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

			List<T_attent_Bean> list_t_attent = T_attent_Sql.select_login_id(login_id);
			
			List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();
			for (int i = 0; i < list_t_attent.size(); i++) {
				T_attent_Bean t_attent = list_t_attent.get(i);
				if(t_attent.getCollection() != 0){
					T_job_Bean t_job = T_job_Sql.select_id(t_attent.getCollection()+"");
					list_t_job.add(t_job);
				}
			}
			
			List<T_merchant_Bean> list_t_merchant = new ArrayList<T_merchant_Bean>();
			for (int i = 0; i < list_t_attent.size(); i++) {
				T_attent_Bean t_attent = list_t_attent.get(i);
				if(t_attent.getFollow() != 0){
					T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_attent.getFollow()+"");
					t_merchant.setIs_follow("1");
					list_t_merchant.add(t_merchant);
				}
			}

				Map map = new HashMap();
				map.put("list_t_job", list_t_job);
				map.put("list_t_merchant", list_t_merchant);
			
				params.put("data", map);
				params.put("message", "查看关注收藏成功");
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
