package testing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.model.AllFilters;
import com.nts.grb.util.DaoUtil;

public class FilterDao {
	private static Connection con = null;
	private ResultSet rs = null;
	private AllFilters alfo;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "";

	public JSONArray dtlsViaEntryType(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();

		if (table_name.equals("Accidental_to_Sea")) {

			table_name = "discharge_to_sea_acc";
		}

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id ";

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_es_all(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		/*
		 * String sqlQuery = "Select * from " + alfo.getTablename1() +
		 * " where garbage_category like '" + alfo.getCategory() + "%'";
		 */

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where garbage_category like '" + alfo.getCategory() + "%'";

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_ec_all(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		/*
		 * String sqlQuery = "Select * from " + alfo.getTablename1() +
		 * " where master_approval_done = " + alfo.getStatusvalue();
		 */

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where master_approval_done = " + alfo.getStatusvalue();

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_ec_strkd(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		// String sqlQuery = "Select * from " + alfo.getTablename1() + " where
		// strike_approval_done =1 ";
		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where strike_approval_done = 1";

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_ec_re_all(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where master_reapproval_done = " + alfo.getStatusvalue();
		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_all_fltr_slctd(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where master_approval_done = " + alfo.getStatusvalue()
				+ " AND garbage_category like '" + alfo.getCategory() + "%'";

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_all_fltr_slctd_re(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where master_reapproval_done = " + alfo.getStatusvalue()
				+ " AND garbage_category like '" + alfo.getCategory() + "%'";

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray dtls_acc_all_fltr_slctd_strkd(AllFilters alfo, String forDate) throws Exception {

		con = dbConnection.getConnection();
		this.alfo = alfo;
		String table_name = alfo.getTablename1();
		System.out.println(table_name);
		if (table_name.equals("Accidental_to_Sea")) {
			table_name = "discharge_to_sea_acc";
		}

		String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
				+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
				+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
				+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
				+ "UDTLS5.user_rank as master_rank FROM " + table_name + " DTS LEFT JOIN user_details "
				+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
				+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
				+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
				+ " DTS.master_id where strike_approval_done =1 " + " AND garbage_category like '" + alfo.getCategory()
				+ "%'";

		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray filter_inc_cond(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id ";

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_acc_cate(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where garbage_category like '" + alfo.getCategory() + "%'";

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_apprvd(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where master_approval_done = " + alfo.getStatusvalue();

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_reapprvd(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where master_reapproval_done = " + alfo.getStatusvalue();

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_reapprvd_cate_wise(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where master_reapproval_done = " + alfo.getStatusvalue()
					+ " AND garbage_category like '" + alfo.getCategory() + "%'";

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_apprvd_cate_wise(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where master_approval_done = " + alfo.getStatusvalue()
					+ " AND garbage_category like '" + alfo.getCategory() + "%'";

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_striked_cond(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where strike_approval_done = 1";

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public JSONArray filter_inc_cond_striked_cond_cate_wise(String tableName[], AllFilters alfo) throws Exception {

		JSONArray abc = new JSONArray();
		JSONArray forRtrn = new JSONArray();

		con = dbConnection.getConnection();

		for (int i = 0; i <= tableName.length - 1; i++) {

			JSONArray forRtrn1 = new JSONArray();

			String sqlQuery = "SELECT DTS.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, "
					+ "UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM " + tableName[i] + " DTS LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = DTS.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = "
					+ "DTS.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = DTS.master_reid LEFT JOIN user_details "
					+ "UDTLS4 ON UDTLS4.user_id = DTS.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id ="
					+ " DTS.master_id where strike_approval_done = 1"
					 + " AND garbage_category like '" + alfo.getCategory() + "%'";

			System.out.println(sqlQuery);
			PreparedStatement ps = con.prepareStatement(sqlQuery);

			ResultSet resultSet = ps.executeQuery();
			forRtrn1 = DaoUtil.convertToJsonArray1(resultSet);

			for (int j = 0; j <= forRtrn1.length() - 1; j++) {

				forRtrn.put(forRtrn1.getJSONObject(j));
			}
		}

		for (int i = 0; i <= forRtrn.length() - 1; i++) {

			abc.put(forRtrn.getJSONObject(i));
		}

		return abc;
	}

	public static void main(String arg[]) throws Exception {
	}

}
