package com.nts.orb1.service;

public class GetDayName {

	public static String getName(int day) {

		String dayName = null;

		switch (day) {

		case 1:
			dayName = "Sunday";
			break;
		case 2:
			dayName = "Monday";
			break;
		case 3:
			dayName = "Tuesday";
			break;
		case 4:
			dayName = "Wednesday";
			break;
		case 5:
			dayName = "Thursday";
			break;
		case 6:
			dayName = "Friday";
			break;
		case 7:
			dayName = "Saturday";
			break;
		}

		return dayName;

	}

	public static void main(String[] args) {
		System.out.println(getName(4));
	}

}
