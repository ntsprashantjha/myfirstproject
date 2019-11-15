package com.nts.orb1.service;

public class GetTableName {

	public static String getQueries(String entryName, String missed) {

		String query = null;
		String entry = entryName;
		switch (entry) {

		case "DisposalOfSludgeViaShoreConnection C 12.1":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_disposal_shoreconnection_c12_1_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Daily Tank Sounding Sheet Updation":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,daily_tank_sounding_sheet_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details "
					+ "UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY stop_date DESC";
//			System.out.println(tableName);
			break;

		case "Transfer Between Sludge Tanks C_12.2":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_transferbw_two_tanks_c12_2orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.destination_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Automatic Entry":
			query = "SELECT DISTINCT dta.*,tank_capacity,tedo.tank_name,ats.source_name,"
					+ "DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM automatic_entry dta LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id 	LEFT JOIN tanks_entry_detalis_orb tedo ON "
					+ "dta.destination_tank_id=tedo.tank_id LEFT JOIN automatic_tank_source ats "
					+ "ON dta.source=ats.id WHERE dta.entry_id =? ORDER BY dta.id DESC";

			break;

		case "Sludge Collected by manual operation C11.4":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  "
					+ "as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,"
					+ "sludge_m_opt_c11_4_orb1 dta LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details "
					+ "UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name "
					+ "and dta.entry_id =? ORDER BY start_date DESC";
			;
//			System.out.println(tableName);
			break;

		case "Incineration of sludge C 12.3":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_inceration_c12_3orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;
//			
		case "Evaportaion of Water From Sludge c12_4_orb1":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_tank_evaporation_of_water_c12_4_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Sludge Burning In Boiler C_12.4":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_burning_in_boiler_c12_4 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Draining Water From Sludge To Blidge tank":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_drain_water_frm_sludge_to_blidge_tank dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Regeneration Of Fuel From Sludge C_12.4":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,sludge_fuel_regeneration_c12_4orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "INVENTORY OF SLUDGE TANK (C 11.1,11.2,11.3)":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank "
					+ "FROM inventory_of_sludge_tank_c11_orb1 dta LEFT JOIN user_details UDTLS1 ON "
					+ "UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Retention of Bilge Tank (i)":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank "
					+ "FROM retendation_of_bilge_orb1 dta LEFT JOIN user_details UDTLS1 ON "
					+ "UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Engine Room Bilge Wells To Bilge Tank D13":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,blidge_er_bwell_to_btank_d13_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.destination_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Disposal Of Bilge Via Shore Connection D13":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	bilge_disposal_shoreconnection_d13_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Disposal of Bilge From Bilge Tank to Sludge Tank":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,blige_disposalbw_btank_to_stank_d13_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Transfer Between Bilge Tanks D13":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,blige_transferbw_two_tanks_d13_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.destination_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Pumping Of Bilge Water Overboard Through OWS From BWell":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM bilge_pumping_overboard_thrw_bwell_ows_d13_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details "
					+ "UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? ORDER BY start_date DESC";

//			System.out.println(tableName);
			break;

		case "Pumping Of Bilge Water Overboard Through OWS From Tank":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,bilge_pumping_overboard_thrw_tnk_ows_d13_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Automatic Bilge Overboard Through Equipment D16,D18":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,bilge_pumping_overboard_thrw_equip_auto_d16_18_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Engine Room Bilge Wells To Bilge Tank Automatic D17,D18":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,blidge_er_bwell_to_btank_auto_d17_18_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.destination_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "FAILURE OF OIL FILTERING EQUIPMENT (F 19,20,21)":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM failure_oil_filter_equipment_f19_20_21_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id "
					+ "LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM restoration_of_faulty_equipment_f19_20_21_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id "
					+ "LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "ACCIDENTAL POLLUTION (G 22,23,24,25)":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM accidental_pollution_g21_25 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id "
					+ "LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = 'NO' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;
		case "FUEL OIL BUNKERING (H 26.1,26.2,26.3)":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM fuel_oil_bunkerig_h26 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id "
					+ "LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "CARGO HOLD BILGE TANK TO BILGE TANK (i)":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,cargo_hold_btank_to_btank dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.destination_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Transfer Sludge To Deck Cargo Slop Tank":
			query = "SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,transfer_sludge_to_deckcargo_slop_tank dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Transfer Bildge To Deck Cargo Slop Tank":
			query = "SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,transfer_bilge_to_deckcargo_slop_tank dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.source_tank = tanks_entry_detalis_orb.tank_name and dta.entry_id =? "
					+ "ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "SealingOfMarpolAnnex1RelatedValueOrEquipment":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month,"
					+ "UDTLS1.user_first_name as officer_fisrt_name,UDTLS1.user_rank as officer_rank,"
					+ "UDTLS2.user_first_name as strike_name,UDTLS2.user_rank as striker_rank,"
					+ "UDTLS3.user_first_name as master_rename,UDTLS3.user_rank as master_rerank,"
					+ "UDTLS5.user_first_name as master_name,UDTLS5.user_rank as master_rank "
					+ "FROM sealing_of_marpol_annex1_related_value dta LEFT JOIN user_details "
					+ "UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON "
					+ "UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON "
					+ "UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? and missed_entry = '" + missed
					+ "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "Breaking Of Marpol Annex1 Related Value Or Equipment Entity":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM breaking_of_marpol_annex1_related_value dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "ENTRY UNDER CODE (i)":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM entry_under_code dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "VOLUNTARY RECORDINGS (TESTING OF EQUIPMENT)":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM voluntary_recordings_equiptestorb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		case "NEWLY JOINED ENGINEER FAMILIARIZATION(i)":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM newly_joined_enginner_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;
		case "VOLUNTARY RECORDING-CALIBRATION OF 15PPM UNIT":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM voluntary_recordings_calibration_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
			break;

		case "Voluntry Recording-OWS overboard Line Inspection":
			query = "SELECT dta.*,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, "
					+ "UDTLS1.user_first_name as officer_fisrt_name, "
					+ "UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, "
					+ "UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, "
					+ "UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, "
					+ "UDTLS5.user_rank as master_rank FROM voluntary_recordings_ows_orb1 dta "
					+ "LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON "
					+ "UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid "
					+ "LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id "
					+ "WHERE dta.entry_id =? and missed_entry = '" + missed + "' ORDER BY start_date DESC";
//			System.out.println(tableName);
			break;

		}

		return query;

	}

