package com.nts.annexvi.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nts.annexvi.model.MasterTableAnx;
import com.nts.grb.connection.dbConnection;
import com.nts.annexvi.util.HibernateUtil;

public class MasterTable {

	public int lastEntry() {

		int lst_mstr_entry_id = 0;
		Connection con = dbConnection.getConnection();

		String sql = "SELECT entry_id FROM `master_table_annexvi` ORDER BY entry_id DESC LIMIT 1";

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				lst_mstr_entry_id = rs.getInt("entry_id");

			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst_mstr_entry_id + 1;
	}

	public int lastEntryUpdation(String entrytype, String dt,String timezone, String dt_only) {
		int success = 0;

		try {
			
			MasterTableAnx obj = new MasterTableAnx();
			
			obj.setEntry_type(entrytype);
			obj.setEntry_date(dt);
			
			obj.setTimezone(timezone);
			obj.setEntry_date_only(dt_only);
			
			Session session = HibernateUtil.buildSessionFactory().openSession();
			Transaction tran = session.beginTransaction();

			session.save(obj);
			tran.commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}

		return success;

	}
}