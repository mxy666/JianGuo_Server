package com.jianguo.util;

public class SmsUtil {
	public static void Enroll(String user_username,String job_name,String truename,String sex,String phone,String school) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ���������ļ�ְ��Ϣ("+job_name+")�����˲μӡ�"+truename+"��"+sex+"��"+phone+"��"+school+"��";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
}
