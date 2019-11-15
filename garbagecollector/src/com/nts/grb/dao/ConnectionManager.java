package com.nts.grb.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection con;
	 String name = "";
	 	static  String port = "3306";
		static  String user = "Logix_415";
		static String pass = "digimon123";
		static String dbname = "garbage_record_book";
		static String host="digidb1.c9s9mm01djbj.ap-south-1.rds.amazonaws.com";

	

	public static java.sql.Connection getConnection() throws InstantiationException, IllegalAccessException {
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 String url = "jdbc:mysql://"+host+":"+  port + "/" + dbname;
			try {
				con = DriverManager.getConnection(url, user, pass);
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException ex) {
			// log an exception. for example:
			ex.printStackTrace();
		}
		return con;
	}
	public static void main(String arg[]) throws InstantiationException, IllegalAccessException
	{
		ConnectionManager.getConnection();
		
	}
}