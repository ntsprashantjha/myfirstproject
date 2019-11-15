package com.nts.grb.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class dbConnection {

	private static Connection con;

	public static Connection getConnection() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			try {

				con = (Connection) DriverManager.getConnection("jdbc:mysql://ethicalnirbhaydb.caafl2zdjl8v.ap-south-1.rds.amazonaws.com/garbage_record_book", "nirbhay07",
						"nirbhay07");
			}

			catch (SQLException ex) {

				ex.printStackTrace();
			}
		}

		catch (ClassNotFoundException ex) {

			ex.printStackTrace();
		}

		return con;
	}
}
