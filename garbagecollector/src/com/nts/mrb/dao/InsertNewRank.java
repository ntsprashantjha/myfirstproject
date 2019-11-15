package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nts.grb.connection.dbConnection;

public class InsertNewRank {
	private static Connection con = null;
	private static ResultSet rs = null;
	private static Statement ps = null;
	private static PreparedStatement prs = null;

	public static int addRankndAccess(String rank_name) throws SQLException {
		con = dbConnection.getConnection();
		prs = con.prepareStatement("select count(user_rank) FROM rank_details where user_rank=?");
		prs.setString(1, rank_name);
		rs = prs.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt(1);
		}
		if(count==0) {
			int rankId = 0;
			
			
			String sql_query = "insert into rank_details (user_rank) values('" + rank_name + "')";
			ps = con.createStatement();
			int rank_id = ps.executeUpdate(sql_query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			rankId = rs.getInt(1);
			return rankId;
		}
		else
		{
			System.out.println("User Already Exist");
			return 0;
		}
	}

	public int insertnewrank(String rank_name) {

		return 0;
	}

	public void insertrankaccess() {

	}
	public static void main(String[] args) throws SQLException {
		//System.out.println(addRankndAccess("CAPTAIN"));
	}

}
