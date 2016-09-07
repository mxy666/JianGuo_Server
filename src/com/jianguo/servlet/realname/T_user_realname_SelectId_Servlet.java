package com.jianguo.servlet.realname;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;
import com.jianguo.util.Frequently;

public class T_user_realname_SelectId_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_realname_SelectId_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_realname_SelectId_Servlet?only=3016E9490D2C47F18954E1277DCA873E&tel=18631017353&password=3
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_realname_SelectId_Servlet---");
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
		
			boolean b = T_user_realname_Sql.check_login_id(login_id);
			if(b == true){
				T_user_realname_Bean t_user_realname = T_user_realname_Sql.select_login_id(login_id);
				
				T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
				t_user_realname.setStatus(t_user_login.getStatus());
				Map map = new HashMap();
				map.put("t_user_realname", t_user_realname);
				
				params.put("message", "已经实名");
				params.put("data", map);
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				params.put("message", "没有实名");
				params.put("code", "500");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}
			
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
