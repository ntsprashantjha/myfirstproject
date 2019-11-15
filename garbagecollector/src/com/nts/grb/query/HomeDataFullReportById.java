package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.JSONArray;

import com.nts.grb.util.DaoUtil;

public class HomeDataFullReportById {
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "";
	static JSONArray entryid;
	String[] tablename = { "" };
	static int minentryid;
	static int maxentryid;

	static String[] sql = {

			"SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	discharge_to_ashore dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dta.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY entry_time DESC",
			"SELECT dtss.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	discharge_to_sea dtss LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dtss.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dtss.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dtss.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dtss.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dtss.master_id WHERE dtss.entry_id =? ",
			"SELECT dtsp.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	discharge_to_sea_acc dtsp LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dtsp.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dtsp.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dtsp.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dtsp.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dtsp.master_id WHERE dtsp.entry_id =? ",
			"SELECT dtsp.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	stop_incineration dtsp LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dtsp.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dtsp.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dtsp.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dtsp.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dtsp.master_id WHERE dtsp.entry_id =? ",
			"SELECT dtsp.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	start_incineration dtsp LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dtsp.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dtsp.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dtsp.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dtsp.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dtsp.master_id WHERE dtsp.entry_id =? ",
			"SELECT dtsp.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	stop_incineration_acc dtsp LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dtsp.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dtsp.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dtsp.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dtsp.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dtsp.master_id WHERE dtsp.entry_id =? ",
			"SELECT dtsp.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	start_incineration_acc dtsp LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dtsp.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dtsp.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dtsp.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dtsp.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dtsp.master_id WHERE dtsp.entry_id =? ", };

	static ResultSet resultSet;

	public static JSONArray getStopIncinerationAcc(Connection con) throws Exception {
		JSONArray obj;
		JSONArray data = new JSONArray();
		JSONArray data1 = new JSONArray();
		for (int id = maxentryid; id >= minentryid; id--) {
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

	public static JSONArray entry_id(String startdate, String enddate, Connection con) throws Exception {

		int[] minid = new int[7];
		int[] maxid = new int[7];
		String[] entry_type_name1 = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc", "start_incineration",
				"stop_incineration", "start_incineration_acc", "stop_incineration_acc" };
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
		JSONArray obj = getStopIncinerationAcc(con);
		System.out.println("tejnrfjnrfjhrf*****************" + obj);
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