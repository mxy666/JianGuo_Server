package com.jianguo.servlet.pc.html;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.JobForWeb;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.MD5Util;

public class T_passWordLogin_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_passWordLogin_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * web∂À’À∫≈√‹¬Îµ«¬º
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_passWordLogin_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		String account =request.getParameter("account");
		String password =request.getParameter("password");
		String mD5password=MD5Util.MD5(password);
		PrintWriter out = response.getWriter();
		boolean flagTel=T_user_login_Sql.check_tel(account);
		
		if(flagTel){
			boolean flag=T_user_login_Sql.checkForAccount(account,mD5password);
			
			if(flag){
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				String hot =request.getParameter("hot");
				String count =request.getParameter("count");//∑÷“≥
				String cityId =request.getParameter("city_id");
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date beginDate = new Date();
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - 3);
				long timeStemp = 0;
				try {
					Date endDate = dft.parse(dft.format(date.getTime()));
					SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String sd = date1.format(endDate);
					Date dates=date1.parse(sd);
					timeStemp = dates.getTime();
					System.out.println("----"+sd+"----"+timeStemp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//
				long ll = timeStemp/1000;
				List<T_job_Bean> list_t_job = null;
				list_t_job = JobForWeb.queryAlljob("1",ll+"","5");


				request.setAttribute("list_t_job", list_t_job);
				request.getRequestDispatcher("forWeb\\jobWeb.jsp").forward(request, response);
			}else{
				out.flush();
				out.println("<script>");
				out.println("alert('√‹¬Î≤ª’˝»∑');");
				out.println("history.back();");
				out.println("</script>");	
			}
			
			
		}else{			
			out.flush();out.println("<script>"); 
			out.println("alert('∏√ ÷ª˙Œ¥◊¢≤·');");
			out.println("history.back();");
			out.println("</script>");	
		}
	
		
		
	}

}
