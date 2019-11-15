package com.nts.orb1.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nts.grb.connection.dbConnection;
import com.nts.orb1.dao.GetQuantity;

public class BilgeReportData {

	public static JSONArray getReportData(String startDate, String endDate) throws Exception {

		int firstSlot = 7, secondSlot = 9;

		Connection con = dbConnection.getConnection();
		LinkedList<Integer> tankId = GetTankDetails.getTankIdByType(2);

		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Find Difference In Days Between User Given Dates
		Date date1 = myFormat.parse(startDate);
		Date date2 = myFormat.parse(endDate);

		long diff = date2.getTime() - date1.getTime();
		int days = (int) (diff / (1000 * 60 * 60 * 24));
		// System.out.println(days);
		days = days + 1;

		// Get All Dates Between User Given Dates In List
		List<String> dates = GetQuantity.getAllDates(startDate, days);
		// System.out.println(dates);

		// Calculate Slots Between Dates BY DIVIDING ALL DAYS BY 7 DAYS
		LinkedList<Integer> slots = new LinkedList<>();

		int temp = days;

		int count = 0;
		int avgTotaladd = 0;

		while (temp > 0) {

			if (temp >= secondSlot) {
				if (count == 0) {
					slots.add(firstSlot);
				} else
					slots.add(secondSlot);
			} else
				slots.add(temp);
			if (count == 0) {
				temp -= firstSlot;
				count = 1;
			} else
				temp -= secondSlot;

		}

		// System.out.println(slots);

		int slotCount = 0;
		JSONArray totalData = new JSONArray();

		double totalQuantityBilgePerDay = 0, totalBilgeQuantity = 0;

		for (int i = 0; i < slots.size(); i++) {

			// System.out.println("Slot - " + slots.get(i));
			double totalTnkCapacity = 0;

			JSONArray alldata = new JSONArray();

			JSONObject totalBilgeQuanToday = new JSONObject();
			totalBilgeQuanToday.put("tank_name", "TOTAL BILGE QUANTITY");

			JSONObject totalBilgeQuanPreDate = new JSONObject();
			totalBilgeQuanPreDate.put("date_1", 0);

			JSONObject totalTankCapacity = new JSONObject();
			totalTankCapacity.put("tank_name", "TOTAL BILGE QUANTITY IN %");

			JSONObject slotDates = new JSONObject();
			LinkedList<String> slotDatesList = new LinkedList<>();

			// putAll Dates In JSONObject with key
			for (int j = 1; j <= slots.get(i); j++) {

				slotDates.put("date_" + j, dates.get(slotCount));
				slotDatesList.add(dates.get(slotCount));
				slotCount++;
			}

			// Put Dates JSON Object In Slot Array
			alldata.put(slotDates);

			// Getting Entries Data In JSONObject

			JSONObject shoreData = EntryDataForReport.getBilgeEntryData("Disposal Of Bilge Via Shore Connection D13",
					slotDatesList, con);
			shoreData.put("tank_name", "QUANTITY DISCHARGE TO SHORE");

			JSONObject overboardData = EntryDataForReport
					.getBilgeEntryData("Automatic Bilge Overboard Through Equipment D16,D18", slotDatesList, con);
			overboardData.put("tank_name", "QUANTITY DISCHARGED OVERBOARD");

			JSONObject transferBtankToStankData = EntryDataForReport
					.getBilgeEntryData("Disposal of Bilge From Bilge Tank to Sludge Tank", slotDatesList, con);
			transferBtankToStankData.put("tank_name", "DISPOSAL OF BILGE WATER TO SLUDGE TANK");

			JSONObject deckSlopData = EntryDataForReport.getBilgeEntryData("Transfer Bildge To Deck Cargo Slop Tank",
					slotDatesList, con);
			deckSlopData.put("tank_name", "QUANTITY TRANSFERRED TO DECK SLOP TANK");

			JSONObject totalEntryQuanData = new JSONObject();
			totalEntryQuanData.put("tank_name", "TOTAL QUANTITY IN ALL ENTRIES PER DAY");

			// Created For Add Average & Total At First Slot Only
			if (avgTotaladd == 0) {

				double sloptotalQuanData = (EntryDataForReport
						.getBilgeTotalAvgData("Transfer Bildge To Deck Cargo Slop Tank", startDate, endDate, con));
				deckSlopData.put("total", sloptotalQuanData);
				deckSlopData.put("average", sloptotalQuanData / days);

				double BtankStanktotalQuanData = (EntryDataForReport.getBilgeTotalAvgData(
						"Disposal of Bilge From Bilge Tank to Sludge Tank", startDate, endDate, con));
				transferBtankToStankData.put("total", BtankStanktotalQuanData);
				transferBtankToStankData.put("average", BtankStanktotalQuanData / days);

				double overboardtotalQuanData = (EntryDataForReport.getBilgeTotalAvgData(
						"Automatic Bilge Overboard Through Equipment D16,D18", startDate, endDate, con));
				overboardData.put("total", overboardtotalQuanData);
				overboardData.put("average", overboardtotalQuanData / days);

				double shoretotalQuanTotal = (EntryDataForReport
						.getBilgeTotalAvgData("Disposal Of Bilge Via Shore Connection D13", startDate, endDate, con));
				shoreData.put("total", shoretotalQuanTotal);
				shoreData.put("average", shoretotalQuanTotal / days);

				avgTotaladd = 1;
			}

			for (int k = 1; k <= slots.get(i); k++) {

				// Creating Total Bilge Quantity JSONObject corresponding to dates
				totalBilgeQuanToday.put("date_" + k, 0);

				// Creating Total Entries Quantity JSONObject corresponding to dates
				totalEntryQuanData.put("date_" + k, 0);

			}

			// System.out.println("First Date - " + slotDatesList.get(0));
			LocalDate currentdate = LocalDate.parse(slotDatesList.get(0));

			// Start Date And End Date Format Should Be Like 2019-12-31
			LocalDate start = LocalDate.parse(currentdate.minusDays(1).toString());

			List<String> preDate = new ArrayList<>();
			preDate.add(start.toString());
			// System.out.println(preDate);

			for (int j = 0; j < tankId.size(); j++) {

				int id = tankId.get(j);

				JSONObject bilgeTnkData = GetQuantityForSludgeReport.getTankData(id, slotDatesList, con);
				JSONObject bilgeTnkDataPre = GetQuantityForSludgeReport.getTankData(id, preDate, con);

				alldata.put(bilgeTnkData);

				totalTnkCapacity += bilgeTnkData.getDouble("tank_capacity");

				for (int k = 1; k <= slots.get(i); k++) {

					totalBilgeQuanToday.put("date_" + k,
							totalBilgeQuanToday.getDouble("date_" + k) + bilgeTnkData.getDouble("date_" + k));

				}
				totalBilgeQuanPreDate.put("date_1",
						totalBilgeQuanPreDate.getDouble("date_1") + bilgeTnkDataPre.getDouble("date_1"));

				// System.out.println(tnkData);

			}

			// For Calculate Total Quantity Between User Given Dates
			for (int k = 1; k <= slotDates.length(); k++) {

				totalBilgeQuantity += totalBilgeQuanToday.getDouble("date_" + k);

				totalTankCapacity.put("date_" + k,
						((totalBilgeQuanToday.getDouble("date_" + k) / totalTnkCapacity) * 100));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + shoreData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + overboardData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + transferBtankToStankData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + deckSlopData.getDouble("date_" + k));

			}

			JSONObject totalBilgePerDay = new JSONObject();
			totalBilgePerDay.put("tank_name", "TOTAL BILGE GENERATED PER DAY");

			for (int k = 1; k <= slots.get(i); k++) {

				if (k == 1) {

					if (slotDatesList.get(0).equalsIgnoreCase(new GetFirstTankAddTime().getDate())) {

						totalBilgePerDay.put("date_" + k, "");

					}

					else {
						totalBilgePerDay.put("date_" + k,
								(totalBilgeQuanToday.getDouble("date_" + k)
										- totalBilgeQuanPreDate.getDouble("date_" + k))
										+ totalEntryQuanData.getDouble("date_" + k));
					}
				}

				else {
					totalBilgePerDay.put("date_" + k,
							(totalBilgeQuanToday.getDouble("date_" + k)
									- totalBilgeQuanToday.getDouble("date_" + (k - 1)))
									+ totalEntryQuanData.getDouble("date_" + k));
				}

				if (!slotDatesList.get(0).equalsIgnoreCase(new GetFirstTankAddTime().getDate())) {

					totalQuantityBilgePerDay += totalBilgePerDay.getDouble("date_" + k);
				}
			}

			alldata.put(totalBilgeQuanToday);
			alldata.put(totalTankCapacity);

			alldata.put(totalBilgePerDay);
			alldata.put(shoreData);

			alldata.put(overboardData);
			alldata.put(transferBtankToStankData);

			alldata.put(deckSlopData);
			totalData.put(alldata);

		}

