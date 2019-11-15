package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;

class TankCapacity {

	protected static boolean checkCapacity(double quantity, int tankid) {

		try {
			double totalcapacity = 0;
			double currentQuantity = 0;
			Connection con = dbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"select tank_capacity,current_quantity from tanks_entry_detalis_orb where tank_id = ?");
			ps.setInt(1, tankid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				totalcapacity = rs.getDouble(1);
				currentQuantity = rs.getInt(2);
			}
			System.out.println(totalcapacity);
			System.out.println(currentQuantity);

			double totalCurrentQuantity = currentQuantity + quantity;
			System.out.println(totalCurrentQuantity);

			if (totalCurrentQuantity <= totalcapacity) {

				return true;
			} else {

				return false;
			}

		} catch (SQLException e) {

			ReturnResponse.retrnresponse_db_error();
			e.printStackTrace();
			return false;

		}

	}

	public static void main(String[] args) throws SQLException {
		System.out.println(checkCapacity(400, 4));
	}
}