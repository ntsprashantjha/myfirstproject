package com.nts.orb1.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.json.JSONArray;

import com.nts.grb.connection.dbConnection;
import com.nts.grb.validation.ReturnResponse;
import com.nts.orb1.service.ResetStrikeQuantity;

import testing.GetEntryType;

public class Strike_Through_Orb1 {

	public static JSONArray strikeUpdate(int entryId, int activeuserid, String strikecomment, String currntDt) {

		JSONArray return_stmt = new JSONArray();
		Connection con = dbConnection.getConnection();
		try {

			LinkedList<String> tables = new GetEntryType().getEntryNames(entryId);
			Statement stmt = con.createStatement();
			for (String str : tables) {
				String sql = "update " + str + " tb INNER JOIN master_table_orb1 mtb set tb.strike_value = '1',"
						+ "tb.strike_approval_done='1',tb.strike_time='" + currntDt + "',"
						+ "tb.strike_approval='Done', tb.strike_id= " + activeuserid + ",tb.strike_comment='"
						+ strikecomment + "',mtb.entry_strike_cond='1' "
						+ "where tb.entry_id=mtb.entry_id and tb.entry_id >=" + entryId;
				System.out.println("Query : -  " + sql);
				stmt.addBatch(sql);

			}
			String sql = "update automatic_entry tb INNER JOIN master_table_orb1 mtb set tb.strike_value = 1,"
					+ "tb.strike_approval_done=1,tb.strike_time='" + currntDt + "', tb.strike_approval='Done',"
					+ "tb.strike_id= " + activeuserid + ",tb.strike_comment='" + strikecomment
					+ "',mtb.entry_strike_cond=1 where mtb.entry_id=tb.entry_id and mtb.source_entry_id =" + entryId;

			System.out.println("Query : -  " + sql);
			stmt.addBatch(sql);

//			sql = "update automatic_entry tb INNER JOIN master_table_orb1 mtb set tb.strike_value = 1,"
//					+ "tb.strike_approval_done=1,tb.strike_time='" + currntDt + "',tb.strike_approval='Done',"
//					+ "tb.strike_id=" + activeuserid + ",tb.strike_comment='" + strikecomment
//					+ "',mtb.entry_strike_cond=1 where tb.entry_id=mtb.entry_id AND "
//					+ "tb.entry_id=(select mtb.entry_id where mtb.source_entry_id >=" + entryId
//					+ " and mtb.entry_strike_cond = 0 LIMIT 1 )";
//			
//			System.out.println("Query : -  " + sql);
//			stmt.addBatch(sql);
			
			//int[] x = 
			stmt.executeBatch();
//			for(int i = 0; i < x.length; i++) {
//			System.out.println("Rows Affected In " + tables.get(i) + " " + x[i]);
//			}
			// Reset Last Updated Quantity To Tables
			new ResetStrikeQuantity().resetQuantity(entryId);
			return_stmt = ReturnResponse.success_condition();

			con.close();
		}

		catch (SQLException e) {

			e.printStackTrace();

			return_stmt = ReturnResponse.retrnresponse_db_error();

		}
		return return_stmt;
	}
}