	public static String getDbTableName(String entryName) {

		String tableName = null;
		String entry = entryName;
		switch (entry) {

		case "INVENTORY OF SLUDGE TANK (C 11.1,11.2,11.3)":
			tableName = "inventory_of_sludge_tank_c11_orb1";
//			System.out.println(tableName);
			break;

		case "Retention of Bilge Tank (i)":
			tableName = "retendation_of_bilge_orb1";
//			System.out.println(tableName);
			break;

		case "VOLUNTARY RECORDINGS (TESTING OF EQUIPMENT)":
			tableName = "voluntary_recordings_equiptestorb1";
//			System.out.println(tableName);
			break;

		case "NEWLY JOINED ENGINEER FAMILIARIZATION(i)":
			tableName = "newly_joined_enginner_orb1";
//			System.out.println(tableName);
			break;

		case "VOLUNTARY RECORDING-CALIBRATION OF 15PPM UNIT":
			tableName = "voluntary_recordings_calibration_orb1";
			break;

		case "DisposalOfSludgeViaShoreConnection C 12.1":
			tableName = "sludge_disposal_shoreconnection_c12_1_orb1";
//			System.out.println(tableName);
			break;
		case "Daily Tank Sounding Sheet Updation":
			tableName = "daily_tank_sounding_sheet_orb1";
//			System.out.println(tableName);
			break;
		case "Tranfer Between Sludge Tanks C_12.2":
			tableName = "sludge_transferbw_two_tanks_c12_2orb1";
//			System.out.println(tableName);
			break;
		case "Automatic Entry":
			tableName = "automatic_entry";
//			System.out.println(tableName);
			break;
		case "Sludge Collected by manual operation C11.4":
			tableName = "sludge_m_opt_c11_4_orb1";
//			System.out.println(tableName);
			break;
		case "Incineration of sludge C 12.3":
			tableName = "sludge_inceration_c12_3orb1";
//			System.out.println(tableName);
			break;
		case "Evaportaion of Water From Sludge c12_4_orb1":
			tableName = "sludge_tank_evaporation_of_water_c12_4_orb1";
//			System.out.println(tableName);
			break;
		case "Sludge Burning In Boiler C_12.4":
			tableName = "sludge_burning_in_boiler_c12_4";
//			System.out.println(tableName);
			break;
		case "Draining Water From Sludge To Blidge tank":
			tableName = "sludge_drain_water_frm_sludge_to_blidge_tank";
//			System.out.println(tableName);
			break;
		case "Regeneration Of Fuel From Sludge C_12.4":
			tableName = "sludge_fuel_regeneration_c12_4orb1";
//			System.out.println(tableName);
			break;
		case "Inventory Of Sludge C11":
			tableName = "inventory_of_sludge_tank_c11_orb1";
//			System.out.println(tableName);
			break;
		case "Engine Room Bilge Wells To Bilge Tank D13":
			tableName = "blidge_er_bwell_to_btank_d13_orb1";
//			System.out.println(tableName);
			break;
		case "Disposal Of Bilge Via Shore Connection D13":
			tableName = "bilge_disposal_shoreconnection_d13_orb1";
//			System.out.println(tableName);
			break;
		case "Disposal of Bilge From Bilge Tank to Sludge Tank":
			tableName = "blige_disposalbw_btank_to_stank_d13_orb1";
//			System.out.println(tableName);
			break;
		case "Transfer Between Bilge Tanks D13":
			tableName = "blige_transferbw_two_tanks_d13_orb1";
//			System.out.println(tableName);
			break;
		case "Pumping Of Bilge Water Overboard Through OWS From BWell":
			tableName = "bilge_pumping_overboard_thrw_bwell_ows_d13_orb1";
//			System.out.println(tableName);
			break;
		case "Pumping Of Bilge Water Overboard Through OWS From Tank":
			tableName = "bilge_pumping_overboard_thrw_tnk_ows_d13_orb1";
//			System.out.println(tableName);
			break;
		case "Automatic Bilge Overboard Through Equipment D16,D18":
			tableName = "bilge_pumping_overboard_thrw_equip_auto_d16_18_orb1";
//			System.out.println(tableName);
			break;
		case "Engine Room Bilge Wells To Bilge Tank Automatic D17,D18":
			tableName = "blidge_er_bwell_to_btank_auto_d17_18_orb1";
//			System.out.println(tableName);
			break;
		case "CARGO HOLD BILGE TANK TO BILGE TANK (i)":
			tableName = "cargo_hold_btank_to_btank";
//			System.out.println(tableName);
			break;
		case "Transfer Bildge To Deck Cargo Slop Tank":
			tableName = "transfer_bilge_to_deckcargo_slop_tank";
//			System.out.println(tableName);
			break;
		case "Transfer Sludge To Deck Cargo Slop Tank":
			tableName = "transfer_sludge_to_deckcargo_slop_tank";
//			System.out.println(tableName);
			break;
		case "FAILURE OF OIL FILTERING EQUIPMENT (F 19,20,21)":
			tableName = "failure_oil_filter_equipment_f19_20_21_orb1";
//			System.out.println(tableName);
			break;
		case "RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)":
			tableName = "restoration_of_faulty_equipment_f19_20_21_orb1";
//			System.out.println(tableName);
			break;
		case "ACCIDENTAL POLLUTION (G 22,23,24,25)":
			tableName = "accidental_pollution_g21_25";
//			System.out.println(tableName);
			break;
		case "SealingOfMarpolAnnex1RelatedValueOrEquipment":
			tableName = "sealing_of_marpol_annex1_related_value";
//			System.out.println(tableName);
			break;
		case "Breaking Of Marpol Annex1 Related Value Or Equipment Entity":
			tableName = "breaking_of_marpol_annex1_related_value";
//			System.out.println(tableName);
			break;

		case "ENTRY UNDER CODE (i)":
			tableName = "entry_under_code";
//			System.out.println(tableName);
			break;

		case "FUEL OIL BUNKERING (H 26.1,26.2,26.3)":
			tableName = "fuel_oil_bunkerig_h26";
//			System.out.println(tableName);
			break;

		case "LUB OIL BUNKERING (H 26.1,26.2,26.4)":
			tableName = "bulk_lubricating_oil_bunkerig_h26";
//			System.out.println(tableName);
			break;

		case "de_bunkering_of_fuel_oil":
			tableName = "de_bunkering_of_fuel_oil";
//			System.out.println(tableName);
			break;

		case "Voluntry Recording-OWS overboard Line Inspection":
			tableName = "voluntary_recordings_ows_orb1";
//			System.out.println(tableName);
			break;

		}

		return tableName;

	}

