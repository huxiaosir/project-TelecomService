package org.joisen.common.util;

/**
 * @Author Joisen
 * @Date 2022/11/30 15:37
 * @Version 1.0
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 将日期字符串按照指定格式转换为日期对象
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期按照指定的格式格式化为字符串
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
