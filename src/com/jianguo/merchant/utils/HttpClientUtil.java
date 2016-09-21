package com.jianguo.merchant.utils;

/**
 * Created by Administrator on 2016/9/13.
 */
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
    public String doPost(String url,Map<String,String> map,String charset){
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    public static void pushResponse(HttpServletResponse response, String code, String message) {
        try {
            final Map<String, String> params =  new HashMap<String, String>();
            Gson g = new Gson();
            PrintWriter pw;
            params.put("message", message);
            params.put("code", code);
            pw = response.getWriter();
            String str = g.toJson(params);
            pw.write(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void pushResponse(HttpServletResponse response, Logger logger, String code, String message, String errorMessage) {
        try {
            logger.error(errorMessage);
            final Map<String, String> params =  new HashMap<String, String>();
            Gson g = new Gson();
            PrintWriter pw;
            params.put("message", message);
            params.put("code", code);
            params.put("codeError", errorMessage);
            pw = response.getWriter();
            String str = g.toJson(params);
            pw.write(str);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
