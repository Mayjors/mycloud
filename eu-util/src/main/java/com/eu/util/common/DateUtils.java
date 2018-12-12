package com.example.util.common;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author yuanjie
 * @date 2018/9/18 9:49
 */
public class DateUtils {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";

    /**
     * 时间格式化层字符串
     * @param date
     * @param pattren
     * @return
     */
    public static String date2String(Date date, String pattren) {
        if (StringUtils.isEmpty(pattren)) {
            pattren = DateUtils.DATE_PATTERN;
        }
        if (GeneralUtils.isNull(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattren);
        return sdf.format(date);
    }

    /**
     * 将字符串解析成时间对象
     * @param dateTimeStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date dateParse(String dateTimeStr, String pattern) throws ParseException {
        if (StringUtils.isEmpty(pattern)) {
            pattern = DateUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeStr);
    }

    /**
     * 将日期时间格式成日期对象 和 dateParse互用
     * @param dateTime
     * @return
     */
    public static Date dateTimeToDate(Date dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 时间加减小时
     * @param startDate
     * @param hours
     * @return
     */
    public static Date dateAddHours(Date startDate, int hours) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return c.getTime();
    }

    public static Date dateAddMinutes(Date startDate, int minutes) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return c.getTime();
    }

    public static Date dateAddSeconds(Date startDate, int seconds) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return c.getTime();
    }

    /**
     * 时间比较 (如果myDate > compareDate 返回1， <返回-1. 相等返回0 )
     * @param myDate
     * @param compareDate
     * @return
     */
    public static int dateCompare(Date myDate, Date compareDate) {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }

    public static int dateBetween(Date startDate, Date endDate) {
        return 0;
    }


    /**
     * 返回当前时间戳
     * @return
     */
    public static String getCurrentTimeStamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
