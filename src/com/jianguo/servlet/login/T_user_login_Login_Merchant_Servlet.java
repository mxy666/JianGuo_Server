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
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;
import com.qiniu.util.Auth;

public class T_user_login_Login_Merchant_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_login_Login_Merchant_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.135/JianGuo_Server/T_user_login_Login_Merchant_Servlet?only=CBE16B433A340A7B3629148F7E729DAD&tel=333&password=E10ADC3949BA59ABBE56E057F20F883E
//http://101.200.205.135/T_user_login_Login_Merchant_Servlet?only=87AEF096C58D3A646CBFA6DDDA92D5C2&tel=jg13614093590&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/T_user_login_Login_Merchant_Servlet?only=1D77EF5EB996DAF8B817DB13021DF25B&tel=222222&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243/banner01.jpg
	//http://101.200.205.243/user_agreement.jsp
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_login_Login_Merchant_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String tel =request.getParameter("tel");
		String password =request.getParameter("password");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
		//------------------访问限制--------结束----------------------
		System.out.println(tel); 
		System.out.println(password);
		boolean b = T_user_login_Sql.check_tel_password_power(tel,password,"0");
		if(b == true){
			//简单的token(七牛)
			Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");	
			//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
			String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
			
			T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
			T_merchant_Bean t_merchant = T_merchant_Sql.select_login_id(t_user_login.getId()+"");
			t_user_login.setQiniu(qiniu_token);
//			t_merchant.setQiniu(qiniu_token);
			
			Map map = new HashMap();
			map.put("t_user_login", t_user_login);
			map.put("t_merchant", t_merchant);
			
			params.put("data", map);			
			params.put("message", "登录成功");			
			params.put("code", "200");
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
