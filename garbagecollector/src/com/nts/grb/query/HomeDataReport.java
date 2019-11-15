package com.nts.grb.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.dao.ConnectionManager;
import com.nts.grb.util.DaoUtil;

public class HomeDataReport {
	private String rprtData;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public String homeRprtDtls() throws Exception {
		// String sql = "SELECT user_dtls.*, DischargeToAshore.* FROM user_dtls INNER
		// JOIN DischargeToAshore ON DischargeToAshore.officer_id=user_dtls.user_id
		// ORDER BY entry_date DESC";
		con = dbConnection.getConnection();
		String sql = "SELECT user_dtls.*, dischargetosea.* FROM user_dtls INNER JOIN dischargetosea ON dischargetosea.officer_id=user_dtls.user_id  ORDER BY entry_date DESC,entry_time DESC";
		// String sql = "SELECT user_dtls.*, discharge_to_sea_acc.* FROM user_dtls INNER
		// JOIN discharge_to_sea_acc ON
		// discharge_to_sea_acc.officer_id=user_dtls.user_id ORDER BY entry_date
		// DESC,entry_time DESC";
		// String sql = "SELECT *, D.id FROM dischargetoashore E, dischargetosea D WHERE
		// D.id = E.id";
		System.out.println("ljnilnu8U*U**&*NO**&*HJKKHHUHKJHJYGGJNBJHHKUGKUBGJYGBJYG");
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return DaoUtil.convertToJsonArray(rs);
	}
}
