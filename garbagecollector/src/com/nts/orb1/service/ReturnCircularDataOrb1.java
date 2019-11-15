package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONArray;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
import com.nts.grb.validation.ReturnResponse;

public class ReturnCircularDataOrb1 {
	
	static Connection con;
	static String sql;
	static PreparedStatement ps;

	public JSONArray deleteCircularInfo(int circularid) {

		JSONArray rr = ReturnResponse.success_condition();

		con = dbConnection.getConnection();
		sql = "delete  from  circular_details_orb1 where id=" + circularid;

		try {
			ps = con.prepareStatement(sql);
			ps.execute();
		}

		catch (SQLException e) {

			e.printStackTrace();
			rr = ReturnResponse.somethingWentWrong();
		}

		return rr;

	}

	public String circularDetails() throws Exception {
		
		con = dbConnection.getConnection();
		String sql = "SELECT rank_details.user_rank, user_details.*, circular_details_orb1.* "
				+ "FROM rank_details, user_details,circular_details_orb1 "
				+ "WHERE user_details.rank_id = rank_details.rank_id AND "
				+ "circular_details_orb1.officer_info=user_details.user_id "
				+ "ORDER BY circular_details_orb1.id DESC";
		
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		return DaoUtil.convertToJsonArray(rs);
	}

}
