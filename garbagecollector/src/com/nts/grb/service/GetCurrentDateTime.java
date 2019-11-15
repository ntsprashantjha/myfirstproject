package com.nts.grb.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentDateTime {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static String currentDate () {

        Date currentdate = new Date();
		return sdf.format(currentdate);
        
    }
    public static String onlycurrentDate () {
    DateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date currentdate = new Date();
		return sdf1.format(currentdate);
        
    }
    
public static void main(String arg[])
{
	System.out.println(GetCurrentDateTime.currentDate());
}
}