package com.jianguo.servlet.pc.html;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_enroll_limit_Sql;
import com.jianguo.sql.T_job_Sql;
import com.jianguo.sql.T_tel_code_Sql;
import com.jianguo.sql.T_user_info_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.MD5Util;
import com.qiniu.util.Auth;

public class T_WebLogin_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_WebLogin_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//便捷登录，手机号登录

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_WebLogin_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();

		String tel =request.getParameter("phone");
		String password =request.getParameter("password");
		String sms_code =request.getParameter("sms_code");
		String city_id =request.getParameter("city_id");
		String city_name =request.getParameter("city_name");

		//验证手机号和验证码
			if(T_tel_code_Sql.check_tel_code(tel, sms_code)){
			
			boolean b = T_user_login_Sql.check_tel(tel);
			if(b == false){
				
				long random =(long)((Math.random()*9+1)*100000);
				String codes = random+"";
				String str_psd = MD5Util.MD5(codes);
				if(password == "" || password == null){
					T_user_login_Sql.insert_tel(tel, str_psd,"1","1","0","0","0");
				}else{
					String password1 = MD5Util.MD5(password);
					T_user_login_Sql.insert_tel(tel, password1,"1","1","0","0","0");
				}

					T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
				
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String ly_time = sdf.format(new java.util.Date());
					T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),"", "http://v3.jianguojob.com/moren.png","","0","0","0", ly_time, ly_time);
					T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(), "","http://v3.jianguojob.com/moren.png","","","1","0","0","","","","","");
					T_user_money_Sql.insert(t_user_login.getId()+"", "0", "8.88", "0", "0", "0", "0", "0");
					
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
						}else{
							T_user_login_Sql.update_city_id(city_name, t_user_login.getId()+"");
						}
					}
				}
					request.getRequestDispatcher("forWeb\\jobWeb.jsp").forward(request, response);
			}else{
				//简单的token(七牛)
//				Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");	
//				//		String token=auth.uploadToken("iqiaqia",null,3600*24*365*10,null);//一年
//				String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7天
//				
//				T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(tel);
//				T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
//				t_user_login.setQiniu(qiniu_token);
//				
//				T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(t_user_login.getId()+"");
//				t_user_info.setUser_sex(t_user_resume.getSex()+"");
//				t_user_info.setQiniu(qiniu_token);
//				
//				if(city_id == "" || city_id == null){
//				}else{
//					T_user_login_Sql.update_city_id(city_id, t_user_login.getId()+"");
//				}
				//兼职信息查询
				String hot =request.getParameter("hot");
				String count =request.getParameter("count");//分页
				String cityId =request.getParameter("city_id");
					String ss = "";
				/*	if(city_id.equals("010")){
						ss = "3";
					}
					if(city_id.equals("0899")){
						ss = "1";
					}
					if(city_id.equals("0898")){
						ss = "2";
					}
					if(city_id.equals("0571")){
						ss = "4";
					}
					if(city_id.equals("029")){
						ss = "5";
					}
					if(city_id.equals("3")){
						ss = "3";
					}
					if(city_id.equals("1")){
						ss = "1";
					}
					if(city_id.equals("2")){
						ss = "2";
					}
					if(city_id.equals("4")){
						ss = "4";
					}
					if(city_id.equals("5")){
						ss = "5";
					}		*/
					SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date beginDate = new Date();
					Calendar date = Calendar.getInstance();
					date.setTime(beginDate);
					date.set(Calendar.DATE, date.get(Calendar.DATE) - 3);
					long timeStemp = 0;
					try {
						Date endDate = dft.parse(dft.format(date.getTime()));
						SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String sd = date1.format(endDate);
						 Date dates=date1.parse(sd);
						 timeStemp = dates.getTime();
						System.out.println("----"+sd+"----"+timeStemp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					long ll = System.currentTimeMillis()/1000;
					long ll = timeStemp/1000;
					List<T_job_Bean> list_t_job = null;
				/*	if(hot.equals("3")){
						list_t_job = T_job_Sql.select_lvxing(hot,ll+"",count);//旅行
					}else if(hot.equals("2")){
						list_t_job = T_job_Sql.select_hot("1","5",ll+"",count);//精品里面没有数据，所以把热门的数据放进去
					}else{*/
						list_t_job = T_job_Sql.select_hot("1","5",ll+"","5");
						
					//}
				
				
				request.setAttribute("list_t_job", list_t_job);
				request.getRequestDispatcher("forWeb\\jobWeb.jsp").forward(request, response);
				
			}
			}else{
				PrintWriter out = response.getWriter();
				out.flush();out.println("<script>");
				out.println("alert('验证码不正确');");
				out.println("history.back();");
				out.println("</script>");	
			}
	}
}
