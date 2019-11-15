package testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class DischargeToSea_dtls {
	private static Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String fulldtls(String forDate) throws Exception {
		con = dbConnection.getConnection();
		JSONObject dichargeTosea = new JSONObject();
		System.out.println("inside dischargetosea method"+forDate);
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.*, discharge_to_sea.* FROM user_details INNER JOIN discharge_to_sea ON discharge_to_sea.officer_id=user_details.user_id where entry_date= ?");
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();

		// dichargeTosea.append("dischargeToSea", value);

		return DaoUtil.convertToJsonObject(rs).toString();
	}

	public static String strikerid(String forDate) throws Exception {
		JSONObject dichargeTosea = new JSONObject();
		System.out.println("inside dischargetosea method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.* FROM user_details INNER JOIN discharge_to_sea ON discharge_to_sea.masterReName=user_details.user_id\r\n"
						+ " where entry_date= ?");
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();

		// dichargeTosea.append("dischargeToSea", value);

		return DaoUtil.convertToJsonObject(rs).toString();
	}

	public static String masterdtls(String forDate) throws Exception {
		JSONObject dichargeTosea = new JSONObject();
		System.out.println("inside dischargetosea method");
		Date start_date = sdf.parse(forDate);
		String start_date_1 = sdf.format(start_date);
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(
				"SELECT user_details.* FROM user_details INNER JOIN discharge_to_sea ON discharge_to_sea.strikeName=user_details.user_id where entry_date= ?");
		pstmt.setString(1, forDate);
		ResultSet rs = pstmt.executeQuery();

		// dichargeTosea.append("dischargeToSea", value);

		return DaoUtil.convertToJsonObject(rs).toString();
	}

}
