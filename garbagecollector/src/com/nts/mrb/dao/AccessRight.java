package com.nts.mrb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
import com.nts.grb.validation.ReturnResponse;

public class AccessRight {

	//private String rprtData;
	private static Connection con = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public String acc_right_details() {

		String retrun_data = null;
		con = dbConnection.getConnection();
		String sql = "SELECT E.*,D.user_rank,D.user_status FROM mrb_access_rights E ,rank_details D WHERE E.rank_id=D.rank_id";

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			retrun_data = DaoUtil.convertToJsonArray(rs);
			

		}

		catch (Exception e) {
			ReturnResponse.retrnresponse_exception_occured();
		}
		return retrun_data;
	}
	
	public String acc_right_detailsuser(String rnkid) {

		String return_data = null;
		con = dbConnection.getConnection();
		String sql = "SELECT * from mrb_access_rights where rank_id =" + rnkid;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return_data = DaoUtil.convertToJsonArray(rs);
			return return_data;
		}

		catch (Exception e) {
			
			ReturnResponse.retrnresponse_exception_occured();
			return " ";
		}
		
	}

	public String module_acc_right_details() {
		String rtrn = null;
		con = dbConnection.getConnection();
		// String sql = "SELECT E.*,D.user_rank FROM mrb_module_access_right E NATURAL
		// JOIN user_details D";
		// String sql = "select software_name from mrb_software_list WHERE
		// mrb_software_list.software_id IN (select software_id from
		// mrb_software_list_rank_details_access_right where
		// mrb_software_list_rank_details_access_right.rank_id=2)";
		/*
		 * String sql="select \r\n" +
		 * "    mrb_software_list.software_name, rank_details.rank_name \r\n" +
		 * "from \r\n" +
		 * "    mrb_software_list, rank_details,mrb_software_list_rank_details_access_right \r\n"
		 * + "where \r\n" +
		 * "   rank_details.rank_id = mrb_software_list_rank_details_access_right.rank_id\r\n"
		 * +
		 * "   AND     mrb_software_list.software_id = mrb_software_list_rank_details_access_right.software_id"
		 * ;
		 */
		try {

			String sql = "SELECT DISTINCT rd.user_rank,E.* FROM mrb_module_access_right E ,rank_details rd WHERE E.user_rank_id=rd.rank_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rtrn = DaoUtil.convertToJsonArray(rs);

		} catch (Exception e) {
			ReturnResponse.retrnresponse_exception_occured();

		}
		return rtrn;
	}
	
	public String module_acc_right_detailsuser(String rnkid) {
	
		con = dbConnection.getConnection();
		try {

			String sql = "SELECT * from mrb_module_access_right where user_rank_id="+ rnkid;
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			return DaoUtil.convertToJsonArray(rs);

		} catch (Exception e) {
			
			ReturnResponse.retrnresponse_exception_occured();
			return " ";
		}
		
	}

	public static String company_details() throws Exception {
		
		try{
			
		con = dbConnection.getConnection();
		String sql = "SELECT * from company_details";
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		
		return DaoUtil.convertToJsonArray(rs);
		
		}catch (SQLException e) {
			
			e.printStackTrace();
			return ReturnResponse.retrnresponse_db_error().toString();			
		}
		
		}

	public String vessel_details() throws Exception {

		con = dbConnection.getConnection();
		// String sql = "SELECT *,DATE_FORMAT(date_delivered, \"%d-%b-%Y\") As
		// delivered_date,DATE_FORMAT(date_keel_laid, \"%d-%b-%Y\") As keel_laid_date
		// FROM `vessel_details`";

		String sql = "SELECT * FROM vessel_details";
		ps = con.prepareStatement(sql);

		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);

	}

	public String time_zone_details() {
		try {
		con = dbConnection.getConnection();
		String sql = "SELECT * from time_zone";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}catch (Exception e) {
		
		e.printStackTrace();
		return ReturnResponse.somethingWentWrong().toString();
	}
		
	}
	
	public static void main(String arg[]) throws Exception {
		
		System.out.println(new AccessRight().module_acc_right_detailsuser("1"));
		
	}

}