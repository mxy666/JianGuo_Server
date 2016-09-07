package com.jianguo.servlet.job;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.util.Frequently;

public class T_job_Merchant_Id_Zhong_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_Merchant_Id_Zhong_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.135/JianGuo_Server/T_job_Merchant_Id_Zhong_Servlet?only=2BB738B37411A5959AF92B747281FDB1&&merchant_id=13&count=0&status=1
	//http://101.200.205.243:8080/T_job_Merchant_Id_Zhong_Servlet?only=2F1B272F4828B2ED5F89E1FE120AD0A8&merchant_id=2&count=0&status=1
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_Merchant_Id_Zhong_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String merchant_id =request.getParameter("merchant_id");
		String count =request.getParameter("count");
		String status =request.getParameter("status");
		//		term,limit_sex,count,sum,regedit_time,status
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			
			
			if(status.equals("1")){
				List<T_job_Bean> list_t_job2 = T_job_Sql.select_merchant_id_zhong(merchant_id,count);
				
				List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();
				for (int i = 0; i < list_t_job2.size(); i++) {
					T_job_Bean t = list_t_job2.get(i);
					T_job_Bean t_job = T_job_Sql.select_id(t.getId()+"");
					t_job.setModel_name("0");
					
//					long ll = System.currentTimeMillis()/1000;
//					int ii = Integer.parseInt(t_job.getStart_date());
//					if(ll <= ii){
//						T_job_Sql.update_status("2", t_job.getId()+"");
//					}
					
//					List<T_enroll_Bean> list_t_enroll = T_enroll_Sql.select_job_id_status2_all(t.getId()+"", "3","5","8","9","10","11","12","13");
//					t_job.setCount(list_t_enroll.size());
					T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
					t_job.setMerchant_id_name(t_merchant.getName());
					
					t_job.setRemarks("暂无备注");
					list_t_job.add(t_job);
				}
			
				Map map = new HashMap();
				map.put("list_t_job", list_t_job);

				params.put("data", map);
				params.put("message", "兼职信息查询成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else if(status.equals("0")){
				List<T_job_Bean> list_t_job2 = T_job_Sql.select_merchant_id_no_zhong(merchant_id,count);
				List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();
				for (int i = 0; i < list_t_job2.size(); i++) {
					T_job_Bean t = list_t_job2.get(i);
					T_job_Bean t_job = T_job_Sql.select_id(t.getId()+"");
					t_job.setModel_name("0");
					
//					long ll = System.currentTimeMillis()/1000;
//					int ii = Integer.parseInt(t_job.getStart_date());
//					if(ll <= ii){
//						T_job_Sql.update_status("2", t_job.getId()+"");
//					}
//					List<T_enroll_Bean> list_t_enroll = T_enroll_Sql.select_job_id_status2_all(t.getId()+"", "3","5","8","9","10","11","12","13");
//						t_job.setCount(list_t_enroll.size());
					T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
					t_job.setMerchant_id_name(t_merchant.getName());
					
					t_job.setRemarks("暂无备注");
					list_t_job.add(t_job);
				}
				
				Map map = new HashMap();
				map.put("list_t_job", list_t_job);

				params.put("data", map);
				params.put("message", "兼职信息查询成功");
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
