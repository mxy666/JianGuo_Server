package com.jianguo.servlet.realname;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_realname_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush_shang;
import com.jianguo.util.Jdpushcc_shang;
import com.jianguo.util.Jdpusher_shang;

public class T_user_realname_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_realname_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_realname_Insert_Servlet?only=5302383B43DE008C950D19609FA08AA4&login_id=42&front_image=3&behind_image=2&realname=1&id_number=5&sex=8
	//http://101.200.205.243:8080/T_user_realname_Insert_Servlet?only=51EDF82FC91AD97CBBB608BCDF5AAA26&login_id=42&front_image=3&behind_image=2&realname=1&id_number=5&sex=8
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_realname_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String front_image =request.getParameter("front_image");
		String behind_image =request.getParameter("behind_image");
		String realname =request.getParameter("realname");
		String id_number =request.getParameter("id_number");
		String sex =request.getParameter("sex");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){	
		//------------------访问限制--------结束----------------------

			T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
			if(t_user_login.getTel() == "0" || t_user_login.getTel().equals("0")){
				params.put("message", "请进行手机验证");
				params.put("code", "500");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				boolean b = T_user_realname_Sql.check_login_id(login_id);
				if(b == false){
				T_user_realname_Sql.insert(login_id, front_image, behind_image, realname, id_number, sex);
				T_user_login_Sql.update_status("3", login_id);
				
				Jdpush_shang.sendPush("有用户提现，快去处理","jianguo8000");
				Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo8000");
				Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo8000");

				Jdpush_shang.sendPush("有用户提现，快去处理","jianguo11446");
				Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo11446");
				Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo11446");
				
				params.put("message", "实名成功");
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
				}else{
					T_user_realname_Sql.update(front_image, behind_image, realname, id_number, sex, login_id);
					T_user_login_Sql.update_status("3", login_id);
					
					Jdpush_shang.sendPush("有用户提现，快去处理","jianguo8000");
					Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo8000");
					Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo8000");
					Jdpush_shang.sendPush("有用户提现，快去处理","jianguo11446");
					Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo11446");
					Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo11446");
					
					params.put("message", "实名修改成功");
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}
				
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
