package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.nts.grb.connection.dbConnection;

public class ResetStrikeQuantity {
	
	public boolean resetQuantity(int entry_id) throws SQLException {
		
		LinkedList<String> tnkNames = new GetTankDetails().getAllTankNames();
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select tank_name,quantity,entry_id from strke_previous_date_quantity "
									+ "where tank_name = ? and entry_id < ?  order by id desc LIMIT 1");
		
		for (String str : tnkNames) {
			ps.setString(1, str);
			ps.setInt(2, entry_id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(str + " " + rs.getDouble(2));
			
			//Update Current Quantity
			new CurrentQuanityUpdation().currentQuantity(str,rs.getDouble(2));
			}
		}
		System.out.println("Done");
		
		return false;
		
		
	}
	
	
	public static void main(String[] args) throws SQLException {
		
		
		new ResetStrikeQuantity().resetQuantity(320);
		
		
	}
	

}
