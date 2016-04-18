package com.haoxitech.canzhaopin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangtao on 16/2/29.
 */
public class DateUtils {

    public static Date getShortDateFromString(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateFromString(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringDateFromDate(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }

    public static String getStringDateAndTimeFromDate(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }

    public static long getTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(time);
            return date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getTime(Date time) {
        return time.getTime() / 1000;
    }

    public static boolean isToday(String time) {
        boolean b = false;
        if (time != null) {
            if (getShortDateFromString(time).equals(getShortDateFromString(getStringDateAndTimeFromDate(new Date())))) {
                b = true;
            }
        }
        return b;
    }

    public static boolean isYestoday(String time) {
        boolean b = false;
        if (time != null) {

        }
        return b;
    }

    public static String formatDisplayTime(String time) {

        long timeSeconds = getShortDateFromString(getStringDateFromDate(getDateFromString(time))).getTime() / 1000;
        long currentTimeSeconds = getShortDateFromString(getStringDateFromDate(new Date())).getTime() / 1000;
        if (timeSeconds > currentTimeSeconds) {
            return null;
        } else {

            int day = (int)(currentTimeSeconds - timeSeconds) / 86400;
            if (day == 0) {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                return format.format(getDateFromString(time));
            } else if (day == 1) {
                return "昨天";
            } else if (day >= 2 && day < 7) {
                Calendar c = Calendar.getInstance();
                c.setTime(getDateFromString(time));
                String result = "";

                switch (c.get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        result = "星期日";
                        break;
                    case 2:
                        result = "星期一";
                        break;
                    case 3:
                        result = "星期二";
                        break;
                    case 4:
                        result = "星期三";
                        break;
                    case 5:
                        result = "星期四";
                        break;
                    case 6:
                        result = "星期五";
                        break;
                    case 7:
                        result = "星期六";
                        break;
                    default:
                        break;
                }
                return result;
            } else {

                SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
                return format.format(getDateFromString(time));
            }
        }
    }

    public static String formatDisplayTime(String time, boolean showTime) {

        long timeSeconds = getShortDateFromString(getStringDateFromDate(getDateFromString(time))).getTime() / 1000;
        long currentTimeSeconds = getShortDateFromString(getStringDateFromDate(new Date())).getTime() / 1000;
        if (timeSeconds > currentTimeSeconds) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String timeString = format.format(getDateFromString(time));
            int day = (int)(currentTimeSeconds - timeSeconds) / 86400;
            if (day == 0) {
                return timeString;
            } else if (day == 1) {
                if (showTime) {
                    return "昨天" +timeString;
                } else
                    return "昨天";
            } else if (day >= 2 && day < 7) {
                Calendar c = Calendar.getInstance();
                c.setTime(getDateFromString(time));
                String result = "";

                switch (c.get(Calendar.DAY_OF_WEEK)) {
                    case 1:
                        result = "星期日";
                        break;
                    case 2:
                        result = "星期一";
                        break;
                    case 3:
                        result = "星期二";
                        break;
                    case 4:
                        result = "星期三";
                        break;
                    case 5:
                        result = "星期四";
                        break;
                    case 6:
                        result = "星期五";
                        break;
                    case 7:
                        result = "星期六";
                        break;
                    default:
                        break;
                }
                if (showTime) {
                    result += "" + timeString;
                }
                return result;
            } else {

                SimpleDateFormat format1;
                if (showTime) {
                    format1 = new SimpleDateFormat("yy/MM/dd HH:mm");
                } else {
                    format1 = new SimpleDateFormat("yy/MM/dd");
                }
                return format1.format(getDateFromString(time));
            }
        }
    }
}
