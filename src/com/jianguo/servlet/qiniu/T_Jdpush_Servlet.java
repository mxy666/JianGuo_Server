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
import com.jianguo.util.Jdpush;
import com.jianguo.util.Jdpush_shang;
import com.jianguo.util.Jdpushcc;
import com.jianguo.util.Jdpushcc_shang;
import com.jianguo.util.Jdpusher;
import com.jianguo.util.Jdpusher_shang;

public class T_Jdpush_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_Jdpush_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_Jdpush_Servlet?only=A9DBCEC8B4AE30105139E7BE544F3EDE&key_id=jianguo1
	//http://192.168.1.233/JianGuo_Server/T_Jdpush_Servlet?only=A9DBCEC8B4AE30105139E7BE544F3EDE&key_id=111111
	//http://101.200.205.243:8080/T_Jdpush_Servlet?only=7A236101E0989874B45982C0CFE224C2&key_id=jianguo20
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_Jdpush_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String key_id =request.getParameter("key_id");
		
//		//------------------访问限制--------开始----------------------
//		String only =request.getParameter("only");
//		String ss_only = Frequently.daycount();
//		String ss_only2 = Frequently.daycount2();
//		String ss_only3 = Frequently.daycount3();
//		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
//		//------------------访问限制--------结束----------------------

//			Jdpush.sendPush(ay_bean2.getUser_name()+"friend request from",friendusername);
//			Jdpusher.sendPush(ay_bean2.getUser_name()+"friend request from",friendusername);
			
//			Jdpush.sendPush("1friend request from",key_id);
//			Jdpusher.sendPush("friend request from",key_id);
//			Jdpushcc.sendPush("friend request from",key_id);
			
			Jdpush_shang.sendPush("1friend request ssssssfrom",key_id);
			Jdpusher_shang.sendPush("friend request sssssssssfrom",key_id);
			Jdpushcc_shang.sendPush("friend request ssssssfrom",key_id);

			params.put("message", "成功");
			params.put("code", "200");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
//		//------------------访问限制--------开始----------------------
//		}else{
//			params.put("message", "无效访问");
//			PrintWriter pw = response.getWriter();
//			Gson g = new Gson();
//			String str = g.toJson(params); 
//			pw.write(str);
//			pw.flush();
//			pw.close();
//		}
//		//------------------访问限制--------结束----------------------
	}
}
