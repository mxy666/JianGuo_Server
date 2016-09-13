package com.jianguo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
    public static String EncoderByMd5(String str) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            bytes = md5.digest(str.toString().getBytes("UTF-8"));
        } catch (Exception ex) {

        }
        // ��MD5����Ķ����ƽ��ת��ΪСд��ʮ������
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }
    public final static String MD5(String s) {  
        char hexDigits[] = { '0', '1', '2', '3', '4',  
                             '5', '6', '7', '8', '9',  
                             'A', 'B', 'C', 'D', 'E', 'F' };
        try {  
            byte[] btInput = s.getBytes();  
     //���MD5ժҪ�㷨�� MessageDigest ����  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
     //ʹ��ָ�����ֽڸ���ժҪ  
            mdInst.update(btInput);  
     //�������  
            byte[] md = mdInst.digest();  
     //������ת����ʮ�����Ƶ��ַ�����ʽ  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                str[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(str);  
    }  
        catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    public static void main(String[] args) {  
        System.out.print(MD5Util.MD5("password"));  
    }  



}
