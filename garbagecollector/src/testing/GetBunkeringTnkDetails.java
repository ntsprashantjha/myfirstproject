package testing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class GetBunkeringTnkDetails {

	public static JSONObject getFuelBunkeringDetails(int id, Connection con) {
		
		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			ResultSet rs1 = ps.executeQuery("select * from fuel_oil_bunkerig_h26 where entry_id = " + id);
			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);
			ResultSet rs = ps.executeQuery(
					"select fo.qty_added,fo.qty_retained,fo.tank_name from fuel_oil_bunkerig_tankdetails_h26 fo "
							+ "LEFT JOIN fuel_oil_bunkerig_h26 fob on fo.source_entryid=fob.entry_id WHERE entry_id = "
							+ id);
			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {
				JSONObject j1 = new JSONObject();
				j1.put("qty_added", rs.getDouble(1));
				j1.put("qty_retained", rs.getDouble(2));
				j1.put("tank_name", rs.getString(3));
				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}
			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);
			System.out.println(entryDetails);
			return entryDetails;

		} catch (Exception e) {
			return null;
		}

	}

	public static JSONObject getBulkLubricateDetails(int id, Connection con) {
		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			ResultSet rs1 = ps.executeQuery("select * from bulk_lubricating_oil_bunkerig_h26 where entry_id = " + id);
			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);
			ResultSet rs = ps.executeQuery(
					"select fo.qty_added,fo.qty_retained,fo.tank_name from bulk_lubricating_oil_bunkerig_tankdetails_h26 fo "
							+ "LEFT JOIN bulk_lubricating_oil_bunkerig_h26 fob on fo.source_entryid=fob.entry_id WHERE entry_id = "
							+ id);
			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {
				JSONObject j1 = new JSONObject();
				j1.put("qty_added", rs.getDouble(1));
				j1.put("qty_retained", rs.getDouble(2));
				j1.put("tank_name", rs.getString(3));
				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}
			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);
			System.out.println(entryDetails);
			return entryDetails;

		} catch (Exception e) {
			return null;
		}

	}

	public static JSONObject getde_bunkering_of_fuel_oil(int id, Connection con) {

		try {
			double total_quantity = 0;
			Statement ps = con.createStatement();

			ResultSet rs1 = ps.executeQuery("select * from de_bunkering_of_fuel_oil where entry_id = " + id);
			JSONObject entryDetails = (JSONObject) DaoUtil.convertToJsonArray1(rs1).get(0);
			ResultSet rs = ps
					.executeQuery("select fo.qty_added,fo.qty_retained,fo.tank_name from de_bunkring_of_fuel_oil fo "
							+ "LEFT JOIN de_bunkering_of_fuel_oil fob on fo.source_entryid=fob.entry_id WHERE entry_id = "
							+ id);
			JSONArray tankDetails = new JSONArray();

			while (rs.next()) {
				JSONObject j1 = new JSONObject();
				j1.put("qty_added", rs.getDouble(1));
				j1.put("qty_retained", rs.getDouble(2));
				j1.put("tank_name", rs.getString(3));
				total_quantity += rs.getDouble(1);
				tankDetails.put(j1);
			}
			entryDetails.put("tank_details", tankDetails);
			entryDetails.put("total_quantity", total_quantity);
			System.out.println(entryDetails);
			return entryDetails;

		} catch (Exception e) {
			return null;
		}

	}

	public static void main(String[] args) throws Exception {

		Connection con = dbConnection.getConnection();
		System.out.println(GetBunkeringTnkDetails.getBulkLubricateDetails(76, con));
	}
}