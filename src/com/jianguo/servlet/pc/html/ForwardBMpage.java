package com.jianguo.servlet.pc.html;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mxy on 2016/9/22.
 */
@WebServlet(name = "ForwardBMpage")
public class ForwardBMpage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-------------ForwardBMpage-------");
        String jobId=request.getParameter("jobid");
        request.setAttribute("jobId",jobId);


        request.getRequestDispatcher("forWeb\\fastbaoming.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        this.doPost(request, response);
    }
}
