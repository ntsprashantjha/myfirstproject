package testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.Statement;
import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.LastEntryDetails;
import com.nts.grb.util.DaoUtil;

public class QueryTsting {
	private String rprtData;
	private static Connection con = null;
	private static Statement stmt = null;
	private ResultSet rs = null;
	static String[] tablesName = { "discharge_to_ashore", "start_incineration", "start_incineration_acc",
			"discharge_to_sea_acc" };

	/*
	 * public JSONArray lastentryDetailsInc() throws Exception { con =
	 * dbConnection.getConnection(); //String
	 * sql="select DTS.*, UDTLS1.user_first_name as officerFisrtName, UDTLS1.user_rank as officerRank,UDTLS2.user_first_name as strikename, UDTLS2.user_rank as strikerRank,UDTLS3.user_first_name as masterrename, UDTLS3.user_rank as masterReRank,UDTLS4.user_first_name as amendname, UDTLS4.user_rank as amendRank,UDTLS5.user_first_name as masterName, UDTLS5.user_rank as masterRank FROM discharge_to_sea DTS LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = DTS.strikeIdLEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.masterReId LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = DTS.amendIdLEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = DTS.masterIdWHERE DTS.entry_date = '2019-04-25'"
	 * ; String sql = "select DTS.*, \r\n" +
	 * "UDTLS1.user_first_name as officerFisrtName, UDTLS1.user_rank as officerRank, \r\n"
	 * +
	 * "UDTLS2.user_first_name as strikename, UDTLS2.user_rank as strikerRank, \r\n"
	 * +
	 * "UDTLS3.user_first_name as masterrename, UDTLS3.user_rank as masterReRank,\r\n"
	 * + "UDTLS4.user_first_name as amendname, UDTLS4.user_rank as amendRank,\r\n" +
	 * "UDTLS5.user_first_name as masterName, UDTLS5.user_rank as masterRank \r\n" +
	 * "FROM discharge_to_sea DTS \r\n" +
	 * "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = DTS.officer_id \r\n" +
	 * "LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = DTS.strikeId\r\n" +
	 * "LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.masterReId \r\n" +
	 * "LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = DTS.amendId\r\n" +
	 * "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = DTS.masterId\r\n" +
	 * "WHERE DTS.entry_date = '2019-04-25'";
	 * 
	 * String sqlQuery = "select discharge_to_sea.* ,\r\n" +
	 * "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.officer_id) AS officerFisrtName,\r\n"
	 * +
	 * "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.officer_id) AS officerRank ,\r\n"
	 * + "\r\n" +
	 * "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.strikeId) AS strikename, \r\n"
	 * +
	 * "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.strikeId) AS strikerRank ,\r\n"
	 * + "\r\n" +
	 * "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.masterReId) AS masterrename, \r\n"
	 * +
	 * "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.masterReId) AS masterReRank ,\r\n"
	 * + "\r\n" +
	 * "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.amendId) AS amendname, \r\n"
	 * +
	 * "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.amendId)AS amendRank ,\r\n"
	 * + "\r\n" +
	 * "(select user_details.user_first_name from user_details where user_details.user_id=discharge_to_sea.masterId) AS masterName, \r\n"
	 * +
	 * "(select user_details.user_rank from user_details where user_details.user_id=discharge_to_sea.masterId)AS masterRank \r\n"
	 * + "\r\n" + "\r\n" + "FROM discharge_to_sea";
	 * 
	 * // String sql = "SELECT incineration_condition,entry_type FROM `master_table`
	 * // WHERE entry_type = 'discharge_to_sea' or entry_type = 'stop_Incineration'
	 * // AND entry_strike_cond=0 ORDER BY entry_id DESC LIMIT 1"; ps =
	 * con.prepareStatement(sqlQuery); rs = ps.executeQuery(); return
	 * DaoUtil.convertToJsonObject1(rs);//.replaceAll("\\[", "").replaceAll("\\]",
	 * ""); }
	 * 
	 * public static void main(String arg[]) throws Exception { JSONArray sb1 = new
	 * JSONArray(); System.out.println(sb1.put(new
	 * QueryTsting().lastentryDetailsInc())); }
	 */
	public static int approveUpdate1(String tablename, int entryId, int activeuserid, String approvecomment,
			String crtdt) throws SQLException {
		con = dbConnection.getConnection();
		stmt = (Statement) con.createStatement();
		String sql = "update " + tablename + "," + tablesName[0] + "," + tablesName[1] + "," + tablesName[2] + ","
				+ tablesName[3] + " set " + tablename + ".masterApprovalDone=1," + tablename + ".masterApprovalDone=1,"
				+ tablename + ".masterTime='" + crtdt + "'," + tablename + ".masterApproval='Done'," + tablename
				+ ".masterId=" + activeuserid + "," + tablename + ".masterComment='" + approvecomment + "',"
				+ tablesName[0] + ".masterApprovalDone=1," + tablesName[0] + ".masterApprovalDone=1," + tablesName[0]
				+ ".masterTime='" + crtdt + "'," + tablesName[0] + ".masterApproval='Done'," + tablesName[0]
				+ ".masterId=" + activeuserid + "," + tablesName[0] + ".masterComment='" + approvecomment + "',"
				+ tablesName[1] + ".masterApprovalDone=1," + tablesName[1] + ".masterApprovalDone=1," + tablesName[1]
				+ ".masterTime='" + crtdt + "'," + tablesName[1] + ".masterApproval='Done'," + tablesName[1]
				+ ".masterId=" + activeuserid + "," + tablesName[1] + ".masterComment='" + approvecomment + "',"
				+ tablesName[2] + ".masterApprovalDone=1," + tablesName[2] + ".masterApprovalDone=1," + tablesName[2]
				+ ".masterTime='" + crtdt + "'," + tablesName[2] + ".masterApproval='Done'," + tablesName[2]
				+ ".masterId=" + activeuserid + "," + tablesName[2] + ".masterComment='" + approvecomment
				+ "' where " + tablename + ".entry_Id<=" + entryId;
		System.out.println(sql + "----:query");
		return stmt.executeUpdate(sql);

	}
	

	public static void main(String arg[]) throws SQLException {
		QueryTsting.approveUpdate1("discharge_to_sea", 22, 1, "pl", "2019-05-06 14:22:04");
	}
}
