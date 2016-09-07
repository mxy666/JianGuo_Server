package com.jianguo.servlet.qiniu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.util.Frequently;
import com.qiniu.util.Auth;

public class T_QiNiu_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_QiNiu_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_QiNiu_Servlet?only=A7A2D6CB9D71549224A1D82CD46E1C2A
	//http://101.200.205.243:8080/T_QiNiu_Servlet?only=A7A2D6CB9D71549224A1D82CD46E1C2A
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_QiNiu_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String only =request.getParameter("only");

		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
					//简单的token
					Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");	
					//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
					String token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
					params.put("message", "200");
					params.put("token", token);
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params);
					pw.write(str);
					pw.flush();
					pw.close();
		}else{
			params.put("message", "无效访问");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
	}
}
