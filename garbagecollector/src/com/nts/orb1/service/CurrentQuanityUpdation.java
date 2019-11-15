package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nts.grb.connection.dbConnection;

public class CurrentQuanityUpdation {
	String tankType;

	public boolean currentQuantity(String tankName, double quntity) {
		boolean quantityupdated = false;

		this.tankType = tankName;

		try {

			String updationQuery = "update tanks_entry_detalis_orb set current_quantity = " + quntity
					+ previousQuantity() + " where tank_name='" + tankName + "'";

			Connection con = dbConnection.getConnection();
			Statement ps = con.createStatement();

			quantityupdated = ps.execute(updationQuery);
			System.out.println(updationQuery);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return quantityupdated;
	}

	public void addQuantity(String tankName, double quntity) {

		this.tankType = tankName;
		try {
			Connection con = dbConnection.getConnection();
			String quanQuery = "select current_quantity from tanks_entry_detalis_orb where tank_name='" + tankName
					+ "'";

			System.out.println("Query :- " + quanQuery);
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(quanQuery);

			rs.next();
			int currentqty = rs.getInt(1);

			System.out.println("Quantity In " + tankName + "Before Tranfer = " + currentqty);
			double updqty = currentqty + quntity;

			new CurrentQuanityUpdation().currentQuantity(tankName, updqty);
			System.out.println("Updated Quantity In " + tankName + "After Tranfer = " + updqty);

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void subQuantity(String tankName, double quntity) {

		this.tankType = tankName;

		try {

			Connection con = dbConnection.getConnection();
			String quanQuery = "select current_quantity from tanks_entry_detalis_orb where tank_name='" + tankName
					+ "'";

			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(quanQuery);

			rs.next();
			int currentqty = rs.getInt(1);

			System.out.println("Quantity In " + tankName + "Before Tranfer = " + currentqty);
			double updqty = currentqty - quntity;

			new CurrentQuanityUpdation().currentQuantity(tankName, updqty);
			System.out.println("Updated Quantity In " + tankName + "After Tranfer = " + updqty);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private int previousQuantity() {

		int quantity = 0;

		try {

			Connection con = dbConnection.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT current_quantity FROM tanks_entry_detalis_orb WHERE tank_name '" + tankType + "'");

			quantity = rs.getInt("current_quantity");

		} catch (SQLException e) {

		}
		System.out.println(quantity);
		return quantity;
	}

	public void dailySoundingSheetUpdate() {

	}

	public static void main(String arg[]) {
		new CurrentQuanityUpdation().addQuantity("SLUDGE TANK", 4.9);
		new CurrentQuanityUpdation().subQuantity("SLUDGE TANK", 5.9);
	}

}