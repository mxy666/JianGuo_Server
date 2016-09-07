package com.jianguo.servlet.jpush;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_push_new_Sql;

public class T_QueryJob_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_QueryJob_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_QueryJob_Servlet---");
		 
		
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		System.out.println(name);
		List<T_job_Bean> list_t_job=new ArrayList<T_job_Bean>();
		if((name!=null&&!name.equals(""))||(city!=null&&!city.equals(""))){
			list_t_job = T_push_new_Sql.select_all(name,city);
		}else{
			list_t_job = T_push_new_Sql.queryAll();
			
		}
		
		String json = "{\"total\":"+list_t_job.size()+",\"rows\":[";
		int i=0;
		for(T_job_Bean developer : list_t_job){
			if(i==0){
				json+="{";
			}else{
				json+=",{";
			}
			//json+="\"manage\":"+"'<input type=\"button\" name=\"check\" id=\"button\"  value=\"Ñ¡Ôñ\" />"'"+"\"";
			json+="\"name\":"+developer.getId();
			json+=",\"id\":\""+developer.getName()+"\"";
			json+=",\"start_date\":\""+developer.getStart_date()+"\"";
			json+=",\"stop_date\":\""+developer.getStop_date()+"\"";
			json+=",\"address\":\""+developer.getAddress()+"\"";
			json+=",\"city_id_name\":\""+developer.getCity_id_name()+"\"";
			json+=",\"money\":\""+developer.getMoney()+"\"";

			json+="}";
			i++;
		}
		json+="]}";
//		System.out.println(json);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
}

}
