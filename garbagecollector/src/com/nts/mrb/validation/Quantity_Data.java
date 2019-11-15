package com.nts.mrb.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.util.DaoUtil;

public class Quantity_Data {
	String[] entry_type_name1 = { "discharge_to_sea", "discharge_to_ashore", "stop_incineration_incinerated" };
	String[] entry_type_name = { "discharge_to_sea", "discharge_to_ashore", "stop_incineration" };
	String[] garbage_category_type = { "B", "G", "A", "D", "E", "F", "H", "I", "C" };
	String[] date_list = { "2019-07-02", "2019-07-04", "2019-07-07", "2019-07-05", "2019-07-06" };
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rset = null;

	public void quantity_dtls() throws Exception {
		con = dbConnection.getConnection();

		for (int i = 0; i <= date_list.length - 1; i++) {
			JSONArray store_garebagel_data = new JSONArray();
			JSONObject store_garebagel_data1 = new JSONObject();

			for (int j = 0; j <= garbage_category_type.length - 1; j++) {

				JSONArray store_indivisual_data = new JSONArray();
				for (int k = 0; k <= entry_type_name.length - 1; k++) {

					String v[] = entry_type_name1[k].split("_");
					String sql_qury = "SELECT  SUM(D.garbage_quantity_" + v[2] + ") As quan FROM " + entry_type_name[k]
							+ " D WHERE D.garbage_category LIKE'" + garbage_category_type[j] + "%' AND entry_date ='"
							+ date_list[i] + "'";
					System.out.println("----- " + sql_qury);
					stmt = con.prepareStatement(sql_qury);
					rset = stmt.executeQuery();

					String abc = DaoUtil.categoryBiseTable(rset);
					store_indivisual_data.put(abc);

				}
				System.out.println("value from first loop:---" + store_indivisual_data);
				store_garebagel_data.put(store_indivisual_data);
				store_garebagel_data1.put(garbage_category_type[i],store_garebagel_data);
			

			}
			System.out.println("value from second loop:---" + store_garebagel_data1);

		}

	}

	public static void main(String arg[]) throws Exception {
		new Quantity_Data().quantity_dtls();
	}

}
