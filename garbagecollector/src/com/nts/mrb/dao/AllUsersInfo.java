package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class AllUsersInfo {
	public String allUsersDetails() throws Exception {
		
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		Connection con = dbConnection.getConnection();
		String sql = "SELECT A.*,B.*,C.rank_id FROM user_details as A inner join mrb_module_access_right  as B on A.rank_id=B.user_rank_id inner join rank_details as C on C.rank_id=A.rank_id";
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}

	public String return_equipmnt_dtls() {
		String rtrn_dtls = null;

		try {
			ResultSet rs = null;
			PreparedStatement ps = null;

			Connection con = dbConnection.getConnection();
			con = dbConnection.getConnection();

			String sql = "SELECT * FROM equipment_details";
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			rtrn_dtls = DaoUtil.convertToJsonArray(rs);
		} catch (SQLException e) {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtrn_dtls;
	}

}
