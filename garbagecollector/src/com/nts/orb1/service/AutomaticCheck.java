package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

public class AutomaticCheck {

	public static boolean isAutomatic(String tankName) {
		boolean cond = false;
		try {

			Connection con = dbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"select tank_automatic from tanks_entry_detalis_orb where tank_name = '" + tankName + "'");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int autoid = rs.getInt(1);
				System.out.println(autoid);

				if (autoid == 1) {
//					System.out.println("Automatic");
					cond = true;
					return cond;

				} else {

//					System.out.println("Non Automatic");
					return cond;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
			ReturnResponse.retrnresponse_db_error();
			return cond;
		}
		return cond;

	}

	public static void main(String[] args) throws SQLException, JSONException {

		System.out.println(AutomaticCheck.isAutomatic("BILGE SEPRATED OIL TANK"));
	}

}