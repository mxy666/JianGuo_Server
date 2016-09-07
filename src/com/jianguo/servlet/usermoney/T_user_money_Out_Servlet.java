package com.jianguo.servlet.usermoney;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_moneyout_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush_shang;
import com.jianguo.util.Jdpushcc_shang;
import com.jianguo.util.Jdpusher_shang;

public class T_user_money_Out_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_money_Out_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_money_Out_Servlet?only=7308A2A181E28E6859F5C5DB56670365&login_id=1&money=50.88
	//http://101.200.205.243:8080/T_user_money_Out_Servlet?only=7308A2A181E28E6859F5C5DB56670365&login_id=5735&money=50
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_user_money_Out_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String login_id =request.getParameter("login_id");
		String money =request.getParameter("money");
		String type =request.getParameter("type");
//		String status =request.getParameter("status");
		
		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------
			double i_money = Double.valueOf(money); 
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ly_time2 = sdf2.format(new java.util.Date());
			
			if(i_money < 50){
				params.put("message", "提现金额不能小于50元");
				params.put("code", "500");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
			
			T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
			if(t_user_login.getStatus() == 2){
			
			T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(login_id);
			if(t_user_money.getMoney() >= i_money){
				int i = T_user_moneyout_Sql.insert(login_id, i_money+"", type, "0", ly_time2);
				if(i == 1){
//					double dd = Double.valueOf(money); 
					double ddd = t_user_money.getMoney()- i_money;
					System.out.println(i_money+"----");
					System.out.println(ddd+"----");
					float scale = (float) ddd; 
					DecimalFormat fnum = new DecimalFormat("##0.00"); 
					String dd=fnum.format(scale); 
					
					T_user_money_Sql.update_money_out(dd+"", login_id);
					
				/*	if(t_user_login.getCity_id().equals("北京")){
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo8000");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo8000");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo8000");
					}else if(t_user_login.getCity_id().equals("三亚")){
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo13");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo13");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo13");
					}else if(t_user_login.getCity_id().equals("杭州")){
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo5");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo5");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo5");
					}else if(t_user_login.getCity_id().equals("海口")){
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo7999");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo7999");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo7999");
					}else if(t_user_login.getCity_id().equals("西安")){
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo7");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo7");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo7");
					}else{
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo13");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo13");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo13");
					}*/
					Jdpush_shang.sendPush("有用户提现，快去处理","jianguo11345");
					Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo11345");
					Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo11345");
					
					params.put("message", "正在提现中");
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}else{
					params.put("message", "提现失败");
					params.put("code", "500");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params); 
					pw.write(str);
					pw.flush();
					pw.close();
				}
			
			}else{
				params.put("message", "提现失败");
				params.put("code", "403");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}
			}else{
				params.put("message", "实名失败");
				params.put("code", "403");
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
