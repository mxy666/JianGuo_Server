package com.jianguo.servlet.pc.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jianguo.bean.T_job_wai_Bean;
import com.jianguo.sql.T_job_wai_Sql;

public class T_job_wai_List_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_job_wai_List_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_job_wai_List_Servlet?only=E374361DAB8120FCE148EDAC7E174C8F&hot=0
	//http://101.200.205.243:8080/T_job_wai_List_Servlet?only=13863CB6975269B4276A2682B6E32786&hot=0&count=10
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_job_wai_List_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		 Logger logger = Logger.getLogger("log");
		   logger.info("日志信息开始!");
		   logger.info("T_job_wai_List_Servlet!");
		String job_id =request.getParameter("job_id");
		 logger.info(job_id);
		List<T_job_wai_Bean> list_t_job_wai = T_job_wai_Sql.select_job_id(job_id);

		List<T_job_wai_Bean> list_t_job_wai2 = new ArrayList<T_job_wai_Bean>();
		for (int i = 0; i < list_t_job_wai.size(); i++) {
			T_job_wai_Bean t_job_wai = list_t_job_wai.get(i);
			if(t_job_wai.getSex() == 1){
				t_job_wai.setS_sex("男");
			}else if(t_job_wai.getSex() == 0){
				t_job_wai.setS_sex("女");
			}
			list_t_job_wai2.add(t_job_wai);
		}
		
			request.setAttribute("job_id", job_id);
			request.setAttribute("list_t_job_wai", list_t_job_wai2);
			request.getRequestDispatcher("job_wai_list.jsp").forward(request, response);
	}
}
