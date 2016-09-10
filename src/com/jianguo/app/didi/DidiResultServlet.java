package com.jianguo.app.didi;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mxy on 2016/9/9.
 */
public class DidiResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Logger logger = Logger.getLogger("log");


        int errno=Integer.parseInt(request.getParameter("errno")) ;
        String tradeno=request.getParameter("tradeno");
        String phone=request.getParameter("phone");
        int amount=Integer.parseInt(request.getParameter("amount")) ;
        String packages = request.getParameter("package");
        String bind_time=request.getParameter("bind_time");
        logger.info(phone+"---------"+tradeno+"------------------");
        Gson info = new Gson();
        ResultInfo resInfo=info.fromJson(packages,ResultInfo.class);


        request.setAttribute("name",resInfo.getName());
        request.setAttribute("deadline",resInfo.getDeadline());
        request.setAttribute("phone",phone);
        request.setAttribute("amount",resInfo.getAmount());
        request.setAttribute("discount",resInfo.getDiscount());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private class ResultInfo{
        private String productid;
        private String name;
        private String amount;
        private String deadline;
        private String discount;

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }


        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

    }
}
