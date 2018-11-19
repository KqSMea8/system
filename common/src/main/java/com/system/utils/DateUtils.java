package com.system.utils;

import com.google.common.base.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhaojian on 17/7/17.
 */
public class DateUtils {


    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    public static final String PATTERN_DAY_SHORT = "yyyyMMdd";
    public static final String PATTERN_STRING_DEFAULT = "yyMMddHHmmss";



    /**
     * Date转换String
     * @param date
     * @return
     */
    public static String parseDate(Date date,String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sm = new SimpleDateFormat(format);
        return sm.format(date);
    }

    /**
     * 字符串转为Date
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr,String format) {
        if(Strings.isNullOrEmpty(dateStr)){
            return null;
        }
        SimpleDateFormat sm = new SimpleDateFormat(format);
        try {
            return sm.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取一天的开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayStartTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayEndTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }


    /**
     * 获取一个月的开始时间
     *
     * @param date
     * @return
     */
    public static Date getMonthStartTime(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDayStartTime(calendar.getTime());
    }


    /**
     * 获取一个月的结束时间
     *
     * @param date
     * @return
     */
    public static Date getMonthEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

        return getDayEndTime(calendar.getTime());
    }

    /**
     * yyyy/mm/dd -> yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String getPatternDefault(String date) {
        String[] strings = date.split("/");
        if(strings.length != 3) {
            return null;
        }
        StringBuilder newDate = new StringBuilder();
        for(int i = 0; i < strings.length - 1; i++) {
            if(strings[i].length() == 1) {
                newDate.append(0);
            }
            newDate.append(strings[i]);
            newDate.append("-");
        }
        newDate.append(strings[strings.length - 1]);
        newDate.append(" 00:00:00");
        return newDate.toString();
    }

}