		for (int i = 1; i < totalData.getJSONArray(0).length(); i++) {

			JSONObject obj = totalData.getJSONArray(0).getJSONObject(i);
			if (obj.getString("tank_name").equalsIgnoreCase("TOTAL BILGE GENERATED PER DAY")) {
				totalData.getJSONArray(0).getJSONObject(i).put("total", totalQuantityBilgePerDay);
				totalData.getJSONArray(0).getJSONObject(i).put("average", totalQuantityBilgePerDay / days);

			} else if (obj.getString("tank_name").equalsIgnoreCase("TOTAL BILGE QUANTITY")) {
				totalData.getJSONArray(0).getJSONObject(i).put("total", totalBilgeQuantity);
				totalData.getJSONArray(0).getJSONObject(i).put("average", totalBilgeQuantity / days);

			} else if (obj.getString("tank_name").equalsIgnoreCase("TOTAL BILGE QUANTITY IN %")) {
				totalData.getJSONArray(0).getJSONObject(i).put("total", (totalBilgeQuantity / days) * 100);
				totalData.getJSONArray(0).getJSONObject(i).put("average", ((totalBilgeQuantity / days) * 100) / days);

			}

		}

		System.out.println(totalData);
		return totalData;

	}

	public static void main(String[] args) throws Exception {

		JSONArray j1 = BilgeReportData.getReportData("2019-10-01", "2019-10-21");
		System.out.println(j1);

	}
}
