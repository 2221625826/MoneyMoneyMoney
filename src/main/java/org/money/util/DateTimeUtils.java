package org.money.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

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
                log.error("Date parse error, string={}", time);
                return date.getTime();
            }
        } catch (ParseException e) {
            log.error("Date parse error, string={}, e={}", time, e.getMessage());
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
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDayFromLong(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static long getStartTimeOfMonth(int year, int month) {
        return getStartTimeOfDay(year, month, 1);
    }

    public static long getStartTimeOfDay(int year, int month, int day) {
        month--;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getEndTimeOfMonth(int year, int month) {
        month--;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 0,  23, 59, 59);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTimeInMillis();
    }

    public static int getDaysOfMonth(int year, int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12->31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (year % 4 != 0 || year % 100 == 0) ? 28 : 29;
            default -> throw new IllegalStateException("Unexpected value: " + month);
        };
    }

    public static List<String> getMonthsList() {
        return Lists.newArrayList(
                "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
        );
    }

}