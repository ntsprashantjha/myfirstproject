package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;
import com.nts.mrb.model.AllFilters;

public class HomeDataFullFilterReport {
	private static Connection con = null;
	private ResultSet rs = null;
	private AllFilters allFilters;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "";

	/*
	 * public static String getHomeRprtDtls(String crtDt, String endDt) throws
	 * Exception {
	 * 
	 * 
	 * JSONArray homeReportArray1 = new JSONArray();
	 * 
	 * Date start_date = sdf.parse(crtDt); Date end_date = sdf.parse(endDt);
	 * JSONArray globalArray = new JSONArray(); System.out.println("Start date: " +
	 * sdf.format(start_date) + " End date: " + sdf.format(end_date)); while
	 * (start_date.compareTo(end_date) >= 0) {
	 * 
	 * String forDate = sdf.format(start_date); JSONArray objJsonArr = new
	 * JSONArray();
	 * 
	 * objJsonArr = getDischargeToSeaReport(forDate); if (objJsonArr.length() != 0)
	 * { for (int i = 0; i < getDischargeToSeaReport(forDate).length(); i++) {
	 * globalArray.put(objJsonArr.get(i)); } }
	 * 
	 * objJsonArr = getDischargeToShoreReport(forDate); if (objJsonArr.length() !=
	 * 0) { for (int i = 0; i < objJsonArr.length(); i++) {
	 * globalArray.put(objJsonArr.get(i)); } } objJsonArr =
	 * getStartIncineration(forDate); if (objJsonArr.length() != 0) { for (int i =
	 * 0; i < objJsonArr.length(); i++) { globalArray.put(objJsonArr.get(i)); } }
	 * objJsonArr = getStopIncineration(forDate); if (objJsonArr.length() != 0) {
	 * for (int i = 0; i < objJsonArr.length(); i++) {
	 * globalArray.put(objJsonArr.get(i)); } } objJsonArr =
	 * getStartIncinerationAcc(forDate); if (objJsonArr.length() != 0) { for (int i
	 * = 0; i < objJsonArr.length(); i++) { globalArray.put(objJsonArr.get(i)); } }
	 * objJsonArr = getDischargeToSeaReportAcc(forDate); if (objJsonArr.length() !=
	 * 0) { for (int i = 0; i < objJsonArr.length(); i++) {
	 * globalArray.put(objJsonArr.get(i)); } } objJsonArr =
	 * getStopIncinerationAcc(forDate); if (objJsonArr.length() != 0) { for (int i =
	 * 0; i < objJsonArr.length(); i++) { globalArray.put(objJsonArr.get(i)); } }
	 * start_date = new Date(start_date.getTime() - 1 * 24 * 3600 * 1000);// one day
	 * minus }
	 * 
	 * // System.out.println("final array data : " + globalArray); return
	 * globalArray.toString(); }
	 */

