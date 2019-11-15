package com.nts.grb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateutil {
	public static Date parseDateTime(String dateString) {

		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		if (dateString.contains("T"))
			dateString = dateString.replace('T', ' ');

		if (dateString.contains("Z"))
			dateString = dateString.replace("Z", "+0000");

		else
			dateString = dateString.substring(0, dateString.lastIndexOf(':'))
					+ dateString.substring(dateString.lastIndexOf(':') + 1);

		try {

			return fmt.parse(dateString);

		}

		catch (ParseException e) {

			return null;
		}
	}

}
