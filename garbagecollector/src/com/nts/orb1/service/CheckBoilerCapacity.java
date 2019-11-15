package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;

import com.nts.grb.connection.dbConnection;

public class CheckBoilerCapacity {
	
	public static double getBoilerCapacity(){
		double capacity = 0;
		try {
			
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select boiler_capacity from equipment_details");

		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			capacity = rs.getDouble(1);
		}
		return capacity;
		}catch (SQLException e) {
			
			System.out.println("Error In Checking Boiler Capacity");
			e.printStackTrace();
			return 0;
			
		}
		
		
	}
	
	public static boolean checkCapacity(String start,String stop,double burned) throws JSONException {
		
		
		double boilercapacity = getBoilerCapacity();
		System.out.println("Boiler Capacity : - " + boilercapacity);
		
		
		double bocapinmin = boilercapacity / 60 ;
		System.out.println("boiler Cap In Min - " + bocapinmin);
		
		int timeinmin = GetTimeDifference.getTotalTimeInMinutes(start, stop);
		System.out.println("Time In Minutes - " + timeinmin);
		
		double total= bocapinmin * timeinmin;
		System.out.println("Total Be Burned : - " + total);
		
		if(total>=burned)
			return true;
		else 
			return false;
		
	}
	
	public static void main(String[] args) throws JSONException {
		
		String start = "2019-09-17 14:47";
		String stop = "2019-09-17 17:42";
		
		System.out.println(checkCapacity(start, stop, 1.6));
		
	}
	
	
	

}
