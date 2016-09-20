package com.jianguo.merchant.login;

import com.google.gson.Gson;
import com.jianguo.merchant.mersql.LoginSql;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
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
    /**
     * @api {post} LoginServlet/ 快速登录
     * @apiName LoginServlet
     * @apiGroup login
     *
     * @apiParam {String} tel Users phone
     * @apiParam {String} tel Users password
     * @apiSuccess {String} code 200
     * @apiSuccess {String} message  验证码已经发送，请注意查收！
     * @apiError {String} code 400
     * @apiError{String} message

     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("登录日志信息开始!");
        logger.info("LoginServlet!");
        String verificationCode =request.getParameter("verification_code");
        String tel =request.getParameter("tel");
        Map map = new HashMap();
        Gson gson=new Gson();
        PrintWriter pw = response.getWriter();
        if (verificationCode==null||verificationCode.equals("")||tel==null||tel.equals("")) {
            map.put("message", "参数错误请检查");
            map.put("code", "400");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();
            return;
        }
        if (LoginSql.checkRegister(tel)) {
            map.put("message", "参数错误请检查");
            map.put("code", "400");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();
            return;
        }
            if (!LoginSql.checkRegister(tel)) {
                LoginSql.insertMerchant(tel);
            }
            map.put("code", "200");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
