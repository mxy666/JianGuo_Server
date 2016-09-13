package com.jianguo.servlet.pc.html;

import com.jianguo.bean.T_job_Bean;
import com.jianguo.sql.T_job_Sql;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mxy on 2016/9/12.
 */
@WebServlet(name = "JobDetailWebServlet")
public class JobDetailWebServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//兼职信息查询
        String hot =request.getParameter("hot");
        String count =request.getParameter("count");//分页
        String cityId =request.getParameter("city_id");
        System.out.print("---------------------------JobDetailWebServlet");
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

//						long ll = System.currentTimeMillis()/1000;
        long ll = timeStemp/1000;
        List<T_job_Bean> list_t_job = null;
					/*	if(hot.equals("3")){
							list_t_job = T_job_Sql.select_lvxing(hot,ll+"",count);//旅行
						}else if(hot.equals("2")){
							list_t_job = T_job_Sql.select_hot("1","5",ll+"",count);//精品里面没有数据，所以把热门的数据放进去
						}else{*/
        list_t_job = T_job_Sql.select_hot("1","5",ll+"","5");

        //}


        request.setAttribute("list_t_job", list_t_job);
        request.getRequestDispatcher("forWeb\\jobWeb.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
