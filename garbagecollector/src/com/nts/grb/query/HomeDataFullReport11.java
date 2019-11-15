package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
public class HomeDataFullReport11 {
	private String rprtData;
	private static Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static String fullJson;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "[]";

	public static String getHomeRprtDtls(String crtDt, String endDt) throws Exception {
		System.out.println(crtDt + "\n" + endDt);
		JSONArray homeReportArray1 = new JSONArray();
		con = dbConnection.getConnection();
		Date start_date = sdf.parse(crtDt);
		Date end_date = sdf.parse(endDt);
		StringBuffer homeReportArray = new StringBuffer();
		homeReportArray.append("[");
		System.out.println("Start date: " + sdf.format(start_date) + " End date: " + sdf.format(end_date));
		while (start_date.compareTo(end_date) >= 0) {
			System.out.println("Inside while loop: " + " Start date: " + sdf.format(start_date) + " End date: "
					+ sdf.format(end_date));
			String forDate = sdf.format(start_date);
			/*
			 * String abc1 = getDischargeToSeaReport(forDate); System.out.println("jferfe" +
			 * abc); if (!abc1.equals("")) { homeReportArray.append(abc1).append(",");
			 * abc1=null; } else { abc1=null;
			 * 
			 * } String abc2=getDischargeToShore(forDate); if (!abc2.equals("")) {
			 * homeReportArray.append(abc2).append(","); abc2=null; } else { abc2=null; }
			 */

			/*
			 * homeReportArray.append(getDischargeToShore(forDate));
			 * homeReportArray.append(getDischargeToSeaReport(forDate));
			 * 
			 * homeReportArray.append(getDischargeToSeaAcc(forDate));
			 * homeReportArray.append(getStartIncineration(forDate));
			 */
			String jb = new String();
			jb = getDischargeToSeaReport(forDate);
			if (jb.length() != 0) {
				homeReportArray.append(jb).append(",");
			}
			jb = getDischargeToShore(forDate);
			if (jb.length() != 0) {
				homeReportArray.append(jb).append(",");
				// homeReportArray1.put(getDischargeToSeaReport(forDate));
			}
			/*
			 * jb = getDischargeToSeaAcc(forDate); if (jb.length() != 0) {
			 * homeReportArray.append(jb).append(","); } jb = getStartIncineration(forDate);
			 * if (jb.length() != 0) { homeReportArray.append(jb).append(","); } jb =
			 * getStopIncineration(forDate); if (jb.length() != 0) {
			 * homeReportArray.append(jb).append(","); }
			 */
			// homeReportArray1.put(getDischargeToShore(forDate));

			// homeReportArray1.put(getDischargeToSeaAcc(forDate));
			/*
			 * homeReportArray1.put(getStartIncineration(forDate));
			 * homeReportArray1.put(getStopIncineration(forDate));
			 */

			/*
			 * homeReportArray1.put(new JSONObject(getDischargeToSeaReport(forDate)));
			 * homeReportArray1.put(new JSONObject(getDischargeToShore(forDate)));
			 * homeReportArray1.put(new JSONObject(getDischargeToSeaAcc(forDate)));
			 * homeReportArray1.put(new JSONObject(getStartIncineration(forDate)));
			 * homeReportArray1.put(new JSONObject(getStopIncineration(forDate)));
			 */

			/*
			 * homeReportArray.append(getDischargeToSeaReport(forDate)).append(
			 * getDischargeToShore(forDate))
			 * .append(getDischargeToSeaAcc(forDate)).append(getStartIncineration(forDate))
			 * .append(getStopIncineration(forDate));
			 */
			start_date = new Date(start_date.getTime() - 1 * 24 * 3600 * 1000);// one day minuse
		}
		// homeReportArray1.put(homeReportArray);
		homeReportArray.append("]");
		System.out.println(homeReportArray.toString());
		return homeReportArray.toString();
	}

