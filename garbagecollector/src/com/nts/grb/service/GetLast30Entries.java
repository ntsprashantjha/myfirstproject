package com.nts.grb.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nts.grb.util.DaoUtil;

public class GetLast30Entries {
	static Connection con = null;
	static ResultSet rs = null;

	public HashMap<Object, Object> getLast30Entry_id() {
		HashMap<Object, Object> lst_30_entry = new HashMap<Object, Object>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garbage_record_book", "root", "root");

			Statement st = con.createStatement();
			rs = st.executeQuery(
					"SELECT mt.entry_id,mt.entry_type FROM master_table mt ORDER BY mt.entry_id DESC LIMIT 0,30");
			int a = rs.getMetaData().getColumnCount();
			System.out.println(a);
			while (rs.next()) {
				lst_30_entry.put(rs.getObject(a), rs.getObject(a - 1));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lst_30_entry;

	}

	public JSONArray getEntryDtls() {

		JSONArray js = new JSONArray();
		HashMap<Object, Object> hm2 = new GetLast30Entries().getLast30Entry_id();

		JSONArray js1;
		for (Map.Entry<Object, Object> ml : hm2.entrySet()) {

			System.out.println(ml.getKey() + "\n" + ml.getValue());
			js1 = new JSONArray();

			try {

				String query = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS4.user_first_name as amend_name, UDTLS4.user_rank as amend_rank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM "
						+ ml.getKey()
						+ " dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS4 ON UDTLS4.user_id = dta.amend_id LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id = "
						+ ml.getValue();

				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garbage_record_book", "root", "root");

				PreparedStatement ps = con.prepareStatement(query);
				// ps.setInt(1, 2);

				System.out.println(query);
				rs = ps.executeQuery(query);

				js1 = DaoUtil.convertToJsonArray1(rs);
				System.out.println("json value of data:---" + js1);

			} catch (Exception e) {
				e.printStackTrace();

			}
			try {

				if (js1.length() != 0)
					js.put(js1.get(0));

			} catch (JSONException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		return js;

	}
}