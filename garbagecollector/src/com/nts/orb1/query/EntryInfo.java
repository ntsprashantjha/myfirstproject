package com.nts.orb1.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
import com.nts.orb1.service.GetFirstTankAddTime;
import com.nts.orb1.service.GetTableName;

public class EntryInfo {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	JSONArray sb = new JSONArray();

	public String entryInfo() throws Exception {

		sb.put(getLastEntryInfo()).put(pendingEntryDetail());
		sb.put(pendingReaprovlEntryDetail());

		sb.put(lastentryDetailsInc());

		// MAX Entry Id Where Entry Is Approved
		sb.put(maxentry_id());

		sb.put(maxentry_id1());
		sb.put(approvalTime());

		sb.put(inc_equipmnt_cate()).put(lastentryDetailsInc_acc());

		// System.out.println("return value for data:----" + sb.toString());
		return sb.toString();
	}

	private JSONObject getLastEntryInfo() {
		JSONObject returnLastEntryInfo = new JSONObject();

		try {
			Connection con = dbConnection.getConnection();
			String sql = "SELECT entry_date FROM master_table_orb1 WHERE entry_date_only= "
					+ "( SELECT MAX(entry_date_only) FROM master_table_orb1 "
					+ "WHERE entry_type <> 'Daily Tank Sounding Sheet Updation' AND entry_strike_cond=0 ) "
					+ "and entry_type <> 'Daily Tank Sounding Sheet Updation' AND entry_strike_cond=0 ORDER BY "
					+ "entry_id DESC LIMIT 1";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			returnLastEntryInfo = DaoUtil.convertToJsonObject(rs);

			if (returnLastEntryInfo.length() == 0)
				returnLastEntryInfo.put("entry_date", new GetFirstTankAddTime().getDate());

		} catch (Exception e) {

		}
		return returnLastEntryInfo;
	}

