package com.jianguo.servlet.usermoney;

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
import com.jianguo.util.Frequently;

public class T_user_money_Pay_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_money_Pay_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_money_Pay_Servlet
	//http://101.200.205.243:8080/T_user_money_Pay_Servlet?only=831B19CDF904CA6133A3982F94C3E5AD&login_id=4&password=22	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_money_Pay_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String name =request.getParameter("name");
		String zhifubao =request.getParameter("zhifubao");
		String yinhang =request.getParameter("yinhang");
		String kahao =request.getParameter("kahao");
		String type =request.getParameter("type");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			if(type.equals("0")){
				int i = T_user_money_Sql.update_zhiofubao(name,zhifubao,login_id);
				if(i == 1){
					params.put("message", "支付宝绑定成功");
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					params.put("message", "支付宝绑定失败");
					params.put("code", "500");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}
			}else if(type.equals("1")){
				int i = T_user_money_Sql.update_kahao(name,yinhang,kahao,login_id);
				if(i == 1){
					params.put("message", "银行卡绑定成功");
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					params.put("message", "银行卡绑定失败");
					params.put("code", "500");
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
