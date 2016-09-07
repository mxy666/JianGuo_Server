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
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;

public class T_user_resume_SelectId_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_resume_SelectId_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_resume_SelectId_Servlet?only=3016E9490D2C47F18954E1277DCA873E&tel=18631017353&password=3
	//http://101.200.205.243:8080/T_user_resume_SelectId_Servlet?only=E3DAEDE0B67A7731C83B1D01F30A2420&tel=18631017353&password=3
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_resume_SelectId_Servlet---");
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
				T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(login_id);
				
//				t_user_resume.setId(rs.getInt("id"));
//				t_user_resume.setLogin_id(rs.getInt("login_id"));
//				t_user_resume.setNickname(rs.getString("nickname")+"");
//				t_user_resume.setName(rs.getString("name")+"");
//				t_user_resume.setName_image(rs.getString("name_image")+"");
//				t_user_resume.setSchool(rs.getString("school")+"");
//				t_user_resume.setIntoschool_date(rs.getString("intoschool_date")+"");
//				t_user_resume.setSex(rs.getInt("sex"));
//				t_user_resume.setHeight(rs.getInt("height"));
//				t_user_resume.setStudent(rs.getInt("student"));
//				t_user_resume.setBirth_date(rs.getString("birth_date")+"");
//				t_user_resume.setShoe_size(rs.getString("shoe_size")+"");
//				t_user_resume.setClothing_size(rs.getString("clothing_size")+"");
//				t_user_resume.setSign(rs.getString("sign")+"");
//				t_user_resume.setLabel(rs.getString("label")+"");
				
				if(t_user_resume.getNickname() == "null" || t_user_resume.getNickname() == null){
					t_user_resume.setNickname("");
				}
				if(t_user_resume.getName() == "null" || t_user_resume.getName() == null){
					t_user_resume.setName("");
				}
				if(t_user_resume.getName_image() == "null" || t_user_resume.getName_image() == null){
					t_user_resume.setName_image("");
				}
				if(t_user_resume.getSchool() == "null" || t_user_resume.getSchool() == null){
					t_user_resume.setSchool("");
				}
				if(t_user_resume.getIntoschool_date() == "null" || t_user_resume.getIntoschool_date() == null){
					t_user_resume.setIntoschool_date("");
				}
				if(t_user_resume.getBirth_date() == "null" || t_user_resume.getBirth_date() == null){
					t_user_resume.setBirth_date("");
				}
				if(t_user_resume.getShoe_size() == "null" || t_user_resume.getShoe_size() == null){
					t_user_resume.setShoe_size("");
				}
				if(t_user_resume.getSchool() == "null" || t_user_resume.getSchool() == null){
					t_user_resume.setSchool("");
				}
				if(t_user_resume.getClothing_size() == "null" || t_user_resume.getClothing_size() == null){
					t_user_resume.setClothing_size("");
				}
				if(t_user_resume.getSign() == "null" || t_user_resume.getSign() == null){
					t_user_resume.setSign("");
				}
				if(t_user_resume.getLabel() == "null" || t_user_resume.getLabel() == null){
					t_user_resume.setLabel("");
				}
				
				Map map = new HashMap();
				map.put("t_user_resume", t_user_resume);
				
				params.put("data", map);
				params.put("message", "简历查询成功");			
				params.put("code", "200");
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
