package com.xz.nba.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang3.time.DateUtils类
 * @author ctc
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	public static String getYM(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM");
	}
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}


	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	/** 
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前 
     *  
     * @return
     */  
    public static String convertTimeToFormat(Date before) {  
            long curTime =System.currentTimeMillis() / (long) 1000 ;  
            long time = curTime - before.getTime();  

            if (time < 60 && time >= 0) {  
                    return "刚刚";  
            } else if (time >= 60 && time < 3600) {  
                    return time / 60 + "分钟前";  
            } else if (time >= 3600 && time < 3600 * 24) {  
                    return time / 3600 + "小时前";  
            } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {  
                    return time / 3600 / 24 + "天前";  
            } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {  
                    return time / 3600 / 24 / 30 + "个月前";  
            } else if (time >= 3600 * 24 * 30 * 12) {  
                    return time / 3600 / 24 / 30 / 12 + "年前";  
            } else {  
                    return "刚刚";  
            }  
    }

	/**
	 * 获取两个月份之间的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDayOfTwoDate(String startDate,String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Date date1 = format.parse(startDate);
			Date date2 = format.parse(endDate);
			int day = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 去年的同一日期
	 * @return
	 */
	public static String sameDateLastYear(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Date newDate = format.parse(date);
			Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
			ca.setTime(newDate); //设置时间为当前时间
			ca.add(Calendar.YEAR, -1); //年份减1
			Date lastYear = ca.getTime();
			return getYM(lastYear);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String sameDateLastYear = sameDateLastYear("2018-1");
		System.out.println(sameDateLastYear);
	}
}