	public static String[] getAllTableName() {
		String[] tableName = { "sludge_inceration_c12_3orb1", "sludge_disposal_shoreconnection_c12_1_orb1",
				"sludge_tank_evaporation_of_water_c12_4_orb1", "automatic_entry", "sludge_burning_in_boiler_c12_4",
				"sludge_fuel_regeneration_c12_4orb1", "sludge_drain_water_frm_sludge_to_blidge_tank",
				"blidge_er_bwell_to_btank_d13_orb1", "blige_transferbw_two_tanks_d13_orb1",
				"bilge_pumping_overboard_thrw_tnk_ows_d13_orb1", "bilge_pumping_overboard_thrw_bwell_ows_d13_orb1",
				"blige_disposalbw_btank_to_stank_d13_orb1", "bilge_pumping_overboard_thrw_equip_auto_d16_18_orb1",
				"blidge_er_bwell_to_btank_auto_d17_18_orb1", "failure_oil_filter_equipment_f19_20_21_orb1",
				"restoration_of_faulty_equipment_f19_20_21_orb1", "accidental_pollution_g21_25",
				"fuel_oil_bunkerig_h26", "sludge_m_opt_c11_4_orb1", "cargo_hold_btank_to_btank",
				"bilge_disposal_shoreconnection_d13_orb1", "bulk_lubricating_oil_bunkerig_h26",
				"de_bunkering_of_fuel_oil", "transfer_sludge_to_deckcargo_slop_tank",
				"transfer_bilge_to_deckcargo_slop_tank", "breaking_of_marpol_annex1_related_value",
				"sealing_of_marpol_annex1_related_value", "entry_under_code", "sludge_transferbw_two_tanks_c12_2orb1",
				"voluntary_recordings_ows_orb1", "voluntary_recordings_equiptestorb1",
				"voluntary_recordings_calibration_orb1", "newly_joined_enginner_orb1",
				"	inventory_of_sludge_tank_c11_orb1", "retendation_of_bilge_orb1" };

		return tableName;

	}

