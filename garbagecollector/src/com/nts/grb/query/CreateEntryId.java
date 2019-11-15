package com.nts.grb.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;

public class CreateEntryId {
	private String rprtData;
	private static Connection con = null;
	private static Statement stmt = null;
	private ResultSet rs = null;

	public static int entryId(String entrytype) throws SQLException {
		String sqlQuery="";
		System.out.println("from lastentry");
		con = dbConnection.getConnection();
		stmt = (Statement) con.createStatement();
		//String sql = "update last_entry set last_entry_date='" + dt + "',last_enrty_time='" + time + "',entry_timeZone='"+timezone+"'";
		stmt.executeUpdate("");
		return 1;
	}

}
