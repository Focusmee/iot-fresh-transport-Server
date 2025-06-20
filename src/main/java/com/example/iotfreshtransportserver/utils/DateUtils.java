package com.example.iotfreshtransportserver.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	/**
	 * 获取当前系统时间
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * Date日期转String日期
	 * @param date 日期
	 * @param pattern 格式 例如：yyyy-MM-dd HH:mm:ss
	 */
	public static String toString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * String日期转Date日期
	 * @param date 日期
	 * @param pattern 格式 例如：yyyy-MM-dd HH:mm:ss
	 */
	public static Date toDate(String date, String pattern) throws Exception {
		return new SimpleDateFormat(pattern).parse(date);
	}

	/**
	 * 增加月份
	 * @param date 日期
	 * @param count 月份数
	 */
	public static Date addMonths(Date date, int count){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, count);
		return calendar.getTime();
	}

	/**
	 * 增加天数
	 * @param date 日期
	 * @param days 天数
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,days);
		return calendar.getTime();
	}

	/**
	 * 增加小时
	 * @param date 日期
	 * @param count 小时数
	 */
	public static Date addHours(Date date, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, count);
		return calendar.getTime();
	}

	/**
	 * 增加分钟
	 * @param date 日期
	 * @param count 分钟数
	 */
	public static Date addMinutes(Date date, int count){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, count);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期处于一周的周几
	 * @param date 日期
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day != 1 ? day - 1 : 7;
	}

	/**
	 * 获取当前日期在所处月份的总天数
	 * @param date 日期
	 */
	public static int getMonthTotalDays(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前日期在所处月份中的第几天
	 * @param date 日期
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}


	/**
	 * 获取日期之间
	 *
	 * @param fromDate 从日期
	 * @param toDate   到目前为止
	 * @return {@link List}<{@link Date}>
	 */
	public static List<Date> getBetweenDates(Date fromDate, Date toDate) {
		List<Date> dates = new ArrayList<>();

		while (fromDate.getTime() <= toDate.getTime()) {
			dates.add(fromDate);
			fromDate = DateUtils.addDays(fromDate, 1);
		}

		return dates;
	}

	public static LocalDateTime longToLocalDateTime(long time) {
		Instant instant = Instant.ofEpochMilli(time);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return localDateTime;
	}
}
