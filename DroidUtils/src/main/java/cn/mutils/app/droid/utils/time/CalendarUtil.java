package cn.mutils.app.droid.utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class CalendarUtil {

    /**
     * 24小时格式化
     */
    public static final String TEMPLATE_ALL_24 = "yyyy-MM-dd HH:mm:ss";

    /**
     * 12小时格式化
     */
    public static final String TEMPLATE_ALL_12 = "yyyy-MM-dd hh:mm:ss";

    /**
     * 精确到天格式化
     */
    public static final String TEMPLATE_DAY = "yyyy-MM-dd";

    /**
     * 获取两个时间之间的天数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 天数
     */
    public static long getDays(Date start, Date end) {
        return (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取当前月份 一月返回1
     *
     * @param date 时间
     * @return 月份
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前月份中的日期
     *
     * @param date 时间
     * @return 日期
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取星期对应数字
     *
     * @param date 时间
     * @return 星期一返回1 星期二返回2 ... 星期日返回7
     */
    public static int getDayOfWeek(Date date) {
        return getDayOfWeek(date, 7);
    }

    /**
     * 获取星期对应数字
     *
     * @param date      时间
     * @param defSunday 星期日数字
     * @return 星期一返回1 星期二返回2 ...
     */
    public static int getDayOfWeek(Date date, int defSunday) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            case Calendar.SUNDAY:
                return defSunday;
            default:
                return 0;
        }
    }

    /**
     * 格式化
     *
     * @param date     日期
     * @param template 模板格式
     * @return 格式化输出
     */
    public static String format(Date date, String template) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(template);
        return dateFormat.format(date);
    }

    /**
     * 格式化
     *
     * @param time     时间
     * @param template 模板代码
     * @return 格式化输出
     */
    public static String format(long time, String template) {
        return format(new Date(time), template);
    }

}
