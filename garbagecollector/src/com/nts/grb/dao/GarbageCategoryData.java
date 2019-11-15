package com.nts.grb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
	String[] garbage_category_type = { "B", "G", "A", "D", "E", "F", "H", "I" };
	String[] datearr = { "2019-06-10", "2019-06-11", "2019-06-12", "2019-06-13", "2019-06-14", "2019-06-15",
			"2019-06-16", "2019-06-17" };

	public String bs(String strt_date, String end_date) throws Exception {
		JSONObject an2 = new JSONObject();
		JSONArray aa = new JSONArray();
		con = dbConnection.getConnection();
		for (int k = 0; k <= datearr.length - 1; k++) {
			JSONObject an1 = new JSONObject();
			for (int i = 0; i <= garbage_category_type.length - 1; i++) {
				JSONObject an = new JSONObject();
				System.out.println(garbage_category_type[i]);
				for (int j = 0; j <= entry_type_name.length - 1; j++) {

					String[] abccc = entry_type_name1[j].split("_");
					String sql_qury = "SELECT  SUM(D.garbage_quantity_" + abccc[2] + ") As quan FROM "
							+ entry_type_name[j] + " D WHERE D.garbage_category LIKE'" + garbage_category_type[i]
							+ "%' AND entry_date='" + datearr[k] + "'";
					stmt = con.createStatement();
					rset = stmt.executeQuery(sql_qury);
					String as = DaoUtil.categoryBiseTable(rset);
				    an.put(entry_type_name[j], as);
					System.out.println("value from first for loop:--" + an);
				}
				an1.put(garbage_category_type[i], an);
				an = null;
				System.out.println("value from Second for loop:--" + an1);
			}
			an2.put(datearr[k], an1);
			System.out.println("value from End for loop:--" + an2);

		}
		aa.put(an2);
		System.out.println(aa);

		return aa.toString();

	}

	public static void main(String arg[]) throws Exception {
		String data = new GarbageCategoryData().bs("2019-06-10", "2019-06-10");
	}

}
