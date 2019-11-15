package com.nts.grb2.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONArray;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
import com.nts.grb.validation.ReturnResponse;

public class ReturnCircularData {
	static Connection con;
	static String sql;
	static PreparedStatement ps;

	public JSONArray deleteCircularInfo(int circularid) {

		JSONArray rr = ReturnResponse.success_condition();

		con = dbConnection.getConnection();
		sql = "delete  from  grb2_circular_details where id=" + circularid;

		try {
			ps = con.prepareStatement(sql);
			ps.execute();

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rr = ReturnResponse.somethingWentWrong();
		}

		return rr;

	}

	public String circularDetails() throws Exception {
		con = dbConnection.getConnection();
		String sql = "SELECT rank_details.user_rank, user_details.*, grb2_circular_details.* FROM rank_details, user_details,grb2_circular_details WHERE user_details.rank_id = rank_details.rank_id AND grb2_circular_details.officer_info=user_details.user_id ORDER BY grb2_circular_details.id DESC";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}

}
