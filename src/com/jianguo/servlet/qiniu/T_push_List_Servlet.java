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
import com.jianguo.bean.T_push_Bean;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.util.Frequently;

public class T_push_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_push_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_push_List_Servlet?only=6CD1B16F831B04A3F2222FD084073ACF&login_id=8257
	//http://192.168.1.233/JianGuo_Server/T_push_List_Servlet?only=A9DBCEC8B4AE30105139E7BE544F3EDE&key_id=111111
	//http://101.200.205.243:8080/T_push_List_Servlet?only=E51DAB17D1BDDCA066388A19A651C48A&login_id=8257
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_push_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		
//		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
//		//------------------访问限制--------结束----------------------

			List<T_push_Bean> list_t_push = T_push_Sql.select_login_id(login_id,"0");
			Map map = new HashMap();
			map.put("list_t_push", list_t_push);

			params.put("data", map);
			params.put("message", "成功");
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
//		//------------------访问限制--------开始----------------------
		}else{
			params.put("message", "无效访问");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
//		//------------------访问限制--------结束----------------------
	}
}
