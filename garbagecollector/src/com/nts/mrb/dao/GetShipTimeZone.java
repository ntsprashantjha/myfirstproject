package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;

public class GetShipTimeZone {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	public static String gettimezone() throws SQLException, JSONException {
		String timezone="";
		con = dbConnection.getConnection();
		String sql = "select time_zone from time_zone";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next())
		{
			timezone=rs.getString("time_zone");
		}
		return timezone;
	}
	
}
