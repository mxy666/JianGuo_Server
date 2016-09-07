package com.jianguo.servlet.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_area_Bean;
import com.jianguo.bean.T_city_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_type_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_school_Sql;

public class Html_Job_Id_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Html_Job_Id_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/Html_Job_Id_Servlet?job_id=8
	//http://101.200.205.243:8080/T_job_Id_Servlet?only=43803E576A53ECF4D30D058541BBFA4E&merchant_id=1&type=0&count=0
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---Html_Job_Id_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String job_id =request.getParameter("job_id");
//System.out.println("-----------"+job_id+"------");
			T_job_Bean t_job = T_job_Sql.select_id(job_id);

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

			if(!t_job.getAlike().equals("0")){
				T_job_Bean t_job2 = T_job_Sql.select_alike(t_job.getAlike());
				t_job.setNv_job_id(t_job2.getId()+"");
				t_job.setNv_sum(t_job2.getSum()+"");
				t_job.setNv_count(t_job2.getCount()+"");
			}
			
			if(t_job.getInfo_term().equals("0")){
				t_job.setInfo_term("月");
			}else if(t_job.getInfo_term().equals("1")){
				t_job.setInfo_term("周");
			}else if(t_job.getInfo_term().equals("2")){
				t_job.setInfo_term("日");
			}else if(t_job.getInfo_term().equals("3")){
				t_job.setInfo_term("小时");
			}else if(t_job.getInfo_term().equals("4")){
				t_job.setInfo_term("次");
			}else if(t_job.getInfo_term().equals("5")){
				t_job.setInfo_term("义工");
			}
			
			if(t_job.getInfo_limit_sex().equals("0")){
				t_job.setInfo_limit_sex("只招女");
			}else if(t_job.getInfo_limit_sex().equals("1")){
				t_job.setInfo_limit_sex("只招男");
			}else if(t_job.getInfo_limit_sex().equals("2")){
				t_job.setInfo_limit_sex("男女不限");
			}
			
			if(t_job.getInfo_other().equals("null") || t_job.getInfo_other().equals(null)){
				t_job.setInfo_other("无");
			}
			
			int i_start_date = Integer.parseInt(t_job.getStart_date());
			int i_stop_date = Integer.parseInt(t_job.getStop_date());
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
			String s_start_date = sdf.format(new Date(i_start_date*1000L));
			String s_stop_date = sdf.format(new Date(i_stop_date*1000L));
			
			String s = new String(t_job.getRegedit_time());   
	        String a[] = s.split(" ");

			request.setAttribute("job_id", job_id);
			request.setAttribute("city_name", t_job.getCity_id_name());
			request.setAttribute("name", t_job.getName());
			request.setAttribute("name_image", t_job.getName_image());
			request.setAttribute("money", t_job.getMoney());
			request.setAttribute("count", t_job.getCount());
			request.setAttribute("sum", t_job.getSum());
			request.setAttribute("regedit_time", a[0]);
			request.setAttribute("address", t_job.getAddress());
			request.setAttribute("start_date", s_start_date);
			request.setAttribute("stop_date", s_stop_date);
			request.setAttribute("info_set_place", t_job.getInfo_set_place());
			request.setAttribute("info_set_time", t_job.getInfo_set_time());
			request.setAttribute("info_limit_sex", t_job.getInfo_limit_sex());
			request.setAttribute("info_term", t_job.getInfo_term());
			request.setAttribute("info_other", t_job.getInfo_other()+"");
			request.setAttribute("info_work_content", t_job.getInfo_work_content());
			request.setAttribute("info_work_require", t_job.getInfo_work_require());
			request.setAttribute("merchant_id_name", t_job.getMerchant_id_name());
			request.getRequestDispatcher("details.jsp").forward(request, response);
	}
}
