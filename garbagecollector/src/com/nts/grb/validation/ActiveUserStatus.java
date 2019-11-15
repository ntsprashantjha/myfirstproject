package com.nts.grb.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.nts.grb.connection.dbConnection;

public class ActiveUserStatus {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public static int userstatus(String mac) {
		
		System.out.println("**from active user status**");
		int activecond = 0;
		
		try {
			
			con = dbConnection.getConnection();
			String query = "select user_activity from user_details where user_macId='" + mac + "'";

			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			rs.next();
			
			activecond = rs.getInt("user_activity");

		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return activecond;
	}

	public static void main(String arg[]) {
		System.out.println(ActiveUserStatus.userstatus("abc")+"nmcnjc");
	}

}
