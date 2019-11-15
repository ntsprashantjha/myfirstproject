package com.nts.annexvi.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.util.DaoUtil;
import com.nts.annexvi.service.GetTableName;
import com.nts.annexvi.service.Getting_homeData;

public class HomeDataFullReportById {

	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "";

	static JSONArray entryid;
	String[] tablename = { "" };

	static int minentryid;
	static int maxentryid;

//			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_m_opt_c11_4_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",

	static ResultSet resultSet;

	public static JSONArray getStopIncinerationAcc(Connection con) throws Exception {
		JSONArray obj;
		JSONArray data = new JSONArray();

		for (int id = maxentryid; id >= minentryid; id--) {

//			System.out.println("Max - " + maxentryid);
//			System.out.println("Min -" + minentryid);

			obj = new Getting_homeData().getDBDtls(id, con);

			if (obj.length() != 0) {
				data.put(obj.get(0));
			}
		}
		return data;
	}

	public static JSONArray data_dtls(String startdate, String enddate, Connection con) throws Exception {

		JSONArray obj2 = new JSONArray();
		String[] entry_type_name1 = GetTableName.getAllTableName();

		for (int i = 0; i < entry_type_name1[i].length(); i++) {
			JSONArray obj = new JSONArray();
			/*
			 * String sqlquery = "select * from " + entry_type_name1[i] +
			 * " where entry_date between '" + startdate + "' and '" + enddate + "'";
			 */
			String sqlquery = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	"
					+ entry_type_name1[i]
					+ " dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.start_date between '"
					+ startdate + " ' and '" + enddate + "' ORDER BY entry_id DESC";
			// String sqlquery=sql[i];

			ps = con.prepareStatement(sqlquery);
			rs = ps.executeQuery();
			obj = DaoUtil.convertToJsonArray1(rs);

			for (int j = 0; j <= obj.length() - 1; j++) {
				obj2.put(obj.get(j));
			}

//			System.out.println("json data via objcet2:--" + obj2);
		}

		return obj2;
	}

	@SuppressWarnings("null")
	public static JSONArray entry_id(String startdate, String enddate, Connection con,String entry_type) {

		JSONArray obj = null;

		//System.out.println("Entry - " + entry_type);
		String entry_type_name1 = GetTableName.getDbTableName(entry_type);

		try {

				String sqlquery = "select min(entry_id) as minid,max(entry_id) as maxid from " + entry_type_name1
						+ " where date between '" + startdate + "' and '" + enddate + "'";

//				System.out.println("Start Date :- "+startdate);
//				System.out.println("End Date :- "+enddate);
//
				
				ps = con.prepareStatement(sqlquery);
				rs = ps.executeQuery();

				if(rs.next()) {
					
					minentryid = rs.getInt(1);
					maxentryid = rs.getInt(2);
					
					
				}


			obj = getStopIncinerationAcc(con);
		}

		catch (SQLException e) {
			e.printStackTrace();
			JSONObject sql = new JSONObject();
			try {
				sql.put("aa", "aa");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			obj.put(sql);

		}

		catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
			JSONObject sql = new JSONObject();
			try {
				sql.put("aa", "aa");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			obj.put(sql);

		}
//		System.out.println("***************-----------------" + obj);
		return obj;
	}

	public static int max(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public static int min(int[] array) {
		int min = array[0];

		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

}