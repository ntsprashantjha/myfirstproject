package com.nts.mrb.auditrecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.nts.grb.connection.dbConnection;

public class CreateAudit {
	
	public static boolean auditUpdate(String userid,String action,
			String description,String pre_value,String next_value,
			String usersystem,String softname) throws SQLException {
		
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO `garbage_record_book`.`audit_details_info` "
				+ "(`timestamp_of_entry`, `user_id`, `action`, `description`, `pre_value`, `next_value`, `user_system`, `software_name`)"
				+ " VALUES (?,?,?,?,?,?,?,?)");
		
		//Getting TimeStamp From Current Date
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		ps.setString(1, ts.toString());
		ps.setString(2, userid);
		
		ps.setString(3, action);
		ps.setString(4, description);
		
		ps.setString(5, pre_value);
		ps.setString(6, next_value);
		
		ps.setString(7, usersystem);
		ps.setString(8, softname);
		int x = ps.executeUpdate();
		System.out.println(x);
		
		return false;
		
		
		
	}
	public static void main(String[] args) throws SQLException {
		
		CreateAudit.auditUpdate("Kumar", "AS101", "TestValue", " ", " ", "127.0.0.1", "GRB");
		
	}
	
	

}