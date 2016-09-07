package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.servlet.pc.CZ_Excel;
import com.jianguo.sql.T_UserManage_Sql;

public class T_JobExport_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_JobExport_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 单人兼职导出
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_JobExport_Servlet---");
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String loginId =request.getParameter("loginId");
		String filePath=this.getServletConfig().getServletContext().getRealPath("/")+"downLoadFile\\Job.xls"; 
		List<T_job_Bean> job=new ArrayList<T_job_Bean>();
		
		
		job=T_UserManage_Sql.queryExportJob(loginId);
		
		out.flush();
		out.println("<script>");
		out.println("alert('导出成功');");
		out.println("history.back();");
		out.println("</script>");
		
		CZ_Excel.initJob(job,filePath);
	}

}
