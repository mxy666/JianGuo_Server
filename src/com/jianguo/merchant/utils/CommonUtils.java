package com.jianguo.merchant.utils;

import com.jianguo.util.MD5Util;

import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by Administrator on 2016/9/18.
 */
public class CommonUtils {
    public static String makeToken(String tel){
        String token;
        Random rand = new Random();
        int randInt = rand.nextInt(MAX_VALUE)%(MAX_VALUE-0+1) + 0;
        token= MD5Util.EncoderByMd5(tel+randInt+System.currentTimeMillis());
      return token;
    }
}
