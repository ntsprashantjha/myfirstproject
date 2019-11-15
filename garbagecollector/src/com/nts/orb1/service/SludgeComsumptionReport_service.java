package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class SludgeComsumptionReport_service {
	public JSONArray geSludgeConsumptionDtls() {
		JSONArray jsonObj = new JSONArray();
		try {
		String sql_query = "SELECT  dts2.date,dts2.quantity,dts2.timestamp,tedo.tank_name from daily_tank_sounding_sheet dts2 LEFT JOIN tanks_entry_detalis_orb tedo ON dts2.tank_id=tedo.tank_id WHERE tedo.tank_type_id=1 AND dts2.date>2019-09-04";
		Connection con = dbConnection.getConnection();
		
			PreparedStatement ps = con.prepareStatement(sql_query);
			ResultSet rs=ps.executeQuery();
			try {
				jsonObj = DaoUtil.convertToJsonArray1(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObj;

	}


}