	private static String getDischargeToSeaReport(String forDate) throws Exception {
		// JSONObject dichargeTosea = new JSONObject();
		String sqlQuery = "select discharge_to_sea.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.officer_id) AS officerFisrtName,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.officer_id) AS officerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.strikeId) AS strikename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.strikeId) AS strikerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.masterReId) AS masterrename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.masterReId) AS masterReRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.amendId) AS amendname, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.amendId)AS amendRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.masterId) AS masterName, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.masterId)AS masterRank \r\n"
				+ "\r\n" + "\r\n" + "FROM discharge_to_sea where entry_date= ?" + "";
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sqlQuery);
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();
		/*
		 * dichargeTosea = DaoUtil.convertToJsonObject1(rs); return dichargeTosea;
		 */
		return DaoUtil.convertToJsonObject1(rs);

	}

	private static String getDischargeToShore(String forDate) throws Exception {
		String sqlQuery = "select discharge_to_ashore.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.officer_id) AS officerFisrtName,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.officer_id) AS officerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.strikeId) AS strikename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.strikeId) AS strikerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.masterReId) AS masterrename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.masterReId) AS masterReRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.amendId) AS amendname, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.amendId)AS amendRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.masterId) AS masterName, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.masterId)AS masterRank \r\n"
				+ "\r\n" + "\r\n" + "FROM discharge_to_ashore where entry_date= ?" + "";
		String dichargeToShore = new String();
		Date start_date2 = sdf.parse(forDate);
		String start_date_2 = sdf.format(start_date2);
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sqlQuery);
		pstmt2.setString(1, forDate);
		ResultSet rs2 = pstmt2.executeQuery();
		return DaoUtil.convertToJsonObject1(rs2);

	}

	private static String getDischargeToSeaAcc(String forDate) throws Exception {
		JSONArray dichargeToseaAcc = new JSONArray();
		String sqlQuery = "select discharge_to_sea_acc.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea_acc.officer_id) AS officerFisrtName,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea_acc.officer_id) AS officerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea_acc.strikeId) AS strikename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea_acc.strikeId) AS strikerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea_acc.masterReId) AS masterrename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea_acc.masterReId) AS masterReRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea_acc.amendId) AS amendname, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea_acc.amendId)AS amendRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea_acc.masterId) AS masterName, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea_acc.masterId)AS masterRank \r\n"
				+ "\r\n" + "\r\n" + "FROM discharge_to_sea_acc where entry_date= ?" + "";
		Date start_date2 = sdf.parse(forDate);
		String start_date_2 = sdf.format(start_date2);
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sqlQuery);
		pstmt2.setString(1, forDate);
		ResultSet rs2 = pstmt2.executeQuery();
		return DaoUtil.convertToJsonObject1(rs2);
	}

	private static String getStartIncineration(String forDate) throws Exception {
		JSONArray startinc = new JSONArray();
		String sqlQuery = "select start_incineration.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=start_incineration.officer_id) AS officerFisrtName,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=start_incineration.officer_id) AS officerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=start_incineration.strikeId) AS strikename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=start_incineration.strikeId) AS strikerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=start_incineration.masterReId) AS masterrename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=start_incineration.masterReId) AS masterReRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=start_incineration.amendId) AS amendname, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=start_incineration.amendId)AS amendRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=start_incineration.masterId) AS masterName, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=start_incineration.masterId)AS masterRank \r\n"
				+ "\r\n" + "\r\n" + "FROM start_incineration where entry_date= ?" + "";
		System.out.println("inside start_incineration method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sqlQuery);
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();
		// startinc = DaoUtil.convertToJsonObject1(rs);
		return DaoUtil.convertToJsonObject1(rs);
	}

	private static String getStopIncineration(String forDate) throws Exception {
		JSONArray stopInc = new JSONArray();
		String sqlQuery = "select stop_incineration.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=stop_incineration.officer_id) AS officerFisrtName,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=stop_incineration.officer_id) AS officerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=stop_incineration.strikeId) AS strikename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=stop_incineration.strikeId) AS strikerRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=stop_incineration.masterReId) AS masterrename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=stop_incineration.masterReId) AS masterReRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=stop_incineration.amendId) AS amendname, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=stop_incineration.amendId)AS amendRank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=stop_incineration.masterId) AS masterName, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=stop_incineration.masterId)AS masterRank \r\n"
				+ "\r\n" + "\r\n" + "FROM stop_incineration where entry_date= ?" + "";
		System.out.println("inside stop_incineration method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sqlQuery);
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();
		// stopInc = DaoUtil.convertToJsonObject1(rs);
		return DaoUtil.convertToJsonObject1(rs);
	}

	public static JSONArray convrt(String data) throws JSONException {
		/******************* Json String to Json Array ******************************/
		// String data = "[{\"userName\": \"sandeep\",\"age\":30},{\"userName\":
		// \"vivan\",\"age\":5}] ";
		System.out.println("Json String: " + data);

		JSONArray jsonArr = new JSONArray(data);
		JSONArray dataArr = new JSONArray();
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);

			System.out.println(jsonObj);
			dataArr.put(jsonObj);
		}

		System.out.println("Data Json Array: " + dataArr);
		return dataArr;
	}
}