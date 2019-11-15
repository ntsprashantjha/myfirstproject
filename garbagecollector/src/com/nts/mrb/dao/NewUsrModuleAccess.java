package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nts.grb.connection.dbConnection;

public class NewUsrModuleAccess {
	private static Connection con = null;
	private static Statement ps = null;

	public static void addRankndAccess(int userId, int userrnkid) throws SQLException {

		con = dbConnection.getConnection();

		String sql_query = "insert into mrb_module_access_right (user_rank_id,grb_part1,grb_part2,orb_part1,orb_part2,crb,annex6) values("
				+ userrnkid + ",'NO','NO','NO','NO','NO','NO')";

		ps = con.createStatement();
		ps.execute(sql_query);
	}

	public void updateRankndAccess(int userrnkid, String grb1, String grb2, String orb1, String orb2, String crb,
			String anx) throws SQLException {

		Connection con = dbConnection.getConnection();
		Statement stmt = (Statement) con.createStatement();

		String sql_query = "update mrb_module_access_right set grb_part1='" + grb1 + "',grb_part2='" + grb2
				+ "',orb_part1='" + orb1 + "',orb_part2='" + orb2 + "',crb='" + crb + "',annex6='" + anx
				+ "' where user_rank_id=" + userrnkid;

		System.out.println(sql_query);
		stmt.executeUpdate(sql_query);
	}

	public static void addRankndAccessForfirstTime(int userId, int userrnkid) throws SQLException {

		con = dbConnection.getConnection();

		String sql_query = "insert into mrb_module_access_right (user_rank_id,grb_part1,grb_part2,orb_part1,orb_part2,crb,annex6) values("
				+ userrnkid + ",'YES','YES','YES','YES','YES','YES')";

		ps = con.createStatement();
		ps.execute(sql_query);

		Statement stmt = (Statement) con.createStatement();

		String sql_query1 = "update user_details set user_status=2 where user_confirm_pasword='Admin' AND user_first_name='Admin'";
		stmt.executeUpdate(sql_query1);
	}

	public int insertnewrank(String rank_name) {

		return 0;
	}

	public void insertrankaccess() {

	}

}
