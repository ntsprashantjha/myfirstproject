package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class LastEnrtyUpdation {

	private String rprtData;
	private Connection con = null;

	private Statement stmt = null;
	private ResultSet rs = null;

	public void last(String dt, String time, String timezone) throws SQLException {

		System.out.println("from lastentry");
		con = dbConnection.getConnection();
		stmt = (Statement) con.createStatement();

		String sql = "update last_entry set last_entry_date='" + dt + "',last_enrty_time='" + time
				+ "',entry_timeZone='" + timezone + "'";

		stmt.executeUpdate(sql);
	}

	public void lastentryInc(String dt, String time, int cond) throws SQLException {

		System.out.println("from lastentry");
		con = dbConnection.getConnection();

		stmt = (Statement) con.createStatement();
		String sql = "update last_entry set last_entry_date='" + dt + "',last_enrty_time='" + time
				+ "',incineration_condition=" + cond;

		stmt.executeUpdate(sql);
	}

	public int tst(String dt, String time, String timezone, String entrytype, int cond, String dt_only)
			throws SQLException {

		int id = 0;
		con = dbConnection.getConnection();
		
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(
				"INSERT INTO master_table (entry_type,entry_date,entry_time,incineration_condition,entry_timeZone,entry_date_only) values (?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, entrytype);
		ps.setString(2, dt);

		ps.setString(3, time);
		ps.setInt(4, cond);

		ps.setString(5, timezone);
		ps.setString(6, dt_only);

		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {

			id = rs.getInt(1);
			System.out.println(id);

		}

		return id;
	}

	public int strt_inc(String dt, String time, String timezone, String entrytype, int cond, String entry_cate,
			String dt_only) throws SQLException {

		int id = 0;
		con = dbConnection.getConnection();

		PreparedStatement ps = (PreparedStatement) con.prepareStatement(
				"INSERT INTO master_table (entry_type,entry_date,entry_time,incineration_condition,entry_timeZone,incinerator_entry_category,entry_date_only) values (?,?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, entrytype);
		ps.setString(2, dt);

		ps.setString(3, time);
		ps.setInt(4, cond);

		ps.setString(5, timezone);
		ps.setString(6, entry_cate);

		ps.setString(7, dt_only);

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			id = rs.getInt(1);
			System.out.println(id);
		}

		return id;
	}

	public int getIncCond() throws SQLException {

		int incinarationCond = 0;
		con = dbConnection.getConnection();

		// String sql = "SELECT * from last_entry";
		// String sql = "SELECT incineration_condition,entry_date FROM master_table
		// WHERE entry_date=( SELECT MAX(entry_date) FROM master_table WHERE
		// entry_strike_cond=0)";

		String sql = "SELECT LAST incineration_condition FROM master_table;  ";
		System.out.println("******from lastEntryDetails*******" + sql);

		PreparedStatement ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {

			incinarationCond = rs.getInt("incineration_condition");
			System.out.println("incinarationCond:-" + incinarationCond);

		}

		System.out.println("incinarationCond:-" + incinarationCond);
		return 0;
	}

	public int get_crnt_mstr_id() {
		int lst_mstr_entry_id = 0;
		con = dbConnection.getConnection();

		String sql = "SELECT entry_id FROM `master_table` ORDER BY entry_id DESC LIMIT 1";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				lst_mstr_entry_id = rs.getInt("entry_id");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst_mstr_entry_id + 1;
	}

	public static void main(String arg[]) throws SQLException {
		System.out.println(new LastEnrtyUpdation().get_crnt_mstr_id());
	}

}
