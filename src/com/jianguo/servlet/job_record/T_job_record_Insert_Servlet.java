//package com.jianguo.servlet.job_record;
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
//import com.jianguo.bean.T_enroll_limit_Bean;
//import com.jianguo.bean.T_job_Bean;
//import com.jianguo.bean.T_merchant_Bean;
//import com.jianguo.mersql.T_enroll_Sql;
//import com.jianguo.mersql.T_enroll_limit_Sql;
//import com.jianguo.mersql.T_job_Sql;
//import com.jianguo.mersql.T_merchant_Sql;
//import com.jianguo.util.Frequently;
//import com.jianguo.util.Jdpush;
//import com.jianguo.util.Jdpush_shang;
//import com.jianguo.util.Jdpushcc;
//import com.jianguo.util.Jdpushcc_shang;
//import com.jianguo.util.Jdpusher;
//import com.jianguo.util.Jdpusher_shang;
//
//public class T_job_record_Insert_Servlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	public T_job_record_Insert_Servlet() {
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
//	//http://192.168.1.233/JianGuo_Server/T_job_record_Insert_Servlet?only=26F9FD590B0EFE6D69217B6588AA0B13&login_id=20&job_id=69
//	//http://101.200.205.243:8080/T_job_record_Insert_Servlet?only=26F9FD590B0EFE6D69217B6588AA0B13&login_id=20&job_id=76
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//		System.out.println("---T_job_record_Insert_Servlet---");
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		Map params =  new HashMap();
//		
//		String login_id =request.getParameter("login_id");
//		String job_id =request.getParameter("job_id");
//		
//		//------------------访问限制--------开始----------------------
//		String only =request.getParameter("only");
//		String ss_only = Frequently.daycount();
//		String ss_only2 = Frequently.daycount2();
//		String ss_only3 = Frequently.daycount3();
//		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
//			//------------------访问限制--------结束----------------------
//
//			
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
