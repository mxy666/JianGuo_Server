package com.jianguo.merchant.utils;

import com.jianguo.merchant.mersql.TelCodeSql;
import com.jianguo.util.Server_Get;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextSms {

	private final static String APP_KEY = "6eb1ac1683e252354051958c3eae100d";
	private final static String APP_NAME = "�����ְ";
/**
*��ݵ�¼���ŷ���
*@param tel
*@author invinjun
*created at 2016/9/18 14:25
*/
	public static String QuickLoginSMS(String tel) throws Exception {
		// TODO Auto-generated method stub
		long random =(long)((Math.random()*9+1)*100000);
		String code = random+"";
			try {
            //����ֻ������Ƿ����
            if (TelCodeSql.checkTel(tel)) {
                //����ϴη�����֤������֮���ʱ����С��30s����ֹ���Ͳ���ʾ
                if (TelCodeSql.checkTime(tel,System.currentTimeMillis())){
                    TelCodeSql.updateTel(code, tel);
                }else {

                }

            } else {
                TelCodeSql.insert(tel, code);
            }
                //�������ݿ�codeû�����������֤��
                Sms.sendSmsQuickLogin(tel,code);
        } catch (Exception e) {
                //�׳��쳣�����ϲ㴦��
            throw new Exception();
        }
        return code;
	}


	public static void textdemos1212(String user_username,String tel) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ��ͯЬ��С�����ѽ����ı��������͸��̼��ˣ������ϲ���绰��ѯ�̼�"+tel+"�����ྫ�����ע���ǹٷ�΢�Ź��ںţ����job.�鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	
	public static void textdemos1(String user_username) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ��ͯЬ��С�����ѽ����ı��������͸��̼��ˣ������ĵȴ��ɣ����ྫ�����ע���ǹٷ�΢�Ź��ںţ����job.�鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}



	public static void textdemos2(String user_username,String job_name,String tel) {
		// TODO Auto-generated method stub
//		String job_test = "�������ְ������ò�ڲŻ���һ����㱨����("+job_name+")�ѱ��̼�¼�ã���ǰ���ҵļ�ְ��ȷ�ϲμӸü�ְ��������ʱȷ�ϻᱻ�̼Ҿܾ�Ŷ�����ྫ�����ע�ٷ�΢�Ź��ںţ����job��";
		String job_test = "�������ְ������ò�ڲŻ���һ����㱨����("+job_name+")�ѱ��̼�¼�ã��밴�̼�Ҫ��׼��������ǧ��Ҫ�Ÿ���Ŷ�����������������ϵ�̼ң�"+tel+"���鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}



	public static void textdemos32(String user_username,String job_name) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ����Ǹ���̼ҿ��ܸ߶Ƚ���û�з�����ĲŻ��� ��ı���("+job_name+")��������ܾ��ˡ����ྫ�����ע�ٷ�΢�Ź��ںţ����job.�鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


	public static void textdemos4(String user_username,String job_name,String money) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ��С������ͣ��İ�("+job_name+")��ְ"+money+"Ԫ���ʷ��������Ǯ�����ע����ա����ྫ�����ע�ٷ�΢�Ź��ںţ����job.�鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}



	public static void textdemos6(String user_username,String job_name,String tel) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ������ȷ�ϲμ�("+job_name+")��ְ���밴�̼�Ҫ��׼��������ǧ��Ҫ�Ÿ���Ŷ����Ϊ���кܶ��ڹ���ѧ��ͬѧ��Ҫ��ݹ��������������������ϵ�̼ң�"+tel+"�����ྫ�����ע�ٷ�΢�Ź��ںţ����job";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}

	public static void textdemos7(String user_username,String job_name) {
		// TODO Auto-generated method stub
//		String job_test = "�������ְ������ȷ�ϲμ�("+job_name+")��ְ���밴�̼�Ҫ��׼��������ǧ��Ҫ�Ÿ���Ŷ����Ϊ���кܶ��ڹ���ѧ��ͬѧ��Ҫ��ݹ��������������������ϵ�̼ң�"+tel+"�����ྫ�����ע�ٷ�΢�Ź��ںţ����job";
		String job_test = "�������ְ����Ǹ������("+job_name+")�����ѱ��̼�ȡ��¼ȡ��������(�ҵļ�ְ)�в鿴����ѯ���飬��ע�ٷ�΢�Ź��ںţ����job�����ྫ�ʹ�������鿴��";
		
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


	public static void textdemos5(String user_username,String job_name,String truename,String sex,String phone,String school) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ���������ļ�ְ��Ϣ("+job_name+")�����˲μӡ�"+truename+"��"+sex+"��"+phone+"��"+school+"��";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}


	/**
	 * 
	 * @param urlAll
	 *            :����ӿ�
	 * @param charset
	 *            :�ַ�����
	 * @return ����json���
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
		String job_test = "�������ְ���������ļ�ְ��Ϣ("+job_name+")�����˲μӡ�"+truename+"��"+sex+"��"+phone+"��"+school+"��";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+user_username+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	
	public static void textdemosForPay(String tel) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ�����ʷ��������Ǯ�����ע����ա����ྫ�����ע�ٷ�΢�Ź��ںţ����job.�鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	public static void textdemosForNoPay(String tel) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ���ܾ�֧�������ྫ�����ע�ٷ�΢�Ź��ںţ����job.�鿴����,��򿪼���ͻ���http://955.cc/d5feZ";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
	public static void textdemosCancle(String tel,String job) {
		// TODO Auto-generated method stub
		String job_test = "�������ְ���������ļ�ְ��"+job+"�����̼Ҿܾ��ˣ������������ְ��";
		String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+job_test+"&ext=&stime=&rrid=&msgfmt=";
		Server_Get.get(url);
	}
}
