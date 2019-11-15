package backup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class HomeDataFullReport {
	private static Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "[]";
	static String crtDt = "2019-05-07";
	static String endDt = "2019-05-01";

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
			objJsonArr = getStopIncineration(forDate);
			if (objJsonArr.length() != 0) {
				for (int i = 0; i < getDischargeToSeaReport(forDate).length(); i++) {
					globalArray.put(objJsonArr.get(i));
				}
			}
			start_date = new Date(start_date.getTime() - 1 * 24 * 3600 * 1000);// one day minus
		}

		System.out.println("final array data : " + globalArray);
		return globalArray.toString();
	}

	private static JSONArray getDischargeToSeaReport(String forDate) throws Exception {
		System.out.println("inside dischargetosea method");
		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_sea DTS LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = DTS.master_id WHERE DTS.entry_date =?"
				+ "";
		System.out.println("forDate: " + forDate);
		String sqlQuery1 = "select discharge_to_sea.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.officer_id) AS officer_fisrtname,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.officer_id) AS officer_rank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.strike_id) AS strike_name, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.strike_id) AS striker_rank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.master_reid) AS master_rename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.master_reid) AS master_rerank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.amend_id) AS amend_name, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.amend_id)AS amend_rank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.master_id) AS master_name, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.master_id)AS master_rank \r\n"
				+ "\r\n" + "\r\n" + "FROM discharge_to_sea where entry_date= ?" + "";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	private static JSONArray getDischargeToShoreReport(String forDate) throws Exception {
		String sqlQuery = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM discharge_to_ashore dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dta.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_date =?";

		String sqlQuery1 = "select discharge_to_ashore.* ,\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.officer_id) AS officer_fisrt_name,\r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.officer_id) AS officer_rank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.strike_id) AS strike_name, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.strike_id) AS striker_rank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.master_reid) AS master_rename, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.master_reid) AS master_rerank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.amend_id) AS amend_name, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.amend_id)AS amend_rank ,\r\n"
				+ "\r\n"
				+ "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_ashore.master_id) AS master_name, \r\n"
				+ "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_ashore.master_id)AS master_rank \r\n"
				+ "\r\n" + "\r\n" + "FROM discharge_to_ashore where entry_date= ?" + "";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	// private static JSONArray getDischargeToSeaAcc(String forDate) throws
	// Exception {}

	// private static JSONArray getStartIncineration(String forDate) throws
	// Exception {}

	private static JSONArray getStopIncineration(String forDate) throws Exception {
		String sqlQuery = "SELECT si.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM stop_incineration si LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = si.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = si.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = si.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = si.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = si.master_id WHERE si.entry_date =?";
		PreparedStatement prepareStatement = (PreparedStatement) con.prepareStatement(sqlQuery);
		prepareStatement.setString(1, forDate);
		ResultSet resultSet = prepareStatement.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet);
	}

}