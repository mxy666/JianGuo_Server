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
import com.jianguo.bean.T_area_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.bean.T_job_label_Bean;
import com.jianguo.bean.T_job_model_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_type_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_job_label_Sql;
import com.jianguo.sql.T_job_model_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_school_Sql;
import com.jianguo.util.Frequently;

public class T_job_Model_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_Model_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.135/JianGuo_Server/T_job_Model_List_Servlet?only=F26B1357113C9537928EBB728B3055C0&merchant_id=4&type=1&count=10
	//http://101.200.205.243:8080/T_job_Model_List_Servlet?only=4ED25206B4FAE5C0BB41FB53FB6F22D7&merchant_id=2&type=1&count=0
	//type=1模板 0历史记录
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_Model_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String merchant_id =request.getParameter("merchant_id");
		String type =request.getParameter("type");
		String count =request.getParameter("count");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			if(type.equals("0")){
				System.out.println(merchant_id+"--------");
				List<T_job_Bean> list_t_job2 = T_job_Sql.select_merchant_id(merchant_id,count);
				
				List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();
				for (int j = 0; j < list_t_job2.size(); j++) {
					T_job_Bean t = list_t_job2.get(j);
					T_job_Bean t_job = T_job_Sql.select_id(t.getId()+"");
					t_job.setModel_name("0");
					
					T_city_Bean t_city = T_school_Sql.select_t_city_id(t_job.getCity_id()+"");
					t_job.setCity_id_name(t_city.getCity());
					
					T_area_Bean t_area = T_school_Sql.select_t_area_id(t_job.getArea_id()+"");
					t_job.setArea_id_name(t_area.getArea_name());
					
					T_type_Bean t_type = T_school_Sql.select_t_type_id(t_job.getType_id()+"");
					t_job.setType_id_name(t_type.getType_name());
					
					T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
					t_job.setMerchant_id_name(t_merchant.getName());
					
					T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_job.getId()+"");
					t_job.setInfo_start_time(t_job_info.getStart_time());
					t_job.setInfo_stop_time(t_job_info.getStop_time());
					t_job.setInfo_set_place(t_job_info.getSet_place());
					t_job.setInfo_set_time(t_job_info.getSet_time());
					t_job.setInfo_limit_sex(t_job_info.getLimit_sex()+"");
					t_job.setInfo_term(t_job_info.getTerm()+"");
					t_job.setInfo_other(t_job_info.getOther()+"");
					t_job.setInfo_work_content(t_job_info.getWork_content()+"");
					t_job.setInfo_work_require(t_job_info.getWork_require()+"");
					t_job.setInfo_tel(t_job_info.getTel());
					
					T_job_label_Bean t_job_label = T_job_label_Sql.select_job_id(t_job.getId()+"");
					List<String> list_limit = new ArrayList<String>();
					if(t_job_label.getLimits() == "" || t_job_label.getLimits() == null){
					}else{
						String [] st_limit=t_job_label.getLimits().split(",");
						for (int i = 0; i < st_limit.length; i++) {
							list_limit.add(st_limit[i]);
						}
					}
					List<String> list_welfare = new ArrayList<String>();
					if(t_job_label.getWelfare() == "" || t_job_label.getWelfare() == null){
					}else{
						String[] st_welfare=t_job_label.getWelfare().split(",");
						for (int i = 0; i < st_welfare.length; i++) {
							list_welfare.add(st_welfare[i]);
						}
					}
					List<String> list_label = new ArrayList<String>();
					if(t_job_label.getLabel() == "" || t_job_label.getLabel() == null){
					}else{
						String[] st_label=t_job_label.getLabel().split(",");
						for (int i = 0; i < st_label.length; i++) {
							list_label.add(st_label[i]);
						}
					}
					t_job.setLimit_name(list_limit);
					t_job.setWelfare_name(list_welfare);
					t_job.setLabel_name(list_label);
					
					list_t_job.add(t_job);
				}
				Map map = new HashMap();
				map.put("list_t_job", list_t_job);
				
				params.put("data", map);
				params.put("message", "历史兼职查询成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
				
			}else if(type.equals("1")){
				List<T_job_model_Bean> list_t_job_model = T_job_model_Sql.select_All_name(merchant_id,count);
				
				List<T_job_Bean> list_t_job = new ArrayList<T_job_Bean>();
				for (int j = 0; j < list_t_job_model.size(); j++) {
					T_job_model_Bean t_job_model = list_t_job_model.get(j);
					T_job_Bean t_job = T_job_Sql.select_id(t_job_model.getJob_id()+"");
					t_job.setModel_name(t_job_model.getModel_name());
					
					T_city_Bean t_city = T_school_Sql.select_t_city_id(t_job.getCity_id()+"");
					t_job.setCity_id_name(t_city.getCity());
					
					T_area_Bean t_area = T_school_Sql.select_t_area_id(t_job.getArea_id()+"");
					t_job.setArea_id_name(t_area.getArea_name());
					
					T_type_Bean t_type = T_school_Sql.select_t_type_id(t_job.getType_id()+"");
					t_job.setType_id_name(t_type.getType_name());
					
					T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
					t_job.setMerchant_id_name(t_merchant.getName());
					
					T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(t_job.getId()+"");
					t_job.setInfo_start_time(t_job_info.getStart_time());
					t_job.setInfo_stop_time(t_job_info.getStop_time());
					t_job.setInfo_set_place(t_job_info.getSet_place());
					t_job.setInfo_set_time(t_job_info.getSet_time());
					t_job.setInfo_limit_sex(t_job_info.getLimit_sex()+"");
					t_job.setInfo_term(t_job_info.getTerm()+"");
					t_job.setInfo_other(t_job_info.getOther()+"");
					t_job.setInfo_work_content(t_job_info.getWork_content()+"");
					t_job.setInfo_work_require(t_job_info.getWork_require()+"");
					t_job.setInfo_tel(t_job_info.getTel());
					
					T_job_label_Bean t_job_label = T_job_label_Sql.select_job_id(t_job.getId()+"");
					List<String> list_limit = new ArrayList<String>();
					if(t_job_label.getLimits() == "" || t_job_label.getLimits() == null){
					}else{
						String [] st_limit=t_job_label.getLimits().split(",");
						for (int i = 0; i < st_limit.length; i++) {
							list_limit.add(st_limit[i]);
						}
					}
					List<String> list_welfare = new ArrayList<String>();
					if(t_job_label.getWelfare() == "" || t_job_label.getWelfare() == null){
					}else{
						String[] st_welfare=t_job_label.getWelfare().split(",");
						for (int i = 0; i < st_welfare.length; i++) {
							list_welfare.add(st_welfare[i]);
						}
					}
					List<String> list_label = new ArrayList<String>();
					if(t_job_label.getLabel() == "" || t_job_label.getLabel() == null){
					}else{
						String[] st_label=t_job_label.getLabel().split(",");
						for (int i = 0; i < st_label.length; i++) {
							list_label.add(st_label[i]);
						}
					}
					t_job.setLimit_name(list_limit);
					t_job.setWelfare_name(list_welfare);
					t_job.setLabel_name(list_label);
					
					list_t_job.add(t_job);
				}
				Map map = new HashMap();
				map.put("list_t_job", list_t_job);
				
				params.put("data", map);
				params.put("message", "历史兼职查询成功");
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
