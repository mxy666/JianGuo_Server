package com.jianguo.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;
import com.qiniu.util.Auth;

public class T_user_login_Insert_QQWX_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_login_Insert_QQWX_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_login_Insert_QQWX_Servlet?only=B3FD51CB01E8C36B9A82B0A7939DF93F&token=BCB04CEB2F4DCA1FAAEE46D9E263D9AE&nickname=hahahaha&nameimage=http://image.com&sex=1
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_login_Insert_QQWX_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String token =request.getParameter("token");
		String nickname =request.getParameter("nickname");
		String nameimage =request.getParameter("nameimage");
		String sex =request.getParameter("sex");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
//			if(sex.equals("1") || sex.equals("0")){
			//------------------访问限制--------结束----------------------
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ly_time = sdf.format(new java.util.Date());
				boolean b = T_user_login_Sql.check_qqwx(token);
				if(b == false){
					//简单的token(七牛)
					Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");	
					//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
					String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
					
					T_user_login_Sql.insert_qqwx("0","0",token,"1","1");
					T_user_login_Bean t_user_login = T_user_login_Sql.select_token(token);
					T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", nickname,"", nameimage,"","0","0","0", ly_time, ly_time);
					T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", nickname,"", nameimage,"","", sex,"0","0","","","","","");
					T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
					t_user_login.setQiniu(qiniu_token);
					t_user_info.setQiniu(qiniu_token);
					
					T_user_money_Sql.insert(t_user_login.getId()+"", "0", "0", "0", "0", "0", "0", "0");
					
					Map map = new HashMap();
					map.put("t_user_login", t_user_login);
					map.put("t_user_info", t_user_info);
					
					params.put("data", map);			
					params.put("message", "登录成功");			
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params);
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					T_user_login_Bean t_user_login = T_user_login_Sql.select_token(token);
					T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
					
					Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");	
					//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
					String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
					
					t_user_login.setQiniu(qiniu_token);
					t_user_info.setQiniu(qiniu_token);
					
					Map map = new HashMap();
					map.put("t_user_login", t_user_login);
					map.put("t_user_info", t_user_info);
					
					params.put("data", map);			
					params.put("message", "登录成功");			
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}

			//------------------访问限制--------开始----------------------
		}else{
			params.put("message", "无效访问");
			params.put("code", "404");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------访问限制--------结束----------------------
	}
}
