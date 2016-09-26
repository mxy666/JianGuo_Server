package com.jianguo.merchant.login;

import com.google.gson.Gson;
import com.jianguo.bean.MerchantInfo;
import com.jianguo.merchant.mersql.LoginSql;
import com.jianguo.merchant.mersql.TelCodeSql;
import com.jianguo.merchant.utils.CommonUtils;
import com.jianguo.merchant.utils.HttpClientUtil;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/9/9.
 */
public class LoginServlet extends HttpServlet {

    private MerchantInfo merchantInfo;

    /**
     * @api {post} LoginServlet/ 快速登录
     * @apiName LoginServlet
     * @apiGroup login
     *
     * @apiParam {String} tel Users phone
     * @apiParam {String} tel Users smsCode
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  登录成功！
     * @apiError (Error 400) {String} code 400
     * @apiError (Error 400) {String} message 服务器忙，请稍后重试
     * @apiError (Error 400) {String} codeError 代码错误详情（供内部测试，查找问题使用）
     * @apiError (Error 401) {String} code 401
     * @apiError (Error 401) {String} message 参数错误请检查
     * @apiError (Error 402) {String} code 402
     * @apiError (Error 402) {String} message 验证码错误
     * @apiError (Error 403) {String} code 403
     * @apiError (Error 403) {String} message 验证码已过期
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("商家端登录日志信息开始!");
        logger.info("LoginServlet!");
        String smsCode =request.getParameter("smsCode");
        String tel =request.getParameter("tel");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        try {
            //判断手机号和验证码是否为空
            if (smsCode==null||smsCode.equals("")||tel==null||tel.equals("")) {
                HttpClientUtil.pushResponse(response,"401","参数错误请检查！");
                return;
            }
            //判断验证码是否匹配
            if (!LoginSql.checkVerificationCode(tel,smsCode)) {
                HttpClientUtil.pushResponse(response,"402","验证码错误！");
                return;
            }
            //判断验证码是否超过有效期
            if (TelCodeSql.checkExpiryDate(tel,System.currentTimeMillis())) {
                HttpClientUtil.pushResponse(response,"403","验证码已过期，请重新获取！");
                return;
            }


            String token = CommonUtils.makeToken(tel);
                //不存在该用户，注册插入数据
                if (!LoginSql.checkRegister(tel)) {
                     LoginSql.insertMerchant(tel,token);
                }else {
                    //存在更新用户token
                    LoginSql.updateToken(tel,token);
                }
            //获取用户信息
            merchantInfo = LoginSql.getMerchantInfo(tel);
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","服务器忙，请稍后再试！",e.getMessage());
            return;
        }

        map.put("merchantInfo",merchantInfo);
        //生成token返回给客户端
        map.put("code", "200");
        map.put("message", "登录成功！");
        String str = gson.toJson(map);
        pw.write(str);
        pw.flush();
        pw.close();
    }

}
