package com.nts.mrb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class GetRefrigeratorEquipDetailsDb {

	public static JSONArray getData() {

		Connection con = dbConnection.getConnection();
		JSONArray ac = null;

		try {

			String data;
			PreparedStatement ps = con.prepareStatement("SELECT * from refrigerator_equip_details_mrb");
			ResultSet rs = ps.executeQuery();

			data = DaoUtil.convertToJsonArray(rs);

			ac = new JSONArray(data);
			return ac;

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return ac;
	}

	public static void main(String[] args) {

		System.out.println(GetRefrigeratorEquipDetailsDb.getData());

	}

}
