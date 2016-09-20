package com.jianguo.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;
import com.qiniu.util.Auth;

public class T_user_login_Login_Tel_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_login_Login_Tel_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_user_login_Login_Tel_Servlet?only=34559D075EC8415D0F19E820136AD71F&tel=18101050626&password=3B875C3464C0682DAB1F8FC343BDAD48&city_id=10
	//http://101.200.205.243:8080/T_user_login_Login_Tel_Servlet?only=B3FD51CB01E8C36B9A82B0A7939DF93F&tel=18289960802&password=023299564B0DB47D5F3E476A254D0C21
	//http://101.200.205.243:8080/user_agreement.jsp
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_login_Login_Tel_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String tel =request.getParameter("tel");
		String password =request.getParameter("password");
		String city_id =request.getParameter("city_id");
		String city_name =request.getParameter("city_name");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
		//------------------访问限制--------结束----------------------
		System.out.println(tel);
		System.out.println(password);
		boolean b = T_user_login_Sql.check_tel_password(tel,password);
		if(b == true){
			//简单的token(七牛)
			Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");	
			//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
			String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
			
			T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
			T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
			t_user_login.setQiniu(qiniu_token);
			
			T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(t_user_login.getId()+"");
			t_user_info.setUser_sex(t_user_resume.getSex()+"");
			t_user_info.setQiniu(qiniu_token);

			if(city_id == "" || city_id == null){
			}else{
			if(city_id == "0" || city_id.equals("0")){
			}else{
				if(city_id.equals("010")){
					T_user_login_Sql.update_city_id("北京", t_user_login.getId()+"");
				}else
				if(city_id.equals("0899")){
					T_user_login_Sql.update_city_id("三亚", t_user_login.getId()+"");
				}else
				if(city_id.equals("0898")){
					T_user_login_Sql.update_city_id("海口", t_user_login.getId()+"");
				}else
				if(city_id.equals("0571")){
					T_user_login_Sql.update_city_id("杭州", t_user_login.getId()+"");
				}else
				if(city_id.equals("029")){
					T_user_login_Sql.update_city_id("西安", t_user_login.getId()+"");
				}else{
					T_user_login_Sql.update_city_id(city_name, t_user_login.getId()+"");
				}
			}
			}
			Map map = new HashMap();
			map.put("t_user_login", t_user_login);
			map.put("t_user_info", t_user_info);
			map.put("version", "22");
			map.put("version_ios", "3.0.7");//ios只需要version_ios即可
			map.put("hobby", "3.0.3");
			map.put("content", "修复了部分Bug，为方便使用，请更新！");
			map.put("apk_url", "http://7xljc3.dl1.z0.glb.clouddn.com/app-release.apk");//安卓
			
			params.put("data", map);
			params.put("message", "登录成功");
			params.put("code", "200");
//			params.put("t_user_login", t_user_login);
//			params.put("t_user_info", t_user_info);
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
		}else{
			params.put("message", "用户名密码不正确");
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
