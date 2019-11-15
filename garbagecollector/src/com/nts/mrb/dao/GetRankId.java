package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;

public class GetRankId {
	private static Connection con = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;

	public static int rankId(String rankName) {
		int return_rank_id = 0;
		try {
			con = dbConnection.getConnection();
			String sql = "select rank_id from rank_details where user_rank='" + rankName + "'";
			System.out.println("query:----" + sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				return_rank_id = rs.getInt("rank_id");
				System.out.println("here is rank id******" + return_rank_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return return_rank_id;
	}

	public static int userId(String userFName, String userpassword) {
		int return_rank_id = 0;
		try {
			con = dbConnection.getConnection();
			String sql = "select user_details.user_id from user_details where user_first_name='" + userFName
					+ "' AND user_confirm_pasword='" + userpassword +"'";
			System.out.println("query:----" + sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				return_rank_id = rs.getInt("user_id");
				System.out.println(return_rank_id + "rank id**************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return return_rank_id;
	}
	public static void dltRank(int rank_id)
	{
		con=dbConnection.getConnection();
		String updtsql="update rank_details set rank_is_deleted=1 where rank_id="+rank_id;
		try {
			Statement st=(Statement) con.createStatement();
			st.executeUpdate(updtsql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String arg[])
	{
		System.out.println(GetRankId.userId("Devesh", "ce"));
	}

}
