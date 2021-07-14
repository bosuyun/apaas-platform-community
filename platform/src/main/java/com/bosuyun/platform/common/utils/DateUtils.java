package com.bosuyun.platform.common.utils;

import com.bosuyun.platform.common.exception.BizzException;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.OptionalLong;
import java.util.stream.Stream;

/**
 * Created by liuyuancheng on 2020/9/9  <br/>
 */
@Slf4j
public class DateUtils {

    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter dFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String toDateTimeString(LocalDateTime time) {
        return time.format(fmt);
    }

    public static LocalDateTime parseDatetime(String time) {
        return LocalDateTime.parse(time, fmt);
    }

    public static LocalDate parseDate(String time) {
        try {
            return LocalDate.parse(time, dFmt);
        } catch (Exception e) {
            throw new BizzException(String.format("日期%s解析为格式%s失败", time, dFmt));
        }
    }

    public static LocalDate parseDateTime(String time) {
        try {
            return LocalDate.parse(time, fmt);
        } catch (Exception e) {
            throw new BizzException(String.format("日期%s解析为格式%s失败", time, dFmt));
        }
    }

    /**
     * 取一个最小的时间
     *
     * @param times
     * @return
     */
    public static LocalDateTime minLocalDateTime(LocalDateTime... times) {
        OptionalLong minTimestamp = Stream.of(times).mapToLong(e -> (e.toInstant(ZoneOffset.of("+8"))).toEpochMilli()).min();
        if (minTimestamp.isPresent()) {
            log.info("最小时间戳：{}", minTimestamp.getAsLong());
            return getDateTimeOfTimestamp(minTimestamp.getAsLong());
        }
        return null;
    }

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneOffset.of("+8"));
    }

    public static Date getNextDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //取今天日期,如果日期类型为String类型,可以使用df.parse()方法,转换为Date类型
        Calendar calendar = Calendar.getInstance();//new一个Calendar类,把Date放进去
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);//实现日期加一操作,也就是明天
        //控制台打印的日期为明天日期,2019-06-11
        System.out.println("明天的日期为:" + df.format(calendar.getTime()));
        //此时的日期为明天的日期,要实现昨天,日期应该减二
        calendar.add(Calendar.DATE, -2);
        return calendar.getTime();
    }
}
