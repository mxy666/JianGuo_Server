package com.jianguo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jianguo.sql.T_tel_code_Sql;

public class Text_Sms {

	private final static String APP_KEY = "6eb1ac1683e252354051958c3eae100d";
	private final static String APP_NAME = "兼果兼职";

	public static String textdemos(String user_username) {
		// TODO Auto-generated method stub
		long random =(long)((Math.random()*9+1)*100000);
		String code = random+"";
		String job_test = "【兼果兼职】欢迎使用兼果兼职，您的手机验证码是"+code+"，有效期为20分钟，请尽快验证。本条信息无需回复";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);

		if(T_tel_code_Sql.check_tel(user_username)){
			T_tel_code_Sql.update_tel(code, user_username);
		}else{
			T_tel_code_Sql.insert(user_username, code);
		}
		return code;
	}

//		public static String textdemos(String mobile) {
//			// TODO Auto-generated method stub
//			String tpl_id = "2255";
//			String timeout = "20";
//	//		String mobile = phone;
//			
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//	//		String code = "111111";
//			
//			if(!mobile.equals("13614093590")){
//			
//			if(T_tel_code_Sql.check_tel(mobile)){
//				T_tel_code_Sql.update_tel(code, mobile);
//			}else{
//				T_tel_code_Sql.insert(mobile, code);
//			}
//			}
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append("&#code#=").append(code).append("&#hour#=").append(timeout).toString();
//	//		JSONObject object = null;
//			try {
//					tpl_values = URLEncoder.encode(tpl_values, charset);
//					String url = "http://v.juhe.cn/sms/send?key=";
//					String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//					// 得到JSON字符串
//					gets(urlAll, charset);
//					// 转化为JSON类
//	//				object = JSONObject.fromObject(jsonResult);
//	
//			} catch (UnsupportedEncodingException e) {
//	//			e.printStackTrace();
//				return code;
//			}
//			return code;
//		}

	public static void textdemos1212(String user_username,String tel) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】童鞋，小果子已将您的报名请求发送给商家了，请马上拨打电话咨询商家"+tel+"。更多精彩请关注我们官方微信公众号：兼果job.查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	
	public static void textdemos1(String user_username) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】童鞋，小果子已将您的报名请求发送给商家了，请耐心等待吧！更多精彩请关注我们官方微信公众号：兼果job.查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


