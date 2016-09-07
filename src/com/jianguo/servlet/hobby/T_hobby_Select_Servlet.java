package com.jianguo.servlet.hobby;

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
import com.jianguo.bean.T_hobby_time_Bean;
import com.jianguo.bean.T_type_Bean;
import com.jianguo.sql.T_hobby_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

public class T_hobby_Select_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_hobby_Select_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_hobby_Select_Servlet?only=6D6DE93A3A1591AE12853DF594A52949&login_id=5794
	//http://101.200.205.243:8080/T_hobby_Select_Servlet?only=6D6DE93A3A1591AE12853DF594A52949&login_id=664
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_hobby_Select_Servlet---");
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
			
//			List<T_type_Bean> list_t_type = T_school_Sql.select_All_type();
//			List<T_type_Bean> list_t_type2 = new ArrayList<T_type_Bean>();
//			for (int i = 0; i < list_t_type.size(); i++) {
//				T_type_Bean t_type = list_t_type.get(i);
//				List<T_hobby_type_Bean> list_t_hobby_type = T_hobby_Sql.select_type(login_id);
//				String ss = "";
//				for (int j = i++; j < list_t_hobby_type.size(); j++) {
//					T_hobby_type_Bean t_hobby_type = list_t_hobby_type.get(j);
////					t_hobby_type.getType();
//					if(t_hobby_type.getType() == t_type.getId()){
//						ss = "true";
//					}else{
//						ss = "false";
//					}
//				}
//				t_type.setIs_type(ss);
//				list_t_type2.add(t_type);
//			}
			
			
			
			List<T_type_Bean> list_t_type = T_school_Sql.select_All_typeaaa(login_id);
			List<T_type_Bean> list_t_type2 = new ArrayList<T_type_Bean>();
			T_type_Bean t = new T_type_Bean();
			t.setId(0);
			t.setType_name("职业不限");
			
			if(T_hobby_Sql.check_type(login_id, "0")){
				t.setIs_type("true");
			}else{
				t.setIs_type("false");
			}
			
			
			list_t_type2.add(t);
			for (int i = 0; i < list_t_type.size(); i++) {
				T_type_Bean t_type = list_t_type.get(i);
				if(t_type.getIs_type().equals("null") || !t_type.getLogin_id().equals(login_id)){
					t_type.setIs_type("false");
				}else if(t_type.getLogin_id().equals(login_id)){
					t_type.setIs_type("true");
				}
					list_t_type2.add(t_type);
			}
			
			List<String> list = new ArrayList<String>();
			List<T_hobby_time_Bean> list_t_hobby_time = T_hobby_Sql.select_time(login_id);
			for (int i = 0; i < list_t_hobby_time.size(); i++) {
				T_hobby_time_Bean t_hobby_time = list_t_hobby_time.get(i);
				list.add(t_hobby_time.getTime()+"");
			}
		
			Map map = new HashMap();
			map.put("list_t_hobby_type", list_t_type2);
			map.put("list_t_hobby_time", list);

			params.put("data", map);
			params.put("message", "偏好查询成功");
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
