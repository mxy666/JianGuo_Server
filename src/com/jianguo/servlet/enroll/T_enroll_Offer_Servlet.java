package com.jianguo.servlet.enroll;

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
import com.jianguo.bean.JpushBean;
import com.jianguo.bean.T_job_Bean;
import com.jianguo.bean.T_job_info_Bean;
import com.jianguo.bean.T_merchant_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.bean.T_user_resume_Bean;
import com.jianguo.sql.T_enroll_Sql;
import com.jianguo.sql.T_job_Sql;

import com.jianguo.sql.T_job_info_Sql;
import com.jianguo.sql.T_job_record_Sql;
import com.jianguo.sql.T_merchant_Sql;
import com.jianguo.sql.T_push_Sql;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.sql.T_user_resume_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Jdpush;
import com.jianguo.util.JdpushUtil;
import com.jianguo.util.Jdpush_shang;
import com.jianguo.util.Jdpushcc;
import com.jianguo.util.Jdpushcc_shang;
import com.jianguo.util.Jdpusher;
import com.jianguo.util.Jdpusher_shang;
import com.jianguo.util.Text_Sms;

public class T_enroll_Offer_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public T_enroll_Offer_Servlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.doPost(request, response);
	}

	//http://192.168.1.233/JianGuo_Server/T_enroll_Offer_Servlet?only=648C6D6926E835F494A0878DBB0CFB90&login_id=2&job_id=1��&Offer=1
	//http://101.200.205.243:8080/T_enroll_Offer_Servlet?only=0692A1C0BE724D7C6E5230483D74D435&login_id=10325&job_id=1567&offer=2
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println("---T_enroll_Offer_Servlet---");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Map params =  new HashMap();
		
		final String login_id =request.getParameter("login_id");
		final String job_id =request.getParameter("job_id");
		String Offer =request.getParameter("offer");
		System.out.println(login_id+"---"+job_id+"---"+Offer);
		//------------------��������--------��ʼ----------------------
		String only =request.getParameter("only");
		String ss_only = Frequently.daycount();
		String ss_only2 = Frequently.daycount2();
		String ss_only3 = Frequently.daycount3();
		if(only.equals(ss_only) || only.equals(ss_only2) || only.equals(ss_only3)){
			//------------------��������--------����----------------------
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final String ly_time = sdf.format(new java.util.Date());
			final JpushBean jpushBean=new JpushBean();
			final Map<String,String> param=new HashMap<>();
			jpushBean.setAppKey("b7b12502ea5672f603fb80c1");
			jpushBean.setMasterSecret("ac2905cd13f1872840f8c273");
			jpushBean.setType("3");
			jpushBean.setUsername("jianguo"+login_id);
			if(T_enroll_Sql.check_login_id_job_id(login_id, job_id)){
				if(Offer.equals("1")){//�û�ȡ��  ������ �̼Ҷ˲���ʾ               
					int i = T_enroll_Sql.update_status("1",login_id, job_id);
					if(i == 1){
						final T_job_Bean t_job11 = T_job_Sql.select_id(job_id);

						if(t_job11.getLimit_sex() == 3){
							T_user_resume_Bean t_user_resume=T_user_resume_Sql.select_sex(login_id);
							if(t_user_resume.getSex() == 0){
								T_job_Sql.update_girl_user_jian(job_id);
							}else if(t_user_resume.getSex() == 1){
								T_job_Sql.update_user_count_jian(job_id);
							}
						}else{
							T_job_Sql.update_user_count_jian(job_id);
						}
						
						new Thread(new Runnable() {			
							public void run() {
							Jdpush.sendPush("�����ɹ�ȡ�������ȡ����Ӱ���������õȼ�","jianguo"+login_id);
							Jdpusher.sendPush("�����ɹ�ȡ�������ȡ����Ӱ���������õȼ�","jianguo"+login_id);
							Jdpushcc.sendPush("�����ɹ�ȡ�������ȡ����Ӱ���������õȼ�","jianguo"+login_id);
							T_push_Sql.insert(login_id, t_job11.getName(), "ȡ������", "�����ɹ�ȡ�������ȡ����Ӱ���������õȼ�", "0","0","0","0", ly_time);
							T_user_resume_Bean t_user_resume = T_user_resume_Sql.select_login_id(login_id);
							T_job_Bean t_job = T_job_Sql.select_id(job_id);
							T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
//							Jdpush_shang.sendPush(t_user_resume.getName()+"ȡ��������"+t_job.getName()+"����鿴","jianguo"+t_merchant.getLogin_id());
//							Jdpusher_shang.sendPush(t_user_resume.getName()+"ȡ��������"+t_job.getName()+"����鿴","jianguo"+t_merchant.getLogin_id());
//							Jdpushcc_shang.sendPush(t_user_resume.getName()+"ȡ��������"+t_job.getName()+"����鿴","jianguo"+t_merchant.getLogin_id());

							jpushBean.setTitle(t_user_resume.getName()+"ȡ��������"+t_job.getName()+"����鿴");
								jpushBean.setUsername("jianguo"+t_merchant.getLogin_id());
								JdpushUtil.sendPush(jpushBean);
							T_push_Sql.insert(String.valueOf(t_merchant.getLogin_id()), t_job11.getName(), "ȡ������", "��"+t_user_resume.getName()+"��ȡ��������ְ��"+t_job11.getName()+"����鿴", "3","0","0",job_id, ly_time);

							}}).start();
						params.put("message", "�û�ȡ���ɹ�");
//					}else{
//					params.put("message", "�û�ȡ��ʧ��");
					}
				}else if(Offer.equals("2")){//�̼�ȡ��¼ȡ�������漰����������¼ȡ��
						T_enroll_Sql.update_status("2",login_id, job_id);
						final T_job_Bean t_job = T_job_Sql.select_id(job_id);
						
//						T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
//						Text_Sms.textdemos32(t_user_login.getTel(),t_job.getName());
//						MD_SMS_Info.jujue32(t_user_login.getTel(),t_job.getName());
						new Thread(new Runnable() {			
								public void run() {
							Jdpush.sendPush("��������"+t_job.getName()+"�����̼Ҿܾ��ˣ������������ְ","jianguo"+login_id);
							Jdpusher.sendPush("��������"+t_job.getName()+"�����̼Ҿܾ��ˣ������������ְ","jianguo"+login_id);
							Jdpushcc.sendPush("��������"+t_job.getName()+"�����̼Ҿܾ��ˣ������������ְ","jianguo"+login_id);
							
	//						T_job_Bean t_job11 = T_job_Sql.select_id(job_id);
							T_push_Sql.insert(login_id, t_job.getName(), "���ܾ�", "��������"+t_job.getName()+"�����̼Ҿܾ��ˣ������������ְ", "0","0","0","0", ly_time);
							T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
							Text_Sms.textdemosCancle(t_user_login.getTel(),t_job.getName());
						}}).start();
						
						params.put("message", "�̼�ȡ��¼ȡ�ɹ�");
				}else if(Offer.equals("3")){//�̼�¼ȡ
					int i = T_enroll_Sql.update_status("5",login_id, job_id);//��ְ״̬���û����ļ�ְ��״̬
						final T_job_Bean t_job = T_job_Sql.select_id(job_id);
						
					/*	if(t_job.getLimit_sex() == 3){
							T_user_resume_Bean t_user_resume=T_user_resume_Sql.select_sex(login_id);
							if(t_user_resume.getSex() == 0){
								T_job_Sql.update_GirlCountss(job_id);//����¼ȡ�����仯
								
							}else if(t_user_resume.getSex() == 1){
								T_job_Sql.update_countss(job_id);//����¼ȡ�����仯
							}
						}else{
							T_job_Sql.update_countss(job_id);//����¼ȡ�����仯
						}
						*/
						T_job_Sql.update_countss(job_id);//����¼ȡ�����仯
						new Thread(new Runnable() {			
							public void run() {
							Jdpush.sendPush("�̼ҳɹ�¼ȡ��"+t_job.getName()+"��","jianguo"+login_id);
							Jdpusher.sendPush("�̼ҳɹ�¼ȡ��"+t_job.getName()+"��","jianguo"+login_id);
							Jdpushcc.sendPush("�̼ҳɹ�¼ȡ��"+t_job.getName()+"��","jianguo"+login_id);
							
							T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(job_id);
							//List<T_enroll_Bean> list_t_enroll = T_enroll_Sql.select_job_id_status2_all(job_id, "3","5","8","9","10","11","12","13");
							//T_job_Sql.update_countss(list_t_enroll.size()+"",job_id);//����¼ȡ�����仯
							
							T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
							Text_Sms.textdemos2(t_user_login.getTel(),t_job.getName(),t_job_info.getTel());
						}}).start();
						
//						MD_SMS_Info.luyong2(t_user_login.getTel(),t_job.getName());
						
//						Jdpush.sendPush("��������"+t_job.getName()+"���ѱ��̼���ʽ¼�ã���ȷ�ϲμ�","jianguo"+login_id);
//						Jdpusher.sendPush("��������"+t_job.getName()+"���ѱ��̼���ʽ¼�ã���ȷ�ϲμ�","jianguo"+login_id);
//						Jdpushcc.sendPush("��������"+t_job.getName()+"���ѱ��̼���ʽ¼�ã���ȷ�ϲμ�","jianguo"+login_id);
						
						
						
						T_push_Sql.insert(login_id, t_job.getName(), "��¼��", "��������"+t_job.getName()+"���ѱ��̼���ʽ¼�ã���ȷ�ϲμ�", "0","0","0","0", ly_time);
						
						params.put("message", "�̼�¼ȡ�ɹ�");
				}else if(Offer.equals("4")){//��ʱ����
					int i = T_enroll_Sql.update_status("4",login_id, job_id);
					if(i == 1){
						final T_job_Bean t_job = T_job_Sql.select_id(job_id);
						
						new Thread(new Runnable() {			
							public void run() {
							T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
//							Jdpush_shang.sendPush("�û�ȡ���μӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpusher_shang.sendPush("�û�ȡ���μӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpushcc_shang.sendPush("�û�ȡ���μӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
								jpushBean.setTitle("�û�ȡ���μӡ�"+t_job.getName()+"��");
								jpushBean.setJobid(job_id);
								jpushBean.setUsername("jianguo"+t_merchant.getLogin_id());
								JdpushUtil.sendPush(jpushBean);

								T_push_Sql.insert(String.valueOf(t_merchant.getLogin_id()), t_job.getName(), "����", "�û�ȡ���μӡ�"+t_job.getName()+"���뼰ʱ����", "3","0","0",job_id, ly_time);

							}}).start();
						
						T_job_Bean t = T_job_Sql.select_id(job_id);
						if(t.getCount() == 0){
						}else{
							T_job_Sql.update_count_jian(job_id);
						}
						T_job_record_Sql.update_cancel(login_id);
						
						params.put("message", "�û�ȡ���μӳɹ�");
					}else{
					params.put("message", "�û�ȡ���μ�ʧ��");
					}
				}else if(Offer.equals("5")){//��ʱ����
					
					int i = T_enroll_Sql.update_status("5",login_id, job_id);
					if(i == 1){
						final T_job_Bean t_job = T_job_Sql.select_id(job_id);
						final T_job_info_Bean t_job_info = T_job_info_Sql.select_job_id(job_id);
						
						new Thread(new Runnable() {			
							public void run() {
							T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
//							Jdpush_shang.sendPush("�û�ȷ�ϲμӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpusher_shang.sendPush("�û�ȷ�ϲμӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpushcc_shang.sendPush("�û�ȷ�ϲμӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
								jpushBean.setTitle("�û�ȷ�ϲμӡ�"+t_job.getName()+"��");
								JdpushUtil.sendPush(jpushBean);
							T_push_Sql.insert(login_id, t_job.getName(), "ȷ�ϲμ�", "�û�ȷ�ϲμӡ�"+t_job.getName()+"��", "3","0","0",job_id, ly_time);
							
							T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
							Text_Sms.textdemos6(t_user_login.getTel(),t_job.getName(),t_job_info.getTel());
//						MD_SMS_Info.querencanjia6(t_user_login.getTel(),t_job.getName(),t_job_info.getTel());
						}}).start();
						
						params.put("message", "�û�ȷ�ϲμӳɹ�");
					}else{
					params.put("message", "�û�ȷ�ϲμ�ʧ��");
					}
				}else if(Offer.equals("6")){//¼ȡ���û�ȡ��
					int i = T_enroll_Sql.update_status("6",login_id, job_id);
					if(i == 1){
						//T_job_Sql.update_count_jian(job_id);
						final T_job_Bean t_job = T_job_Sql.select_id(job_id);
						
						if(t_job.getLimit_sex() == 3){
							T_user_resume_Bean t_user_resume=T_user_resume_Sql.select_sex(login_id);
							if(t_user_resume.getSex() == 0){
								T_job_Sql.update_Girlcount_jian(job_id);//����¼ȡ�����仯
								
							}else if(t_user_resume.getSex() == 1){
								T_job_Sql.update_count_jian(job_id);//����¼ȡ�����仯
							}
						}else{
							T_job_Sql.update_count_jian(job_id);//����¼ȡ�����仯
						}
						
						new Thread(new Runnable() {			
							public void run() {
							T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
//							Jdpush_shang.sendPush("�û�ȡ���μӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpusher_shang.sendPush("�û�ȡ���μӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpushcc_shang.sendPush("�û�ȡ���μӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
								jpushBean.setTitle("�û�ȡ���μӡ�"+t_job.getName()+"��");
								jpushBean.setUsername("jianguo"+t_merchant.getLogin_id());
								JdpushUtil.sendPush(jpushBean);
								T_push_Sql.insert(String.valueOf(t_merchant.getLogin_id()), t_job.getName(), "����", "�û�ȡ���μӡ�"+t_job.getName()+"���뼰ʱ����", "3","0","0",job_id, ly_time);

							}}).start();
						
						params.put("message", "�û�ȡ���μӳɹ�");
					}else{
					params.put("message", "�û�ȡ���μ�ʧ��");
					}
				}else if(Offer.equals("7")){//�̼�¼ȡ����ȡ����
					int i = T_enroll_Sql.update_status("7",login_id, job_id);
					if(i == 1){
						T_job_Bean t = T_job_Sql.select_id(job_id);
						if(t.getCount() == 0){
						}else{
							if(t.getLimit_sex() == 3){
								T_user_resume_Bean t_user_resume=T_user_resume_Sql.select_sex(login_id);
								if(t_user_resume.getSex() == 0){
									T_job_Sql.update_Girlcount_jian(job_id);//����¼ȡ�����仯
								}else if(t_user_resume.getSex() == 1){
									T_job_Sql.update_count_jian(job_id);//����¼ȡ�����仯
								}
							}else{
								T_job_Sql.update_count_jian(job_id);//����¼ȡ�����仯
							}
						}
						
						new Thread(new Runnable() {			
							public void run() {
							T_job_Bean t_job = T_job_Sql.select_id(job_id);
							Jdpush.sendPush("���Ĺ�����"+t_job.getName()+"���ѱ�ȡ��¼ȡ������鿴�����̼�����ȡ��¼ȡ״̬���û���","jianguo"+login_id);
							Jdpusher.sendPush("���Ĺ�����"+t_job.getName()+"���ѱ�ȡ��¼ȡ������鿴�����̼�����ȡ��¼ȡ״̬���û���","jianguo"+login_id);
							Jdpushcc.sendPush("���Ĺ�����"+t_job.getName()+"���ѱ�ȡ��¼ȡ������鿴�����̼�����ȡ��¼ȡ״̬���û���","jianguo"+login_id);
							T_push_Sql.insert(login_id, t_job.getName(), "�̼���ȡ�����ı�������", "���Ĺ�����"+t_job.getName()+"���ѱ�ȡ��¼ȡ������鿴�����̼�����ȡ��¼ȡ״̬���û���", "0","0","0","0", ly_time);
							T_user_login_Bean t_user_login = T_user_login_Sql.select_id(login_id);
							Text_Sms.textdemos7(t_user_login.getTel(),t_job.getName());
						}}).start();
						
						params.put("message", "�̼�ȡ��¼ȡ�ɹ�");
					}else{
					params.put("message", "�̼�ȡ��¼ȡʧ��");
					}
				}else if(Offer.equals("8")){
					int i = T_enroll_Sql.update_status("8",login_id, job_id);
					if(i == 1){
						params.put("message", "������ʱ��ɹ�");
					}else{
					params.put("message", "������ʱ��ʧ��");
					}
				}else if(Offer.equals("9")){
					int i = T_enroll_Sql.update_status("9",login_id, job_id);
					
					if(i == 1){
						params.put("message", "�̼ҵ�������ɹ�");
					}else{
					params.put("message", "�̼ҵ������ʧ��");
					}
				}else if(Offer.equals("10")){
					int i = T_enroll_Sql.update_status("10",login_id, job_id);
					if(i == 1){
						
						new Thread(new Runnable() {			
							public void run() {
							T_job_Bean t_job = T_job_Sql.select_id(job_id);
							Jdpush.sendPush("���ѳɹ����ʹ߹��������������ĵȴ���","jianguo"+login_id);
							Jdpusher.sendPush("���ѳɹ����ʹ߹��������������ĵȴ���","jianguo"+login_id);
							Jdpushcc.sendPush("���ѳɹ����ʹ߹��������������ĵȴ���","jianguo"+login_id);
							T_push_Sql.insert(login_id, t_job.getName(), "�޹���", "���ѳɹ����ʹ߹��������������ĵȴ���", "0","0","0","0", ly_time);
							
							T_merchant_Bean t_merchant = T_merchant_Sql.select_id(t_job.getMerchant_id()+"");
//							Jdpush_shang.sendPush("�û�ȷ�ϲμӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpusher_shang.sendPush("�û�ȷ�ϲμӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());
//							Jdpushcc_shang.sendPush("�û�ȷ�ϲμӡ�"+t_job.getName()+"��","jianguo"+t_merchant.getLogin_id());

							}}).start();
						
						params.put("message", "�û��߹��ʳɹ�");
					}else{
					params.put("message", "�û��߹���ʧ��");
					}
				}else if(Offer.equals("11")){
					int i = T_enroll_Sql.update_status("11",login_id, job_id);
					if(i == 1){
						params.put("message", "�̼ҽ���ɹ�");
					}else{
					params.put("message", "�̼ҽ���ʧ��");
					}
				}else if(Offer.equals("12")){
					int i = T_enroll_Sql.update_status("12",login_id, job_id);
					if(i == 1){
						params.put("message", "�̼����۳ɹ�");
					}else{
					params.put("message", "�̼�����ʧ��");
					}
				}else if(Offer.equals("13")){
					int i = T_enroll_Sql.update_status("13",login_id, job_id);
					
					if(i == 1){
						params.put("message", "�̼����۳ɹ�");
					}else{
					params.put("message", "�̼�����ʧ��");
					}
				}
				
				params.put("code", "200");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}else{
				params.put("message", "�û�û�б����ü�ְ�����ܲ�����");
				params.put("code", "500");
				PrintWriter pw = response.getWriter();
				Gson g = new Gson();
				String str = g.toJson(params); 
				pw.write(str);
				pw.flush();
				pw.close();
			}

			//------------------��������--------��ʼ----------------------
		}else{
			params.put("message", "��Ч����");
			params.put("code", "404");
			PrintWriter pw = response.getWriter();
			Gson g = new Gson();
			String str = g.toJson(params); 
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------��������--------����----------------------
	}
}
