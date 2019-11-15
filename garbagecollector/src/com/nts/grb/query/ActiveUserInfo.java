package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.util.DaoUtil;

public class ActiveUserInfo {
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public String activateUser(String macid) throws Exception {
		System.out.println("****mac id from active user*****" + macid);
		con = dbConnection.getConnection();
		/*
		 * String sql =
		 * "select user_id,user_first_name,user_country,user_seamanBook,user_dob,user_joindate,user_last_name,user_confirm_pasword,user_rank from user_details where user_activity=1 AND user_macId='"
		 * + macid + "'";
		 */
		String sql = "SELECT E.*,D.*,rnk.user_rank FROM mrb_module_access_right E, user_details D,rank_details rnk WHERE E.user_rank_id=D.rank_id AND (D.user_macId='"
				+ macid + "' AND user_activity=1) AND (E.user_rank_id=rnk.rank_id)";
		/*
		 * String
		 * sql="select software_name from mrb_software_list WHERE mrb_software_list.software_id IN (select software_id from mrb_software_list_rank_details_access_right where mrb_software_list_rank_details_access_right.rank_id IN (select user_details.rank_id FROM user_details WHERE user_details.user_macId='"
		 * +macid+"' AND user_details.user_activity=1))";
		 */ // String sql = "select * user_details where user_activity=1 AND
			// user_macId='"+macid+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		return DaoUtil.convertToJsonArray(rs);
	}

	public static int activeUserId(String macid) throws SQLException {
		int user_id = 0;
		con = dbConnection.getConnection();
		String sql = "select user_id from user_details where user_activity=1 AND user_macId='" + macid + "'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			user_id = rs.getInt("user_id");
		}
		System.out.println("**--from return active user id--**" + user_id);
		return user_id;
	}

	public String activateUser1(String macid) throws Exception {
		System.out.println(macid);
		con = dbConnection.getConnection();
		String sql = "select user_id,user_first_name,emp_id,user_country,user_seamanBook,user_dob,user_joindate,user_last_name,user_confirm_pasword,user_rank from user_details where user_activity=1 AND user_macId='"
				+ macid + "'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}

	public static void main(String arg[]) throws Exception {
		

		System.out.println(((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1("192.168.1.101"))).get(0)).getString("user_first_name"));
	}
}
