package com.jianguo.util;

public class StrUtils {
	 public static StringBuffer buffer(String []array){
	    	StringBuffer sb = new StringBuffer();
	    	for (String s : array) {
				sb.append(s);
				sb.append("','");
		}
		if (sb.charAt(sb.length() - 1) == '\'') {					
				sb.deleteCharAt(sb.length() - 1);
				if(sb.charAt(sb.length() - 1) == ','){
					sb.deleteCharAt(sb.length() - 1);
				}
		}
	    	return sb;
	    }
}
