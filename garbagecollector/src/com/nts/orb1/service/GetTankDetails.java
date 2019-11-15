package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class GetTankDetails {

	public LinkedList<String> getAllTankNames() throws SQLException {

		LinkedList<String> lst = new LinkedList<>();
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select tank_name from tanks_entry_detalis_orb");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			lst.add(rs.getString(1));
		}

		return lst;
	}

	public LinkedList<String> getTankNameByType(int typeId) throws SQLException {

		LinkedList<String> lst = new LinkedList<>();
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("select tank_name from tanks_entry_detalis_orb where tank_type_id = ?");
		ps.setInt(1, typeId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			lst.add(rs.getString(1));
		}

		return lst;
	}

	public JSONArray getTankName_IdByType(int typeId) throws Exception {

		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("select tank_name,tank_id from tanks_entry_detalis_orb where tank_type_id = ?");
		ps.setInt(1, typeId);
		ResultSet rs = ps.executeQuery();
		JSONArray data = DaoUtil.convertToJsonArray1(rs);

		return data;
	}

	public static LinkedList<Integer> getTankIdByType(int typeId) {

		LinkedList<Integer> lst = null;
		try {

			lst = new LinkedList<>();
			Connection con = dbConnection.getConnection();
			PreparedStatement ps = con
					.prepareStatement("select tank_id from tanks_entry_detalis_orb where tank_type_id = ?");
			ps.setInt(1, typeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lst.add(rs.getInt(1));
			}

			return lst;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	public static void main(String[] args) throws SQLException {

		// System.out.println(new GetTankNames().getTankNames());

	}

}
