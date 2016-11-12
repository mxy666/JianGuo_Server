package com.jianguo.servlet.update;

import com.google.gson.Gson;

import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/1.
 */
public class UpdateServlet extends HttpServlet {



    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        this.doPost(request, response);
    }

    //http://192.168.1.233/JianGuo_Server/T_Text_Sms_Servlet?only=D1F4C2041C993D383D2D0447FF15DB63&phone=13614093590
//   http://192.168.1.118:8080/v_update?v=9
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Logger logger = Logger.getLogger("log");
        logger.info("更新地址日志!");
        int curr_code =Integer.parseInt(request.getParameter("v"));

        SAXReader reader = new SAXReader();
        logger.info("UpdateServlet1"+this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            Document document = reader.read(Thread.currentThread().getContextClassLoader().getResource("version.xml"));
            Thread.currentThread().getContextClassLoader().getResource("version.xml");
            Element root = document.getRootElement();

            String version = "1.0";
            int code = 0;
            String url = "";
            List<Element> childElements = root.elements();
            for (Element child : childElements) {
                if("version".equals(child.getName())){
                    version = child.getStringValue();
                }else if("url".equals(child.getName())){
                    url = child.getStringValue();
                }else if("code".equals(child.getName())){
                    code = Integer.parseInt(child.getStringValue());
                }
            }
            final Map<String, String> params =  new HashMap<String, String>();
            if(curr_code>=code){
                params.put("url", "");
            }else{
                params.put("url", url);
            }
            params.put("version", version);
            System.out.println(url);
            PrintWriter pw = response.getWriter();
            Gson g = new Gson();
            String str = g.toJson(params);
            pw.write(str);
            pw.flush();
            pw.close();

        } catch (DocumentException e) {
            logger.info("UpdateServlet!"+e.getMessage());
            e.printStackTrace();
        }


    }



}
