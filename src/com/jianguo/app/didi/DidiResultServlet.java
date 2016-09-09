package com.jianguo.app.didi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mxy on 2016/9/9.
 */
public class DidiResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int errno=Integer.parseInt(request.getParameter("errno")) ;
        String tradeno=request.getParameter("tradeno");
        String phone=request.getParameter("phone");
        int amount=Integer.parseInt(request.getParameter("amount")) ;
        String packages = request.getParameter("package");
        String bind_time=request.getParameter("bind_time");

        request.setAttribute("errno",errno);
        request.setAttribute("tradeno",tradeno);
        request.setAttribute("phone",phone);
        request.setAttribute("amount",amount);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
