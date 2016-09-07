package com.jianguo.servlet.merchant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_job_record_Bean;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.sql.PigeonSql;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_record_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;

public class T_UserPigeon_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_UserPigeon_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 商家端标记鸽子接口
	 * 	http://192.168.1.135/JianGuo_Server/T_UserPigeon_Servlet?only=583C73324BE65EC30E5056CA4821E4FD&login_id=1&job_id=3
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_UserPigeon_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		String job_id =request.getParameter("job_id");
		String login_id =request.getParameter("login_id");
		String merID =request.getParameter("merchant_id");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			Map map = new HashMap();
			//查询是否被标记过
			boolean flag=PigeonSql.queryByID(login_id,job_id);
			if(flag){
				params.put("message", "您已标记过");
				params.put("code", "500");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				int i=PigeonSql.update_UserPigeon(login_id);//更新鸽子数
				
				if(i==1){
					int n=PigeonSql.addPigeon(login_id,job_id, merID);					
					T_enroll_Sql.update_status("2",login_id, job_id);//商家端标记鸽子后，更新用户的状态未取消状态					
					T_user_info_Bean user_info=PigeonSql.selectByLogin_id(login_id);								
					T_enroll_Bean t_enroll2 = T_enroll_Sql.select_login_id_job_time(login_id, job_id);					
					user_info.setTime_job(t_enroll2.getLogin_time());//报兼职的时间					
					user_info.setUser_status(t_enroll2.getStatus());//用户是否完成兼职或
					
					T_job_record_Bean t_job_record = T_job_record_Sql.select_login_id(login_id);
					user_info.setComplete_job(t_job_record.getComplete());
					user_info.setCancel_job(t_job_record.getCancel());

										
				 	map.put("user_info", user_info);
				 	
				 	params.put("data", map);
					params.put("message", "标记成功");
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					params.put("message", "标记失败");
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
