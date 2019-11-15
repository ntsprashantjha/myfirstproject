package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nts.grb.connection.dbConnection;

public class ForFirstEntryInitialDate {

	public boolean countEntry(String tablename) {
		try {
			Connection con = dbConnection.getConnection();
			String query="";
			if(tablename.equalsIgnoreCase("master_table_orb1"))
			{
				query = "select count(entry_id) from "+tablename+" where entry_strike_cond = 0";
			}
			else {
				query = "select count(id) from " + tablename;
			}
			System.out.println(query);
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				if (rs.getInt(1) == 0) {
					return true;
				}
			}

			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {
		new ForFirstEntryInitialDate().countEntry("master_table_orb1");
	}

}
