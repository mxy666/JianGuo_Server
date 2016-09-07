package com.jianguo.servlet.job_info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.util.Frequently;

public class T_Job_info_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_Job_info_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_Job_info_Insert_Servlet?only=181E2CCAE710259E09F0135325E28E28&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_Job_info_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String job_id =request.getParameter("job_id");
		String tel =request.getParameter("tel");
		String address =request.getParameter("address");
		String lon =request.getParameter("lon");
		String lat =request.getParameter("lat");
		String start_date =request.getParameter("start_date");
		String stop_date =request.getParameter("stop_date");
		String start_time =request.getParameter("start_time");
		String stop_time =request.getParameter("stop_time");
		String set_place =request.getParameter("set_place");
		String set_time =request.getParameter("set_time");
		String limit_sex =request.getParameter("limit_sex");
		String term =request.getParameter("term");
		String other =request.getParameter("other");
		String work_content =request.getParameter("work_content");
		String work_require =request.getParameter("work_require");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------
			
			int i = T_job_info_Sql.insert(job_id,tel, address, lon, lat, start_date, stop_date, start_time,stop_time, set_place, set_time, limit_sex, term, other, work_content, work_require);
			
			if(i == 1){
				params.put("message", "兼职详情信息录入成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				params.put("message", "兼职详情信息录入失败");
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
}
