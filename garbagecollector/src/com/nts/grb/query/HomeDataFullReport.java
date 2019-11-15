package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class HomeDataFullReport {
	private static Connection con = null;
	private PreparedStatement ps = null;
	static Statement stmt = null;
	ResultSet rset = null;
	private ResultSet rs = null;
	static String abc1="";
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "[]";

	public static String getHomeRprtDtls(String crtDt, String endDt) throws Exception {
		JSONArray homeReportArray1 = new JSONArray();
		con = dbConnection.getConnection();
		Date start_date = sdf.parse(crtDt);
		Date end_date = sdf.parse(endDt);
		JSONArray globalArray = new JSONArray();

		System.out.println("Start date: " + sdf.format(start_date) + " End date: " + sdf.format(end_date));
		while (start_date.compareTo(end_date) >= 0) {

			String forDate = sdf.format(start_date);
			JSONArray objJsonArr = new JSONArray();

			objJsonArr = getDischargeToSeaReport(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < getDischargeToSeaReport(forDate).length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}

			objJsonArr = getDischargeToShoreReport(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStartIncineration(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStopIncineration(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStartIncinerationAcc(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getDischargeToSeaReportAcc(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			objJsonArr = getStopIncinerationAcc(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < objJsonArr.length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			start_date = new Date(start_date.getTime() - 1 * 24 * 3600 * 1000);// one day minus
		}

		// System.out.println("final array data : " + globalArray);
		return globalArray.toString();
	}

	private static JSONArray getDischargeToSeaReport(String forDate) throws Exception {
		System.out.println("inside dischargetosea method");
		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_sea DTS LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = DTS.master_id WHERE DTS.entry_date =? ORDER BY entry_time DESC"
				+ "";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getDischargeToShoreReport(String forDate) throws Exception {
		String sqlQuery = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_ashore dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dta.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_date =? ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStartIncineration(String forDate) throws Exception {
		String sqlQuery = "SELECT strtinc.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM start_incineration strtinc LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = strtinc.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = strtinc.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = strtinc.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = strtinc.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = strtinc.master_id WHERE strtinc.entry_date =? ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStopIncineration(String forDate) throws Exception {
		String sqlQuery = "SELECT si.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM stop_incineration si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id WHERE si.entry_date =? ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStartIncinerationAcc(String forDate) throws Exception {
		String sqlQuery = "SELECT si.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM start_incineration_acc si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id WHERE si.entry_date =? ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getDischargeToSeaReportAcc(String forDate) throws Exception {
		String sqlQuery = "SELECT si.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_sea_acc si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id WHERE si.entry_date =? ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getStopIncinerationAcc(String forDate) throws Exception {
		String sqlQuery = "SELECT si.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM stop_incineration_acc si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id WHERE si.entry_date =? ORDER BY entry_time DESC";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONObject getEntryId(String strDt, String endt) throws Exception {
		String[] tableName = { "discharge_to_sea", "discharge_to_ashore", "discharge_to_sea_acc", "start_incineration",
				"stop_incineration", "start_incineration_acc", "stop_incineration_acc" };
		con = dbConnection.getConnection();
		for (int i = 0; i < tableName.length - 1; i++) {
			String sql_qury = "SELECT entry_Id from " + tableName[i] + " where entry_date between '" + strDt + "' AND '"
					+ endt+"'";
			stmt = con.createStatement();
			System.out.println(sql_qury);
			ResultSet rset = stmt.executeQuery(sql_qury);
			abc1 = DaoUtil.convertToJsonArray(rset);
		}
System.out.println(abc1);
		return null;
	}

	public static void main(String arg[]) throws Exception {
		 System.out.println(HomeDataFullReport.getEntryId("2019-06-10","2019-06-12"));
	}

}