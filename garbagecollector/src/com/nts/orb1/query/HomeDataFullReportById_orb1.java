package com.nts.orb1.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.json.JSONArray;

import com.nts.grb.util.DaoUtil;
import com.nts.orb1.service.GetTableName;
import com.nts.orb1.service.Getting_homeData;

public class HomeDataFullReportById_orb1 {

	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static String abc = "";

	static JSONArray entryid;
	String[] tablename = { "" };

	static int minentryid;
	static int maxentryid;

	static String[] sql = {

			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_m_opt_c11_4_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_transferbw_two_tanks_c12_2orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_inceration_c12_3orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_disposal_shoreconnection_C12_1_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	daily_tank_sounding_sheet_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY stop_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_tank_evaporation_of_water_c12_4_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY stop_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.stop_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	sludge_burning_in_boiler_c12_4 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY stop_date DESC",
			"SELECT DISTINCT dta.*,tank_capacity,tedo.tank_name,ats.source_name,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	automatic_entry dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id 	LEFT JOIN tanks_entry_detalis_orb tedo ON dta.destination_tank_id=tedo.tank_id 	LEFT JOIN automatic_tank_source ats ON dta.source=ats.id WHERE dta.entry_id =? ORDER BY dta.id DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, sludge_fuel_regeneration_c12_4orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, sludge_drain_water_frm_sludge_to_blidge_tank dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, blidge_er_bwell_to_btank_d13_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, blige_transferbw_two_tanks_d13_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, bilge_pumping_overboard_thrw_tnk_ows_d13_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM bilge_pumping_overboard_thrw_bwell_ows_d13_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, blige_disposalbw_btank_to_stank_d13_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb, bilge_pumping_overboard_thrw_equip_auto_d16_18_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	blidge_er_bwell_to_btank_auto_d17_18_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM failure_oil_filter_equipment_f19_20_21_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM restoration_of_faulty_equipment_f19_20_21_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM accidental_pollution_g21_25 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\") as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM fuel_oil_bunkerig_h26 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,cargo_hold_btank_to_btank dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.destination_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC",
			"SELECT dta.*,tank_capacity,DATE_FORMAT(dta.start_date, \"%d-%b-%Y\")  as enrtrydate_month, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM tanks_entry_detalis_orb,	bilge_disposal_shoreconnection_d13_orb1 dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.source_tank =tanks_entry_detalis_orb.tank_name and dta.entry_id =? ORDER BY start_date DESC" };

	static ResultSet resultSet;

	public static JSONArray getStopIncinerationAcc(Connection con) throws Exception {
		JSONArray obj;
		JSONArray data = new JSONArray();

		for (int id = maxentryid; id > minentryid; id--) {

			// System.out.println("Ent Id:- " +id);
			obj = new Getting_homeData().getDBDtls(id, con);

			if (obj.length() != 0) {
				data.put(obj.get(0));
			}
		}
		return data;
	}

	public static JSONArray data_dtls(String startdate, String enddate, Connection con) throws Exception {

		JSONArray obj2 = new JSONArray();
		String[] entry_type_name1 = GetTableName.getAllTableName();

		for (int i = 0; i <= sql.length - 1; i++) {
			JSONArray obj = new JSONArray();
			/*
			 * String sqlquery = "select * from " + entry_type_name1[i] +
			 * " where entry_date between '" + startdate + "' and '" + enddate + "'";
			 */
			String sqlquery = "SELECT dta.*, UDTLS1.user_first_name as officer_fisrt_name, UDTLS1.user_rank as officer_rank,UDTLS2.user_first_name as strike_name, UDTLS2.user_rank as striker_rank,UDTLS3.user_first_name as master_rename, UDTLS3.user_rank as master_rerank,UDTLS5.user_first_name as master_name, UDTLS5.user_rank as master_rank FROM 	"
					+ entry_type_name1[i]
					+ " dta LEFT JOIN user_details UDTLS1 ON UDTLS1.user_id = dta.officer_id LEFT JOIN user_details UDTLS2 ON UDTLS2.user_id = dta.strike_id LEFT JOIN user_details UDTLS3 ON UDTLS3.user_id = dta.master_reid LEFT JOIN user_details UDTLS5 ON UDTLS5.user_id = dta.master_id WHERE dta.start_date between '"
					+ startdate + " ' and '" + enddate + "' ORDER BY entry_id DESC";
			// String sqlquery=sql[i];
			ps = con.prepareStatement(sqlquery);
			rs = ps.executeQuery();
			obj = DaoUtil.convertToJsonArray1(rs);

			for (int j = 0; j <= obj.length() - 1; j++) {
				obj2.put(obj.get(j));
			}

//			System.out.println("json data via objcet2:--" + obj2);
		}

		return obj2;
	}

	@SuppressWarnings("null")
	public static JSONArray entry_id(String startdate, String enddate, Connection con) {

		JSONArray obj = null;

		String[] entry_type_name1 = GetTableName.getAllTableName();

		int[] minid = new int[entry_type_name1.length];
		int[] maxid = new int[entry_type_name1.length];
		try {

			for (int i = 0; i < entry_type_name1.length; i++) {

				String sqlquery = "select min(entry_id) as minid,max(entry_id) as maxid from " + entry_type_name1[i]
						+ " where stop_date between '" + startdate + "' and '" + enddate + "'";

//				System.out.println("Start Date :- "+startdate);
//				System.out.println("End Date :- "+enddate);
//
				// System.out.println("Count Entry :- "+sqlquery);

				ps = con.prepareStatement(sqlquery);
				rs = ps.executeQuery();

				if (rs.next()) {
					minid[i] = rs.getInt(1);
					maxid[i] = rs.getInt(2);
				}
				minentryid = min(minid);
				maxentryid = max(maxid);

//				System.out.println(minentryid);
//				System.out.println(maxentryid);
//				System.out.println(entry_type_name1[i]);

			}

			System.out.println("minid===" + minentryid);
			System.out.println("maxid===" + maxentryid);

			obj = getStopIncinerationAcc(con);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
		}
//		System.out.println("***************-----------------" + obj);
		return obj;
	}

	public static int max(int[] array) {
		int max = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}

	public static int min(int[] array) {
		int min = array[0];

		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}

}