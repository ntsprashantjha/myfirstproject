package com.nts.grb.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nts.grb.connection.dbConnection;

public class SeaCheckDeckWash {
	
	static Connection con;
	static String str;
	static String type;
	static Statement stmt;
	public static String check() throws SQLException
	 {
		 con = new dbConnection().getConnection();
		  str ="SELECT entry_type,entry_id FROM `master_table` WHERE entry_type='Start_DeckWashing_Grb2' OR entry_type='Stop_deck_washing_Grb2' ORDER BY entry_id DESC LIMIT 1";
		  stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery(str);		  
		  
		  if(rs.next()) {
			  type=rs.getString("entry_type");
		  }
		  
		  return type; 
	 }
	public static void main (String arg[])
	{
	try {
		String s=check();
		System.out.println(s);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}

}
