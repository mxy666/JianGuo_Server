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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/10/21.
 */
@WebServlet(name = "RegisterServlet",urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    /**
     * @api {post} RegisterServlet/ 商家注册
     * @apiName RegisterServlet
     * @apiGroup login
     *
     * @apiParam {String} tel User phone
     * @apiParam {String} smsCode User smsCode
     * @apiParam {String} password User password
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
     *  @apiError (Error 405) {String} code 405
     * @apiError (Error 405) {String} message 手机号码已经存在
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=GBK");
//        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("商家端登录日志信息开始!");
        logger.info("RegisterServlet!");
        String smsCode =request.getParameter("smsCode");
        String smsTel =request.getParameter("tel");
        String tel ="jg"+smsTel;
        String password =request.getParameter("password");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        try {
            //判断手机号和验证码是否为空
            if (smsCode==null||smsCode.equals("")||tel==null||tel.equals("")||password==null||password.equals("")) {
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
                int loginId = LoginSql.insertMerchant(tel,password, token);
                LoginSql.insertMerchantInfo(loginId);
                //生成token返回给客户端
                map.put("code", "200");
                map.put("message", "注册成功！");
                String str = gson.toJson(map);
                pw.write(str);
                pw.flush();
                pw.close();
            }else {
                //存在用户提示已经存在改手机号
                //生成token返回给客户端
                map.put("code", "405");
                map.put("message", "手机号已经存在！");
                String str = gson.toJson(map);
                pw.write(str);
                pw.flush();
                pw.close();
                return;
            }
        } catch (SQLException e) {
            HttpClientUtil.pushResponse(response,logger,"400","服务器忙，请稍后再试！",e.getMessage());
            return;
        }


    }

}
