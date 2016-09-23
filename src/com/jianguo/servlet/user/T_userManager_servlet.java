package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class T_userManager_servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_userManager_servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	 
	   System.out.println("---T_userManager_servlet---");
	   //���ָ��logger���֣����ǰ���־�������pay-log ָ������־�ļ���ȥ
	   Logger logger = Logger.getLogger("log");
	   logger.info("��־��Ϣ��ʼ!");
	   logger.info("��־��Ϣ����!");
		String cityId = request.getParameter("city_id");
		String cityName = request.getParameter("cityName");
		Date dt= new Date();
		String time= dt.getTime()+"";
	  try {

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

		System.out.print("+++++++++++++++++++++++++++++"+time.substring(0,time.length()-3));

	  } catch (Exception  e) {
		   logger.error("�쳣",e);
		   e.printStackTrace();
	  }
	  
	
		request.setAttribute("cityId", cityId);
		request.setAttribute("cityName", cityName);
		request.getRequestDispatcher("userManage\\user_manage.jsp").forward(request, response);
	}

}
