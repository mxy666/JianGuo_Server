package com.jianguo.servlet.pc.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_job_Sql;

public class T_PC_job_Id_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_PC_job_Id_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_PC_job_Id_Servlet?only=E374361DAB8120FCE148EDAC7E174C8F&hot=0
	//http://101.200.205.243:8080/T_PC_job_Id_Servlet?only=13863CB6975269B4276A2682B6E32786&hot=0&count=10
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_PC_job_Id_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String job_id =request.getParameter("job_id");


			request.setAttribute("job_id", job_id);
			request.getRequestDispatcher("user_insert.jsp").forward(request, response);
	}
}
