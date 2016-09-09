package com.jianguo.merchant.login;

import com.google.gson.Gson;

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
public class MerchantLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");
        logger.info("登录日志信息开始!");
        logger.info("InsertUserServlet!");
        String password =request.getParameter("password");
        String tel =request.getParameter("tel");
        Map map = new HashMap();
        Gson gson=new Gson();
        if (password==null||password.equals("")) {

        }
        PrintWriter pw = response.getWriter();
            map.put("message", "参数错误请检查");
            map.put("code", "200");
            String str = gson.toJson(map);
            pw.write(str);
            pw.flush();
            pw.close();
            return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
