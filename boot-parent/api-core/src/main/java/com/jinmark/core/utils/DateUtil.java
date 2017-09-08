package com.jinmark.core.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	
	/**
	 * 得到相对当前时间的某个时间
	 * @param field 时间单位 如Calendar.DAY_OF_YEAR
	 * @param amount 相对当前时间的时间量 正整数为当前时间后 负整数为当前时间前
	 * @return Date
	 */
	public static Date getRelativeDate(Integer field, Integer amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, amount);
		return calendar.getTime();

	}

	public static Date getNextDay() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		return date;
	}

	/**
	 * 把时间格式化为"yyyy年MM月dd日"的形式
	 * @param date 时间
	 * @return String
	 */
	public static String formatDateToChinese(Date date) {
		return new SimpleDateFormat("yyyy年MM月dd日").format(date);
	}
	
	/**
	 * 把时间格式化为"yyyy年MM月dd日 HH:mm"的形式
	 * @param date
	 * @return String
	 */
	public static String formatDateToChinese1(Date date) {
		return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(date);
	}

	/**
	 * 把时间格式化为"yyyy-MM-dd HH:mm:ss"的形式
	 * @param date 时间
	 * @return String
	 */
	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	/**
	 * 把时间格式化为"yyyy-MM-dd HH:mm"的形式
	 * @param date
	 * @return String
	 */
	public static String formatToYyyyMMddHHmm(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	/**
	 * 把时间格式化为"yyyyMMddHHmmss"的形式
	 * @param date 时间
	 * @return String
	 */
	public static String formatAsNoSign(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	/**
	 * 把时间格式化为"yyyy-MM-dd HH:mm:ss.SSS"的形式
	 * @param date 时间
	 * @return String
	 */
	public static String formatAsMilliscond(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
	}

	/**
	 * 把时间格式化为"yyyyMMddHHmmss"的形式
	 * @param date 时间
	 * @return String
	 */
	public static String formatOne(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	public static Date timeToDate(long time) {
		return new Date(time);
	}

	/**
	 * 把毫秒数时间转换成年月日格式的字符串 "yyyy-MM-dd"
	 * @param time 时间
	 * @return String
	 */
	public static String formatToYTDBytime(long time) {
		return formatToYTDByDate(timeToDate(time));
	}

	/**
	 * 把日期格式转换成年月日格式的字符串 "yyyy-MM-dd"
	 * @param date 时间
	 * @return String
	 */
	public static String formatToYTDByDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 将字符串转换为java.util.Date 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD
	 * at 22:10:59 PSD' yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00' yy/MM/dd
	 * HH:mm:ss pm 如 '2002/1/1 17:55:00 pm' yy-MM-dd HH:mm:ss 如 '2002-1-1
	 * 17:55:00' yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am'
	 * @param str 字符串
	 * @return Date
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat formatter;
		int tempPos = str.indexOf("AD");
		str = str.trim();
		formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
		if (tempPos > -1) {
			str = str.substring(0, tempPos) + "公元"
					+ str.substring(tempPos + "AD".length()); // china
			formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
		}
		tempPos = str.indexOf("-");
		if (tempPos > -1 && (str.indexOf(" ") < 0)) {
			formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
		} else if ((str.indexOf("/") > -1) && (str.indexOf(" ") > -1)) {
			formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		} else if ((str.indexOf("-") > -1) && (str.indexOf(" ") > -1)) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if ((str.indexOf("/") > -1) && (str.indexOf("am") > -1)
				|| (str.indexOf("pm") > -1)) {
			formatter = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss a");
		} else if ((str.indexOf("-") > -1) && (str.indexOf("am") > -1)
				|| (str.indexOf("pm") > -1)) {
			formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		}
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(str, pos);
		return date;

	}

}