	public JSONArray toType(AllFilters allFilters, String forDate) throws Exception {
		con = dbConnection.getConnection();
		this.allFilters = allFilters;
		System.out.println("inside dischargetosea method");
		String sqlQuery = "Select *from " + allFilters.getTablename1() + "";
		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeDate(AllFilters allFilters, String forDate) throws Exception {
		con = dbConnection.getConnection();
		this.allFilters = allFilters;
		System.out.println("inside dischargetosea method where entry_date between '" + allFilters.getTodate()
				+ "' and '" + allFilters.getFordate() + "'");
		String sqlQuery = "Select *from " + allFilters.getTablename1() + "";
		System.out.println(sqlQuery);

		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeCat(AllFilters allFilters, String forDate) throws Exception {
	Connection	conn = dbConnection.getConnection();
		this.allFilters = allFilters;
		String sqlQuery1 = "Select * from " + allFilters.getTablename1() + " where garbage_category like '"//concat('"+allFilters.getCategory()+"','%')";
				+ allFilters.getCategory() + "%'";
		System.out.println(sqlQuery1);
		//ps.setString(1, allFilters.getCategory());
		PreparedStatement ps1 = conn.prepareStatement(sqlQuery1);
		ResultSet resultSet1 = ps1.executeQuery();
		return DaoUtil.convertToJsonArray1(resultSet1);
	}

	public JSONArray toTypeCatDate(AllFilters allFilters, String forDate) throws Exception {
		con = dbConnection.getConnection();
		this.allFilters = allFilters;

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where entry_date between "
				+ allFilters.getTodate() + " and " + allFilters.getFordate() + " && garbage_category like '"
				+ allFilters.getCategory() + "%'";
		PreparedStatement ps = con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeStatusCatA(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();
		System.out.println("on totyectas");
		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where garbage_category like '"
				+ allFilters.getCategory() + "%' and master_approval_done=" + allFilters.getStatusvalue() + "";
		System.out.println(sqlQuery);
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeStatusCatADate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' and garbage_category like '"
				+ allFilters.getCategory() + "%' and master_approval_done=" + allFilters.getStatusvalue() + " ";
		System.out.println(sqlQuery);
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeStatusCatReApp(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where garbage_category like '"
				+ allFilters.getCategory() + "%' and master_reapproval_done=" + allFilters.getStatusvalue() + "  ";
		System.out.println(sqlQuery);
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeStatusCatReAppDate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && garbage_category like '"
				+ allFilters.getCategory() + "%' and master_reapproval_done=" + allFilters.getStatusvalue() + "  ";
		System.out.println(sqlQuery);
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeStatusA(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where master_approval_done='"
				+ allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);

	}

	public JSONArray toTypeStatusADate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && master_approval_done='"
				+ allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);

	}

	public JSONArray toTypeStatusReApp(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where master_reapproval_done='"
				+ allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);
		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toTypeStatusReAppDate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where  entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && master_reapproval_done='"
				+ allFilters.getStatusvalue() + "' ";
		System.out.println(sqlQuery);
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);

	}

	public JSONArray toCat(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where garbage_category like '"
				+ allFilters.getCategory() + "%' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toCatDate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where  entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && garbage_category like '"
				+ allFilters.getCategory() + "%' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toCatStatusReApp(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where garbage_category like '"
				+ allFilters.getCategory() + "'% and master_reapproval_done='" + allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toCatStatusReAppDate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && garbage_category like '"
				+ allFilters.getCategory() + "'% and master_reapproval_done='" + allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toCatStatusA(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where garbage_category like '"
				+ allFilters.getCategory() + "%'and master_approval_done='" + allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toCatStatusADate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where  entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && garbage_category like '"
				+ allFilters.getCategory() + "%'and master_approval_done='" + allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toStatusA(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where  master_approval_done='"
				+ allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toStatusADate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from " + allFilters.getTablename1() + " where entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && master_approval_done='"
				+ allFilters.getStatusvalue() + "' ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toStatusReApp(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();

		String sqlQuery = "Select *from '" + allFilters.getTablename1() + "' where entry_date between "
				+ allFilters.getTodate() + " and " + allFilters.getFordate() + " && master_reapproval_done='"
				+ allFilters.getStatusvalue() + "'  ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public JSONArray toStatusReAppDate(AllFilters allFilters, String forDate) throws Exception {
		this.allFilters = allFilters;
		con = dbConnection.getConnection();
		String sqlQuery = "Select *from '" + allFilters.getTablename1() + "' where entry_date between '"
				+ allFilters.getTodate() + "' and '" + allFilters.getFordate() + "' && master_reapproval_done='"
				+ allFilters.getStatusvalue() + "'  ";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlQuery);
		ResultSet resultSet = ps.executeQuery();
		System.out.println(sqlQuery);

		return DaoUtil.convertToJsonArray1(resultSet);
	}

	public static void main(String arg[]) throws Exception {
		//System.out.println(new HomeDataFullReport().getDischargeToSeaReportAcc(""));
	}

}