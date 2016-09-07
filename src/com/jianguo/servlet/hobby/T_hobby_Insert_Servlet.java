package com.jianguo.servlet.hobby;

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
import com.jianguo.sql.T_hobby_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;

public class T_hobby_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_hobby_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_hobby_Insert_Servlet?only=88BDEE78CF4BC0D027C806AB18DE9CAA&login_id=41&follow=2&collection=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_hobby_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String json_type =request.getParameter("json_type");
		String json_time =request.getParameter("json_time");
		
//		System.out.println("---"+login_id+"---");
//		System.out.println("---"+json_type+"---");
//		System.out.println("---"+json_time+"---");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			T_hobby_Sql.delete_type(login_id);
			T_hobby_Sql.delete_time(login_id);
			
				Gson g3 = new Gson();
				user_type list = g3.fromJson(json_type, user_type.class);
				for (int i = 0; i < list.getList_t_type().size(); i++) {
					String t_type = list.getList_t_type().get(i);
					T_hobby_Sql.insert_type(login_id,t_type);
				}
				
				Gson g2 = new Gson();
				user_time list2 = g2.fromJson(json_time, user_time.class);
				for (int i = 0; i < list2.getList_t_time().size(); i++) {
					String t_time = list2.getList_t_time().get(i);
					T_hobby_Sql.insert_time(login_id,t_time);
				}
				if(list.getList_t_type().size() != 0 && list2.getList_t_time().size() != 0){
					T_user_login_Sql.update_hobby("1", login_id);
				}
				
				params.put("message", "求职意向设置成功");
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
	
	private class user_type{
		private List<String> list_t_type;
		public void setList_t_type(List<String> list_t_type) {
			this.list_t_type = list_t_type;
		}
		public List<String> getList_t_type() {
			return list_t_type;
		}
	}
	
	private class user_time{
		private List<String> list_t_time;
		public void setList_t_time(List<String> list_t_time) {
			this.list_t_time = list_t_time;
		}
		public List<String> getList_t_time() {
			return list_t_time;
		}
	}
	
}
