package com.jianguo.servlet.enroll;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_enroll_limit_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_enroll_limit_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush;
import com.jianguo.util.Jdpush_shang;
import com.jianguo.util.Jdpushcc;
import com.jianguo.util.Jdpushcc_shang;
import com.jianguo.util.Jdpusher;
import com.jianguo.util.Jdpusher_shang;

public class T_enroll_limit_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_enroll_limit_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_enroll_limit_Servlet?only=D676F8B429AC1D11E2F0BE13493190AE&login_id=2&job_id=1
	//http://101.200.205.243:8080/T_enroll_limit_Servlet?only=7A236101E0989874B45982C0CFE224C2&login_id=20&job_id=13
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_enroll_limit_Servlet---");
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

			SimpleDateFormat sdf2 = new SimpleDateFormat("MMdd");
			String ly_time2 = sdf2.format(new java.util.Date());
			
			if(T_enroll_limit_Sql.check(login_id)){
				T_enroll_limit_Bean t_enroll_limit = T_enroll_limit_Sql.select_login_id(login_id);
				if(t_enroll_limit.getCount() >= 3 && t_enroll_limit.getDate()+"" == ly_time2){
					params.put("message", "今日报名已达上限");
					params.put("code", "500");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					T_enroll_limit_Bean t_enroll_limit2 = T_enroll_limit_Sql.select_login_id(login_id);
					if(t_enroll_limit2.getDate()+"" != (ly_time2)){
						T_enroll_limit_Sql.update_count0(ly_time2,login_id);
					}else{
					T_enroll_limit_Sql.update_count(ly_time2,login_id);
					}
					params.put("message", "今日报名已达上限");
					params.put("code", "500");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}
			}else{
				T_enroll_limit_Sql.insert(login_id, "1", ly_time2);
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
