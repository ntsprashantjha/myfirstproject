package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class FullookReport {
	private static Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "[]";

	public static String getHomeRprtDtls() throws Exception {
		JSONArray homeReportArray1 = new JSONArray();
		con = dbConnection.getConnection();
		JSONArray globalArray = new JSONArray();
			JSONArray objJsonArr = new JSONArray();

			objJsonArr = getDischargeToSeaReport();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < getDischargeToSeaReport().length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}

			objJsonArr = getDischargeToShoreReport();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStartIncineration();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStopIncineration();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStartIncinerationAcc();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getDischargeToSeaReportAcc();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStopIncinerationAcc();
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			
		// System.out.println("final array data : " + globalArray);
		return globalArray.toString();
	}

	private static JSONArray getDischargeToSeaReport() throws Exception {
		System.out.println("inside dischargetosea method");
		String sqlQuery = "SELECT DTS.*,DATE_FORMAT(DTS.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_sea DTS LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = DTS.master_id ORDER BY entry_time DESC"
				+ "";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getDischargeToShoreReport() throws Exception {
		String sqlQuery = "SELECT dta.*,DATE_FORMAT(dta.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_ashore dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dta.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStartIncineration() throws Exception {
		String sqlQuery = "SELECT strtinc.*,DATE_FORMAT(strtinc.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM start_incineration strtinc LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = strtinc.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = strtinc.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = strtinc.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = strtinc.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = strtinc.master_id ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStopIncineration() throws Exception {
		String sqlQuery = "SELECT si.*,DATE_FORMAT(si.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM stop_incineration si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStartIncinerationAcc() throws Exception {
		String sqlQuery = "SELECT si.*,DATE_FORMAT(si.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM start_incineration_acc si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getDischargeToSeaReportAcc() throws Exception {
		String sqlQuery = "SELECT si.*,DATE_FORMAT(si.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_sea_acc si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStopIncinerationAcc() throws Exception {
		String sqlQuery = "SELECT si.*,DATE_FORMAT(si.entry_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM stop_incineration_acc si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public static void main(String arg[]) throws Exception {
		System.out.println("uhiujoi"+FullookReport.getHomeRprtDtls());
	}

}