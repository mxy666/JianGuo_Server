package com.jianguo.util;

import java.text.SimpleDateFormat;

public class Frequently {

	public static String daycount(){
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH");
		String ly_time2 = sdf2.format(new java.util.Date());
		
//		String str2 = ly_time2.replaceAll(" ", "");
//		String str3 = str2.replaceAll("-", "");
		
		int i_time = Integer.parseInt(ly_time2);
		String str = "xse2iowiowdg3542d49z"+i_time+"jfiejdw4gdeqefw33ff23fi999";
		String str_psd = MD5Util.MD5(str);
		
			return str_psd;
	}
	
	public static String daycount2(){
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH");
		String ly_time2 = sdf2.format(new java.util.Date());
		
//		String str2 = ly_time2.replaceAll(" ", "");
//		String str3 = str2.replaceAll("-", "");
		
		int i_time = Integer.parseInt(ly_time2);
		i_time -= 1;
		String str = "xse2iowiowdg3542d49z"+i_time+"jfiejdw4gdeqefw33ff23fi999";
		String str_psd = MD5Util.MD5(str);
		
			return str_psd;
	}
	
	public static String daycount3(){
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH");
		String ly_time2 = sdf2.format(new java.util.Date());
		
//		String str2 = ly_time2.replaceAll(" ", "");
//		String str3 = str2.replaceAll("-", "");
		
		int i_time = Integer.parseInt(ly_time2);
		i_time += 1;
		String str = "xse2iowiowdg3542d49z"+i_time+"jfiejdw4gdeqefw33ff23fi999";
		String str_psd = MD5Util.MD5(str);
		
			return str_psd;
	}
}
