package com.jianguo.servlet.pc.html;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_job_Sql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class JobListForWebServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String hot =request.getParameter("hot");
        String count =request.getParameter("count");//·ÖÒ³
        String cityId =request.getParameter("city_id");
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 3);
        long timeStemp = 0;
        try {
            Date endDate = dft.parse(dft.format(date.getTime()));
            SimpleDateFormat date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sd = date1.format(endDate);
            Date dates=date1.parse(sd);
            timeStemp = dates.getTime();
            System.out.println("----"+sd+"----"+timeStemp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//
        long ll = timeStemp/1000;
        List<T_job_Bean> list_t_job = null;
        list_t_job = T_job_Sql.select_hot("1","5","",ll+"","5");


        request.setAttribute("list_t_job", list_t_job);
        request.getRequestDispatcher("forWeb\\jobWeb.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
