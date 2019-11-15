package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.nts.grb.connection.dbConnection;
import com.nts.orb1.service.GetTableName;

public class GetEntryType {
	
	public LinkedList<String> getEntryNames(int entry_id) throws SQLException {
		
		LinkedList<String> lst = new LinkedList<>();
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT DISTINCT entry_type from master_table_orb1 where entry_id >=?");
		ps.setInt(1, entry_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			lst.add(GetTableName.getDbTableName(rs.getString(1)));
		}
		
		return lst;	
	}
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println(new GetEntryType().getEntryNames(261));
		
	}
	

}
