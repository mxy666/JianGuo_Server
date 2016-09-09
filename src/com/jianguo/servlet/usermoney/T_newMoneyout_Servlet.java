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
import com.jianguo.bean.T_tel_code_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.sql.T_tel_code_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_moneyout_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush_shang;
import com.jianguo.util.Jdpushcc_shang;
import com.jianguo.util.Jdpusher_shang;

public class T_newMoneyout_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public T_newMoneyout_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.135/JianGuo_Server/T_newMoneyout_Servlet?only=AD540E0382D71B76E3821F283E172374&login_id=5&money=50.88&smscode=340837
	//http://101.200.205.243:8080/T_newMoneyout_Servlet?only=7308A2A181E28E6859F5C5DB56670365&login_id=5735&money=50
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_newMoneyout_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();  

		String login_id =request.getParameter("login_id");
		String money =request.getParameter("money");
		String type =request.getParameter("type");
		String code =request.getParameter("smscode");
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
			
			T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
			T_tel_code_Bean code1=T_tel_code_Sql.querySmsCode(t_user_login.getTel());
			int csmscode=code1.getCode();
			if(csmscode== Integer.parseInt(code)){
				
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
				
			
				if(t_user_login.getStatus() == 2){
				
				T_user_money_Bean t_user_money = T_user_money_Sql.select_login_id(login_id);
				if(t_user_money.getMoney() >= i_money){
					int i = T_user_moneyout_Sql.insert(login_id, i_money+"", type, "0", ly_time2);
					if(i == 1){
//						double dd = Double.valueOf(money); 
						double ddd = t_user_money.getMoney()- i_money;
						System.out.println(i_money+"----");
						System.out.println(ddd+"----");
						float scale = (float) ddd; 
						DecimalFormat fnum = new DecimalFormat("##0.00"); 
						String dd=fnum.format(scale); 
						
						T_user_money_Sql.update_money_out(dd+"", login_id);
						new Thread(new Runnable() {			
							public void run() {
						Jdpush_shang.sendPush("有用户提现，快去处理","jianguo11446");
						Jdpusher_shang.sendPush("有用户提现，快去处理","jianguo11446");
						Jdpushcc_shang.sendPush("有用户提现，快去处理","jianguo11446");
							}
						}).start();
						params.put("message", "提现申请成功");
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
			}else{
				params.put("message", "验证码不正确");
				params.put("code", "403");
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
