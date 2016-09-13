package com.jianguo.servlet.merchant;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.servlet.jpush.action.PushAction;
import com.jianguo.sql.MerchantSql;
import com.jianguo.util.StrUtils;

public class MerPushServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MerPushServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	/**
	 * 商家端推送分男女或全推
	 * 
	 * @param request the request Send by the client to the server
	 * @type 推送范围0全推 1男 2女
	 * @status录取状态（已报名=0；已录取=1；已取消=2）
	 * @throws IOException if an error occurred
	 * MerPushServlet?message="message"&job_id=2989&type=0&status=1
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("---MerPushServlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		final String message=request.getParameter("message");				
		String type=request.getParameter("type");	
		String status=request.getParameter("status");
		String job_id=request.getParameter("job_id");
		Map map = new HashMap();
		Logger logger = Logger.getLogger("log");
		logger.info("日志信息开始!");
		logger.info("MerPushServlet!");
		
		final List<T_enroll_Bean> list_t_enroll=MerchantSql.queryUser(job_id,status);//根据id,status查出来这条job的人loginId
		
		try {
					
			if(type.equals("0")){
				//new Thread(new Runnable() {			
					//public void run() {
						PushAction.merPush(list_t_enroll,message);	
					//}

				//}).start();
				map.put("message", "推送成功");
				map.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(map);
				pw.write(str);
				pw.flush();
				pw.close();
			}else if(type.equals("1")||type.equals("2")){
				
				String loginIds="";
				for (int i = 0; i < list_t_enroll.size(); i++) {
					 String []idStr=new String[list_t_enroll.size()];
					 idStr[i]=list_t_enroll.get(i).getLogin_id()+"";
					 loginIds=StrUtils.buffer(idStr).toString();
				}
				final List<T_enroll_Bean>  ids = MerchantSql.queryByID(loginIds,type);	
				if(ids.size()>0){
					
					//new Thread(new Runnable() {
						//public void run() {
							PushAction.merPush(ids,message);
											
						//}
				//	}).start();
					map.put("message", "推送成功");
					map.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(map);
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					map.put("message", "没有您要推送的用户");
					map.put("code", "500");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(map); 
					pw.write(str);
					pw.flush();
					pw.close();
				}				
				
			}

			
		} catch (Exception e) {
			
			   e.printStackTrace();
			   logger.info("异常",e);
			   logger.error("异常",e);
		
		}
		
		
	}

}
