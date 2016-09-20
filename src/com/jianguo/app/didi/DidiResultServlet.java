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
        logger.info("DidiResultServlet--------------------------");

        int errno=Integer.parseInt(request.getParameter("errno")) ;//�����룬��ʶ����״̬
        String tradeno=request.getParameter("tradeno");//Ψһ�������������������ӿ�ʱ�����Ĳ�����ԭ�ⲻ���Ļص���������
        String phone=request.getParameter("phone");
        int amount=Integer.parseInt(request.getParameter("amount")) ;//��ȯ���ܽ��
        String packages = request.getParameter("package");
        String bind_time=request.getParameter("bind_time");
        logger.info(phone+"---------"+tradeno+"------------------");
        logger.info("----------"+packages+"---------------------");
        Gson info = new Gson();
        DidiBean resInfo=info.fromJson(packages,DidiBean.class);
        List<DidiBean.DataBean.PackageBean> infos = resInfo.getData().getPackageX();

          // request.setAttribute("name",resInfo.getData().getPackageX().get(0).getName());//ȯ����
        request.setAttribute("phone",phone);
        request.setAttribute("amount",amount);
        request.setAttribute("info",infos);

       // request.getRequestDispatcher("didi\\didiInfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
