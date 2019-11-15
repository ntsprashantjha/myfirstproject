package com.nts.grb2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.LastEntryDetails;
import com.nts.grb.util.DaoUtil;

public class LastEntryDetails_GRB2 {

	private static Connection con = null;

	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public String main() {
		return null;

	}

	public JSONArray last_entry() {
		return null;

	}

	// getting last entry info
	public JSONObject LastEntryDetail() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT * FROM master_table WHERE entry_date_only=( SELECT MAX(entry_date_only) FROM master_table WHERE entry_strike_cond=0) ORDER BY entry_id DESC LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		return DaoUtil.convertToJsonObject(rs);
	}

	// equipmnt dtls for incnrtsn
	public JSONObject inc_equipmnt_cate() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT incinerator_capacity,plastic_burn_approval FROM equipment_details";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		return DaoUtil.convertToJsonObject(rs);
	}

	// **last appraoval time
	public JSONObject approvalTime() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT * FROM settings_of_grb1 LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		return DaoUtil.convertToJsonObject(rs);
	}

	// last approval entry dtls
	public JSONArray lastApprovedEntryDtls() {

		JSONObject jsnobj = new JSONObject();
		JSONObject jsnobj1 = new JSONObject();

		JSONArray ac = new JSONArray();
		String acc = null;

		String[] tableName = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc", "start_incineration",
				"stop_incineration", "start_incineration_acc", "stop_incineration_acc" };

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			String sql = "SELECT entry_id FROM " + tableName[i] + " WHERE master_approval_done=1 LIMIT 1";

			try {

				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				acc = DaoUtil.categoryBiseTable(rs);

			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ac.put(acc);

		}
		return ac;
	}

	// rreapproval dtls
	public JSONObject getmasterapprovaldone(Connection con, int maxid) {

		String[] entry_type_name1 = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc",
				"start_incineration_acc", "start_incineration", "stop_incineration", "stop_incineration_acc" };

		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();

		try {

			for (int i = 0; i <= entry_type_name1.length - 1; i++) {

				String sql = "SELECT entry_id,entry_date,entry_time,entry_timestamp FROM " + entry_type_name1[i]
						+ " WHERE entry_id=" + maxid;

				ps = (PreparedStatement) con.prepareStatement(sql);
				rs = ps.executeQuery();

				obj = DaoUtil.convertToJsonObject(rs);
				if (obj.length() == 0) {

				} else {
					obj1 = obj;
				}

			}
			System.out.println("vffffab=" + obj);

		}

		catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (Exception e) {

		}

		System.out.println("rrrrrrrrrrr" + obj1);
		return obj1;

	}

	public JSONObject maxentry_id() {

		Connection con = dbConnection.getConnection();
		JSONObject abcbc = new JSONObject();

		int[] minid = new int[7];
		int[] maxid = new int[7];

		String[] entry_type_name1 = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc",
				"start_incineration_acc", "start_incineration", "stop_incineration", "stop_incineration_acc" };
		try {

			int minentryid = 0;
			int maxentryid = 0;

			for (int i = 0; i <= entry_type_name1.length - 1; i++) {

				String sqlquery = "select max(entry_id) as maxid from " + entry_type_name1[i]
						+ " where master_approval_done=1";

				System.out.println(sqlquery);

				ps = con.prepareStatement(sqlquery);
				rs = ps.executeQuery();

				if (rs.next()) {

					maxid[i] = rs.getInt("maxid");
				}

				maxentryid = max(maxid);

			}
			abcbc = getmasterapprovaldone(con, maxentryid);

			if (abcbc.length() == 0) {

				abcbc.put("entry_id", 1);
				abcbc.put("entry_timestamp", 0);

				abcbc.put("entry_time", 0);
				abcbc.put("entry_date", 0);
			}

			System.out.println("maxid===" + maxentryid);
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		catch (Exception e) {

		}
		return abcbc;
	}

	public boolean makeEntryOrNot() {
		boolean rtrn = true;
		return rtrn;

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

	// pending entry details that are not approved
	public JSONObject pendingEntryDetail() throws Exception {

		JSONObject jsnob = new JSONObject();
		int a = 0;

		String[] tableName = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc", "start_incineration",
				"stop_incineration", "start_incineration_acc", "stop_incineration_acc" };

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			String sql = "SELECT COUNT(master_approval_done)      \r\n" + "FROM " + tableName[i]
					+ " where master_approval_done='0'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			int s = Integer.parseInt(DaoUtil.categoryBiseTable(rs));
			a = a + s;

		}

		jsnob.put("pending_entrys", a);
		System.out.println(jsnob);
		return jsnob;

	}

	// entry for pending for reapproved
	public JSONObject pendingReaprovlEntryDetail() throws Exception {

		JSONObject jsnob = new JSONObject();
		int a = 0;

		String[] tableName = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc", "start_incineration",
				"stop_incineration", "start_incineration_acc", "stop_incineration_acc" };

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {
			String sql = "SELECT COUNT(master_reapproval_done)      \r\n" + "FROM " + tableName[i]
					+ " where master_reapproval_done='1' AND strike_time > master_time";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			int s = Integer.parseInt(DaoUtil.categoryBiseTable(rs));
			a = a + s;

		}

		jsnob.put("pending_re_entrys", a);
		return jsnob;

	}

	// last incenertion entry dtls
	public JSONObject lastentryDetailsInc() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT entry_timeZone,incineration_condition,entry_date,entry_type,entry_time,incinerator_entry_category FROM `master_table` WHERE entry_type = 'start_Incineration' or entry_type = 'stop_Incineration' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		JSONObject json = DaoUtil.convertToJsonObject(rs);
		if (json.length() == 0) {

			json.put("incineration_condition", 0);
			json.put("entry_type", "stop_incineration");

		}

		return json;
	}

	// last entry inc acc
	public JSONObject lastentryDetailsInc_acc() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT entry_timeZone,incineration_condition,entry_date,entry_type,entry_time,incinerator_entry_category FROM `master_table` WHERE entry_type = 'Stop_Incineration_Acc' or entry_type = 'Start_Incineration_Acc' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		JSONObject json = DaoUtil.convertToJsonObject(rs);
		if (json.length() == 0) {

			json.put("incineration_condition", 0);
			json.put("entry_type", "Stop_Incineration_Acc");

		}

		return json;
	}

	public static String lst_Strt_inc_cate() {
		String entry_cate = null;
		try {
			con = dbConnection.getConnection();
			String sql = "SELECT incinerator_entry_category FROM `master_table` WHERE entry_type = 'start_Incineration' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				entry_cate = rs.getString("incinerator_entry_category");
			}

		}

		catch (Exception e) {

		}

		return entry_cate;
	}

	public static String lst_Strt_inc_acc_cate() {
		String entry_cate = null;

		try {

			con = dbConnection.getConnection();
			String sql = "SELECT incinerator_entry_category FROM `master_table` WHERE entry_type = 'Start_Incineration_Acc' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				entry_cate = rs.getString("incinerator_entry_category");
			}

		}

		catch (Exception e) {

		}

		return entry_cate;
	}

	public JSONObject maxentry_id1() {

		int day_before = 5;
		Connection con = dbConnection.getConnection();

		JSONObject abcbc = new JSONObject();

		int[] minid = new int[7];
		int[] maxid = new int[7];

		try {
			int minentryid = 0;
			int maxentryid = 0;

			JSONArray strk_e_id_arr = new JSONArray();
			JSONObject strk_e_id_arr1 = new JSONObject();

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -day_before);

			Date as = cal.getTime();
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			String crt_dt = sdf.format(as).replaceAll("/", "-");
			System.out.println("Date = " + crt_dt);
			con = dbConnection.getConnection();

			String[] tableName = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc",
					"start_incineration", "stop_incineration", "start_incineration_acc", "stop_incineration_acc" };

			con = dbConnection.getConnection();

			for (int i = 0; i <= tableName.length - 1; i++) {

				String sql = "SELECT max(entry_id) as maxid FROM " + tableName[i]
						+ " where strike_approval_done='1' and entry_date='" + crt_dt + "'";

				System.out.println(sql);

				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				if (rs.next()) {

					maxid[i] = rs.getInt("maxid");
				}

				maxentryid = max(maxid);

			}

			abcbc = getmasterapprovaldone(con, maxentryid);
			System.out.println("maxid===" + maxentryid);

			if (abcbc.length() == 0) {

				abcbc.put("entry_id", 1);
				abcbc.put("entry_timestamp", 0);

				abcbc.put("entry_time", 0);
				abcbc.put("entry_date", 0);

			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		catch (Exception e) {

		}
		return abcbc;
	}

	public static void main(String arg[]) throws Exception {

		System.out.println(new LastEntryDetails().ship());
	}

}
