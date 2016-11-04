package com.jianguo.servlet.job;

import com.google.gson.Gson;
import com.jianguo.bean.JpushBean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_job_label_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.JdpushUtil;
import com.jianguo.util.JpushUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T_job_UpdateStatus_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_UpdateStatus_Servlet() {
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
//		System.out.println("---T_job_Update_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String id = request.getParameter("id");
		String name = request.getParameter("name");

		//下架处理
		T_job_Sql.update_status("6",id);

		JpushBean bean = new JpushBean();
		Map<String,String> map = new HashMap<>();
		bean.setType(String.valueOf(1));
		bean.setUsername(name);
		map.put("username",name);
		map.put("type",bean.getType());

		JdpushUtil.sendPush(bean);

		//------------------访问限制--------结束----------------------
	}
	
//	private class limit{
//		private List<String> list_t_limit;
//		public void setList_t_limit(List<String> list_t_limit) {
//			this.list_t_limit = list_t_limit;
//		}
//		public List<String> getList_t_limit() {
//			return list_t_limit;
//		}
//	}
//
//	private class welfare{
//		private List<String> list_t_welfare;
//		public void setList_t_welfare(List<String> list_t_welfare) {
//			this.list_t_welfare = list_t_welfare;
//		}
//		public List<String> getList_t_welfare() {
//			return list_t_welfare;
//		}
//	}
//
//	private	class label{
//		private List<String> list_t_label;
//		public void setList_t_label(List<String> list_t_label) {
//			this.list_t_label = list_t_label;
//		}
//		public List<String> getList_t_label() {
//			return list_t_label;
//		}
//
//
//	}
	}
