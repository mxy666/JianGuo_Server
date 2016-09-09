package com.jianguo.util;

import java.util.Random;

/**
 * Created by mxy on 2016/9/9.
 * generateString
 * 返回一个定长的随机字符串(只包含大小写字母、数字)
 *
 * @length
 *            随机字符串长度
 * @return 随机字符串
 */
public class RandomUtil {
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String generateString(int length) {

        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }
}
