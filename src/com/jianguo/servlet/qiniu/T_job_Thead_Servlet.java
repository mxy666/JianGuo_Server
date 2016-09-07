package com.jianguo.servlet.qiniu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.squareup.okhttp.MediaType;

public class T_job_Thead_Servlet extends HttpServlet {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private static final long serialVersionUID = 1L;
	public T_job_Thead_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_Thead_Servlet?only=64D78679E0E12A7875F052650905B6D3
	//http://101.200.205.243:8080/T_job_Thead_Servlet
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_Thead_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		MyThead myThead = new MyThead();  
		Thread thread = new Thread(myThead);  
		thread.start();
		
		MyThead2 myThead2 = new MyThead2();  
		Thread thread2 = new Thread(myThead2);  
		thread2.start();
		
		params.put("message", "¿ªÊ¼¡£¡£¡£");
		params.put("code", "200");
		PrintWriter pw = response.getWriter();
		Gson g = new Gson();
		String str = g.toJson(params); 
		pw.write(str);
		pw.flush();
		pw.close();
	}
	
	class MyThead implements Runnable  
	{  
		public void run()  
		{  
			try  
			{  for (int i = 0; i <999999999; i++) {
				Thread.sleep(600000); 
				
				List<T_job_Bean> list = T_job_Sql.select_all_status_wai();
				for (int j = 0; j < list.size(); j++) {
					T_job_Bean t_job = list.get(j);
					long ll = System.currentTimeMillis()/1000;
					T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_job.getId()+"");
					int ii = Integer.parseInt(t_job_info.getStart_time());
//					int ii2 = Integer.parseInt(t_job_info.getStop_time());
					if(ll > ii){
						T_enroll_Sql.update_status00("2", t_job.getId()+"");
						T_enroll_Sql.update_status("8", t_job.getId()+"");
						T_job_Sql.update_status("2", t_job.getId()+"");
					}
//					if(ll > ii2){
//						T_job_Sql.update_status("3", t_job.getId()+"");
//						T_enroll_Sql.update_statusww("9", t_job.getId()+"");
//					}
				}
			}
			}  
			catch (InterruptedException e)  
			{  
			}  
			
		}  
	}  
	
	class MyThead2 implements Runnable  
	{  
		public void run()  
		{  
			try  
			{  for (int i = 0; i <999999999; i++) {
				Thread.sleep(600000); 
				
				List<T_job_Bean> list = T_job_Sql.select_all_status_wai();
				for (int j = 0; j < list.size(); j++) {
					T_job_Bean t_job = list.get(j);
					long ll = System.currentTimeMillis()/1000;
					T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_job.getId()+"");
//					int ii = Integer.parseInt(t_job_info.getStart_time());
					int ii2 = Integer.parseInt(t_job_info.getStop_time());
//					if(ll > ii){
//						T_enroll_Sql.update_status00("2", t_job.getId()+"");
//						T_enroll_Sql.update_status("8", t_job.getId()+"");
//						T_job_Sql.update_status("2", t_job.getId()+"");
//					}
					if(ll > ii2){
//						List<T_enroll_Bean> list_t_enroll = T_enroll_Sql.select_job_id_status2(t_job.getId()+"", "5","5","8","9","10","11","12","13","0");
//						if(list_t_enroll.size() == 0){
//							T_job_Sql.update_status("5", t_job.getId()+"");
//						}else{
							T_job_Sql.update_status("3", t_job.getId()+"");
//						}
						T_enroll_Sql.update_status("9", t_job.getId()+"");
						T_enroll_Sql.update_statusss("9", t_job.getId()+"");
						
					}
				}
			}
			}  
			catch (InterruptedException e)  
			{  
			}  
			
		}  
	}  

}
