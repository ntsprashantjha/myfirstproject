package com.nts.grb.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.nts.grb.connection.dbConnection;

public class ActiveMacUsrId {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static int activeuserId = 0;

	public static int gettingId(String macId) throws SQLException {
		
		con = dbConnection.getConnection();
		String sql = "select user_id from user_details where user_activity=1 and user_macId='" + macId + "'";
		
		ps = (PreparedStatement) con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while (rs.next()) {
		
			activeuserId = rs.getInt("user_id");
		}
		
		
		return activeuserId;
	}

}
