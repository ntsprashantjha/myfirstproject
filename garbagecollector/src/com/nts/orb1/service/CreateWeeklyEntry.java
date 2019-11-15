package com.nts.orb1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nts.grb.connection.dbConnection;
import com.nts.mrb.dao.GetShipTimeZone;
import com.nts.orb1.util.HibernateUtil;

public class CreateWeeklyEntry {

	public static boolean createEntry(Connection con, String date, String mac) {

		createBilgeEntry(con, date, mac);
		createSludgeEntry(con, date, mac);

		return true;

	}

	public static boolean createSludgeEntry(Connection con, String date, String mac) {

		List<Integer> tankid = GetTankDetails.getTankIdByType(1);

		try {
			String timezone = GetShipTimeZone.gettimezone();
			for (int i = 0; i < tankid.size(); i++) {
				PreparedStatement ps = con.prepareStatement(
						"select tank_name,tank_capacity,current_quantity from tanks_entry_detalis_orb where tank_id = ?");
				ps.setInt(1, tankid.get(i));

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

//					System.out.println(rs.getString(1));
//					System.out.println(rs.getDouble(2));
//					System.out.println(rs.getDouble(3));

					com.nts.orb1.model.TLDInventoryOfSludgeOrb1 obj = new com.nts.orb1.model.TLDInventoryOfSludgeOrb1();

					obj.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));

					obj.setStop_date(date);
					obj.setStart_date(date);

					obj.setTank_name(rs.getString(1));
					obj.setTank_capacity(rs.getDouble(2));
					obj.setTank_qty(rs.getDouble(3));

					MasterTable mstrObj = new MasterTable();
					obj.setEntry_id(mstrObj.lastEntry());

					Session session = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.saveOrUpdate(obj);
					tran.commit();

					mstrObj.lastEntryUpdation("INVENTORY OF SLUDGE TANK (C 11.1,11.2,11.3)", date, "", 0, timezone,
							date, 0, 0);
				}

			}

			System.out.println("Done");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean createBilgeEntry(Connection con, String date, String mac) {
		List<Integer> tankid = GetTankDetails.getTankIdByType(2);

		try {
			String timezone = GetShipTimeZone.gettimezone();
			for (int i = 0; i < tankid.size(); i++) {
				PreparedStatement ps = con.prepareStatement(
						"select tank_name,tank_capacity,current_quantity from tanks_entry_detalis_orb where tank_id = ?");
				ps.setInt(1, tankid.get(i));

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

//					System.out.println(rs.getString(1));
//					System.out.println(rs.getDouble(2));
//					System.out.println(rs.getDouble(3));

					com.nts.orb1.model.RetendationOfBilgeOrb1 obj = new com.nts.orb1.model.RetendationOfBilgeOrb1();

					obj.setOfficer_id(com.nts.grb.query.ActiveUserInfo.activeUserId(mac));

					obj.setStop_date(date);
					obj.setStart_date(date);

					obj.setTank_name(rs.getString(1));
					obj.setTank_capacity(rs.getDouble(2));
					obj.setTank_qty(rs.getDouble(3));

					MasterTable mstrObj = new MasterTable();
					obj.setEntry_id(mstrObj.lastEntry());

					Session session = HibernateUtil.buildSessionFactory().openSession();
					Transaction tran = session.beginTransaction();

					session.saveOrUpdate(obj);
					tran.commit();

					mstrObj.lastEntryUpdation("Retention of Bilge Tank (i)", date, "", 0, timezone, date, 0, 0);
				}

			}

			System.out.println("Done");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {

		Connection con = dbConnection.getConnection();
		createBilgeEntry(con, "2019-11-08", "64");

	}

}
