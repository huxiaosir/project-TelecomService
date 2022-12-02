package org.joisen.common.util;

/**
 * @Author Joisen
 * @Date 2022/11/30 15:27
 * @Version 1.0
 */

import java.text.DecimalFormat;

/**
 * 数字格式化
 */
public class NumberUtil {

    /**
     * 将数字格式化为指定长度的字符串
     * @param num
     * @param len
     * @return
     */
    public static String format(int num, int len){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }

        DecimalFormat df = new DecimalFormat(sb.toString());
        return df.format(num);
    }

    public static void main(String[] args) {
        System.out.println(format(123, 10));
    }

}
