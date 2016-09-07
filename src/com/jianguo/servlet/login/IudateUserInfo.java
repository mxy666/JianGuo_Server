package com.jianguo.servlet.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jianguo.bean.T_UserManage_Bean;
import com.jianguo.sql.T_UserManage_Sql;

public class IudateUserInfo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public IudateUserInfo() {
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

	/**
	 * 更新用户昵称为空的人
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---IudateUserInfo---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		Logger logger = Logger.getLogger("log");
		logger.info("日志信息开始!");
		logger.info("日志信息结束!");
		try {
			List<T_UserManage_Bean> user = T_UserManage_Sql.queryNullUser();
			for(int i=0;i<user.size();i++){
				boolean falg=T_UserManage_Sql.updateNullName(user.get(i).getLogin_id()+"");
			}
			System.out.println("------");
		} catch (Exception e) {
			 logger.error("异常",e);
			   e.printStackTrace();
		}
	
		
	}

}
