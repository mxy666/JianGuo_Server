package com.jianguo.servlet.job;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.Job_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_job_label_Sql;
import com.jianguo.util.Frequently;

import org.apache.log4j.Logger;

public class T_job_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_Update_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_Update_Servlet?only=181E2CCAE710259E09F0135325E28E28&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_Update_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String city_id =request.getParameter("city_id");
		String aera_id =request.getParameter("aera_id");
		String type_id =request.getParameter("type_id");
		String merchant_id =request.getParameter("merchant_id");
		String name =request.getParameter("name");
		String name_image =request.getParameter("name_image");
		String start_date =request.getParameter("start_date");
		String stop_date =request.getParameter("stop_date");
		String address =request.getParameter("address");
		String mode =request.getParameter("mode");
		String money =request.getParameter("money");
		String term =request.getParameter("term");
		String limit_sex =request.getParameter("limit_sex");
		String sum =request.getParameter("sum");
		String hot =request.getParameter("hot");
		String alike =request.getParameter("alike");
		String reg_date =request.getParameter("reg_date");
		String girl_sum =request.getParameter("girl_sum");
		
		String lon =request.getParameter("lon");
		String lat =request.getParameter("lat");
		String tel =request.getParameter("tel");
		String start_time =request.getParameter("start_time");
		String stop_time =request.getParameter("stop_time");
		String set_place =request.getParameter("set_place");
		String set_time =request.getParameter("set_time");
		String other =request.getParameter("other");
		String work_content =request.getParameter("work_content");
		String work_require =request.getParameter("work_require");
		
//		String job_model =request.getParameter("job_model");
		String job_id =request.getParameter("job_id");
		
		String json_limit =request.getParameter("json_limit");
		String json_welfare =request.getParameter("json_welfare");
		String json_label =request.getParameter("json_label");
		
		System.out.println(json_limit+"---------------------1");
		System.out.println(json_welfare+"---------------------2");
		System.out.println(json_label+"---------------------3");
		
		System.out.println("city_id  "+city_id);
		System.out.println("aera_id  "+aera_id);
		System.out.println("type_id  "+type_id);
		System.out.println("merchant_id  "+merchant_id);
		System.out.println("name  "+name);
		System.out.println("name_image  "+name_image);
		System.out.println("start_date  "+start_date);
		System.out.println("stop_date  "+stop_date);
		System.out.println("address  "+address);
		System.out.println("mode  "+mode);
		System.out.println("money  "+money);
		System.out.println("term  "+term);
		System.out.println("limit_sex  "+limit_sex);
		System.out.println("sum  "+sum);
		System.out.println("hot  "+hot);
		System.out.println("alike  "+alike);
		System.out.println("lon  "+lon);
		System.out.println("lat  "+lat);
		System.out.println("tel  "+tel);
		System.out.println("start_time  "+start_time);
		System.out.println("stop_time  "+stop_time);
		System.out.println("set_place  "+set_place);
		System.out.println("set_time  "+set_time);
		System.out.println("other  "+other);
		System.out.println("work_content  "+work_content);
		System.out.println("work_require  "+work_require);
		Logger logger = Logger.getLogger("log");
		logger.info("修改兼职");
		logger.info("T_job_Update_Servlet!");
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			String ly_time = sdf.format(new java.util.Date());
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ly_time2 = sdf2.format(new java.util.Date());

			String ss = "";
			if(start_date.length() == 13 ){
				ss = start_date.substring(0,10);
			}else{
				ss = start_date;
			}
			
			String sss = "";
			if(stop_date.length() == 13 ){
				sss = stop_date.substring(0,10);
			}else{
				sss = stop_date;
			}
			
			String ss2 = "";
			if(start_time.length() == 13){
				ss2 = start_time.substring(0,10);
			}else{
				ss2 = start_time;
			}
			
			String sss2 = "";
			if(stop_time.length() == 13){
				sss2 = stop_time.substring(0,10);
			}else{
				sss2 = stop_time;
			}
			String str_girl_sum = "";
			if(girl_sum == "" || girl_sum == null){
				str_girl_sum = "0";
			}else{
				str_girl_sum = girl_sum;
			}
			
			int i = T_job_Sql.update_all(city_id,aera_id,type_id, merchant_id, name, name_image, ss, sss, address, mode,money, term, limit_sex, sum, ly_time, "0","1",alike,"1","0",ss2,str_girl_sum,job_id);
			if(i == 1){
				T_job_Bean t_job = null;
				try {
				 t_job = Job_Sql.selectJobInfoById(job_id);
					T_job_info_Sql.update_all(tel, address, lon, lat, ss, sss, ss2, sss2, set_place, set_time, limit_sex, term, other, work_content, work_require,job_id);
					logger.info("t_job.getId()="+t_job.getId()+"___job_id="+job_id);
				} catch (SQLException e) {
					logger.error("error:"+e.getMessage());
					e.printStackTrace();
				}
				Gson gson = new Gson();
							String t_limit = "";
							String t_welfare = "";
							String t_label = "";
							if(json_limit!=null&&!json_limit.equals("")){
								limit list_limit = gson.fromJson(json_limit, limit.class);
								for (int j = 0; j < list_limit.getList_t_limit().size(); j++) {
									t_limit += list_limit.getList_t_limit().get(j)+",";
								}
							}
							if(json_welfare!=null&&!json_welfare.equals("")){
								welfare list_welfare = gson.fromJson(json_welfare, welfare.class);
								
								for (int k = 0;k < list_welfare.getList_t_welfare().size(); k++) {
									t_welfare += list_welfare.getList_t_welfare().get(k)+",";
								}
							}
							if(json_label!=null&&!json_label.equals("")){
								label list_label = gson.fromJson(json_label, label.class);
								for (int n = 0; n < list_label.getList_t_label().size(); n++) {
									t_label += list_label.getList_t_label().get(n)+",";
								}
							}
							T_job_label_Sql.updateLable(t_limit, t_welfare, t_label,t_job.getId()+"");

				params.put("message", "兼职信息修改成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();	
			}else{
				params.put("message", "兼职信息修改失败");
				params.put("code", "500");
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
	
	private class limit{
		private List<String> list_t_limit;
		public void setList_t_limit(List<String> list_t_limit) {
			this.list_t_limit = list_t_limit;
		}
		public List<String> getList_t_limit() {
			return list_t_limit;
		}
	}
	
	private class welfare{
		private List<String> list_t_welfare;
		public void setList_t_welfare(List<String> list_t_welfare) {
			this.list_t_welfare = list_t_welfare;
		}
		public List<String> getList_t_welfare() {
			return list_t_welfare;
		}
	}
	
	private	class label{
		private List<String> list_t_label;
		public void setList_t_label(List<String> list_t_label) {
			this.list_t_label = list_t_label;
		}
		public List<String> getList_t_label() {
			return list_t_label;
		}
	
	
}
	}
