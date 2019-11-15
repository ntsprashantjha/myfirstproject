package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;

public class MasterTable {

	public int lastEntry() {

		int lst_mstr_entry_id = 0;
		Connection con = dbConnection.getConnection();

		String sql = "SELECT entry_id FROM `master_table_orb1` ORDER BY entry_id DESC LIMIT 1";

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				lst_mstr_entry_id = rs.getInt("entry_id");

			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst_mstr_entry_id + 1;
	}

	public int lastEntryUpdation(String entrytype, String dt, String time, int cond, String timezone, String dt_only,int auto_type, int souentryid) {
		int id = 0;
		int success = 0;

		try {
			Connection con = dbConnection.getConnection();

			PreparedStatement ps = (PreparedStatement) con.prepareStatement(
					"INSERT INTO master_table_orb1 (entry_type,entry_date,entry_time,incineration_condition,entry_timeZone,entry_date_only,entry_automatic_type,source_entry_id) values (?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, entrytype);
			ps.setString(2, dt);

			ps.setString(3, time);
			ps.setInt(4, cond);

			ps.setString(5, timezone);
			ps.setString(6, dt_only);
			
			ps.setInt(7, auto_type);
			ps.setInt(8, souentryid);

			success = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {

				id = rs.getInt(1);
				System.out.println(id);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;

	}
}