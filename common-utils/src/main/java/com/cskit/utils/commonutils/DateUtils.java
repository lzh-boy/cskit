package com.cskit.utils.commonutils;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期处理工具类
 *
 * @author 董争光 2018年5月21日下午1:47:48
 */
public class DateUtils implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4016651052102523560L;

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {

    }

    public static Date longFormatDate(Long time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = format.format(time);
            return format.parse(d);
        } catch (Exception e) {
            DateUtils.logger.info("", e);
            return null;
        }
    }

    public static String dateFormatTime(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String dateFormatDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getStringNoDate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static Date getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }


    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     *
     * @throws java.text.ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     *
     * @throws java.text.ParseException
     */
    public static String getNowYearMothDay() {
        String dString = "";
        try {
            Calendar now = Calendar.getInstance();
            dString = String.valueOf(now.get(Calendar.YEAR)) + String.valueOf((now.get(Calendar.MONTH) + 1))
                    + String.valueOf(now.get(Calendar.DAY_OF_MONTH)) + String.valueOf(now.get(Calendar.HOUR_OF_DAY))
                    + String.valueOf(now.get(Calendar.MINUTE)) + String.valueOf(now.get(Calendar.SECOND));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dString;
    }

    public static String formatStringByPattern(String date) {
        DateUtils.logger.info("date:" + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = "";
        try {
            dateTimeStr = format1.format(format.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }


        DateUtils.logger.info("result:" + dateTimeStr);
        return dateTimeStr;
    }

    public static String DateFormatString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = sdf.format(date);
        return newDate;
    }

    public static String DateFormatString2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String newDate = sdf.format(date);
        return newDate;
    }

    public static Date StringFormatDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;
    }

    public static Date StringFormatTime(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date;
    }

    public static long StringFormartLong(String dateStr) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateStr);

            return date.getTime();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMonth(Integer addMonth, String dataFormat) {
        if (StringUtils.isEmpty(dataFormat)) {
            dataFormat = "yyyy-MM";
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, addMonth);
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        String time = format.format(c.getTime());
        return time;
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss类型的当前时间
     */
    public static String getNowDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        return df.format(new Date());
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwoCount(Date fDate, Date lDate) {

        Calendar aCalendar = Calendar.getInstance();

        aCalendar.setTime(fDate);

        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

        aCalendar.setTime(lDate);

        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

        return day2 - day1;

    }

    public static Date getTodayStartTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = format.format(date);
        Date startTime = null;
        try {
            startTime = sdf.parse(sDate + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startTime;
    }

    public static Date getTodayEndTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = format.format(date);
        Date endTime = null;
        try {
            endTime = sdf.parse(sDate + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return endTime;
    }


    public static Date getThisMonthStartTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = format.format(date);
        Date startTime = null;
        try {
            startTime = sdf.parse(sDate + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startTime;
    }

    public static Date getThisMonthEndTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = format.format(date);
        Date endTime = null;
        try {
            endTime = sdf.parse(sDate + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return endTime;
    }


    public static Date getSpecifiedDayBefore(Date date, int before) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = format.format(date);
        Date formatDate = null;
        try {
            formatDate = format.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c.setTime(formatDate);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - before);

        return c.getTime();
    }

    public static Date getFormateStringDate(String str) {
        Date date = null;
        try {
            date = org.apache.commons.lang3.time.DateUtils.parseDate(str, new String[] {"yyyy-MM-dd"});
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // 30/11 10:31:00
    public static Date getFormateDate(String str) {
        Date date = null;
        str = str.substring(0, 5);
        String[] strSplit = str.split("/");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        str = year + strSplit[1] + strSplit[0];
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getSpecifiedDayBefore(String specifiedDay) {
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    public static String translationWeekday(Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = format.format(date);
            Calendar c = Calendar.getInstance();
            c.setTime(format.parse(formatDate));
            int dayForWeek = 0;
            if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
            }
            String weekday = "";
            switch (dayForWeek) {
                case 0:
                    weekday = "星期天";
                    break;
                case 1:
                    weekday = "星期一";
                    break;
                case 2:
                    weekday = "星期二";
                    break;
                case 3:
                    weekday = "星期三";
                    break;
                case 4:
                    weekday = "星期四";
                    break;
                case 5:
                    weekday = "星期五";
                    break;
                case 6:
                    weekday = "星期六";
                    break;
                case 7:
                    weekday = "星期日";
                    break;
            }
            return weekday;
        } catch (Exception e) {
            return null;
        }

    }
}
