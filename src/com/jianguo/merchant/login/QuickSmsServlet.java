package com.jianguo.merchant.login;

import com.google.gson.Gson;
import com.jianguo.sql.T_user_login_Sql;
import com.jianguo.util.Frequently;
import com.jianguo.util.Text_Sms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuickSmsServlet extends HttpServlet {
/**
 * @api {post} /login/:tel Request login information
 * @apiName MerchantQuickSms
 * @apiGroup login
 *
 * @apiParam {Number} id Users unique ID.
 *
 * @apiSuccess {String} firstname Firstname of the User.
 * @apiSuccess {String} lastname  Lastname of the User.
 */
	/**
	 * Constructor of the object.
	 */
	public QuickSmsServlet() {
		super();
	}

	//http://192.168.1.135/JianGuo_Server/MerchantQuickSms?tel=13163153160
	public void doPost(HttpServletRequest request, final HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		final Map<String, String> params =  new HashMap<String, String>();
		final String tel =request.getParameter("tel");
		Gson g = new Gson();
		//------------------��������--------��ʼ----------------------
		//�ֻ��Ŵ��ڲŷ�����֤��
		boolean b = T_user_login_Sql.check_tel(tel);
		if(b){
			new Thread(new Runnable() {
				public void run() {
               Text_Sms.textdemos(tel);}
            }).start();
			PrintWriter pw;
			params.put("message", "��֤���Ѿ����ͣ���ע����գ�");
			params.put("code", "200");
			pw = response.getWriter();
			String str = g.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
		}else{
			params.put("message", "�����ֻ�����δע��!");
			params.put("code", "500");
			PrintWriter pw = response.getWriter();
			String str = g.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
		}
		//------------------��������--------��ʼ----------------------
		//------------------��������--------����----------------------
	}

}
