package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.query.ActiveUserInfo;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb.validation.ReturnResponse;

public class DailyTankQuantityUpdation {

	LinkedHashMap<String, Double> preQuntity = new LinkedHashMap<String, Double>();
	static LinkedHashMap<String, Integer> preQTankId = new LinkedHashMap<String, Integer>();
	static LinkedHashMap<String, Integer> tankCapacity = new LinkedHashMap<String, Integer>();

	static String date = null;
	String entrydate = date;

	static String macid = null;
	int currentqnty = 0;

	String userid = null;
	MasterTable mstrObj = new MasterTable();

	public JSONArray quantityUpdation(JSONArray jObj, String mac, Connection con) {
		macid = mac;

		boolean updateCond = false;
		LinkedHashMap<String, Double> storeQuntity = new LinkedHashMap<String, Double>();

		try {

			// Getting User Employee Id
			userid = ((JSONObject) (StringToJsonConverter.convrt(new ActiveUserInfo().activateUser1(mac))).get(0))
					.getString("emp_id");

			preQuntity = currentTankValueCheck(con);
			System.out.println("**json data from modal operation controller**" + jObj.length() + jObj);

			for (int i = 0; i < jObj.length(); i++) {

				if (i == 0) {

					JSONObject jsanObject = jObj.getJSONObject(i);
					date = jsanObject.getString("date_7");

				} else {

					JSONObject jsanObject = jObj.getJSONObject(i);

					// if (jsanObject.getString("tank_automatic").equals("1")) {

					double quantity = jsanObject.getDouble("date_7");
					String tankname = jsanObject.getString("tank_name");
					storeQuntity.put(tankname, quantity);

					// }
				}

			}

			Set<?> set = storeQuntity.entrySet();
			Iterator<?> it = set.iterator();

			while (it.hasNext()) {

				boolean capacityCheck = false;
				@SuppressWarnings("unchecked")
				Map.Entry<String, Double> get = (Map.Entry<String, Double>) it.next();

				String tankAsKey = get.getKey();
				double quantityAsValue = get.getValue();

				if (tankCapacity.get(tankAsKey) >= quantityAsValue) {
					capacityCheck = true;
				}
				if (tankAsKey.equalsIgnoreCase("FUEL CONSUMPTION PER DAY")) {
					capacityCheck = true;
				}
				if (!capacityCheck) {
					return ReturnResponse.ownMessage("Tank Capacity Exceeded");
				}

			}

			Iterator<?> it3 = set.iterator();

			while (it3.hasNext()) {

				boolean capacityCheck = false;
				@SuppressWarnings("unchecked")
				Map.Entry<String, Double> get = (Map.Entry<String, Double>) it3.next();

				String tankAsKey = get.getKey();

				if (preQuntity.get(tankAsKey) <= get.getValue()) {
					capacityCheck = true;
				}
				if (tankAsKey.equalsIgnoreCase("FUEL CONSUMPTION PER DAY")) {
					capacityCheck = true;
				}
				if (!capacityCheck) {
//					System.out.println(preQuntity.toString());
//					System.out.println("Previous = " + preQuntity.get(tankAsKey));
//					System.out.println("Current = " + get.getValue());

					return ReturnResponse.ownMessage("Entered Tank Quantity Is Less Than Exist Quantity.");
				}

			}

			Iterator<?> it2 = set.iterator();

			while (it2.hasNext()) {

				@SuppressWarnings("unchecked")
				Map.Entry<String, Double> get = (Map.Entry<String, Double>) it2.next();
				String k = get.getKey();

				double v = get.getValue();
//				System.out.println("key_value:--" + k + "\n" + v);
//				System.out.println("key_value2:--" + preQuntity.get(k) + "\n" + get.getValue());

				if (preQuntity.get(k) <= get.getValue() && tankCapacity.get(k) >= get.getValue()) {

					System.out.println("Tank Id :- " + preQTankId.get(k) + "  " + k);
					updateCond = updateQuntity(v, preQTankId.get(k), k, con);

				}
				// System.out.println(k);
				if (k.equalsIgnoreCase("FUEL CONSUMPTION PER DAY")) {
					updateCond = updateQuntity(v, preQTankId.get(k), k, con);
				}

			}

		} catch (JSONException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (updateCond) {

			// Insert Into Daily Tank Sounding Sheet Entry
			new AutomaticEntryGeneration().autoEntryByTankSoundingFilled(date, macid);

			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

			Date date1 = null;
			try {
				date1 = myFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date1);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			String dayName = GetDayName.getName(dayOfWeek);

			if (dayName.equalsIgnoreCase("SATURDAY")) {

				CreateWeeklyEntry.createEntry(con, date,macid);

			}

			return ReturnResponse.success_condition();
		}

		else {
			return ReturnResponse.somethingWentWrong();
		}
	}

	private static LinkedHashMap<String, Double> currentTankValueCheck(Connection con) throws SQLException {

		LinkedHashMap<String, Double> preQuntity = new LinkedHashMap<String, Double>();

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(
				"select ted.current_quantity,ted.tank_capacity,ted.tank_name,ted.tank_id from daily_tank_sounding_sheet dtss RIGHT JOIN tanks_entry_detalis_orb ted on dtss.tank_id=ted.tank_id");

		while (rs.next()) {
			preQuntity.put(rs.getString("tank_name"), rs.getDouble("current_quantity"));
			preQTankId.put(rs.getString("tank_name"), rs.getInt("tank_id"));
			tankCapacity.put(rs.getString("tank_name"), rs.getInt("tank_capacity"));

		}

		// System.out.println(preQuntity.keySet() + "" + preQuntity.get("BILGE SEPRATED
		// OIL TANK"));

		return preQuntity;
	}

	private boolean updateQuntity(double quntity, int tank, String tankName, Connection con)
			throws SQLException, ParseException {
		boolean i = false;

		// System.out.println("Tank Id - " + tank + " TankName - " + tankName);

		String crtdate = null, nxtdate = null;
		String nextdate = LocalDate.parse(date).plusDays(1).toString();

		String update = "update daily_tank_sounding_sheet set quantity = ? where date = ? and tank_id = ?";
		String insert = "INSERT INTO `garbage_record_book`.`daily_tank_sounding_sheet` "
				+ "(`tank_id`, `date`, `quantity`) VALUES (?,?,?)";

		try {

			if (AutomaticCheck.isAutomatic(tankName)) {
				new AutomaticEntryGeneration().automaticEntryByAutoTypeTankDTSS(macid, date, tankName,
						preQuntity.get(tankName), quntity, userid, mstrObj.lastEntry() + 1);
			}

			PreparedStatement cond = con.prepareStatement(
					"select distinct date from daily_tank_sounding_sheet WHERE date = ? and tank_id = ?");
			cond.setString(1, date);
			cond.setInt(2, tank);
			ResultSet rs = cond.executeQuery();

			if (rs.next()) {
				crtdate = rs.getString(1);
			}

			if (crtdate != null) {

				PreparedStatement ps = con.prepareStatement(update);

				ps.setDouble(1, quntity);
				ps.setString(2, date);

				ps.setInt(3, tank);
				// int x =
				ps.executeUpdate();

				new CurrentQuanityUpdation().currentQuantity(tankName, quntity);

//				System.out.println("TankName - " + tankName);
//				System.out.println("Quantity - "+ quntity);
//				System.out.println("Date - "+ date);
//				System.out.println("Tank Id - " + tank);
//				System.out.println("Rows Updated In Current Date " + x);
			}

			else {

				PreparedStatement ps = con.prepareStatement(insert);
				ps.setInt(1, tank);

				ps.setString(2, date);
				ps.setDouble(3, quntity);

				// int y =
				ps.executeUpdate();

				new CurrentQuanityUpdation().currentQuantity(tankName, quntity);

//				System.out.println("TankName - " + tankName);
//				System.out.println("Quantity - "+ quntity);
//				System.out.println("Date - "+ date);
//				System.out.println("Tank Id - " + tank);
//				System.out.println("Rows Inserted Previous Date " + y);

			}

			cond.setString(1, nextdate);
			ResultSet rs2 = cond.executeQuery();

			if (rs2.next())
				nxtdate = rs2.getString(1);

			if (nxtdate != null) {

				PreparedStatement ps = con.prepareStatement(update);

				ps.setDouble(1, quntity);
				ps.setString(2, nextdate);

				ps.setInt(3, tank);
				// int x =
				ps.executeUpdate();
				new CurrentQuanityUpdation().currentQuantity(tankName, quntity);
//				System.out.println("TankName - " + tankName);
//				System.out.println("Quantity - "+ quntity);
//				System.out.println("Date - "+ date);
//				System.out.println("Tank Id - " + tank);
//				System.out.println("Rows Updated In Next Date " + x);
			}

			else {

				PreparedStatement ps = con.prepareStatement(insert);
				ps.setInt(1, tank);

				ps.setString(2, nextdate);
				ps.setDouble(3, quntity);

				// int y =
				ps.executeUpdate();

				new CurrentQuanityUpdation().currentQuantity(tankName, quntity);
//				System.out.println("TankName - " + tankName);
//				System.out.println("Quantity - "+ quntity);
//				System.out.println("Date - "+ date);
//				System.out.println("Tank Id - " + tank);
//				System.out.println("Rows Inserted In Next Date " + y);

			}

			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return i;
		}

	}

	public static void main(String[] args) throws SQLException {
//		currentTankValueCheck();
	}

}
