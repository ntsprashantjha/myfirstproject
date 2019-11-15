package com.nts.orb1.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class AccessRight_dtls {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public String userManagmentDetails_orb1() throws Exception {
		con = dbConnection.getConnection();
		String sql = "select * from user_management_orb1 ORDER BY rank_id";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}
	public static void main(String arg[]) throws Exception
	{
		
		System.out.println(new AccessRight_dtls().userManagmentDetails_orb1());
		
	}
	
}
