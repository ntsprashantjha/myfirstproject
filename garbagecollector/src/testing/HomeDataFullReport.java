package testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

import testing.DischargeToSea_dtls;

public class HomeDataFullReport {
	private String rprtData;
	private static Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private static String fullJson;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
private static JSONObject jsonobj;
static JSONArray homeReportArray1 = new JSONArray();
	public static String getHomeRprtDtls(String crtDt, String endDt) throws Exception {
		con = dbConnection.getConnection();
		Date start_date = sdf.parse(crtDt);
		Date end_date = sdf.parse(endDt);
		StringBuffer homeReportArray = new StringBuffer();
		// homeReportArray.append("[");
		System.out.println("Start date: " + sdf.format(start_date) + " End date: " + sdf.format(end_date));
		while (start_date.compareTo(end_date) >= 0) {
			System.out.println("Inside while loop: " + " Start date: " + sdf.format(start_date) + " End date: "
					+ sdf.format(end_date));
//		String forDate = "2019-04-02";		
			String forDate = sdf.format(start_date);
			System.out.println("Data for date: " + forDate);
			homeReportArray.append(getDischargeToSeaReport(forDate)).append(getDischargeToShore(forDate))
					.append(getDischargeToSeaAcc(forDate)).append(getStartIncineration(forDate))
					.append(getStopIncineration(forDate));
			/*
			 * System.out.println("Called method getDischargeToSea: " +
			 * getDischargeToSeaReport(forDate));
			 * System.out.println("Called method getDischargeToShore: " +
			 * getDischargeToShore(forDate));
			 * System.out.println("Called method getDischargeToSeaAcc: " +
			 * getDischargeToSeaAcc(forDate));
			 * System.out.println("Called method getDischargeToSeaAcc: " +
			 * getStopIncineration(forDate));
			 * System.out.println("Called method getDischargeToSeaAcc: " +
			 * getStartIncineration(forDate));
			 */
			start_date = new Date(start_date.getTime() - 1 * 24 * 3600 * 1000);// one day minuse
		}
		// homeReportArray.append("]");
		homeReportArray1.put(homeReportArray);
		// ***************************************************************
		return homeReportArray1.toString();
	}

	private static String getDischargeToSeaReport(String forDate) throws Exception {
		/*JSONObject dichargeTosea=new JSONObject();
		System.out.println("inside dischargetosea method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.*, discharge_to_sea.* FROM user_details INNER JOIN discharge_to_sea ON discharge_to_sea.officer_id=user_details.user_id where entry_date= ?");
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();
		
		//dichargeTosea.append("dischargeToSea", value);
		
		return DaoUtil.convertToJsonObject(rs).toString();*/
		
		jsonobj.append("full", DischargeToSea_dtls.fulldtls(forDate));
		jsonobj.append("strikerdtls", DischargeToSea_dtls.fulldtls(forDate));
		jsonobj.append("mstrdtls", DischargeToSea_dtls.fulldtls(forDate));
		return homeReportArray1.toString();
	}

	private static String getDischargeToShore(String forDate) throws Exception {
		Date start_date2 = sdf.parse(forDate);
		String start_date_2 = sdf.format(start_date2);
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.*, discharge_to_ashore.* FROM user_details INNER JOIN discharge_to_ashore ON discharge_to_ashore.officer_id=user_details.user_id where entry_date= ?");
		pstmt2.setString(1, forDate);
		ResultSet rs2 = pstmt2.executeQuery();
		return DaoUtil.convertToJsonObject(rs2).toString();
	}

	private static String getDischargeToSeaAcc(String forDate) throws Exception {
		Date start_date2 = sdf.parse(forDate);
		String start_date_2 = sdf.format(start_date2);
		PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.*, discharge_to_sea_acc.* FROM user_details INNER JOIN discharge_to_sea_acc ON discharge_to_sea_acc.officer_id=user_details.user_id where entry_date= ?");
		pstmt2.setString(1, forDate);
		ResultSet rs2 = pstmt2.executeQuery();
		return DaoUtil.convertToJsonObject(rs2).toString();
	}

	private static String getStartIncineration(String forDate) throws Exception {
		System.out.println("inside start_incineration method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.*, start_incineration.* FROM user_details INNER JOIN start_incineration ON start_incineration.officer_id=user_details.user_id where entry_date= ?");
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();
		return DaoUtil.convertToJsonObject(rs).toString();
	}

	private static String getStopIncineration(String forDate) throws Exception {
		System.out.println("inside stop_incineration method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.*, stop_incineration.* FROM user_details INNER JOIN stop_incineration ON stop_incineration.officer_id=user_details.user_id where entry_date= ?");
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();
		return DaoUtil.convertToJsonObject(rs).toString().replaceAll("[/]", " ");
	}

	public static void main(String arg[]) throws Exception {
		System.out.println("Record fetched: " + new HomeDataFullReport().getHomeRprtDtls("", ""));
		// System.out.println("max date: " + HomeDataReport.getMaxDate());
	}

}