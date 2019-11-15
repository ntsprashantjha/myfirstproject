package com.nts.orb1.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class GetTimeDifference {

	public static int compareStrikeTime(String strikeDate, String approvalDate) {

		try {

			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);

			Date startdateobj = format.parse(strikeDate);// Sat Jan 02 00:00:00 GMT 2010
			Date stopdateobj = format.parse(approvalDate);

			int x = startdateobj.compareTo(stopdateobj);

			System.out.println(x);
			return x;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static JSONObject printDifference(Date startDate, Date endDate) {

		try {

			// milliseconds
			long different = endDate.getTime() - startDate.getTime();

			long secondsInMilli = 1000;
			long minutesInMilli = secondsInMilli * 60;
			long hoursInMilli = minutesInMilli * 60;

			long elapsedHours = different / hoursInMilli;
			different = different % hoursInMilli;

			long elapsedMinutes = different / minutesInMilli;
			different = different % minutesInMilli;

			long elapsedSeconds = different / secondsInMilli;

			JSONObject diffrence = new JSONObject();
			diffrence.put("Hours", elapsedHours);
			diffrence.put("Minutes", elapsedMinutes);
			diffrence.put("Seconds", elapsedSeconds);

			return diffrence;
		} catch (JSONException e) {

			e.printStackTrace();
			return null;
		}

	}

	public static JSONObject findDifference(String startdate, String stopdate) {
		try {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);

			Date startdateobj = format.parse(startdate);// Sat Jan 02 00:00:00 GMT 2010

			Date stopdateobj = format.parse(stopdate);

			return printDifference(startdateobj, stopdateobj);

		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static int getHours(String startdate, String stopdate) throws JSONException {

		JSONObject hours = findDifference(startdate, stopdate);
		return hours.getInt("Hours");

	}

	public static int getMinutes(String startdate, String stopdate) throws JSONException {

		JSONObject Minutes = findDifference(startdate, stopdate);
		return Minutes.getInt("Minutes");

	}

	public static int getSeconds(String startdate, String stopdate) throws JSONException {

		JSONObject Seconds = findDifference(startdate, stopdate);
		return Seconds.getInt("Seconds");

	}

	public static int getTotalTimeInMinutes(String startdate, String stopdate) throws JSONException {

		JSONObject GTTIM = findDifference(startdate, stopdate);
		int hour = GTTIM.getInt("Hours");
		hour = hour * 60;
		int minute = GTTIM.getInt("Minutes");
		int totalminutes = minute + hour;
		return totalminutes;

	}

	public static void main(String[] args) throws ParseException, JSONException {
		{
			String string = "2019-09-17 14:47";
			String string2 = "2019-09-16 14:46";
			System.out.println(findDifference(string2, string));
			System.out.println(getHours(string2, string));
			System.out.println(getTotalTimeInMinutes(string2, string));
		}
	}

}