	public static String getBilgeEntryQuery(String entryName) {

		String tableName = null;
		String entry = entryName;

		switch (entry) {
		case "Disposal Of Bilge Via Shore Connection D13":
			tableName = "select sum(qty_transfered) from bilge_disposal_shoreconnection_d13_orb1  where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Automatic Bilge Overboard Through Equipment D16,D18":
			tableName = "select sum(qty_discharged) from bilge_pumping_overboard_thrw_equip_auto_d16_18_orb1  where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Disposal of Bilge From Bilge Tank to Sludge Tank":
			tableName = "select sum(qty_transfered) from blige_disposalbw_btank_to_stank_d13_orb1 where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Transfer Bildge To Deck Cargo Slop Tank":
			tableName = "select sum(qty_transfered) from transfer_bilge_to_deckcargo_slop_tank  where stop_date = ";
//			System.out.println(tableName);
			break;
		}

		return tableName;
	}

	public static String getBilgeTotalAvgQuery(String entryName) {

		String query = null;
		String entry = entryName;

		switch (entry) {
		case "Disposal Of Bilge Via Shore Connection D13":
			query = "select sum(qty_transfered),AVG(qty_transfered) from bilge_disposal_shoreconnection_d13_orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Automatic Bilge Overboard Through Equipment D16,D18":
			query = "select sum(qty_discharged),AVG(qty_discharged) from bilge_pumping_overboard_thrw_equip_auto_d16_18_orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Disposal of Bilge From Bilge Tank to Sludge Tank":
			query = "select sum(qty_transfered),AVG(qty_transfered) from blige_disposalbw_btank_to_stank_d13_orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Transfer Bildge To Deck Cargo Slop Tank":
			query = "select sum(qty_transfered),AVG(qty_transfered) from transfer_bilge_to_deckcargo_slop_tank "
					+ "where stop_date BETWEEN ? and ?";
			break;
		}

		return query;
	}

