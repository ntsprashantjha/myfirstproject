package com.nts.grb2.query;

import java.sql.Connection;
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
import com.nts.grb.util.DaoUtil;
import com.nts.grb.validation.ReturnResponse;

public class LastEntryDetails_GRB2 {
	private String rprtData;
	private static Connection con = null;

	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	JSONArray sb = new JSONArray();

	public String lstEntry(){

		try {
		sb.put(lastEntryDetail()).put(pendingEntryDetail());
		sb.put(pendingReaprovlEntryDetail());
		sb.put(lastEntryDeckWash());
		sb.put(maxentry_id());

		sb.put(maxentry_id1());
		sb.put(approvalTime());

		sb.put(inc_equipmnt_cate()).put(lastentryDetailsInc_acc());

		System.out.println("return value for data grb2----" + sb.toString());
		return sb.toString();}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ReturnResponse.somethingWentWrong().toString();
		}
	}

	// getting last entry info
	public JSONObject lastEntryDetail() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT * FROM master_table_grb2 WHERE entry_date_only=( SELECT MAX(entry_date_only) FROM master_table_grb2 WHERE entry_strike_cond=0) ORDER BY entry_id DESC LIMIT 1";

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

		String[] tableName = { "discharge_to_ashore_grb2", "start_deck_washing_grb2", "stop_deck_washing_grb2" };

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

	// reapproval dtls
	public JSONObject getmasterapprovaldone(Connection con, int maxid) {

		String[] entry_type_name1 = { "discharge_to_ashore_grb2", "start_deck_washing_grb2",
				"stop_deck_washing_acc_grb2", "start_deck_washing_acc_grb2", "stop_deck_washing_grb2" };

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

		int[] minid = new int[5];
		int[] maxid = new int[5];

		String[] entry_type_name1 = { "discharge_to_ashore_grb2", "start_deck_washing_grb2",
				"stop_deck_washing_acc_grb2", "start_deck_washing_acc_grb2", "stop_deck_washing_grb2" };
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
				abcbc.put("entry_timestamp", "2019-07-29 09:29:13.0");

				abcbc.put("entry_time", 0);
				abcbc.put("entry_date", "2019-07-29");
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

		String[] tableName = { "discharge_to_ashore_grb2", "stop_deck_washing_acc_grb2", "start_deck_washing_acc_grb2",
				"start_deck_washing_grb2", "stop_deck_washing_grb2" };

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

		String[] tableName = { "discharge_to_ashore_grb2", "stop_deck_washing_acc_grb2", "start_deck_washing_acc_grb2",
				"start_deck_washing_grb2", "stop_deck_washing_grb2" };
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
	public JSONObject lastEntryDeckWash() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT entry_timeZone,deck_washing_condition,entry_date,entry_type,entry_time,deck_washing_entry_category FROM `master_table_grb2` WHERE entry_type = 'start_deck_washing_grb2' or entry_type = 'stop_deck_washing_Grb2' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		JSONObject json = DaoUtil.convertToJsonObject(rs);

		if (json.length() == 0) {

			json.put("deck_washing_condition", 0);
			json.put("entry_type", "stop_deck_washing_grb2");

		}

		return json;
	}

	// last entry inc acc
	public JSONObject lastentryDetailsInc_acc() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT entry_timezone,deck_washing_condition,entry_date,entry_type,entry_time,deck_washing_entry_category FROM `master_table_grb2` WHERE entry_type = 'start_deck_washing_acc_grb2' or entry_type = 'stop_deck_washing_acc_grb2' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		JSONObject json = DaoUtil.convertToJsonObject(rs);
		if (json.length() == 0) {

			json.put("deck_washing_condition", 0);
			json.put("entry_type", "Stop_deck_washing_acc_grb2");

		}

		return json;
	}

	public static String last_Strt_inc_cate() {
		String entry_cate = null;
		try {
			con = dbConnection.getConnection();
			String sql = "SELECT deck_washing_entry_category FROM `master_table_grb2` WHERE entry_type = 'start_deck_washing_grb2' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				entry_cate = rs.getString("deck_washing_entry_category");
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
			String sql = "SELECT deck_washing_entry_category FROM `master_table_grb2` WHERE entry_type = 'start_deck_washing_acc_grb2' AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				entry_cate = rs.getString("deck_washing_entry_category");
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

			String[] tableName = { "discharge_to_ashore_grb2", "stop_deck_washing_acc_grb2",
					"start_deck_washing_acc_grb2", "start_deck_washing_grb2", "stop_deck_washing_grb2" };
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
				abcbc.put("entry_timestamp", "2019-07-29 09:29:13.0");

				abcbc.put("entry_time", 0);
				abcbc.put("entry_date", "2019-07-05");

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
		System.out.println(new LastEntryDetails_GRB2().last_Strt_inc_cate());

	}
}
