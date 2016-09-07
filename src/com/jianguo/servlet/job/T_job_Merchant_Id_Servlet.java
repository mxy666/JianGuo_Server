package com.jianguo.servlet.job;

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
import com.jianguo.sql.T_job_Sql;
import com.jianguo.util.Frequently;

public class T_job_Merchant_Id_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_Merchant_Id_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_Merchant_Id_Servlet?only=DB0A8D73654EC9CE1C172B95E84F3501&merchant_id=45
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_Merchant_Id_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String merchant_id =request.getParameter("merchant_id");
		String count =request.getParameter("count");
		//		term,limit_sex,count,sum,regedit_time,status
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			List<T_job_Bean> list_t_job = T_job_Sql.select_merchant_id(count,merchant_id);

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
