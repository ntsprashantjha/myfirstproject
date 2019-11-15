package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nts.grb.connection.dbConnection;

public class UpdatePreviousDateQuantity {
	
	public boolean updateData(int entry_id,String tank_name,String date,double quantity){
		try {
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO `strke_previous_date_quantity` (`entry_id`, `tank_name`, `date`, `quantity`) VALUES (?,?,?,?)");
		ps.setInt(1, entry_id);
		ps.setString(2, tank_name);
		ps.setString(3, date);
		ps.setDouble(4, quantity);

		ps.execute();
		
		return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println(new UpdatePreviousDateQuantity().updateData(12, "BILGE TANK", "2019-09-09", 2.9));
		
	}

}
