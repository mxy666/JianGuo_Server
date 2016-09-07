package com.jianguo.servlet.resume;

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
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;

public class T_user_resume_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_resume_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_resume_Insert_Servlet?only=3016E9490D2C47F18954E1277DCA873E&tel=18631017353&password=3
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_resume_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String name =request.getParameter("name");
		String nickname =request.getParameter("nickname");
		String name_image =request.getParameter("name_image");
		String school =request.getParameter("school");
		String intoschool_date =request.getParameter("intoschool_date");
		String sex =request.getParameter("sex");
		String height =request.getParameter("height");
		String student =request.getParameter("student");
		String birth_date =request.getParameter("birth_date");
		String shoe_size =request.getParameter("shoe_size");
		String clothing_size =request.getParameter("clothing_size");
		String sign =request.getParameter("sign");
		String label =request.getParameter("label");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
		//------------------访问限制--------结束----------------------
		
			T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
			if(t_user_login.getTel() == "0" || t_user_login.getTel().equals("0")){
				params.put("message", "请进行手机验证");
				params.put("code", "500");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				int i = T_user_resume_Sql.insert_qq_wx(login_id, nickname, name, name_image, school, intoschool_date, sex, height, student, birth_date, shoe_size, clothing_size, sign, label);
				
				T_user_info_Sql.update_nickname(nickname, login_id);
				T_user_info_Sql.update_school(school, login_id);
				T_user_login_Sql.update_resume("1", login_id);
				
				if(i == 1){
				params.put("message", "资料填写成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
				}else{
					params.put("message", "资料填写失败");
					params.put("code", "403");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}
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
