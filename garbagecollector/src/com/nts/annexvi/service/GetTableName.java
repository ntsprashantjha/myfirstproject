package com.nts.annexvi.service;

public class GetTableName {

	public static String getQueries(String entryName) {

		String query = null;
		String entry = entryName;
		switch (entry) {

		case "ODS LOG":
			query = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM ods_log_anx dta LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id WHERE dta.entry_id = ?";
//			System.out.println(tableName);
			break;

		}
		return query;
	}

	public static String getDbTableName(String entryName) {

		String tableName = null;
		String entry = entryName;
		switch (entry) {
		case "ODS LOG":
			tableName = "ods_log_anx";
//			System.out.println(tableName);
			break;
		case "BUNKER LOG":
			tableName = "bunker_log_anx";
//			System.out.println(tableName);
			break;

		}

		return tableName;

	}

	public static String[] getAllTableName() {
		String[] tableName = { "bunker_log_anx", "ods_log_anx" };

		return tableName;

	}

	public static void main(String[] args) {

		System.out.println(getDbTableName("Inventory Of Sludge C11 Orb1"));

	}

}