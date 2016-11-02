package com.jianguo.servlet.update;

import com.google.gson.Gson;
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


        int curr_code =Integer.parseInt(request.getParameter("v"));


        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+"version.xml"));

            Element root = document.getRootElement();


            int version = 0;

            String url = "";

            List<Element> childElements = root.elements();

            for (Element child : childElements) {

                if("code".equals(child.getName())){
                    version = Integer.parseInt(child.getStringValue());
                }

                if("url".equals(child.getName())){
                    url = child.getStringValue();
                }
                String c = child.getName();

            }



            System.out.println(url);
            System.out.println(version);
            final Map<String, String> params =  new HashMap<String, String>();

            if(curr_code>version){
                params.put("url", "");
            }else{

//                params.put("code", "200");
                params.put("url", url);
            }
            System.out.println(url);

            PrintWriter pw = response.getWriter();
            Gson g = new Gson();
            String str = g.toJson(params);
            pw.write(str);
            pw.flush();
            pw.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }



}
