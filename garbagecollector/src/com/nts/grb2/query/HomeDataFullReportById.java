package com.nts.grb2.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.util.DaoUtil;

public class HomeDataFullReportById
{
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "";
	static JSONArray entryid;
	String[] tablename = { "" };
	static int minentryid;
	static int maxentryid;

	static String[] sql = {

			"SELECT dta.*,DATE_FORMAT(dta.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	discharge_to_ashore_grb2 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY entry_time DESC",
			"SELECT dta.*,DATE_FORMAT(dta.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	start_deck_washing_grb2 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY entry_time DESC",
			"SELECT dta.*,DATE_FORMAT(dta.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	start_deck_washing_acc_grb2 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY entry_time DESC",
			"SELECT dta.*,DATE_FORMAT(dta.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	stop_deck_washing_acc_grb2 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY entry_time DESC",
			"SELECT dta.*,DATE_FORMAT(dta.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	stop_deck_washing_grb2 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY entry_time DESC" };

	static ResultSet resultSet;

	public static JSONArray getStopIncinerationAcc(Connection con) throws Exception {

		JSONArray obj;
		JSONArray data = new JSONArray();

		for (int id = maxentryid; id > minentryid; id--) {

			for (int i = 0; i < sql.length; i++) {

				ps = (PreparedStatement) con.prepareStatement(sql[i]);
				ps.setInt(1, id);

				rs = ps.executeQuery();
				obj = DaoUtil.convertToJsonArray1(rs);

				if (obj.length() != 0) {
					data.put(obj.get(0));
				}
			}

		}

		return data;

	}

	public static JSONArray data_dtls(String startdate, String enddate, Connection con) throws Exception {

		JSONArray obj1 = new JSONArray();
		JSONArray obj2 = new JSONArray();
		int[] minid = new int[3];

		int[] maxid = new int[3];
		String[] entry_type_name1 = { "discharge_to_ashore_grb2", "stop_deck_washing_grb2",
				"stop_deck_washing_acc_grb2", "start_deck_washing_acc_grb2", "start_deck_washing_grb2" };

		for (int i = 0; i <= sql.length - 1; i++) {
			JSONArray obj = new JSONArray();
			/*
			 * String sqlquery = "select * from " + entry_type_name1[i] +
			 * " where entry_date between '" + startdate + "' and '" + enddate + "'";
			 */
			String sqlquery = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	"
					+ entry_type_name1[i]
					+ " dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_date between '"
					+ startdate + " ' and '" + enddate + "' ORDER BY entry_id DESC";
			// String sqlquery=sql[i];
			System.out.println(sqlquery);
			ps = con.prepareStatement(sqlquery);
			rs = ps.executeQuery();
			obj = DaoUtil.convertToJsonArray1(rs);

			for (int j = 0; j <= obj.length() - 1; j++) {
				obj2.put(obj.get(j));
			}

			System.out.println("json data via objcet2:--" + obj2);
		}

		return obj2;
	}

	public static JSONArray entry_id(String startdate, String enddate, Connection con) {
		JSONArray obj = null;
		int[] minid = new int[5];
		int[] maxid = new int[5];
		String[] entry_type_name1 = { "discharge_to_ashore_grb2", "start_deck_washing_grb2", "stop_deck_washing_grb2",
				"stop_deck_washing_acc_grb2","start_deck_washing_acc_grb2" };

		try {

			for (int i = 0; i < entry_type_name1.length; i++) {

				String sqlquery = "select min(entry_id) as minid,max(entry_id) as maxid from " + entry_type_name1[i]
						+ " where entry_date between '" + startdate + "' and '" + enddate + "'";

				ps = con.prepareStatement(sqlquery);
				rs = ps.executeQuery();

				if (rs.next()) {

					minid[i] = rs.getInt("minid");
					System.out.println(minid[i]);
					maxid[i] = rs.getInt("maxid");

				}
				minentryid = min(minid);
				maxentryid = max(maxid);

			}
			System.out.println("minid===" + minentryid);
			System.out.println("maxid===" + maxentryid);
			obj = getStopIncinerationAcc(con);
		}

		catch (SQLException e) {

			e.printStackTrace();
			JSONObject sql = new JSONObject();
			try {
				sql.put("aa", "aa");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			obj.put(sql);

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			JSONObject sql = new JSONObject();
			try {
				sql.put("aa", "aa");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			obj.put(sql);

		}
		return obj;
	}

	public static int max(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length ; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public static int min(int[] array) {
		int min = array[0];

		for (int i = 0; i < array.length ; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

}