package com.jianguo.util;

import java.util.Random;

/**
 * Created by mxy on 2016/9/9.
 * generateString
 * ����һ������������ַ���(ֻ������Сд��ĸ������)
 *
 * @length
 *            ����ַ�������
 * @return ����ַ���
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
