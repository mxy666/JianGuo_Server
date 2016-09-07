package com.jianguo.servlet.job;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_hobby_type_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_label_Bean;
import com.jianguo.sql.T_hobby_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_job_label_Sql;
import com.jianguo.sql.T_job_model_Sql;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush_job;
import com.jianguo.util.Jdpushcc_job;
import com.jianguo.util.Jdpusher_job;

public class T_Job_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_Job_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_Job_Insert_Servlet?only=181E2CCAE710259E09F0135325E28E28&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_Job_Insert_Servlet---");
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
		
		String max =request.getParameter("max");
//		String girl_count =request.getParameter("girl_count");
		String girl_sum =request.getParameter("girl_sum");
		
		
		
		String job_model =request.getParameter("job_model");
		
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
			
//			String str_girl_count = "";
			String str_girl_sum = "";
			if(girl_sum == "" || girl_sum == null){
//				str_girl_count = "0";
				str_girl_sum = "0";
			}else{
//				str_girl_count = girl_count;
				str_girl_sum = girl_sum;
			}
			
			int ii = 0;
			if(job_model.equals("0")){
				ii = T_job_Sql.insert(city_id,aera_id,type_id, merchant_id, name, name_image, ss, sss, address, mode,money, term, limit_sex, "0", sum, ly_time, "0","1",alike,ly_time2,"1","0","0",ss2,max,"0",str_girl_sum,"0");
			}else{
				ii = T_job_Sql.insert(city_id,aera_id,type_id, merchant_id, name, name_image, ss, sss, address, mode,money, term, limit_sex, "0", sum, ly_time, "1","1",alike,ly_time2,"1","1","0",ss2,max,"0",str_girl_sum,"0");
			}
			
			if(ii == 1){
				T_job_Bean t_job = T_job_Sql.select_regedit_time(ly_time);
				T_job_info_Sql.insert(t_job.getId()+"",tel, address, lon, lat, ss, sss, ss2,sss2, set_place, set_time, limit_sex, term, other, work_content, work_require);
				if(job_model.equals("0")){
				}else{
					T_job_model_Sql.insert(job_model, merchant_id, t_job.getId()+"");
				}
				
				//----------------兼职标签---------------
				if((json_limit!=null&&!json_limit.equals(""))||(json_welfare!=null&&!json_welfare.equals(""))
						||(json_label!=null&&!json_limit.equals(""))){
					
					Gson g_limit = new Gson();
					Gson g_welfare = new Gson();
					Gson g_label = new Gson();
					
					String t_limit = "";
					String t_welfare = "";
					String t_label = "";
					if(json_limit!=null&&!json_limit.equals("")){
						user_limit list_limit = g_limit.fromJson(json_limit, user_limit.class);
						for (int i = 0; i < list_limit.getList_t_limit().size(); i++) {
							t_limit += list_limit.getList_t_limit().get(i)+",";
						}
					}
					
					//T_job_label_Sql.insert(t_job.getId()+"", t_limit, "", "");
					
					
					if(json_welfare!=null&&!json_welfare.equals("")){
						user_welfare list_welfare = g_welfare.fromJson(json_welfare, user_welfare.class);
						
						for (int i = 0; i < list_welfare.getList_t_welfare().size(); i++) {
							t_welfare += list_welfare.getList_t_welfare().get(i)+",";
							//T_job_label_Sql.update_welfare(t_welfare, t_job.getId()+"");
						}
					}
					
						
					if(json_label!=null&&!json_limit.equals("")){
						user_label list_label = g_label.fromJson(json_label, user_label.class);
						
						for (int i = 0; i < list_label.getList_t_label().size(); i++) {
							t_label += list_label.getList_t_label().get(i)+",";
							//T_job_label_Sql.update_label(t_label, t_job.getId()+"");
						}
					}
					T_job_label_Sql.insert(t_job.getId()+"", t_limit, t_welfare, t_label);
					System.out.println("1111111111111111111111");
					
				
					//查询标签返回
					T_job_label_Bean t_job_label = T_job_label_Sql.select_job_id(t_job.getId()+"");
					String [] st_limit=t_job_label.getLimits().split(",");//限制
					List<String> list_limit2 = new ArrayList<String>();
					for (int i = 0; i < st_limit.length; i++) {
						list_limit2.add(st_limit[i]);
					}
					String[] st_welfare=t_job_label.getWelfare().split(",");//福利
					List<String> list_welfare2 = new ArrayList<String>();
					for (int i = 0; i < st_welfare.length; i++) {
						list_welfare2.add(st_welfare[i]);
					}
					String[] st_label=t_job_label.getWelfare().split(",");//标签
					List<String> list_label2 = new ArrayList<String>();
					for (int i = 0; i < st_label.length; i++) {
						list_label2.add(st_label[i]);
					}
					t_job.setLimit_name(list_limit2);
					//t_job.setLimit_name(list_label2);
					t_job.setWelfare_name(list_welfare2);
					t_job.setLabel_name(list_label2);
					
				}

				//----------------兼职标签---------------
				
				
				
				Map map = new HashMap();
				//map.put("t_job", t_job);

				params.put("data", map);
				params.put("message", "兼职信息录入成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
				

				//---------------兼职偏好--------------------

				Calendar  c =  Calendar.getInstance();  
//		        Date date = new Date(); // 取当前时间  
				int iii = Integer.parseInt(ss2);
				long oo = iii*1000L;
		        Date date = new Date(oo);  // 取固定时间  
		        System.out.println("当前时间："+date);  
		        c.setTime(date);  //当时间set 进calendar 里面  
		        int i = c.get(Calendar.DAY_OF_WEEK);  //取星期  
		        System.out.println("星期几："+i);
		        int i_xingqi = 0;
		        if(i == 1){
		        	i_xingqi = 7;
		        }else{
		        	i_xingqi = i-1;
		        }
		        
		        String sd = sdf2.format(new Date(Long.parseLong(ss2+"100")));
				String s = new String(sd);   
				String a[] = s.split(" ");
				String ssss = new String(a[1]);   
				String aaaa[] = ssss.split(":");
				
				int i_aaaa = Integer.parseInt(aaaa[0]);
				int i_shangxia = 0;
				if(i_aaaa > 6 && i_aaaa <12){
					i_shangxia = 1;
				}else if(i_aaaa > 13 && i_aaaa <18){
					i_shangxia = 2;
				}else if(i_aaaa > 19 && i_aaaa <5){
					i_shangxia = 3;
				}

				//---------------兼职偏好--------------------
			
			}else{
				params.put("message", "兼职信息录入失败");
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
	
	private class user_limit{
		private List<String> list_t_limit;
		public void setList_t_limit(List<String> list_t_limit) {
			this.list_t_limit = list_t_limit;
		}
		public List<String> getList_t_limit() {
			return list_t_limit;
		}
	}
	
	private class user_welfare{
		private List<String> list_t_welfare;
		public void setList_t_welfare(List<String> list_t_welfare) {
			this.list_t_welfare = list_t_welfare;
		}
		public List<String> getList_t_welfare() {
			return list_t_welfare;
		}
	}
	
	private class user_label{
		private List<String> list_t_label;
		public void setList_t_label(List<String> list_t_label) {
			this.list_t_label = list_t_label;
		}
		public List<String> getList_t_label() {
			return list_t_label;
		}
	}
	
}
