package com.jianguo.servlet.qiniu;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jianguo.bean.T_user_money_Bean;
import com.jianguo.bean.T_wages_Bean;
import com.jianguo.sql.T_user_money_Sql;
import com.jianguo.sql.T_wages_Sql;
import com.jianguo.util.MailSenderInfo;
import com.jianguo.util.SimpleMailSender;
import com.squareup.okhttp.MediaType;

public class Ceshi_Servlet extends HttpServlet {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private static final long serialVersionUID = 1L;
	public Ceshi_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.132/JianGuo_Server/Ceshi_Servlet?only=64D78679E0E12A7875F052650905B6D3
	//http://101.200.197.237:8080/Ceshi_Servlet?only=C9B1DDF8F5CEE9C376794456D3D463E6
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---Ceshi_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String height =request.getParameter("height");
		
		//聊天系统消息
//		OkHttpClient client = new OkHttpClient();
////		String post() throws IOException {
//String sss = "{\"name\": \"Notification Channel\",\"sys\": true}";
//
//		RequestBody body = RequestBody.create(JSON, sss);
//
//		      Request requestq = new Request.Builder()
//		      .url("https://api.leancloud.cn/1.1/classes/_Conversation")
//		      .addHeader("X-LC-Id", "AtwJtfIJPKQFtti8D3gNjMmb-gzGzoHsz")
//		      .addHeader("X-LC-Key", "spNrDrtGWAXP633DkMMWT65B")
//		      .addHeader("Content-Type", "application/json")
//		      .post(body)
//		      .build();
//
//		      Response responseq = client.newCall(requestq).execute();
//		    if (responseq.isSuccessful()) {
////		        return responseq.body().string();
//		    	System.out.println(responseq.body().string());
//		    } else {
//		        throw new IOException("Unexpected code " + responseq);
//		    }
		
//		String beginDate="1328007600000";
//
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String sd = sdf.format(new Date(Long.parseLong(beginDate)));
//		System.out.println(sd);
		
//		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date beginDate = new Date();
//		Calendar date = Calendar.getInstance();
//		date.setTime(beginDate);
//		date.set(Calendar.DATE, date.get(Calendar.DATE) - 3);
//		try {
//			Date endDate = dft.parse(dft.format(date.getTime()));
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String sd = sdf.format(endDate);
//			 Date dates=sdf.parse(sd);
//			 long timeStemp = dates.getTime();
//			System.out.println("----"+sd+"----"+timeStemp);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		float scale = (float) 50.999; 
//		DecimalFormat fnum = new DecimalFormat("##0.00"); 
//		String dd=fnum.format(scale); 
//		System.out.println(dd);
		
		//http://192.168.1.132/JianGuo_Server/Ceshi_Servlet
//		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
//		String date = sdf.format(new Date(1467156600*1000L));
//		System.out.println("---"+date+"---"); 
		
//		//这个类主要是设置邮件
//		  MailSenderInfo mailInfo = new MailSenderInfo(); 
//		  mailInfo.setMailServerHost("smtp.ym.163.com"); 
//		  mailInfo.setMailServerPort("25"); 
//		  mailInfo.setValidate(true); 
//		  mailInfo.setUserName("imiu@muwood.com"); 
//		  mailInfo.setPassword("MUshi888");//您的邮箱密码 
//		  mailInfo.setFromAddress("imiu@muwood.com"); 
//		  mailInfo.setToAddress(user_username); 
//		  mailInfo.setSubject("IMIU注册验证码"); 
////		  mailInfo.setContent("IMIU验证码:"+test+"（邮箱）工作人员不会向您索要密码、验证码等信息。如非本人操作，请联系IMIU客服或忽略"); 
//		  mailInfo.setContent("IMIU："+test+""); 
//	        //这个类主要来发送邮件
//		  SimpleMailSender sms = new SimpleMailSender();
//	         sms.sendTextMail(mailInfo);//发送文体格式 
		
		//http://192.168.1.132/JianGuo_Server/Ceshi_Servlet
//		Calendar  c =  Calendar.getInstance();  
////        Date date = new Date(); // 取当前时间  
//		int iii = Integer.parseInt("1469688605");
//		long oo = iii*1000L;
//        Date date = new Date(oo);  // 取固定时间  
//        System.out.println("当前时间："+date);  
//        c.setTime(date);  //当时间set 进calendar 里面  
//        int i = c.get(Calendar.DAY_OF_WEEK);  //取星期  
//        System.out.println("星期几："+i);
//        int i_xingqi = 0;
//        if(i == 1){
//        	i_xingqi = 7;
//        }else{
//        	i_xingqi = i-1;
//        }
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String sd = sdf2.format(new Date(Long.parseLong(1469688605+"100")));
//		String s = new String(sd);   
//		String a[] = s.split(" ");
//		String ssss = new String(a[1]);   
//		String aaaa[] = ssss.split(":");
//		
//		int i_aaaa = Integer.parseInt(aaaa[0]);
//		int i_shangxia = 0;
//		if(i_aaaa > 6 && i_aaaa <12){
//			i_shangxia = 1;
//		}else if(i_aaaa > 13 && i_aaaa <18){
//			i_shangxia = 2;
//		}else if(i_aaaa > 19 && i_aaaa <5){
//			i_shangxia = 3;
//		}
//		String s_aihao = i_shangxia+""+i_xingqi;
//		System.out.println(aaaa[0]+"-----==="+s_aihao);
		
//		List<T_wages_Bean> list = T_user_money_Sql.select_all();
//		for (int i = 0; i < list.size(); i++) {
//			T_user_money_Bean t = list.get(i);
//			
//		}
		
		//http://192.168.1.104/JianGuo_Server/Ceshi_Servlet
		List<T_wages_Bean> list = T_wages_Sql.select_All();
		for (int i = 0; i < list.size(); i++) {
			T_wages_Bean t = list.get(i);
		if(!T_user_money_Sql.check_login_id(t.getLogin_id()+"")){
			System.out.println(t.getLogin_id()+"-------------000");
		}
	}
		
	}
}
