package com.jianguo.servlet.jpush.action;

import java.text.SimpleDateFormat;
import java.util.List;

import com.jianguo.bean.T_enroll_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.sql.T_push_new_Sql;
import com.jianguo.util.Jdpush;
import com.jianguo.util.Jdpush_all;
import com.jianguo.util.Jdpush_html;
import com.jianguo.util.Jdpush_job;
import com.jianguo.util.Jdpush_main;
import com.jianguo.util.Jdpush_money;
import com.jianguo.util.Jdpush_realname;
import com.jianguo.util.Jdpushcc;
import com.jianguo.util.Jdpushcc_all;
import com.jianguo.util.Jdpushcc_html;
import com.jianguo.util.Jdpushcc_job;
import com.jianguo.util.Jdpushcc_main;
import com.jianguo.util.Jdpushcc_money;
import com.jianguo.util.Jdpushcc_realname;
import com.jianguo.util.Jdpusher;
import com.jianguo.util.Jdpusher_all;
import com.jianguo.util.Jdpusher_html;
import com.jianguo.util.Jdpusher_job;
import com.jianguo.util.Jdpusher_main;
import com.jianguo.util.Jdpusher_money;
import com.jianguo.util.Jdpusher_realname;
import com.jianguo.util.Server_Get;

public class PushAction {
	public static void push(String pushWay,String message,String cityId,String school,String tel,String sex,
			String type,String html_url,String job_name,String job_id) {
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ly_time = time.format(new java.util.Date());
		List<T_user_resume_Bean>pushObj = null;
		if((cityId.equals("all") && sex.equals("all"))&&(school==null&&school.equals(""))&&(tel==null&&tel.equals(""))){
			Jdpush_all.sendPush(message);
			Jdpushcc_all.sendPush(message);
			Jdpusher_all.sendPush(message);
		}else{
			pushObj =T_push_new_Sql.queryAll(cityId,school,tel,sex);
		}
		//��������
	if(pushWay.equals("light")){
		int n=0;
			for(int i=0;i<pushObj.size();i++){
				n++;
				if(type.equals("4")){
					Jdpush_html.sendPush(message,"jianguo"+pushObj.get(i).getId(),html_url);//iOS
					Jdpusher_html.sendPush(message,"jianguo"+pushObj.get(i).getId(),html_url);//��׿
					Jdpushcc_html.sendPush(message,"jianguo"+pushObj.get(i).getId(),html_url);//iOS
					//T_push_Sql.insert(pushObj.get(i).getId()+"", "�", "�", message, "4","0",html_url,"0", ly_time);
					T_push_Sql.insert(pushObj.get(i).getId()+"", "", "", message, "4","0",html_url,"0", ly_time);
				}else if(type.equals("0")){
					Jdpush.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					Jdpusher.sendPush(message,"jianguo"+pushObj.get(i).getId());//��׿
					Jdpushcc.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					//T_push_Sql.insert(pushObj.get(i).getId()+"", "����", "����", message, "0","0","0","0", ly_time);
					T_push_Sql.insert(pushObj.get(i).getId()+"", "�ѱ���", "�ѱ���", message, "0","0","0","0", ly_time);
				}else if(type.equals("1")){
					Jdpush_money.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					Jdpusher_money.sendPush(message,"jianguo"+pushObj.get(i).getId());//��׿
					Jdpushcc_money.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					//T_push_Sql.insert(pushObj.get(i).getId()+"", "���ʵ���", "����", message, "1","0","0","0", ly_time);
					T_push_Sql.insert(pushObj.get(i).getId()+"", "���ʵ���", "", message, "1","0","0","0", ly_time);
				}else if(type.equals("2")){
					Jdpush_realname.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					Jdpusher_realname.sendPush(message,"jianguo"+pushObj.get(i).getId());//��׿
					Jdpushcc_realname.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					//T_push_Sql.insert(pushObj.get(i).getId()+"", "ʵ����֤", "����", message, "2","0","0","0", ly_time);
					T_push_Sql.insert(pushObj.get(i).getId()+"", "ʵ����֤", "", message, "2","0","0","0", ly_time);
				}else if(type.equals("3")){
					Jdpush_main.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					Jdpusher_main.sendPush(message,"jianguo"+pushObj.get(i).getId());//��׿
					Jdpushcc_main.sendPush(message,"jianguo"+pushObj.get(i).getId());//iOS
					T_push_Sql.insert(pushObj.get(i).getId()+"", "��ϵͳ��Ϣ", "", message, "3","0","0","0", ly_time);
			
				}else if(type.equals("5")){
					Jdpush_job.sendPush(job_name,"jianguo"+pushObj.get(i).getId(),job_name,job_id);//iOS
					Jdpusher_job.sendPush(job_name,"jianguo"+pushObj.get(i).getId(),job_name,job_id);//��׿
					Jdpushcc_job.sendPush(job_name,"jianguo"+pushObj.get(i).getId(),job_name,job_id);//iOS
					T_push_Sql.insert(pushObj.get(i).getId()+"", "һ���¼�ְ�Ƽ�", "", job_name, "5","0","0",job_id, ly_time);
				
				}
				
				//				System.out.println("---"+pushObj.get(i).getId()+"---");
			}
			T_push_Sql.insertPushRecord(0,message,n,ly_time);
		}else if(pushWay.equals("sms")){//��������//����1����0
			//			System.out.println(message+"-------------");
			String str_message = "�������ְ����ӭʹ�ü����ְ,"+message;
			int n=0;
			for(int i=0;i<pushObj.size();i++){
				String phone =pushObj.get(i).getTel().toString();
				String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+phone+"&content="+str_message+"&ext=&stime=&rrid=&msgfmt=";
				Server_Get.get(url);
				//			System.out.println("---"+tel+"---");
				T_push_Sql.insert(pushObj.get(i).getId()+"", "", "", message, "0","1","0","0", ly_time);
				n++;
			}
			T_push_Sql.insertPushRecord(1,message,n,ly_time);
		}

	}
	//�̼Ҷ�������
	public static void pushForMerchant(String phone,String message ){

        String []phoneStr=phone.split(" ");
        for(int i=0;i<phoneStr.length;i++)
        {	      
        	String tel=phoneStr[i];
            System.out.println("-----"+tel+"------"+message); 
        	String str_message = "�������ְ����ӭѡ������ְ,"+message+"�ظ�TD�˶�";
    		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String ly_time = time.format(new java.util.Date());
    		//String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-24859&pwd=FF906E078CC0AFCBF3286AD39DDD98C9&mobile="+tel+"&content="+str_message+"&ext=&stime=&rrid=&msgfmt=";
			String url = "http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-BBX-010-25250&pwd=29388A6BA2A36103258E39440DEF1C2E&mobile="+tel+"&content="+str_message+"&ext=&stime=&rrid=&msgfmt=";

			Server_Get.get(url);
    		T_push_new_Sql.add(tel,message,ly_time);
        }
	
	}
	//����������͡���ʽ����
	public static void pushForFeedBack(String message,String login_id){
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ly_time = time.format(new java.util.Date());
		Jdpush_main.sendPush(message,"jianguo"+login_id);//iOS
		Jdpusher_main.sendPush(message,"jianguo"+login_id);//��׿
		Jdpushcc_main.sendPush(message,"jianguo"+login_id);//iOS
		T_push_Sql.insert(login_id+"", "����ظ�", "����ظ�", message, "3","0","0","0", ly_time);
		
	}
	
	
	//�̼Ҷ�����nan
	
	public static void merPush(List<T_enroll_Bean> loginId,String message){
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ly_time = time.format(new java.util.Date());
		for(int i=0;i<loginId.size();i++){
			Jdpush_main.sendPush(message,"jianguo"+loginId.get(i).getLogin_id());//iOS
			Jdpusher_main.sendPush(message,"jianguo"+loginId.get(i).getLogin_id());//��׿
			Jdpushcc_main.sendPush(message,"jianguo"+loginId.get(i).getLogin_id());//iOS
			T_push_Sql.insert(loginId.get(i).getLogin_id()+"", "��ϵͳ��Ϣ", "����", message, "3","0","0","0", ly_time);
		}
	}
}
