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
 * @api {post} QuickSmsServlet/ 快速登录验证码
 * @apiName QuickSmsServlet
 * @apiGroup login
 * @apiParam {String} tel Users phone
 *  @apiParam {String} type 请求类型，可选字段（忘记密码中的验证码请求，注册传0，忘记密码传1，其余不传该字段）
 *  @apiSuccess {String} code 200
 * @apiSuccess {String} message  验证码已经发送，请注意查收！
 * @apiError (Error 400) {String} code 400
 * @apiError (Error 400) {String} message 服务器忙，请稍后重试！（sql或者IO错误,给用户提示服务器忙）
 * @apiError (Error 401) {String} code 401
 * @apiError (Error 401) {String} message 您的验证码请求过于频繁，请稍后再试！
 * @apiError (Error 402) {String} code 402
 * @apiError (Error 402) {String} message 忘记密码情况下，手机账号不存在！
 * @apiError (Error 403) {String} code 403
 * @apiError (Error 403) {String} message 注册情况下，手机账号已经存在！
 */
	public QuickSmsServlet() {
		super();
	}
	public void doPost(HttpServletRequest request, final HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Logger logger = Logger.getLogger("log");
		logger.info("验证码日志信息开始!");
		logger.info("QuickSmsServlet!");
		String smstel=request.getParameter("tel");
		String tel="jg"+smstel;
		String type=request.getParameter("type");

		//发送验证码
		try {
			//判断该请求是否是忘记密码，若是，查询手机号是否存在，存在才能发送
			if (null!=type&&!type.equals("")){

				if (!LoginSql.checkRegister(tel)) {
					if(type.equals("1")){//忘记密码，必须存在该手机号，不存在提示用户
						HttpClientUtil.pushResponse(response,"402","手机账号不存在");
						return;
					}
				}else {//不存在手机号
					if(type.equals("0")){//注册，手机号不能存在，存在提示用户
						HttpClientUtil.pushResponse(response,"403","手机账号已存在");
						return;
					}

				}
			}



			long random =(long)((Math.random()*9+1)*100000);
			String code = random+"";
					//检查手机号码是否存在
					if (TelCodeSql.checkTel(tel)) {
						//检查上次发送验证码和这次之间的时间间隔小于30s，禁止发送并提示
						if (TelCodeSql.checkTime(tel,System.currentTimeMillis())){
							TelCodeSql.updateTel(code, tel);
						}else {
							HttpClientUtil.pushResponse(response,"401","您的验证码请求过于频繁，请稍后再试！");
							return;
						}
					} else {
						//不存在直接插入数据库
						TelCodeSql.insert(tel, code);
					}
					//更新数据库code没有问题后发送验证码
			Sms.sendSmsQuickLogin(smstel,code);
		} catch (Exception e) {
			logger.error("QuickLoginSMS:"+e.getMessage());
			HttpClientUtil.pushResponse(response,"400","服务器忙，请稍后重试！！");
			return;
		}
		HttpClientUtil.pushResponse(response,"200","验证码已经发送，请注意查收！");
	}

}
