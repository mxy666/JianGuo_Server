package com.jianguo.servlet.school;

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
import com.jianguo.bean.T_school_Bean;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

public class T_school_LikeName_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_school_LikeName_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_school_LikeName_Servlet?only=64D78679E0E12A7875F052650905B6D3
	//http://101.200.197.237:8080/T_school_LikeName_Servlet?only=66782C4854B88C5E72131F3EFC5DF1F3&name=海南
	//http://101.200.205.243:8080/T_school_LikeName_Servlet?only=66782C4854B88C5E72131F3EFC5DF1F3&name=海南
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_school_LikeName_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String name =request.getParameter("name");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
			//------------------访问限制--------结束----------------------

			List<T_school_Bean> list_t_school = T_school_Sql.select_All_name(name);
			Map map = new HashMap();
			map.put("list_t_school", list_t_school);
			
			params.put("data", map);
			params.put("message", "学校查询成功");
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
