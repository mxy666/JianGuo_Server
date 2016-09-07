package com.jianguo.servlet.opinion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_opinion_Bean;
import com.jianguo.sql.T_opinion_Sql;
import com.jianguo.util.PageModel;


public class T_opinion_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_opinion_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_opinion_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
	/*	
		String json = "{\"total\":"+opinion.size()+",\"rows\":[";
		int i=0;
		for(T_opinion_Bean feedback : opinion){
			if(i==0){
				json+="{";
			}else{
				json+=",{";
			}
			//json+="\"manage\":"+"'<input type=\"button\" name=\"check\" id=\"button\"  value=\"Ñ¡Ôñ\" />"'"+"\"";
			json+="\"id\":"+feedback.getId();
			json+=",\"tel\":\""+feedback.getTel()+"\"";
			json+=",\"text\":\""+feedback.getText().replace("\r", "")+"\"";
			
			 json+=",\"time\":\""+feedback.getTime()+"\"";
			
		

			json+="}";
			i++;
		}
		json+="]}";
		System.out.println(json);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();*/
		
		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel page = new PageModel();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = (PageModel) T_opinion_Sql.queryAllT(page);
		request.setAttribute("page", page);
		List<T_opinion_Bean> opinion=new ArrayList<T_opinion_Bean>();
		
		opinion = T_opinion_Sql.queryAll();
			
		
		request.setAttribute("opinion", opinion);
		request.getRequestDispatcher("interaction\\opinion.jsp").forward(request, response);
		
		
		
		
		
	}

}
