package com.jianguo.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by mxy on 2016/9/8.
 */
public class DiDiSignUtil {
    /**
     * 签名生成算法
     * @ashMap<String,String> params 请求参数集，所有参数必须已转换为字符串类型
     * @return 签名
     * @IOException
     */
    public static String getSignature(HashMap<String,String> params, String secret) throws IOException
    {
        params.remove("sign");
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();

        if(params.getClass().isArray()&&params.size()>0){

            for (Map.Entry<String, String> param : entrys) {

                if (param.getValue()!=null){

                basestring.append(param.getKey()).append("=").append(param.getValue());

                }
            }
        }
        basestring.append(secret);

        // 使用MD5对待签名串求签
        String sign="";
        try {
           /* MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(basestring.toString().getBytes("UTF-8"));*/
           sign= MD5Util.MD5(basestring.toString());
        } catch (Exception ex) {
            throw new IOException(ex);
        }


        return sign.toUpperCase();
    }
}
