package testing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDate {
	private static String currentDate, oneMonthEndDate;

	public static void main(String args[]) {
		final int YEAR = -365;
		final int MONTH = -30;

		Date date = new Date();
//System.out.println(getPastDate(date, YEAR));//for get last month 
		System.out.println(getPastDate(date, MONTH));
		oneMonthEndDate = getPastDate(date, MONTH);

	}

	public static String getPastDate(Date day, int interval) {
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Calendar c = Calendar.getInstance();
			c.setTime(day);
			currentDate = dateFormat.format(c.getTime());
			c.add(Calendar.DATE, interval); // not sure

			return dateFormat.format(c.getTime());

		} catch (Exception exp) {
			return null;
		}
	}
}