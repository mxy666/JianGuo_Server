package com.jianguo.servlet.sms;

import com.jianguo.util.Server_Get;

public class MD_SMS_Info {

	public static String yanzhengma0ss(String user_username) {
		// TODO Auto-generated method stub
		long random =(long)((Math.random()*9+1)*100000);
		String test = random+"";
		String job_test = "【兼果兼职】欢迎使用兼果兼职，您的手机验证码是"+test+"，有效期为20分钟，请尽快验证。本条信息无需回复";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
		return test;
	}
	
	public static void baoming1(String user_username) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】童鞋，小果子已将您的报名请求发送给商家了，请耐心等待吧！更多精彩请关注我们官方微信公众号：兼果job";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	public static void luyong2(String user_username,String job_name) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】集美貌于才华于一身的你报名的("+job_name+")已被商家录用，请前往我的兼职中确认参加该兼职，若不及时确认会被商家拒绝哦！更多精彩请关注官方微信公众号：兼果job。";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}

	public static void jujue32(String user_username,String job_name) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】抱歉，商家可能高度近视没有发现你的才华， 你的报名("+job_name+")请求被无情拒绝了，更多精彩请关注官方微信公众号：兼果job。";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	public static void daqian4(String user_username,String job_name,String money) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】小果子马不停蹄的把("+job_name+")兼职"+money+"元工资发到了你的钱包里，请注意查收。更多精彩请关注官方微信公众号：兼果job。";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	public static void querencanjia6(String user_username,String job_name,String tel) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】您已确认参加("+job_name+")兼职，请按商家要求准备出发。千万不要放鸽子哦，因为还有很多勤工俭学的同学需要这份工作。如有特殊情况请联系商家："+tel+"。更多精彩请关注官方微信公众号：兼果job";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	public static void wailu5(String user_username,String job_name,String truename,String sex,String phone,String school) {
		// TODO Auto-generated method stub
		String job_test = "【兼果兼职】您发布的兼职信息("+job_name+")已有人参加。"+truename+"，"+sex+"，"+phone+"，"+school+"。";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
}
