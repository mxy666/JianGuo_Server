package com.jianguo.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_realname_Bean;
import com.jianguo.sql.T_UserManage_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;

public class T_updateUser_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_updateUser_Servlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. 
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 更新用户信息
	 *
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---T_updateUser_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		PrintWriter out = response.getWriter();
		String login_id =request.getParameter("login_id");
		String sex =request.getParameter("sex");
		String money =request.getParameter("money");
		String school =request.getParameter("school");
		String cityId =request.getParameter("cityId");
		int sexNum=0;
		if(sex.equals("男")){
			sexNum=1;//男
		}else{
			sexNum=0;//女
		}
		
		Boolean flag=T_UserManage_Sql.updateUser(login_id,sexNum,money,school,cityId);
		if(flag){
			out.flush();
			out.println("<script>");
			out.println("alert('修改成功');");
			out.println("history.back();");
			out.println("</script>");
		}else{
			out.flush();
			out.println("<script>");
			out.println("alert('修改失败，请重新修改');");
			out.println("history.back();");
			out.println("</script>");
		}
		

	/*	request.setAttribute("realname", t_user_realname.getRealname());
		request.setAttribute("id_number", t_user_realname.getId_number());
		request.setAttribute("front_image", t_user_realname.getFront_image());
		request.setAttribute("behind_image", t_user_realname.getBehind_image());
		request.setAttribute("sex", sex);
		request.setAttribute("tel", t_user_login.getTel());
		request.setAttribute("login_id", login_id);
		request.setAttribute("money", money);
		request.setAttribute("school", school);
		request.setAttribute("cityId", cityId);*/
		request.getRequestDispatcher("userManage\\user_manage.jsp").forward(request, response);

	}

}
