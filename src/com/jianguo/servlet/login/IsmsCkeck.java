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
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Text_Sms;

public class IsmsCkeck extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public IsmsCkeck() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.135/JianGuo_Server/IsmsCkeck?tel=13163153160&only=275642D6F14FD5B0E0CA17827B1B9546
	//短信验证只返回成功或失败,验证码发送
	public void doPost(HttpServletRequest request, final HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---IsmsCkeck---");//忘记密码，提现
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		final Map<String, String> params =  new HashMap<String, String>();
 
		final String tel =request.getParameter("tel");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
		//------------------访问限制--------结束----------------------

		boolean b = T_user_login_Sql.check_tel(tel);
		if(b == true){//手机号存在才发送验证码
			
		
			
			new Thread(new Runnable() {			
				public void run() {

					final String code = Text_Sms.textdemos(tel);			
				}
				}).start();
			
			PrintWriter pw;
			params.put("message", "验证码已经发送，请注意查收！");
			params.put("code", "200");
			pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		
		}else{
			params.put("message", "改手机号尚未注册!");
			params.put("code", "500");
			//params.put("code", "500");
			//params.put("is_tel", "0");
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
