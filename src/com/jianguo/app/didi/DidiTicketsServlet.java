package com.jianguo.app.didi;

import com.google.gson.Gson;
import com.jianguo.bean.T_user_info_Bean;
import com.jianguo.bean.T_user_login_Bean;
import com.jianguo.merchant.utils.HttpClientUtil;
import com.jianguo.sql.*;
import com.jianguo.util.DiDiSignUtil;
import com.jianguo.util.MD5Util;
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
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by mxy on 2016/9/8.
 */
public class DidiTicketsServlet extends HttpServlet {

    private static  String sSeretkey="11f5b19715f6b6506e184fb5669e783b";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String phone=request.getParameter("phone");
        long random =(long)((Math.random()*9+1)*100000);
        String codes = random+"";
        String str_psd = MD5Util.MD5(codes);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ly_time = sdf.format(new java.util.Date());
        if (!T_user_login_Sql.check_tel(phone)) {
            T_user_login_Sql.insert_tel(phone, str_psd,"1","1","0","0","0");
            T_user_login_Bean t_user_login = T_user_login_Sql.select_tel(phone);
            T_user_info_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(),"", "http://v3.jianguojob.com/moren.png","","0","0","0", ly_time, ly_time);
            T_user_resume_Sql.insert_qq_wx(t_user_login.getId()+"", "兼果"+t_user_login.getId(), "","http://v3.jianguojob.com/moren.png","","","1","0","0","","","","","");
            T_user_money_Sql.insert(t_user_login.getId()+"", "0", "8.88", "0", "0", "0", "0", "0");

            T_user_info_Bean t_user_info = T_user_info_Sql.select_login_id(t_user_login.getId()+"");
            Auth auth = Auth.create("l8JTtsVLzAV4yEvMvLd7Jno_4pDBwg180-_sGPbP","lkYt1WH8OPHoDkOHD_raJugSeJhaRzf7OJStBkNO");

            String qiniu_token=auth.uploadToken("jianguo",null,3600*24*7,null);//7??
            t_user_info.setQiniu(qiniu_token);

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
            String ly_time2 = sdf2.format(new java.util.Date());
            T_enroll_limit_Sql.insert(t_user_login.getId()+"", "0", ly_time2);//????????
        }
        Logger logger = Logger.getLogger("log");
        logger.info("兼果");
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

  public String postDD(String url, String phone) throws IOException {
      HttpClientUtil httpClientUtil = new HttpClientUtil();
      Date dt= new Date();
      String time= (dt.getTime()+"").substring(0,(dt.getTime()+"").length()-3);
      Random rand = new Random();
      int randInt = rand.nextInt(MAX_VALUE)%(MAX_VALUE-0+1) + 0;
      String httpOrgCreate = "http://gsactivity.diditaxi.com.cn/gulfstream/activity/v2/bindpackage/bindPackage";
      Map<String,String> createMap = new HashMap<String,String>();
      HashMap <String,String> map=new HashMap<String,String>();
      map.put("channel", "6155997522e890d390c36fd14713e8d7");//
      // map.put("code", "3774091addd4b55b72908f9a10303518");
      map.put("phone", phone);
      map.put("tsp", time);
      map.put("key", "a660088621337ea4e9e8eb98f8585819");
      map.put("akaosz", String.valueOf(randInt));
       map.put("func", "http://101.200.195.147:8080/DidiResultServlet");
      map.put("tradeno", MD5Util.EncoderByMd5("6155997522e890d390c36fd14713e8d7"+phone) );
      map.put("sign",DiDiSignUtil.getSignature(map,sSeretkey));

      createMap.put("channel","6155997522e890d390c36fd14713e8d7");
      createMap.put("phone", phone);
      createMap .put("key", "a660088621337ea4e9e8eb98f8585819");
      createMap .put("akaosz", String.valueOf(randInt));//
      createMap.put("tsp",time);//
              //.add("code","3774091addd4b55b72908f9a10303518")//
      createMap.put("tradeno", MD5Util.EncoderByMd5("6155997522e890d390c36fd14713e8d7"+phone));
      createMap .put("func","http://101.200.195.147:8080/DidiResultServlet");//
      createMap.put("sign", map.get("sign"));//

      String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreate,createMap,"utf-8");
      return httpOrgCreateTestRtn;
  }

    //okhttp??????????
   /* key:a660088621337ea4e9e8eb98f8585819
    ??:11f5b19715f6b6506e184fb5669e783b
    ???????6155997522e890d390c36fd14713e8d7*/
    String post(String url,String phone) throws IOException {
        Logger logger = Logger.getLogger("log");
        OkHttpClient client = new OkHttpClient();
        Date dt= new Date();
        String time= (dt.getTime()+"").substring(0,(dt.getTime()+"").length()-3);
        Random rand = new Random();
        int randInt = rand.nextInt(MAX_VALUE)%(MAX_VALUE-0+1) + 0;
        HashMap <String,String> map=new HashMap<String,String>();
        map.put("channel", "6155997522e890d390c36fd14713e8d7");//
        // map.put("code", "3774091addd4b55b72908f9a10303518");
        map.put("phone", phone);
        map.put("tsp", time);
        map.put("key", "a660088621337ea4e9e8eb98f8585819");
        map.put("akaosz", String.valueOf(randInt));
        map.put("func", "http://101.200.195.147:8080/DidiResultServlet");
        map.put("tradeno", MD5Util.EncoderByMd5("6155997522e890d390c36fd14713e8d7"+phone) );
        map.put("sign",DiDiSignUtil.getSignature(map,sSeretkey));
        RequestBody formBody = new FormEncodingBuilder()
                .add("channel","6155997522e890d390c36fd14713e8d7")
                .add("phone", phone)
                .add("key", "a660088621337ea4e9e8eb98f8585819")
                .add("akaosz", String.valueOf(randInt))//
                .add("tsp",time)//
                .add("tradeno", MD5Util.EncoderByMd5("6155997522e890d390c36fd14713e8d7"+phone))
                //.add("code","3774091addd4b55b72908f9a10303518")//
                .add("func","http://101.200.195.147:8080/DidiResultServlet")//
                .add("sign", map.get("sign"))//
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
