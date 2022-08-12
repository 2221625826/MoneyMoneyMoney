package org.money.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 时间工具类
 *
 * @author zhangyiheng03
 * @since 2022/5/19 10:24
 */
@Slf4j
public class DateTimeUtils {
    //转换器
    public static final SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat RFC_3339_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    //时间常量
    public static final long TIME_OF_SECOND = 1000L;
    public static final long TIME_OF_MINUTE = 60000L;
    public static final long TIME_OF_HOUR = 3600000L;
    public static final long TIME_OF_DAY = 86400000L;

    /**
     * 日期格式化字符串
     *
     * @param date 日期
     * @param sdf  格式
     * @return 日期/时间字符串
     */
    public static String parseDateToString(Date date, SimpleDateFormat sdf) {
        if (Objects.isNull(date)) {
            return null;
        }
        if (Objects.isNull(sdf)) {
            sdf = DATE_TIME_FORMAT;
        }
        return sdf.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param time 日期/时间字符串
     * @param sdf  格式
     * @return 日期实例
     */
    public static Date parseStringToDate(String time, SimpleDateFormat sdf) {
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            log.error("Date parse error, string={}, e={}", time, e);
        }
        return null;
    }

    /**
     * 毫秒时间戳转字符串
     *
     * @param time 毫秒时间戳
     * @param sdf  格式
     * @return 日期/时间字符串
     */
    public static String parseLongToString(long time, SimpleDateFormat sdf) {
        if (Objects.isNull(sdf)) {
            sdf = DATE_TIME_FORMAT;
        }
        return sdf.format(new Date(time));
    }

    /**
     * 字符串转毫秒时间戳
     *
     * @param time 日期/时间字符串
     * @param sdf  格式
     * @return 毫秒时间戳
     */
    public static long parseStringToLong(String time, SimpleDateFormat sdf) {
        try {
            Date date = sdf.parse(time);
            if (Objects.nonNull(date)) {
                return date.getTime();
            }
        } catch (ParseException e) {
            log.error("Date parse error, string={}, e={}", time, e);
        }
        return 0L;
    }

    /**
     * 增加天数
     * @param date 基础时间
     * @param amount 增加的天数
     * @return 增加后的时间
     */
    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    /**
     * 计算两个时间相差的自然天数
     * @param time1 前时间
     * @param time2 后时间
     * @return 相差的天数
     */
    public static long getDiffDay(long time1, long time2) {
        long time1Start = getStartTimeInMillis(time1);
        long time2Start = getStartTimeInMillis(time2);
        return (time2Start - time1Start) / TIME_OF_DAY;
    }

    private static Calendar getStartTimeOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getStartTimeOfDay(calendar);
    }

    private static Calendar getStartTimeOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return getStartTimeOfHour(calendar);
    }

    private static Calendar getStartTimeOfHour(Calendar calendar) {
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 获取某天的开始时间
     * @param date 日期
     * @return 当天零点时间
     */
    public static Date getStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getStartTimeOfDay(calendar).getTime();
    }

    /**
     * 获取时间当天零点的时间戳 xxxx-xx-xx 00:00:00:000
     * @param date 日期
     * @return 当天零点时间戳
     */
    public static long getStartTimeInMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getStartTimeOfDay(calendar).getTimeInMillis();
    }

    /**
     * 获取时间戳当天零点的时间戳 xxxx-xx-xx 00:00:00:000
     * @param time  时间戳
     * @return 当天零点时间戳
     */
    public static long getStartTimeInMillis(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return getStartTimeOfDay(calendar).getTimeInMillis();
    }
}