//		public static String textdemos1(String mobile) {
//			// TODO Auto-generated method stub
//			String tpl_id = "14261";
//			String timeout = "20";
//	//		String mobile = phone;
//			
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//			
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append(timeout).toString();
//	//		JSONObject object = null;
//			try {
//				tpl_values = URLEncoder.encode(tpl_values, charset);
//				String url = "http://v.juhe.cn/sms/send?key=";
//				String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//				// 得到JSON字符串
//				gets(urlAll, charset);
//				// 转化为JSON类
//	//				object = JSONObject.fromObject(jsonResult);
//				
//			} catch (UnsupportedEncodingException e) {
//	//			e.printStackTrace();
//				return code;
//			}
//			return code;
//		}


	public static void textdemos2(String user_username,String job_name,String tel) {
		// TODO Auto-generated method stub
//		String job_test = "【兼果兼职】集美貌于才华于一身的你报名的("+job_name+")已被商家录用，请前往我的兼职中确认参加该兼职，若不及时确认会被商家拒绝哦！更多精彩请关注官方微信公众号：兼果job。";
		String job_test = "【兼果兼职】集美貌于才华于一身的你报名的("+job_name+")已被商家录用，请按商家要求准备出发。千万不要放鸽子哦。如有特殊情况请联系商家："+tel+"。查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}

//		public static String textdemos2(String mobile,String name) {
//			// TODO Auto-generated method stub
//			String tpl_id = "14262";
//			String timeout = "20";
////			String mobile = phone;
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append("&#name#=").append(name).toString();
////			JSONObject object = null;
//			try {
//				tpl_values = URLEncoder.encode(tpl_values, charset);
//				String url = "http://v.juhe.cn/sms/send?key=";
//				String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//				// 得到JSON字符串
//				gets(urlAll, charset);
//				// 转化为JSON类
////					object = JSONObject.fromObject(jsonResult);
//			} catch (UnsupportedEncodingException e) {
////				e.printStackTrace();
//				return code;
//			}
//			return code;
//		}


	public static void textdemos32(String user_username,String job_name) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】抱歉，商家可能高度近视没有发现你的才华， 你的报名("+job_name+")请求被无情拒绝了。更多精彩请关注官方微信公众号：兼果job.查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


//		public static String textdemos32(String mobile,String name) {
//			// TODO Auto-generated method stub
//			String tpl_id = "14263";
//			String timeout = "20";
//	//		String mobile = phone;
//			
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//			
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append("&#name#=").append(name).toString();
//	//		JSONObject object = null;
//			try {
//				tpl_values = URLEncoder.encode(tpl_values, charset);
//				String url = "http://v.juhe.cn/sms/send?key=";
//				String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//				// 得到JSON字符串
//				gets(urlAll, charset);
//				// 转化为JSON类
//	//				object = JSONObject.fromObject(jsonResult);
//				
//			} catch (UnsupportedEncodingException e) {
//	//			e.printStackTrace();
//				return code;
//			}
//			return code;
//		}


	public static void textdemos4(String user_username,String job_name,String money) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】小果子马不停蹄的把("+job_name+")兼职"+money+"元工资发到了你的钱包里，请注意查收。更多精彩请关注官方微信公众号：兼果job.查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


//		public static String textdemos4(String mobile,String name,String money) {
//			// TODO Auto-generated method stub
//			String tpl_id = "14265";
//			String timeout = "20";
//	//		String mobile = phone;
//			
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//			
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append("&#name#=").append(name).append("&#money#=").append(money).toString();
//	//		JSONObject object = null;
//			try {
//				tpl_values = URLEncoder.encode(tpl_values, charset);
//				String url = "http://v.juhe.cn/sms/send?key=";
//				String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//				// 得到JSON字符串
//				gets(urlAll, charset);
//				// 转化为JSON类
//	//				object = JSONObject.fromObject(jsonResult);
//				
//			} catch (UnsupportedEncodingException e) {
//	//			e.printStackTrace();
//				return code;
//			}
//			return code;
//		}


	public static void textdemos6(String user_username,String job_name,String tel) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】您已确认参加("+job_name+")兼职，请按商家要求准备出发。千万不要放鸽子哦，因为还有很多勤工俭学的同学需要这份工作。如有特殊情况请联系商家："+tel+"。更多精彩请关注官方微信公众号：兼果job";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}

	public static void textdemos7(String user_username,String job_name) {
		// TODO Auto-generated method stub
//		String job_test = "【兼果兼职】您已确认参加("+job_name+")兼职，请按商家要求准备出发。千万不要放鸽子哦，因为还有很多勤工俭学的同学需要这份工作。如有特殊情况请联系商家："+tel+"。更多精彩请关注官方微信公众号：兼果job";
		String job_test = "【兼果兼职】抱歉，您的("+job_name+")工作已被商家取消录取。您可在(我的兼职)中查看并咨询详情，关注官方微信公众号：兼果job。更多精彩工作等你查看。";
		
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}

//		public static String textdemos6w(String mobile,String name,String tel) {
//			// TODO Auto-generated method stub
//			String tpl_id = "15411";
//			String timeout = "20";
//	//		String mobile = phone;
//			
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//			
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append("&#name#=").append(name).append("&#tel#=").append(tel).toString();
//	//		JSONObject object = null;
//			try {
//				tpl_values = URLEncoder.encode(tpl_values, charset);
//				String url = "http://v.juhe.cn/sms/send?key=";
//				String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//				// 得到JSON字符串
//				gets(urlAll, charset);
//				// 转化为JSON类
//	//				object = JSONObject.fromObject(jsonResult);
//				
//			} catch (UnsupportedEncodingException e) {
//	//			e.printStackTrace();
//				return code;
//			}
//			return code;
//		}


	public static void textdemos5(String user_username,String job_name,String truename,String sex,String phone,String school) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】您发布的兼职信息("+job_name+")已有人参加。"+truename+"，"+sex+"，"+phone+"，"+school+"。";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


//		public static String textdemos5(String mobile,String job_title,String truename,String sex,String phone,String school) {
//			// TODO Auto-generated method stub
//			String tpl_id = "5548";
//			String timeout = "20";
//	//		String mobile = phone;
//			
//			long random =(long)((Math.random()*9+1)*100000);
//			String code = random+"";
//			
//			String charset = "UTF-8";
//			String tpl_values = new StringBuffer("#app#=").append(APP_NAME).append("&#job_title#=").append(job_title).append("&#truename#=").append(truename).append("&#sex#=").append(sex).append("&#phone#=").append(phone).append("&#school#=").append(school).toString();
//	//		JSONObject object = null;
//			try {
//				tpl_values = URLEncoder.encode(tpl_values, charset);
//				String url = "http://v.juhe.cn/sms/send?key=";
//				String urlAll = new StringBuffer(url).append(APP_KEY).append("&dtype=json&mobile=").append(mobile).append("&tpl_id=").append(tpl_id).append("&tpl_value=").append(tpl_values).toString();
//				// 得到JSON字符串
//				gets(urlAll, charset);
//				// 转化为JSON类
//	//				object = JSONObject.fromObject(jsonResult);
//				
//			} catch (UnsupportedEncodingException e) {
//	//			e.printStackTrace();
//				return code;
//			}
//			return code;
//		}

	/**
	 * 
	 * @param urlAll
	 *            :请求接口
	 * @param charset
	 *            :字符编码
	 * @return 返回json结果
	 */
	public static String gets(String urlAll, String charset) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
		try {
			URL url = new URL(urlAll);
			HttpURLConnection connection = (HttpURLConnection) url
			.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(30000);
			connection.setConnectTimeout(30000);
			connection.setRequestProperty("User-agent", userAgent);
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, charset));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void Enroll(String user_username,String job_name,String truename,String sex,String phone,String school) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】您发布的兼职信息("+job_name+")已有人参加。"+truename+"，"+sex+"，"+phone+"，"+school+"。";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	public static void textdemosForPay(String tel) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】工资发到了你的钱包里，请注意查收。更多精彩请关注官方微信公众号：兼果job.查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	public static void textdemosForNoPay(String tel) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】拒绝支付。更多精彩请关注官方微信公众号：兼果job.查看详情,请打开兼果客户端http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	public static void textdemosCancle(String tel,String job) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】您所报的兼职【"+job+"】被商家拒绝了，请浏览其他兼职！";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
}
