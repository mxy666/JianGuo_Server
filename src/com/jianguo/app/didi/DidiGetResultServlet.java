package com.jianguo.app.didi;

import com.google.gson.Gson;
import com.jianguo.util.DiDiSignUtil;
import com.jianguo.util.MD5Util;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by Administrator on 2016/9/14.
 */
public class DidiGetResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
       String phone=request.getParameter("phone");
        //String phone="13163153160";
        String result=postResult("http://gsactivity.diditaxi.com.cn/gulfstream/activity/v2/bindpackage/getReceiveInfo",phone);
        Gson info = new Gson();
        DidiBean resInfo=info.fromJson(result,DidiBean.class);
        List<DidiBean.DataBean.PackageBean> infos = resInfo.getData().getPackageX();

        // request.setAttribute("name",resInfo.getData().getPackageX().get(0).getName());//È¯Ãû³Æ
        request.setAttribute("phone",phone);
        request.setAttribute("amount",resInfo.getData().getAmount());
        request.setAttribute("info",infos);

        request.getRequestDispatcher("didi\\didiInfo.jsp").forward(request, response);
       /* Map params=new HashMap();
        params.put("result",result);
        PrintWriter pw = response.getWriter();
        Gson g = new Gson();
        String str = g.toJson(params);
        pw.write(str);
        pw.flush();
        pw.close();*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    String postResult(String url,String phone) throws IOException {
        Logger logger = Logger.getLogger("log");
        OkHttpClient client = new OkHttpClient();
        Date dt= new Date();
        String time= (dt.getTime()+"").substring(0,(dt.getTime()+"").length()-3);
        Random rand = new Random();
        int randInt = rand.nextInt(MAX_VALUE)%(MAX_VALUE-0+1) + 0;
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("channel", "6155997522e890d390c36fd14713e8d7");//
        // map.put("code", "3774091addd4b55b72908f9a10303518");
        map.put("phone", phone);
        map.put("timestamp", time);
        map.put("key", "a660088621337ea4e9e8eb98f8585819");
        map.put("sign", DiDiSignUtil.getSignature(map,"11f5b19715f6b6506e184fb5669e783b"));
        RequestBody formBody = new FormEncodingBuilder()
                .add("channel","6155997522e890d390c36fd14713e8d7")
                .add("phone", phone)
                .add("key", "a660088621337ea4e9e8eb98f8585819")
                .add("timestamp",time)//
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
