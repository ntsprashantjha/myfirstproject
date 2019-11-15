package com.nts.orb1.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonOtherServices {

	public static String getDD_MMM_YYYYFormat(String date) {
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = myFormat.parse(date);
			SimpleDateFormat DateFor = new SimpleDateFormat("dd-MMM-yyyy");
			String stringDate = DateFor.format(date1);
//			System.out.println(stringDate);
			return stringDate;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static String getDDMMMYYYYFormat(String date) {
		try {
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date1 = myFormat.parse(date);
			SimpleDateFormat DateFor = new SimpleDateFormat("dd-MMM-yyyy");
			String stringDate = DateFor.format(date1);
//			System.out.println(stringDate);
			return stringDate;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public static String getTimeOnly(String date) {

		String temp = date;
		String temp2 = "";

		for (int i = 11; i <= 18; i++) {

			temp2 += temp.charAt(i);

		}
		return temp2;

	}

	public static void main(String[] args) {
		String date[] = "2019/11/11 15:13:47".split(" ");
		System.out.println(getDDMMMYYYYFormat(date[0]));
	}

}
