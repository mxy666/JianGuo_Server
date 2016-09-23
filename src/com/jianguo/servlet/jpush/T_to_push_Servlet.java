package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_push_new_Sql;
import com.jianguo.util.PageModel;

public class T_to_push_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_to_push_Servlet() {
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
		System.out.println("---T_to_push_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		
		String cityId=request.getParameter("cityId");
		String school=request.getParameter("school");
		String tel=request.getParameter("tel");
		String sex=request.getParameter("sex");
		String job_namesss=request.getParameter("job_namesss");
		
		String name =request.getParameter("name");
		String city =request.getParameter("city");
		int pageNo =Integer.parseInt(request.getParameter("pageNo"));
		PageModel page = new PageModel();
		if(pageNo != 0 ){
			page.setPageNo(pageNo);
		}
		page = (PageModel) T_push_new_Sql.queryAllT(page,cityId,school,tel,sex);
		request.setAttribute("page", page);
		
		List<T_user_resume_Bean>pushObj =T_push_new_Sql.queryAll(cityId,school,tel,sex);

		

			request.setAttribute("cityId", cityId);

			request.setAttribute("school", school);
			System.out.println(school+"--------");

			request.setAttribute("tel", tel);

			request.setAttribute("sex", sex);

			request.setAttribute("job_namesss", job_namesss);
		
		request.setAttribute("pushObj", pushObj);

		if(city.equals("boss")){
			request.getRequestDispatcher("interaction\\push.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("interaction\\pushForCity.jsp").forward(request, response);
		}
		
		
	}

	

	
}
