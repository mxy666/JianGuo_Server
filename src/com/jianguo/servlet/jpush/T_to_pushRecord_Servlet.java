package com.jianguo.servlet.jpush;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_platForm_Push_Bean;
import com.jianguo.bean.T_push_Bean;
import com.jianguo.sql.T_push_new_Sql;
import com.jianguo.util.PageModel;

public class T_to_pushRecord_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_to_pushRecord_Servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_to_pushRecord_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		//String name=request.getParameter("name");		
		//String tel=request.getParameter("tel");	
		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel<T_platForm_Push_Bean> page = new PageModel<T_platForm_Push_Bean>();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = (PageModel<T_platForm_Push_Bean>) T_push_new_Sql.queryRecordT(page);
		request.setAttribute("page", page);
		
//		List<T_push_Bean>record =T_push_new_Sql.queryRecord(tel,name);
	
//		request.setAttribute("record", record);
	//	request.setAttribute("tel", tel);
		//System.out.println(name+"-----------");
	//	request.setAttribute("name", name);
		request.getRequestDispatcher("interaction\\pushRecord.jsp").forward(request, response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
