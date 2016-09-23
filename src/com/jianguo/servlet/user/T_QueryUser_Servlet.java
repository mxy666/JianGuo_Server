package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_UserManage_Sql;

public class T_QueryUser_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_QueryUser_Servlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String cityId = request.getParameter("cityId");
		String cityName = request.getParameter("cityName");

        // 获取前台页数  
        int page = Integer.parseInt(request.getParameter("page"));  
        // 获取前台每页显示条数  
        int row = Integer.parseInt(request.getParameter("rows"));  
      
		
		List<T_UserManage_Bean> user=new ArrayList<T_UserManage_Bean>();
		int total=0;
		if(name!=null&&!name.equals("")||tel!=null&&!tel.equals("")){
			user=T_UserManage_Sql.queryAll(name,tel);
			total=T_UserManage_Sql.queryCount(name, tel,cityName);
		}else{
			//user = T_UserManage_Sql.selectAll();
			user = T_UserManage_Sql.queryByPage(page,row,cityName);
			total=T_UserManage_Sql.queryCount(name, tel,cityName);
		}
		 JSONObject jobj = new JSONObject();//  
	        jobj.put("total", total);  
	        jobj.put("rows", user); 
	    /*  
		JSONArray array = null; 
		array = JSONArray.fromObject(user);*/
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("name", name);
		PrintWriter out = response.getWriter();
		out.println(jobj);
		out.flush();
		out.close();
	       /* HttpServletResponse res = ServletActionContext.getResponse();  
	        res.setContentType("text/html;charset=utf-8");  
	        res.getWriter().print(jobj.toString());  */
		
		
	}

}
