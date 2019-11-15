package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
import com.nts.grb.validation.ReturnResponse;

public class UserManagementDetails {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public String userManagmentDetails() {
		String rtrn_dtls = null;

		try {

			con = dbConnection.getConnection();
			 String sql = "select * from user_management ORDER BY rank_id";
			//String sql = "SELECT DISTINCT rd.user_rank as rank,E.* FROM mrb_module_access_right E ,rank_details rd WHERE E.user_rank_id=rd.rank_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			rtrn_dtls = DaoUtil.convertToJsonArray(rs);

		} catch (Exception e) {

			ReturnResponse.retrnresponse_exception_occured();
		}

		return rtrn_dtls;
	}

	public static void main(String arg[]) throws Exception {

		System.out.println(new UserManagementDetails().userManagmentDetails());

	}

}
