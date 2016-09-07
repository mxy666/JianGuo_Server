//package com.jianguo.servlet.enroll;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.google.gson.Gson;
//import com.jianguo.sql.T_enroll_Sql;
//import com.jianguo.sql.T_job_Sql;
//import com.jianguo.util.Frequently;
//
//public class T_enroll_User_Cancel_Servlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	public T_enroll_User_Cancel_Servlet() {
//		super();
//	}
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		this.doPost(request, response);
//	}
//
//	//http://192.168.1.233/JianGuo_Server/T_enroll_User_Cancel_Servlet?only=648C6D6926E835F494A0878DBB0CFB90&login_id=2&job_id=1万&Offer=1
//	//http://101.200.205.243:8080/T_enroll_User_Cancel_Servlet?only=648C6D6926E835F494A0878DBB0CFB90&login_id=52&job_id=58&offer=1
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//		System.out.println("---T_enroll_User_Cancel_Servlet---");
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		Map params =  new HashMap();
//		
//		String login_id =request.getParameter("login_id");
//		String job_id =request.getParameter("job_id");
//		String Offer =request.getParameter("offer");
//		
//		//------------------访问限制--------开始----------------------
//		String only =request.getParameter("only");
//		String ss_only = Frequently.daycount();
//		String ss_only2 = Frequently.daycount2();
//		String ss_only3 = Frequently.daycount3();
//		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
//			//------------------访问限制--------结束----------------------
//
//			if(T_enroll_Sql.check_login_id_job_id(login_id, job_id)){
//				if(Offer.equals("0")){
//					T_enroll_Sql.update_login_status_job_status("6", "5","","",login_id, job_id);
//				}
//				T_job_Sql.update_count(job_id);
//				
//				params.put("message", "商家录取操作成功");
//				params.put("code", "200");
//				PrintWriter pw = response.getWriter();
//				Gson g = new Gson();
//				String str = g.toJson(params); 
//				pw.write(str);
//				pw.flush();
//				pw.close();
//			}else{
//				params.put("message", "商家录取操作失败");
//				params.put("code", "500");
//				PrintWriter pw = response.getWriter();
//				Gson g = new Gson();
//				String str = g.toJson(params); 
//				pw.write(str);
//				pw.flush();
//				pw.close();
//			}
//
//			//------------------访问限制--------开始----------------------
//		}else{
//			params.put("message", "无效访问");
//			params.put("code", "404");
//			PrintWriter pw = response.getWriter();
//			Gson g = new Gson();
//			String str = g.toJson(params); 
//			pw.write(str);
//			pw.flush();
//			pw.close();
//		}
//		//------------------访问限制--------结束----------------------
//	}
//}
