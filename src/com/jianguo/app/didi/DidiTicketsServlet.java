package com.jianguo.app.didi;

import com.google.gson.Gson;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.sql.*;
import com.jianguo.util.DiDiSignUtil;
import com.jianguo.util.MD5Util;
import com.jianguo.util.RandomUtil;
import com.qiniu.util.Auth;
import com.squareup.okhttp.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        long random =(long)((Math.random()*9+1)*100000);
        String codes = random+"";
        String str_psd = MD5Util.MD5(codes);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ly_time = sdf.format(new java.util.Date());
        //�ж��ֻ��ַ�ע��
        if (!T_user_login_Sql.check_tel(phone)) {
            T_user_login_Sql.insert_tel(phone, str_psd,"1","1","0","0","0");//����t_user_login
            T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(phone);
            //����user_info
            T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "���"+t_user_login.getId(),"", "http://v3.jianguojob.com/moren.png","","0","0","0", ly_time, ly_time);
            //����t_user_resume
            T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "���"+t_user_login.getId(), "","http://v3.jianguojob.com/moren.png","","","1","0","0","","","","","");
            //����t_user_money
            T_user_money_Sql.insert(t_user_login.getId()+"", "0", "8.88", "0", "0", "0", "0", "0");

            T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
            //�򵥵�token(��ţ)
            Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");

            String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7��
            t_user_info.setQiniu(qiniu_token);

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
            String ly_time2 = sdf2.format(new java.util.Date());
            T_enroll_limit_Sql.insert(t_user_login.getId()+"", "0", ly_time2);//����¼ȡ��
        }
        Logger logger = Logger.getLogger("log");
        logger.info("��־��Ϣ��ʼ!");
        String url="http://gsactivity.diditaxi.com.cn/gulfstream/activity/v2/bindpackage/bindPackage";
        String result= post(url,phone);
        Map params=new HashMap();
        params.put("result",result);
        logger.info(result);
        PrintWriter pw = response.getWriter();
        Gson g = new Gson();
        String str = g.toJson(params);
        pw.write(str);
        pw.flush();
        pw.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }



    //okhttp����������
   /* key:a660088621337ea4e9e8eb98f8585819
    ˽Կ:11f5b19715f6b6506e184fb5669e783b
    �����ţ�6155997522e890d390c36fd14713e8d7*/
    String post(String url,String phone) throws IOException {
        Logger logger = Logger.getLogger("log");
        OkHttpClient client = new OkHttpClient();
        Date dt= new Date();
        String time= (dt.getTime()+"").substring(0,(dt.getTime()+"").length()-3);


        HashMap <String,String> map=new HashMap<String,String>();
            map.put("channel", "6155997522e890d390c36fd14713e8d7");//������
           // map.put("code", "3774091addd4b55b72908f9a10303518");
            map.put("phone", phone);
            map.put("tsp", time);
            map.put("key", "a660088621337ea4e9e8eb98f8585819");
            map.put("akaosz", RandomUtil.generateString(16));
           // map.put("func", "http://101.200.195.147:8080/DidiResultServlet");
            map.put("tradeno", MD5Util.MD5("6155997522e890d390c36fd14713e8d7"+phone) );
            map.put("sign",DiDiSignUtil.getSignature(map,sSeretkey));


        RequestBody formBody = new FormEncodingBuilder()
                    .add("channel","6155997522e890d390c36fd14713e8d7")
                    .add("phone", phone)
                    .add("key", "2351452DE7916B091077F5A914EC9612")
                    .add("akaosz", RandomUtil.generateString(16))//����������ɸ���32λ
                    .add("tsp",time)//��ǰʱ���
                    //.add("code","3774091addd4b55b72908f9a10303518")//����Ƕ���������ش�
                    .add("func","http://101.200.195.147:8080/DidiResultServlet")//�ص�����������ǰ��ӡ�http://���� ��ѡ�����������򲻻���лص���
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
