package com.air.weekchoose;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 */
public class DateUtil {

    //三种形式的日期
    public static final String ymdhms = "yyyy-MM-dd HH:mm:ss";
    public static final String ymd = "yyyy-MM-dd";
    public static final String ym = "yyyy-MM";

    //阿拉伯数字到中文名字的映射
    public static String monthNumToMonthName(String month) {
        String m = month;
        if ("1".equals(month)) {
            m = "一月份";
        } else if ("2".equals(month)) {
            m = "二月份";
        } else if ("3".equals(month)) {
            m = "三月份";
        } else if ("4".equals(month)) {
            m = "四月份";
        } else if ("5".equals(month)) {
            m = "五月份";
        } else if ("6".equals(month)) {
            m = "六月份";
        } else if ("7".equals(month)) {
            m = "七月份";
        } else if ("8".equals(month)) {
            m = "八月份";
        } else if ("9".equals(month)) {
            m = "九月份";
        } else if ("10".equals(month)) {
            m = "十月份";
        } else if ("11".equals(month)) {
            m = "十一月份";
        } else if ("12".equals(month)) {
            m = "十二月份";
        }
        return m;
    }


    //本周开始结束时间 周一到周日,返回的是一个String字符串，包括开始时间和结束时间
    //本周开始时间到结束时间
    public static List<String> getWeekBeginAndEndTime() {

        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.add(Calendar.DATE, -1);//如果当前时间是星期日,减去一天再获取周一,否则获取的是下周一日期
        }
        cal.set(Calendar.DAY_OF_WEEK, 2);
        String beginTime = formatDate(cal.getTime().getTime(), ymd);
        String endDate = "";
        cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            endDate = formatDate(cal.getTime().getTime(), ymd);//如果当前时间是星期日则结束时间为当前时间
        } else {
            cal.set(Calendar.DAY_OF_WEEK, 7);
            cal.add(Calendar.DATE, 1);
            endDate = formatDate(cal.getTime().getTime(), ymd);
        }
        List<String> list = new ArrayList<>();
        list.add(beginTime);
        list.add(endDate);
        return list;
    }

    /**
     * 上周开始结束时间 上周周一到上周周日
     *
     * @return
     */
    //获得上周的开始和结束时间，上周一到上周日
    public static List<String> getLastWeekBeginAndEndTime() {
        Calendar cal = Calendar.getInstance();

        String endTime = "";
        String beginTime = "";
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            cal.set(Calendar.DAY_OF_WEEK, 1);
            cal.add(Calendar.WEEK_OF_MONTH, -1);
            endTime = formatDate(cal.getTime().getTime(), ymd);

            cal.add(Calendar.WEEK_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_WEEK, 2);
            beginTime = formatDate(cal.getTime().getTime(), ymd);

        } else {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            cal.set(Calendar.DAY_OF_WEEK, 1);
            endTime = formatDate(cal.getTime().getTime(), ymd);

            cal.add(Calendar.WEEK_OF_MONTH, -1);
            cal.set(Calendar.DAY_OF_WEEK, 2);
            beginTime = formatDate(cal.getTime().getTime(), ymd);
        }

        List<String> list = new ArrayList<>();
        list.add(beginTime);
        list.add(endTime);

        return list;
    }

    /**
     * 取月开始结束时间
     *
     * @param month 0为本月 -1为上月 1为下月
     * @return
     */
    //获得月的开始和结束时间，参数为0的时候代表本月，-1代表上个月，1代表下个月
    public static List<String> getMonthBeginAndEndTime(int month) {
        Calendar calendar = new GregorianCalendar();
        if (month != 0) {
            calendar.add(Calendar.MONTH, month);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date1 = calendar.getTime();
        String beginDate = formatDate(date1.getTime(), ymd);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date date2 = calendar.getTime();
        String endDate = formatDate(date2.getTime(), ymd);

        List<String> list = new ArrayList<>();
        list.add(beginDate);
        list.add(endDate);
        return list;
    }

    //获得明天的日期 year-month-day形式
    public static String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month > 9 ? month : ("0" + month)) + "-" + day;
    }
    //获得特定日期的第二天，返回为year-month-day形式
    public static String getDayTomorrow(int Y, int M, int D) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Y, M, D);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return (month > 9 ? month : ("0" + month)) + "月" + (day > 9 ? day : ("0" + day));
    }

    //获得年份
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    //获得月份
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }
    //获得日
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + 1;
    }
    //获得月份的字符串形式，如果小于是十月份，在前面加0
    public static String getMonthMM() {

        String mm;
        int mNum = DateUtil.getMonth();
        if (mNum < 10) mm = "0" + mNum;
        else mm = mNum + "";

        return mm;
    }

    //获得今天的日期，年-月-日形式
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + (month > 9 ? month : ("0" + month)) + "-" + (day > 9 ? day : ("0" + day));
    }

    //将日期封装成一个整型数组
    public static List<Integer> getDateForString(String date) {
        String[] dates = date.split("-");
        List<Integer> list = new ArrayList<>();
        list.add(Integer.parseInt(dates[0]));
        list.add(Integer.parseInt(dates[1]));
        list.add(Integer.parseInt(dates[2]));
        return list;
    }

    //通过Date获得一个Calender对象
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    //根据format来对日期的形式加工
    public static String formatDate(String date, String format) {
        String resultD = date;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date d = sdf.parse(date);
            resultD = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultD;
    }
    //根据format将毫秒转换为对应的日期
    public static String formatDate(long milliseconds, String format) {
        String resultD = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date d = new Date(milliseconds);
            resultD = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultD;
    }

    public static Date formatDateStr(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date1 = null;
        try {
            date1 = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * 通过年份和月份 得到当月的yigong日子
     *
     * @param year
     * @param month
     * @return
     */
    //获得月份对应的天数
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }


    //确定当月的一号是礼拜几 //需要注意一下
    public static int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获得当月第一天是礼拜几
    public static int getFirstDayWeek17(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        int c = calendar.get(Calendar.DAY_OF_WEEK);
        c--;
        if (c == 0) c = 7;
        return c;
    }
    //根据年月日知道当天是周一/周二../周日
    public static String getDayWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());

        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "周日";

            case 2:
                return "周一";

            case 3:
                return "周二";

            case 4:
                return "周三";

            case 5:
                return "周四";

            case 6:
                return "周五";

            case 7:
                return "周六";

            default:
                return "";

        }
    }

    //根据字符串形式的日期获得当天是周一/周二..、周日
    public static String getDayWeek(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatDateStr(date, ymd));
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "周日";

            case 2:
                return "周一";

            case 3:
                return "周二";

            case 4:
                return "周三";

            case 5:
                return "周四";

            case 6:
                return "周五";

            case 7:
                return "周六";

            default:
                return "";
        }
    }

    //////
    public static String IntYYYYMMToYYYYGANGMM(int date) {

        String s = date + "";

        StringBuilder sb = new StringBuilder(s);//构造一个StringBuilder对象
        sb.insert(4, "-");//在指定的位置1，插入指定的字符串
        return sb.toString();


    }

    //获得year-month-day形式的日期字符串
    public static String getYYYY_MM_DD(int year, int month, int day) {

        String s = "" + year + "-";
        String m = "";
        String d = "";
        if (month < 10) m = "0" + month;
        else m = month + "";
        if (day < 10) d = "0" + day;
        else d = day + "";
        s = s + m + "-" + d;
        return s;


    }

    //将日期转换成一个整型（年份*100+月份），有了这个整型以后可以将其转换成标准的字符串形式
    public static String getNowYYYYGANGMM() {

        int year = DateUtil.getYear();
        int month = DateUtil.getMonth();
        int z = year * 100 + month;
        return IntYYYYMMToYYYYGANGMM(z);


    }

    //根据日期获得所在周的起始和结束，即礼拜一到礼拜日
    public static int[] getWeekDay(int Y, int M, int D) throws ParseException {

        int[] is = new int[6];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        String s = Y + "-" + M + "-" + D;
        Date time = sdf.parse(s + " 14:22:47");
        cal.setTime(time);
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期


        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        System.out.println("所在周星期一的日期：" + sdf.format(cal.getTime()));
        is[0] = Integer.parseInt(sdf.format(cal.getTime()).split("-")[0]);
        is[1] = Integer.parseInt(sdf.format(cal.getTime()).split("-")[1]);
        is[2] = Integer.parseInt(sdf.format(cal.getTime()).split("-")[2]);


        System.out.println(cal.getFirstDayOfWeek() + "-" + day + "+6=" + (cal.getFirstDayOfWeek() - day + 6));

        cal.add(Calendar.DATE, 6);
        is[3] = Integer.parseInt(sdf.format(cal.getTime()).split("-")[0]);
        is[4] = Integer.parseInt(sdf.format(cal.getTime()).split("-")[1]);
        is[5] = Integer.parseInt(sdf.format(cal.getTime()).split("-")[2]);

        System.out.println("所在周星期日的日期：" + sdf.format(cal.getTime()));

        return is;
    }


}
