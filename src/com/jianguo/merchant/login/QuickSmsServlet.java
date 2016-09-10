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
 * @api {post} QuickSmsServlet/ 快速登录验证码
 * @apiName QuickSmsServlet
 * @apiGroup login
 *
 * @apiParam {String} tel Users phone
 * @apiSuccess {String} code 200
 * @apiSuccess {String} message  验证码已经发送，请注意查收！
 * @apiError {String} code 400
 * @apiError{String} message

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
		//------------------访问限制--------开始----------------------
		//发送验证码
			new Thread(() -> {
               Text_Sms.textdemos(tel);
            }).start();
			PrintWriter pw;
			params.put("message", "验证码已经发送，请注意查收！");
			params.put("code", "200");
			pw = response.getWriter();
			String str = g.toJson(params);
			pw.write(str);
			pw.flush();
			pw.close();
//		}else{
//			params.put("message", "您的手机号尚未注册!");
//			params.put("code", "500");
//			PrintWriter pw = response.getWriter();
//			String str = g.toJson(params);
//			pw.write(str);
//			pw.flush();
//			pw.close();
//		}
		//------------------访问限制--------开始----------------------
		//------------------访问限制--------结束----------------------
	}

}
