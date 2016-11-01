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
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_enroll_limit_Sql;
import com.jianguo.sql.T_tel_code_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.MD5Util;
import com.qiniu.util.Auth;

public class T_user_login_Insert_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_user_login_Insert_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_user_login_Insert_Servlet?only=51EDF82FC91AD97CBBB608BCDF5AAA26&tel=111118101s050625&password=E10ADC3949BA59ABBE56E057F20F883E
	//http://101.200.205.243:8080/T_user_login_Insert_Servlet?only=E3DAEDE0B67A7731C83B1D01F30A2420&tel=13614093590
	//http://101.200.205.243:8080/user_agreement.jsp
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---T_user_login_Insert_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String tel =request.getParameter("tel");
		String password =request.getParameter("password");
		String sms_code =request.getParameter("sms_code");
		String city_id =request.getParameter("city_id");
		String city_name =request.getParameter("city_name");

		//------------------访问限制--------开始----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------访问限制--------结束----------------------

			if(T_tel_code_Sql.check_tel_code(tel, sms_code)){

				boolean b = T_user_login_Sql.check_tel(tel);
				if(b == false){

					long random =(long)((Math.random()*9+1)*100000);
					String codes = random+"";
					String str_psd = MD5Util.MD5(codes);
					if(password == "" || password == null){
						T_user_login_Sql.insert_tel(tel, str_psd,"1","1","0","0","0");
					}else{
						T_user_login_Sql.insert_tel(tel, password,"1","1","0","0","0");
					}
//				if(i == 1){
					T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
//
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String ly_time = sdf.format(new java.util.Date());
					T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),"", "http://v3.jianguojob.com/moren.png","","0","0","0", ly_time, ly_time);
					T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(), "","http://v3.jianguojob.com/moren.png","","","1","0","0","","","","","");
					T_user_money_Sql.insert(t_user_login.getId()+"", "0", "0", "0", "0", "0", "0", "0");

					T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
					//简单的token(七牛)
					Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");
					//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
					String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
					t_user_info.setQiniu(qiniu_token);

					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
					String ly_time2 = sdf2.format(new java.util.Date());
					T_enroll_limit_Sql.insert(t_user_login.getId()+"", "0", ly_time2);

					if(city_id == "" || city_id == null){
					}else{
						if(city_id == "0" || city_id.equals("0")){
						}else{
							if(city_id.equals("010")){
								T_user_login_Sql.update_city_id("北京", t_user_login.getId()+"");
							}else
							if(city_id.equals("0899")){
								T_user_login_Sql.update_city_id("三亚", t_user_login.getId()+"");
							}else
							if(city_id.equals("0898")){
								T_user_login_Sql.update_city_id("海口", t_user_login.getId()+"");
							}else
							if(city_id.equals("0571")){
								T_user_login_Sql.update_city_id("杭州", t_user_login.getId()+"");
							}else
							if(city_id.equals("029")){
								T_user_login_Sql.update_city_id("西安", t_user_login.getId()+"");
							}if(city_id.equals("027")){
								T_user_login_Sql.update_city_id("武汉", t_user_login.getId()+"");
							}
							else{
								T_user_login_Sql.update_city_id(city_name, t_user_login.getId()+"");
							}
						}
					}

					Map map = new HashMap();
					map.put("t_user_login", t_user_login);
					map.put("t_user_info", t_user_info);
					map.put("version", "23");
					map.put("version_ios", "3.0.9");
					map.put("content", "修复了部分Bug，为方便使用，请更新！");
					map.put("apk_url", "http://7xljc3.dl1.z0.glb.clouddn.com/jg_v3.1.2.apk");

					params.put("data", map);
					params.put("message", "登录成功");
					params.put("code", "200");
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params);
					pw.write(str);
					pw.flush();
					pw.close();
//				}else{
//					params.put("message", "登录失败");
//					params.put("code", "500");
//					PrintWriter pw = response.getWriter();
//					Gson g = new Gson();
//					String str = g.toJson(params);
//					pw.write(str);
//					pw.flush();
//					pw.close();
//				}
				}else{
					//简单的token(七牛)
					Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");
					//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
					String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天

					T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
					T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
					t_user_login.setQiniu(qiniu_token);

					T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(t_user_login.getId()+"");
					t_user_info.setUser_sex(t_user_resume.getSex()+"");
					t_user_info.setQiniu(qiniu_token);

					if(city_id == "" || city_id == null){
					}else{
						T_user_login_Sql.update_city_id(city_id, t_user_login.getId()+"");
					}

					Map map = new HashMap();
					map.put("t_user_login", t_user_login);
					map.put("t_user_info", t_user_info);
					map.put("version", "21");
					map.put("version_ios", "3.0.6");
					map.put("content", "修复了部分Bug，为方便使用，请更新！");
					map.put("apk_url", "http://7xljc3.dl1.z0.glb.clouddn.com/jg_v3.1.0apk.apk");

					params.put("data", map);
					params.put("message", "登录成功");
					params.put("code", "200");
//				params.put("t_user_login", t_user_login);
//				params.put("t_user_info", t_user_info);
					PrintWriter pw = response.getWriter();
					Gson g = new Gson();
					String str = g.toJson(params);
					pw.write(str);
					pw.flush();
					pw.close();
				}
			}else{
				params.put("message", "验证码不正确");
				params.put("code", "500");
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
