package com.jianguo.servlet.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_wx_Sql;
import com.jianguo.util.Frequently;

public class T_user_wx_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_wx_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/T_user_wx_Insert_Servlet?only=88BDEE78CF4BC0D027C806AB18DE9CAA&login_id=41&follow=2&collection=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_wx_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String login_id =request.getParameter("login_id");
		String openid =request.getParameter("openid");
		String nickname =request.getParameter("nickname");
		String sex =request.getParameter("sex");
		String province =request.getParameter("province");
		String city =request.getParameter("city");
		String country =request.getParameter("country");
		String headimgurl =request.getParameter("headimgurl");
		String privilege =request.getParameter("privilege");
		String unionid =request.getParameter("unionid");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			int i = T_user_wx_Sql.insert(login_id, openid, nickname, sex, province, city, country, headimgurl, privilege, unionid);
			if(i == 1){
				
				T_user_money_Sql.update_weixin("1", login_id);
				
				params.put("message", "微信绑定成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				params.put("message", "微信绑定失败");
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
