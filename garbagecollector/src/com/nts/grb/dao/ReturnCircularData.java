package com.nts.grb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class ReturnCircularData {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public String circularDetails() throws Exception {
		con = dbConnection.getConnection();
		// String sql = "select from grb1_circular_details ORDER BY id";
		String sql = "SELECT rank_details.user_rank, user_details.*, grb1_circular_details.* FROM rank_details, user_details,grb1_circular_details WHERE user_details.rank_id = rank_details.rank_id AND grb1_circular_details.officer_info=user_details.user_id ORDER BY grb1_circular_details.id DESC";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}

	public void deleteCircularInfo(int circularid) throws SQLException {
		con = dbConnection.getConnection();
		String sql = "delete from grb1_circular_details where id=" + circularid;
		ps = con.prepareStatement(sql);
		ps.execute();
	}

	public static void main(String arg[]) throws Exception {
		System.out.println(new ReturnCircularData().circularDetails());
	}
}
