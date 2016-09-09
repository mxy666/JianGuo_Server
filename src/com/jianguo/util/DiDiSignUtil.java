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
     * ǩ�������㷨
     * @ashMap<String,String> params ��������������в���������ת��Ϊ�ַ�������
     * @return ǩ��
     * @IOException
     */
    public static String getSignature(HashMap<String,String> params, String secret) throws IOException
    {
        params.remove("sign");
        // �Ƚ�����������������ֵ��������������
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // �����������ֵ䣬�����в�����"key=value"��ʽƴ����һ��
        StringBuilder basestring = new StringBuilder();

        if(params.getClass().isArray()&&params.size()>0){

            for (Map.Entry<String, String> param : entrys) {

                if (param.getValue()!=null){

                basestring.append(param.getKey()).append("=").append(param.getValue());

                }
            }
        }
        basestring.append(secret);

        // ʹ��MD5�Դ�ǩ������ǩ
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
