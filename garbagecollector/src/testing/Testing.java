package testing;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Date> dates = new ArrayList<Date>();
		String[] dateformater = {};
		String frmDt = "2019-06-10".replaceAll("-", "/");
		String toDt = "2019-07-15".replaceAll("-", "/");
		String dateOfPdf;
		dateformater = frmDt.split("/");
		dateOfPdf = dateformater[2] + "-" + dateformater[1] + "-" + dateformater[0];
		String str_date = dateOfPdf.replace("-","/");
		dateformater = toDt.split("/");
		dateOfPdf = dateformater[2] + "-" + dateformater[1] + "-" + dateformater[0];
		String end_date = dateOfPdf.replace("-","/");
		System.out.println(str_date+"\n"+end_date);

		DateFormat formatter;

		formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = null;
		try {
			startDate = (Date) formatter.parse(str_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endDate = null;
		try {
			endDate = (Date) formatter.parse(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
		long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
		long curTime = startDate.getTime();
		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}
		for (int i = 0; i < dates.size(); i++) {
			Date lDate = (Date) dates.get(i);
			String ds = formatter.format(lDate);
			System.out.println(" Date is ..." + ds);
		}

	}

}