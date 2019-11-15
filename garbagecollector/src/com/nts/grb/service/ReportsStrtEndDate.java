package com.nts.grb.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportsStrtEndDate {
	private static String currentDate, oneMonthEndDate;
	final int YEAR = -365;
	final static int MONTH = -30;

	public static String getPastDate(Date day, int interval) {
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(day);
			currentDate = dateFormat.format(c.getTime());
			c.add(Calendar.DATE, interval);
			return dateFormat.format(c.getTime()) + "and" + currentDate;

		} catch (Exception exp) {
			return null;
		}
	}
}