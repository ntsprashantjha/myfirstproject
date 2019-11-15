package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.mysql.jdbc.PreparedStatement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class AuditDetailsDao {

	// this audit_dtls() retrun information of audit table
	public static JSONArray audit_dtls() {

		String return_details = null;
		JSONArray audit_info = null;
		JSONArray audit_info_2 = new JSONArray();
		String audit_table[] = { "discharge_to_sea_aud", "discharge_to_ashore_aud", "discharge_to_sea_acc_aud",
				"start_incineration_acc_aud", "start_incineration_aud", "stop_incineration_aud",
				"stop_incineration_acc_aud" };
		try {
			for (int i = 0; i <= audit_table.length - 1; i++) {
				/*
				 * String sql=" select t1.REV,t1.REVTYPE,t1.entry_type,t2.user_first_name from
				 * discharge_to_sea_aud t1 INNER JOIN user_details t2 on
				 * t2.user_id=t1.officer_id"
				 */

				// String sql "select
				// t1.REV,t1.REVTYPE,t1.entry_type,t2.user_first_name,t3.REVTSTMP from
				// discharge_to_sea_aud t1 left JOIN user_details t2 on t1.officer_id=t2.user_id
				// LEFT JOIN revinfo t3 on t3.REV=t1.REV";

				String sql = "select t1.REV,t1.REVTYPE,t1.entry_type,t1.*,t2.user_first_name,t2.user_rank,t3.REVTSTMP from "
						+ audit_table[i]
						+ " t1 left JOIN user_details t2 on t1.officer_id=t2.user_id LEFT JOIN revinfo t3 on t3.REV=t1.REV";

				System.out.println(sql);

				Connection con = dbConnection.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				audit_info = DaoUtil.convertToJsonArray1(rs);

			}
			for (int ii = 0; ii <= audit_info.length() - 1; ii++) {

				audit_info_2.put(audit_info.get(ii));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		System.out.println(audit_info_2);
		return audit_info_2;
	}

	public static JSONArray getInfo() throws Exception {
		String sql = "select a.* from audit_details_info a  ORDER by id DESC";

		System.out.println(sql);

		Connection con = dbConnection.getConnection();
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		JSONArray audit_info = DaoUtil.convertToJsonArray1(rs);
		System.out.println("it is json arrray:--" + audit_info);
		return audit_info;
	}
	public static void getInfo1() throws Exception {
		String sql = "select a.* from audit_details_info a  ORDER by id DESC";

		System.out.println(sql);

		Connection con = dbConnection.getConnection();
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while(rs.next())
		{
			System.out.println(rs.getInt(1));
			System.out.println(rs.getObject(2));
			
		}
	}
	public static void main(String arg[]) throws Exception {
		getInfo1();
	}

}
