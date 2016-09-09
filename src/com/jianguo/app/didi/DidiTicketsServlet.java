package com.jianguo.app.didi;

import com.jianguo.util.DiDiSignUtil;
import com.jianguo.util.MD5Util;
import com.jianguo.util.RandomUtil;
import com.squareup.okhttp.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mxy on 2016/9/8.
 */
public class DidiTicketsServlet extends HttpServlet {

    private static  String sSeretkey="2351452DE7916B091077F5A914EC9612";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String phone=request.getParameter("phone");
        Logger logger = Logger.getLogger("log");
        logger.info("��־��Ϣ��ʼ!");
        String url="http://gsactivity.diditaxi.com.cn/gulfstream/activity/v2/bindpackage/bindPackage";
        String result= post(url,phone);
        logger.info(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }




    String post(String url,String phone) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Date dt= new Date();
        String time= (dt.getTime()+"").substring(0,(dt.getTime()+"").length()-3);


        HashMap <String,String> map=new HashMap<String,String>();
            map.put("channel", "77901372b80c1198451552f8d7e586cc");
            map.put("code", "3774091addd4b55b72908f9a10303518");
            map.put("phone", phone);
            map.put("tsp", time);
            map.put("key", "2351452DE7916B091077F5A914EC9612");
            map.put("akaosz", RandomUtil.generateString(16));
            map.put("func", "");
            map.put("tradeno", MD5Util.MD5("77901372b80c1198451552f8d7e586cc"+phone) );
            map.put("sign",DiDiSignUtil.getSignature(map,sSeretkey));


        RequestBody formBody = new FormEncodingBuilder()

                    .add("channel", map.get("channel"))

                    .add("phone", phone)

                    .add("key", "2351452DE7916B091077F5A914EC9612")
                    .add("akaosz", RandomUtil.generateString(16))//����������ɸ���32λ
                    .add("tsp",time)//��ǰʱ���
                    .add("code","3774091addd4b55b72908f9a10303518")//����Ƕ���������ش�
                //.add("func",)//�ص�����������ǰ��ӡ�http://���� ��ѡ�����������򲻻���лص���
                    .add("sign", map.get("sign"))//ǩ��
                    .build();



            Request request = new Request.Builder()

                    .url(url)

                    .post(formBody)

                    .build();


            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {

                return response.body().string();

            } else {

                throw new IOException("Unexpected code " + response);

            }

        }

}
