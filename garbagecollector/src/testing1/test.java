package testing1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.orb1.service.GetTimeDifference;

public class test {

	public static void main(String[] args) throws ParseException, JSONException {

		String date1 = "2019/11/11 12:07:17.0";
		String date2 = "2019/11/11 12:07:20.0";

		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);

		Date startdateobj = format.parse(date1);// Sat Jan 02 00:00:00 GMT 2010

		Date stopdateobj = format.parse(date2);
		
		int x = startdateobj.compareTo(stopdateobj);
		
		System.out.println(x);

	}

}