	// equipmnt dtls for incnrtsn
	private JSONObject inc_equipmnt_cate() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT incinerator_capacity,plastic_burn_approval FROM equipment_details";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		return DaoUtil.convertToJsonObject(rs);
	}

	// **last appraoval time
	private JSONObject approvalTime() throws Exception {

		con = dbConnection.getConnection();
		String sql = "SELECT * FROM settings_of_grb1 LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		return DaoUtil.convertToJsonObject(rs);
	}

	// rreapproval dtls
	private JSONObject getmasterapprovaldone(Connection con, int maxid) {

		String[] entry_type_name1 = GetTableName.getAllTableName();

		JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();

		try {

			for (int i = 0; i <= entry_type_name1.length - 1; i++) {

				String sql = "SELECT entry_id,stop_date,entry_timestamp FROM " + entry_type_name1[i]
						+ " WHERE entry_id=" + maxid;

				// System.out.println("Query : - "+sql);

				ps = (PreparedStatement) con.prepareStatement(sql);
				rs = ps.executeQuery();

				obj = DaoUtil.convertToJsonObject(rs);
				if (obj.length() == 0) {

				} else {
					obj1 = obj;
				}

			}
			// System.out.println("Last Entry Approved Data : - " + obj);

		}

		catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (Exception e) {

		}

//		System.out.println("Last Entry Approved Data : - " + obj1);
		return obj1;

	}

	private JSONObject maxentry_id() {

		Connection con = dbConnection.getConnection();
		JSONObject abcbc = new JSONObject();

		String[] entry_type_name1 = GetTableName.getAllTableName();
		int[] maxid = new int[entry_type_name1.length];
		try {

			// int minentryid = 0;
			int maxentryid = 0;

			for (int i = 0; i < entry_type_name1.length; i++) {

				String sqlquery = "select max(entry_id) as maxid from " + entry_type_name1[i]
						+ " where master_approval_done=1";

				// System.out.println(sqlquery);

				ps = con.prepareStatement(sqlquery);
				rs = ps.executeQuery();

				if (rs.next()) {

					maxid[i] = rs.getInt("maxid");
				}

				maxentryid = max(maxid);
//				System.out.println("Max Id : - " + maxentryid);

			}
//			 System.out.println("Max Id : - " + maxentryid);
			abcbc = getmasterapprovaldone(con, maxentryid + 1);
//			System.out.println("LAst Approve : - " + abcbc);

			if (abcbc.length() == 0) {

				abcbc.put("entry_id", 1);
				abcbc.put("entry_timestamp", new GetFirstTankAddTime().getTimeStamp());
				abcbc.put("stop_date", new GetFirstTankAddTime().getDate());
			}

			// System.out.println("maxid===" + maxentryid);
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
	private JSONObject pendingEntryDetail() throws Exception {

		JSONObject jsnob = new JSONObject();
		int a = 0;

		String[] tableName = GetTableName.getAllTableName();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			String sql = "SELECT COUNT(master_approval_done) " + "FROM " + tableName[i]
					+ " where master_approval_done='0'";
			// System.out.println("Query : - " + sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			int s = Integer.parseInt(DaoUtil.categoryBiseTable(rs));
			a = a + s;

		}

		jsnob.put("pending_entrys", a);
		// System.out.println(jsnob);
		return jsnob;

	}

	// entry for pending for reapproved
	private JSONObject pendingReaprovlEntryDetail() throws Exception {

		JSONObject jsnob = new JSONObject();
		int a = 0;

		String[] tableName = GetTableName.getAllTableName();

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
		String sql = "SELECT entry_timeZone,incineration_condition,entry_date,entry_type,entry_time,incinerator_entry_category FROM `master_table` where entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

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
		String sql = "SELECT entry_timeZone,incineration_condition,entry_date,entry_type,entry_time,incinerator_entry_category FROM `master_table` where entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1";

		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		JSONObject json = DaoUtil.convertToJsonObject(rs);
		if (json.length() == 0) {

			json.put("incineration_condition", 0);
			json.put("entry_type", "Stop_Incineration_Acc");

		}

		return json;
	}

	public String lst_Strt_inc_cate() {
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

	public String lst_Strt_inc_acc_cate() {
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

		Connection con = dbConnection.getConnection();
		JSONObject abcbc = new JSONObject();
		try {
			int strikedays = 0;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select strike_days from settings_of_grb1");
			if (rs.next()) {
				strikedays = rs.getInt(1);
			}

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -strikedays);

			Date as = cal.getTime();
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			String crt_dt = sdf.format(as).replaceAll("/", "-");
			// System.out.println("Date = " + crt_dt);
			con = dbConnection.getConnection();

//			String[] tableName = { "sludge_burning_in_boiler_c12_4", "sludge_disposal_shoreconnection_c12_1_orb1",
//					"sludge_drain_water_frm_sludge_to_blidge_tank", "sludge_fuel_regeneration_c12_4orb1",
//					"sludge_inceration_c12_3orb1", "sludge_m_opt_c11_4_orb1",
//					"sludge_tank_evaporation_of_water_c12_4_orb1", "sludge_transferbw_two_tanks_c12_2orb1" };

			// for (int i = 0; i <= tableName.length - 1; i++) {

			String sql = "SELECT max(entry_id) as maxid FROM master_table_orb1 "
					+ "where entry_strike_cond='1' and entry_date_only='" + crt_dt + "'";

			// System.out.println(sql);

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				abcbc.put("entry_id", rs.getInt(1));
			}
			return abcbc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// JSONObject abcbc = new JSONObject();

		/*
		 * try {
		 * 
		 * con = dbConnection.getConnection(); String sql =
		 * "select max(entry_id) from master_table_orb1 where entry_strike_cond = 1";
		 * 
		 * ps = con.prepareStatement(sql); rs = ps.executeQuery();
		 * 
		 * if(rs.next()) { abcbc.put("entry_id", rs.getInt(1)); } return abcbc;
		 * 
		 * }
		 * 
		 * catch (Exception e) { e.printStackTrace(); return null;
		 * 
		 * }
		 */

	}

	public JSONArray ship() throws Exception {

		ResultSet rs = null;
		String classname = "org.postgresql.Driver";
		String urldb = "jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com"
				+ ":5432/d9mn6hk25h95pe?sslmode=require";
		String dbuser = "fbdlpvmecxgbnh";
		String dbpass = "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb";
		String sql = "select * from newuserdb";

		Connection con = null;
		PreparedStatement pstm = null;

		Class.forName(classname);

		con = DriverManager.getConnection(urldb, dbuser, dbpass);
		pstm = con.prepareStatement(sql);
		// System.out.println("from post methode");
		rs = pstm.executeQuery();
		return DaoUtil.convertToJsonArray1(rs);

	}

}
