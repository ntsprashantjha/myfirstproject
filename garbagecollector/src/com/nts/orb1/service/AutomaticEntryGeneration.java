package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;
import com.nts.mrb.auditrecord.CreateAudit;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.orb1.model.AutomaticEntryOrb1;
import com.nts.orb1.model.DailyTankSouningSheetOrb1;
import com.nts.orb1.util.HibernateUtil;

public class AutomaticEntryGeneration {

	public static int getAutoTankSourceId(String tnkName) {

		try {

			int source = 0;
			Connection con = dbConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"select et.id,dt.tank_id from tanks_entry_detalis_orb dt LEFT JOIN automatic_tank_source et on et.tank_id=dt.tank_id where dt.tank_name = '"
							+ tnkName + "'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				source = rs.getInt(1);
			}

			return source;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(ReturnResponse.retrnresponse_db_error());
			return 0;
		}
	}

	public static double getTankCurrentQnty(String tnkName) throws SQLException {

		double qunty = 0;
		Connection con = dbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"select current_quantity from tanks_entry_detalis_orb WHERE tank_name = '" + tnkName + "'");

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			qunty = rs.getDouble(1);
		}

		return qunty;
	}

	public void automaticEntryByAutoTypeTankDes(String mac, String date, String tankName, double qtyretained,
			double quantity, String user, int source_entry_id) throws InterruptedException {

		try {

			String[] stopdate = date.split("T");
			double currentqty = getTankCurrentQnty(tankName), tnkid = 0;
			int source = getAutoTankSourceId(tankName);

			double totalqty = currentqty + quantity;

			System.out.print("from Des;********" + qtyretained + "" + quantity + "" + totalqty + "" + currentqty);

			Connection con = dbConnection.getConnection();
			PreparedStatement ps2 = con.prepareStatement(
					"select dt.tank_id from tanks_entry_detalis_orb dt where dt.tank_name = '" + tankName + "'");

			ResultSet rs2 = ps2.executeQuery();

			if (rs2.next()) {
				tnkid = rs2.getInt(1);
			}

			if (qtyretained > currentqty) {
				System.out.println("avjfenvoi");

				String timezone = GetShipTimeZone.gettimezone();
				AutomaticEntryOrb1 obj = new AutomaticEntryOrb1();

				obj.setStart_date(stopdate[0]);
				obj.setStop_date(stopdate[0]);
				obj.setSource(source);

				obj.setDestination_tank_id(tnkid);
				double count1 = qtyretained - totalqty;
				System.out.println("AKAKA&&#&#&@&@&#&#@&@NW" + count1);
				if (count1 > 0.0) {
					obj.setQuantity_transferred(count1);
					obj.setQuan_ret_des_tank(currentqty + (qtyretained - totalqty));

					obj.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));

					MasterTable mstrObj = new MasterTable();
					obj.setEntry_id(mstrObj.lastEntry());

					Session session = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.save(obj);
					tran.commit();

					mstrObj.lastEntryUpdation("Automatic Entry", date, "", 0, timezone, stopdate[0], 1,
							source_entry_id);

					// Create Audit Of Following Entry In Audit Table
					CreateAudit.auditUpdate(user, "Automatic", "Automatic Entry Created", " ", " ", mac, "ORB1");

				}
			}

		} catch (JSONException e) {

			e.printStackTrace();
			ReturnResponse.retrnresponse_wrong_json();
			// TODO: handle exception
		} catch (SQLException e) {

			e.printStackTrace();
			ReturnResponse.retrnresponse_db_error();
			// TODO: handle exception
		}
	}

	public boolean automaticEntryByAutoTypeTankSou(String mac, String date, String tankName, double qtyretained,
			double quantity, String user, int source_entry_id) throws InterruptedException {
		if (quantity > 0) {

			try {

				String[] stopdate = date.split("T");
				double currentqty = getTankCurrentQnty(tankName), tnkid = 0;
				int source = getAutoTankSourceId(tankName);

				double totalqty = qtyretained + quantity;
				double temp = currentqty - quantity;

				if (temp > 0)
					System.out.print(
							"from source:;********" + qtyretained + "," + quantity + "," + totalqty + "," + currentqty);

				Connection con = dbConnection.getConnection();
				PreparedStatement ps2 = con.prepareStatement(
						"select dt.tank_id from tanks_entry_detalis_orb dt where dt.tank_name = '" + tankName + "'");

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					tnkid = rs2.getInt(1);
				}

				if (qtyretained > temp) {

					String timezone = GetShipTimeZone.gettimezone();
					AutomaticEntryOrb1 obj = new AutomaticEntryOrb1();

					obj.setStart_date(stopdate[0]);
					obj.setStop_date(stopdate[0]);
					obj.setSource(source);

					obj.setDestination_tank_id(tnkid);

					double count = currentqty + (qtyretained - temp);
					double count1 = qtyretained - temp;

					if (count1 > 0.0) {

						obj.setQuantity_transferred(count1);
						obj.setQuan_ret_des_tank(count);
						obj.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));

						MasterTable mstrObj = new MasterTable();
						obj.setEntry_id(mstrObj.lastEntry());

						Session session = HibernateUtil.buildSessionFactory().openSession();
						Transaction tran = session.beginTransaction();

						session.save(obj);
						tran.commit();

						mstrObj.lastEntryUpdation("Automatic Entry", date, "", 0, timezone, stopdate[0], 1,
								source_entry_id);

						// Create Audit Of Following Entry In Audit Table
						CreateAudit.auditUpdate(user, "Automatic", "Automatic Entry Created", " ", " ", mac, "ORB1");
						return true;
					}
				}

			} catch (JSONException e) {

				e.printStackTrace();
				ReturnResponse.retrnresponse_wrong_json();
				// TODO: handle exception
			} catch (SQLException e) {

				e.printStackTrace();
				ReturnResponse.retrnresponse_db_error();
				// TODO: handle exception
			}
		}
		return false;
	}

	public boolean automaticEntryByAutoTypeTankDTSS(String mac, String date, String tankName, double currentqnty,
			double quantity, String user, int source_entry_id) {
		System.out.println("****************************" + currentqnty);
		if (quantity > 0) {
			String[] stopdate = date.split("T");
			int source = getAutoTankSourceId(tankName);
			int tnkid = 0;

			try {

				Connection con = dbConnection.getConnection();
				PreparedStatement ps2 = con.prepareStatement(
						"select dt.tank_id from tanks_entry_detalis_orb dt where dt.tank_name = '" + tankName + "'");

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					tnkid = rs2.getInt(1);
				}

				if (currentqnty < quantity) {

					String timezone = GetShipTimeZone.gettimezone();
					AutomaticEntryOrb1 obj = new AutomaticEntryOrb1();

					obj.setStart_date(stopdate[0]);
					obj.setStop_date(stopdate[0]);
					obj.setSource(source);

					obj.setDestination_tank_id(tnkid);

					obj.setQuantity_transferred(quantity - currentqnty);
					obj.setQuan_ret_des_tank(currentqnty + (quantity - currentqnty));

					obj.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));

					MasterTable mstrObj = new MasterTable();
					obj.setEntry_id(mstrObj.lastEntry());

					Session session = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.save(obj);
					tran.commit();

					mstrObj.lastEntryUpdation("Automatic Entry", date, "", 0, timezone, stopdate[0], 1,
							source_entry_id);

					// Create Audit Of Following Entry In Audit Table
					CreateAudit.auditUpdate(user, "Automatic", "Automatic Entry Created", " ", " ", mac, "ORB1");

					return true;
				}

			} catch (JSONException e) {

				e.printStackTrace();
				ReturnResponse.retrnresponse_wrong_json();
				// TODO: handle exception
			} catch (SQLException e) {

				e.printStackTrace();
				ReturnResponse.retrnresponse_db_error();
				// TODO: handle exception
			}
		}
		return false;
	}

	public boolean autoEntryByTankSoundingFilled(String stopdate, String mac) {

		try {
			String timezone = GetShipTimeZone.gettimezone();

			DailyTankSouningSheetOrb1 obj = new DailyTankSouningSheetOrb1();

			obj.setStop_date(stopdate);
			obj.setStart_date(stopdate);

			obj.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));

			Session session = HibernateUtil.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();

			MasterTable mstrObj = new MasterTable();
			obj.setEntry_id(mstrObj.lastEntry());

			session.save(obj);
			tran.commit();

			mstrObj.lastEntryUpdation("Daily Tank Sounding Sheet Updation", "", "", 0, timezone, stopdate, 0, 0);

			return true;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws SQLException {

		System.out.println(getAutoTankSourceId("BILGE SEPRATED OIL TANK"));
		System.out.println(getTankCurrentQnty("BILGE SEPRATED OIL TANK"));
	}

}