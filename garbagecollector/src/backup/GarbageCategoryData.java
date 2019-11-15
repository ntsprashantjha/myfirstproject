package backup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class GarbageCategoryData {
	Connection con = null;
	Statement stmt = null;
	ResultSet rset = null;
	String[] entry_type_name1 = { "discharge_to_sea", "discharge_to_ashore", "stop_incineration_incinerated" };
	String[] entry_type_name = { "discharge_to_sea", "discharge_to_ashore", "stop_incineration" };
	String[] garbage_category_type = { "B", "G", "A", "D", "E", "F", "H", "I", "C" };

	public String bs(String strt_date, String end_date) throws Exception {
		List<String> datearray = aldtsBtTwoDts(strt_date,end_date);
		JSONObject an2 = new JSONObject();
		JSONObject an3 = new JSONObject();
		JSONArray aa = new JSONArray();
		JSONArray aa1 = new JSONArray();
		con = dbConnection.getConnection();
		JSONObject an0=null;
		for (int k = 0; k <= datearray.size() - 1; k++) {
			 an0 = new JSONObject();
			for (int i = 0; i <= garbage_category_type.length - 1; i++) {
				
				JSONObject an = new JSONObject();
				for (int j = 0; j <= entry_type_name.length - 1; j++) {
					String[] abccc = entry_type_name1[j].split("_");
					String sql_qury = "SELECT  SUM(D.garbage_quantity_" + abccc[2] + ") As quan FROM "
							+ entry_type_name[j] + " D WHERE D.garbage_category LIKE'" + garbage_category_type[i]
							+ "%' AND entry_date ='" + datearray.get(k).replace("/", "-") + "'";
					stmt = con.createStatement();
					rset = stmt.executeQuery(sql_qury);
					String abc = DaoUtil.categoryBiseTable(rset);
					if (abc.equals("null")) {
						abc = "0";
					}
					an.put(entry_type_name[j], abc);
					
					

				}
				an2.put(garbage_category_type[i], an);
				
				aa1.put(an2);
			}
			System.out.println(aa1);
			an0.put("date",  datearray.get(k).replace("/", "-"));
			an0.put("data", aa1.get(k));
			aa.put(an0);
		}
		System.out.println("aaaa=="+aa);

		for(int i=0;i<aa.length();i++)
		{
			an3.append("fulldata", aa.get(i));
		}
		return 	an3.toString();

	}

	public JSONObject totalData(String a, String b) throws Exception {
		JSONArray aa = new JSONArray();
		con = dbConnection.getConnection();
		JSONObject an1 = new JSONObject();
		for (int i = 0; i <= garbage_category_type.length - 1; i++) {
			JSONObject an = new JSONObject();
			for (int j = 0; j <= entry_type_name.length - 1; j++) {
				String[] abccc = entry_type_name1[j].split("_");
				String sql_qury = "SELECT  SUM(D.garbage_quantity_" + abccc[2] + ") As quan FROM " + entry_type_name[j]
						+ " D WHERE D.garbage_category LIKE'" + garbage_category_type[i]
						+ "%' AND entry_date BETWEEN '2019-06-10' AND '2019-06-12'";
				stmt = con.createStatement();
				rset = stmt.executeQuery(sql_qury);
				String abc = DaoUtil.categoryBiseTable(rset);
				if (abc.equals("null")) {
					abc = "0";
				}
				an.put(entry_type_name[j], abc);
			}
			an1.put(garbage_category_type[i], an);
			System.out.println(an1);
		}
		aa.put(an1);
		JSONObject rrr = new JSONObject(aa);
		System.out.println(aa);
		return an1;
	}

	public List<String> aldtsBtTwoDts(String st_date,String end_dt) {
		// TODO Auto-generated method stub
		List<Date> dates = new ArrayList<Date>();
		List<String> dates1 = new ArrayList<String>();
		String[] dateformater = {};
		String[] dts = new String[500];
		String frmDt = st_date.replaceAll("-", "/");
		String toDt = end_dt.replaceAll("-", "/");
		String dateOfPdf;
		dateformater = frmDt.split("/");
		dateOfPdf = dateformater[2] + "-" + dateformater[1] + "-" + dateformater[0];
		String str_date = dateOfPdf.replace("-", "/");
		dateformater = toDt.split("/");
		dateOfPdf = dateformater[2] + "-" + dateformater[1] + "-" + dateformater[0];
		String end_date = dateOfPdf.replace("-", "/");
		System.out.println(str_date + "\n" + end_date);

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
			dates1.add(ds);
			System.out.println(" Date is ..." + ds);
		}
		return dates1;

	}

	public static void main(String arg[]) throws Exception {
		System.out.println(new GarbageCategoryData().aldtsBtTwoDts("2019-06-10", "2019-06-17"));

	}

}