package com.nts.grb.service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CheckTime {

	static boolean check(String s) throws ParseException {

		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
															// ***** Calculating Server Date & Time UTC *****
		String date = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
		ZoneId india = ZoneId.of("Asia/Calcutta");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localtDateAndTime = LocalDateTime.parse(date, formatter);
		ZonedDateTime dateAndTimeInSydney = ZonedDateTime.of(localtDateAndTime, india);
		ZonedDateTime utcDate = dateAndTimeInSydney.withZoneSameInstant(ZoneOffset.UTC);
		
		DateTimeFormatter formatteddate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sttdate = utcDate.format(formatteddate);     // Getting  Server UTC Date & Time INTO DATE AS a String
		int stdate = Integer.valueOf(sttdate.replaceAll("-", "")); //Replace All Special Symbol From Server Date For Compare
		System.out.println("Client date and Time in UTC :" + s);
		System.out.println("Server Current date and Time in IST : " + date);
		System.out.println("Server date and time in UTC : " + utcDate);
		
		DateTimeFormatter formattedtime = DateTimeFormatter.ofPattern("HH:mm");
		String sttime = utcDate.format(formattedtime);		// Getting  Server UTC Date & Time INTO Time AS a String
		Date stime = sdf.parse(sttime);
		cal.setTime(stime);
		int smins = cal.get(Calendar.HOUR) * 60 + cal.get(Calendar.MINUTE); //Convert Server Time In Minutes 
		System.out.println("Server Time In Minutes :- "+smins);
		
		
												// ***** Calculating Client Date & Time From String *****
		String client = s;
		String cttdate = "";
		char[] c = client.toCharArray();
		for (int i = 0; i < 10; i++) {		
			cttdate = cttdate + c[i];			// Getting  Client UTC Date as a String From String
		}
		int ctdate = Integer.valueOf(cttdate.replaceAll("-", "")); //Replace All Special Symbol From Client Date For Compare
		
		String cttime = "";
		for (int i = 11; i <= 15; i++) {
			cttime = cttime + c[i];				// Getting  Client UTC Time as a String From String
		}
		Date ctime = sdf.parse(cttime);
		cal.setTime(ctime);
		int cmins = cal.get(Calendar.HOUR) * 60 + cal.get(Calendar.MINUTE);	//Convert Client Time In Minutes 
		System.out.println("Client Time In Minutes :- "+cmins);

		
		
																	// ***** Comparing Client Date & Time  *****
		if (stdate - ctdate == 0) {

			if (smins - cmins >= -5 && smins - cmins <= 5) {

				return true;

			} else {

				return false;
			}

		} else {

			return false;
		}

	}
}