	// Created For Sludge Report Entry Data
	public static String getSludgeEntryQuery(String entryName) {

		String tableName = null;
		String entry = entryName;

		switch (entry) {
		case "Incineration of sludge C 12.3":
			tableName = "select sum(quantity_incinerated) from sludge_inceration_c12_3orb1 where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Evaportaion of Water From Sludge c12_4_orb1":
			tableName = "select sum(quantity_evaporated) from sludge_tank_evaporation_of_water_c12_4_orb1 where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Sludge Burning In Boiler C_12.4":
			tableName = "select sum(quantity_Burned) from sludge_burning_in_boiler_c12_4 where stop_date = ";
//			System.out.println(tableName);
			break;

		case "DisposalOfSludgeViaShoreConnection C 12.1":
			tableName = "select sum(quantity_transferred) from sludge_disposal_shoreconnection_c12_1_orb1 where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Draining Water From Sludge To Blidge tank":
			tableName = "select sum(quantity_transferred) from sludge_drain_water_frm_sludge_to_blidge_tank where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Transfer Sludge To Deck Cargo Slop Tank":
			tableName = "select sum(quantity_transferred) from transfer_sludge_to_deckcargo_slop_tank where stop_date = ";
//			System.out.println(tableName);
			break;

		case "Regeneration Of Fuel From Sludge C_12.4":
			tableName = "select sum(quan_of_sludge_disposed) from sludge_fuel_regeneration_c12_4orb1 where stop_date = ";
//			System.out.println(tableName);
			break;

		}
		return tableName;
	}

	// Created For Sludge Report Entry Data
	public static String getSludgeTotalAvgQuery(String entryName) {

		String tableName = null;
		String entry = entryName;

		switch (entry) {
		case "Incineration of sludge C 12.3":
			tableName = "select sum(quantity_incinerated),AVG(quantity_incinerated) from sludge_inceration_c12_3orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;
		case "FUEL OIL CONSUMPTION PER DAY":
			tableName = "select sum(quantity),AVG(quantity) from daily_tank_sounding_sheet "
					+ "WHERE date BETWEEN ? and ? and tank_id = 13";
			break;

		case "Evaportaion of Water From Sludge c12_4_orb1":
			tableName = "select sum(quantity_evaporated),AVG(quantity_evaporated) from sludge_tank_evaporation_of_water_c12_4_orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Sludge Burning In Boiler C_12.4":
			tableName = "select sum(quantity_Burned),AVG(quantity_Burned) from sludge_burning_in_boiler_c12_4 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "DisposalOfSludgeViaShoreConnection C 12.1":
			tableName = "select sum(quantity_transferred),AVG(quantity_transferred) from sludge_disposal_shoreconnection_c12_1_orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Draining Water From Sludge To Blidge tank":
			tableName = "select sum(quantity_transferred),AVG(quantity_transferred) from sludge_drain_water_frm_sludge_to_blidge_tank "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Transfer Sludge To Deck Cargo Slop Tank":
			tableName = "select sum(quantity_transferred),AVG(quantity_transferred) from transfer_sludge_to_deckcargo_slop_tank "
					+ "where stop_date BETWEEN ? and ?";
			break;

		case "Regeneration Of Fuel From Sludge C_12.4":
			tableName = "select sum(quan_of_sludge_disposed),AVG(quan_of_sludge_disposed) from sludge_fuel_regeneration_c12_4orb1 "
					+ "where stop_date BETWEEN ? and ?";
			break;

		}
		return tableName;
	}

	public static void main(String[] args) {

		System.out.println(getDbTableName("Inventory Of Sludge C11 Orb1"));

	}

}