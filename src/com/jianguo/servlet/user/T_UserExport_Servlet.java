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

import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.bean.T_use_Money_Bean;
import com.jianguo.servlet.pc.CZ_Excel;
import com.jianguo.sql.T_UserManage_Sql;
import com.jianguo.sql.T_useMoney_Sql;
import com.jianguo.util.PageModel;

public class T_UserExport_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_UserExport_Servlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 *用户数据导出
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		System.out.println("---T_UserExport_Servlet---");
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name =URLDecoder.decode(request.getParameter("name"),"utf-8");
		String tel=URLDecoder.decode(request.getParameter("tel"),"utf-8");
		String filePath=this.getServletConfig().getServletContext().getRealPath("/")+"downLoadFile\\User.xls"; 
		List<T_UserManage_Bean> user=new ArrayList<T_UserManage_Bean>();
		
		user = T_UserManage_Sql.queryAll(name,tel);

		
		out.flush();
		out.println("<script>");
		out.println("alert('导出成功');");
		out.println("history.back();");
		out.println("</script>");
		
		CZ_Excel.initUser(user,filePath);
		
		//request.setAttribute("useMoney", useMoney);
		
		//request.getRequestDispatcher("use\\money_use_list.jsp").forward(request, response);
	}

}
