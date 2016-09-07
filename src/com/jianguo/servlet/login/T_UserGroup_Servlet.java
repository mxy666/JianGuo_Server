package com.jianguo.servlet.login;

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
import com.jianguo.bean.T_userChat_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_UserChat_Sql;
import com.jianguo.sql.T_push_new_Sql;
import com.jianguo.util.Frequently;

public class T_UserGroup_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_UserGroup_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 *用户群组查询
	 *http://localhost:8080/JianGuo_Server/T_UserGroup_Servlet
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_UserGroup_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		Map map = new HashMap();
		String login_id =request.getParameter("login_id");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			Gson g3 = new Gson();
			UserInfo list = g3.fromJson(login_id, UserInfo.class);
			
			//String loginStr="";
			String loginId []=new String [list.getLogin_id().size()];
			for(int i=0;i<list.getLogin_id().size();i++){
				loginId [i]=list.getLogin_id().get(i);
				
			}
			String loginStr=T_push_new_Sql.buffer(loginId).toString();
		
			List<T_userChat_Bean>userGroup =T_UserChat_Sql.queryByGroup(loginStr);
			if(userGroup.size()==0){				
				params.put("message", "无此用户");
				params.put("code", "400");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				map.put("userGroup", userGroup);
				params.put("data", userGroup);
				params.put("message", "用户查询成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}

			
		
			
			
			
			//------------------访问限制--------结束----------------------
			
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
	private class UserInfo{
		private List<String> login_id;

	
		public void setLogin_id(List<String> login_id) {
			this.login_id = login_id;
		}
		public List<String> getLogin_id() {
			return login_id;
		}
		
	}
}
