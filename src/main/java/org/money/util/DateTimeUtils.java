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
    public static final SimpleDateFormat YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat RFC_3339_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    //时间常量
    public static final long TIME_OF_SECOND = 1000L;
    public static final long TIME_OF_MINUTE = 60000L;
    public static final long TIME_OF_HOUR = 3600000L;
    public static final long TIME_OF_DAY = 86400000L;

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
    public static long parseStringToLong(String time, SimpleDateFormat sdf) throws ParseException {
        try {
            Date date = sdf.parse(time);
            if (Objects.nonNull(date)) {
                return date.getTime();
            }
        } catch (ParseException e) {
            log.error("Date parse error, string={}, e={}", time, e);
            throw e;
        }
        return 0L;
    }

    public static int getYearFromLong(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonthFromLong(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.MONTH);
    }

    public static long getStartTimeOfMonth(int year, int month) {
        return getStartTimeOfDay(year, month, 1);
    }

    public static long getStartTimeOfDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getEndTimeOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }

}