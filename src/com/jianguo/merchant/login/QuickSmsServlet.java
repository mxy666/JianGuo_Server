package com.jianguo.merchant.login;

import com.google.gson.Gson;
import com.jianguo.merchant.mersql.LoginSql;
import com.jianguo.merchant.mersql.TelCodeSql;
import com.jianguo.merchant.utils.HttpClientUtil;
import com.jianguo.merchant.utils.Sms;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.PageBreakRecord;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuickSmsServlet extends HttpServlet {
/**
 * @apidoc 1.0.0
 * @api {post} QuickSmsServlet/ ���ٵ�¼��֤��
 * @apiName QuickSmsServlet
 * @apiGroup login
 * @apiParam {String} tel Users phone
 *  @apiParam {String} type �������ͣ���ѡ�ֶΣ����������е���֤������ע�ᴫ0���������봫1�����಻�����ֶΣ�
 *  @apiSuccess {String} code 200
 * @apiSuccess {String} message  ��֤���Ѿ����ͣ���ע����գ�
 * @apiError (Error 400) {String} code 400
 * @apiError (Error 400) {String} message ������æ�����Ժ����ԣ���sql����IO����,���û���ʾ������æ��
 * @apiError (Error 401) {String} code 401
 * @apiError (Error 401) {String} message ������֤���������Ƶ�������Ժ����ԣ�
 * @apiError (Error 402) {String} code 402
 * @apiError (Error 402) {String} message ������������£��ֻ��˺Ų����ڣ�
 * @apiError (Error 403) {String} code 403
 * @apiError (Error 403) {String} message ע������£��ֻ��˺��Ѿ����ڣ�
 */
	public QuickSmsServlet() {
		super();
	}
	public void doPost(HttpServletRequest request, final HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Logger logger = Logger.getLogger("log");
		logger.info("��֤����־��Ϣ��ʼ!");
		logger.info("QuickSmsServlet!");
		String smstel=request.getParameter("tel");
		String tel="jg"+smstel;
		String type=request.getParameter("type");

		//������֤��
		try {
			//�жϸ������Ƿ����������룬���ǣ���ѯ�ֻ����Ƿ���ڣ����ڲ��ܷ���
			if (null!=type&&!type.equals("")){

				if (!LoginSql.checkRegister(tel)) {
					if(type.equals("1")){//�������룬������ڸ��ֻ��ţ���������ʾ�û�
						HttpClientUtil.pushResponse(response,"402","�ֻ��˺Ų�����");
						return;
					}
				}else {//�������ֻ���
					if(type.equals("0")){//ע�ᣬ�ֻ��Ų��ܴ��ڣ�������ʾ�û�
						HttpClientUtil.pushResponse(response,"403","�ֻ��˺��Ѵ���");
						return;
					}

				}
			}



			long random =(long)((Math.random()*9+1)*100000);
			String code = random+"";
					//����ֻ������Ƿ����
					if (TelCodeSql.checkTel(tel)) {
						//����ϴη�����֤������֮���ʱ����С��30s����ֹ���Ͳ���ʾ
						if (TelCodeSql.checkTime(tel,System.currentTimeMillis())){
							TelCodeSql.updateTel(code, tel);
						}else {
							HttpClientUtil.pushResponse(response,"401","������֤���������Ƶ�������Ժ����ԣ�");
							return;
						}
					} else {
						//������ֱ�Ӳ������ݿ�
						TelCodeSql.insert(tel, code);
					}
					//�������ݿ�codeû�����������֤��
			Sms.sendSmsQuickLogin(smstel,code);
		} catch (Exception e) {
			logger.error("QuickLoginSMS:"+e.getMessage());
			HttpClientUtil.pushResponse(response,"400","������æ�����Ժ����ԣ���");
			return;
		}
		HttpClientUtil.pushResponse(response,"200","��֤���Ѿ����ͣ���ע����գ�");
	}

}
