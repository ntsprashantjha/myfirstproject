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
import com.nts.orb1.service.EntryDataForReport;
import com.nts.orb1.service.GetFirstTankAddTime;
import com.nts.orb1.service.GetQuantityForSludgeReport;
import com.nts.orb1.service.GetTankDetails;

public class SludgeReportData {

	public static JSONArray getReportData(String startDate, String endDate) throws Exception {

		int firstSlot = 7, secondSlot = 9;

		Connection con = dbConnection.getConnection();
		LinkedList<Integer> tankId = GetTankDetails.getTankIdByType(1);

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

		// For Calculate JSONArray Index
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

		int slotCount = 0;

		JSONArray totalData = new JSONArray();
		double totalQuantitySludgePerDay = 0, totalSludgeQuan = 0;

		for (int i = 0; i < slots.size(); i++) {

			double totalTnkCapacity = 0;

			JSONArray alldata = new JSONArray();

			JSONObject totalSludgeQuanToday = new JSONObject();
			totalSludgeQuanToday.put("tank_name", "TOTAL SLUDGE QUANTITY");

			JSONObject totalSludgeQuanPreDate = new JSONObject();
			totalSludgeQuanPreDate.put("date_1", 0);

			JSONObject totalSludgeQuanPercent = new JSONObject();
			totalSludgeQuanPercent.put("tank_name", "TOTAL SLUDGE QUANTITY IN %");

			JSONObject inciburnData = new JSONObject();
			inciburnData.put("tank_name", "QUANTITY INCINERATED/BURNED");

			JSONObject slotDates = new JSONObject();
			LinkedList<String> slotDatesList = new LinkedList<>();

			// putAll Dates In JSONObject with key
			for (int j = 1; j <= slots.get(i); j++) {

				slotDates.put("date_" + j, dates.get(slotCount));
				slotDatesList.add(dates.get(slotCount));
				slotCount++;

			}
//			System.out.println(slotDatesList);

			// Put Dates JSON Object In Slot Array
			alldata.put(slotDates);

			JSONObject fuelConsumptionTnkData = GetQuantityForSludgeReport.getTankData(4, slotDatesList, con);
			fuelConsumptionTnkData.put("tank_name", "FUEL OIL CONSUMPTION PER DAY");

			// Getting Entries Data I JSONObject
			JSONObject evapData = EntryDataForReport.getSludgeEntryData("Evaportaion of Water From Sludge c12_4_orb1",
					slotDatesList, con);
			evapData.put("tank_name", "QUANTITY EVAPORATED");

			JSONObject shoreData = EntryDataForReport.getSludgeEntryData("DisposalOfSludgeViaShoreConnection C 12.1",
					slotDatesList, con);
			shoreData.put("tank_name", "QUANTITY DISCHARGE TO SHORE");

			JSONObject drainData = EntryDataForReport.getSludgeEntryData("Draining Water From Sludge To Blidge tank",
					slotDatesList, con);
			drainData.put("tank_name", "QUANTITY DRAINED TO BILGE TANK");

			JSONObject trnfrSlopTankData = EntryDataForReport
					.getSludgeEntryData("Transfer Sludge To Deck Cargo Slop Tank", slotDatesList, con);
			trnfrSlopTankData.put("tank_name", "QUANTITY TRANSFERRED TO SLOP TANK");

			JSONObject fuelRegnerationData = EntryDataForReport
					.getSludgeEntryData("Regeneration Of Fuel From Sludge C_12.4", slotDatesList, con);
			fuelRegnerationData.put("tank_name", "QUANTITY DISPOSED FOR REGENERATION OF FUEL");

			// Created For Add Average & Total At First Slot Only
			if (avgTotaladd == 0) {

				double fueltotalQuanData = (EntryDataForReport
						.getSludgeTotalAvgData("Regeneration Of Fuel From Sludge C_12.4", startDate, endDate, con));
				fuelRegnerationData.put("total", fueltotalQuanData);
				fuelRegnerationData.put("average", fueltotalQuanData / days);

				double sloptotalQuanData = (EntryDataForReport
						.getSludgeTotalAvgData("Transfer Sludge To Deck Cargo Slop Tank", startDate, endDate, con));
				trnfrSlopTankData.put("total", sloptotalQuanData);
				trnfrSlopTankData.put("average", sloptotalQuanData / days);

				double draintotalQuanData = (EntryDataForReport
						.getSludgeTotalAvgData("Draining Water From Sludge To Blidge tank", startDate, endDate, con));
				drainData.put("total", draintotalQuanData);
				drainData.put("average", draintotalQuanData / days);

				double shoretotalQuanTotal = (EntryDataForReport
						.getSludgeTotalAvgData("DisposalOfSludgeViaShoreConnection C 12.1", startDate, endDate, con));
				shoreData.put("total", shoretotalQuanTotal);
				shoreData.put("average", shoretotalQuanTotal / days);

				double evaptotalQuanTotal = (EntryDataForReport
						.getSludgeTotalAvgData("Evaportaion of Water From Sludge c12_4_orb1", startDate, endDate, con));
				evapData.put("total", evaptotalQuanTotal);
				evapData.put("average", evaptotalQuanTotal / days);

				double inciburnQuantotal = (EntryDataForReport.getSludgeTotalAvgData("Sludge Burning In Boiler C_12.4",
						startDate, endDate, con)
						+ EntryDataForReport.getSludgeTotalAvgData("Incineration of sludge C 12.3", startDate, endDate,
								con));
				inciburnData.put("total", inciburnQuantotal);
				inciburnData.put("average", inciburnQuantotal / days);

				double fuelConsumpQuantotal = (EntryDataForReport.getSludgeTotalAvgData("FUEL OIL CONSUMPTION PER DAY",
						startDate, endDate, con));
				fuelConsumptionTnkData.put("total", fuelConsumpQuantotal);
				fuelConsumptionTnkData.put("average", fuelConsumpQuantotal / days);

				avgTotaladd = 1;
			}

			JSONObject totalEntryQuanData = new JSONObject();
			totalEntryQuanData.put("tank_name", "TOTAL QUANTITY IN ALL ENTRIES PER DAY");

			for (int k = 1; k <= slots.get(i); k++) {

				// Creating Total Sludge Quantity JSONObject corresponding to dates
				totalSludgeQuanToday.put("date_" + k, 0);

				// Creating Total Entries Quantity JSONObject corresponding to dates
				totalEntryQuanData.put("date_" + k, 0);

				// Creating SludgeGenerated Per Day JSONObject corresponding to dates
				JSONObject inciData = EntryDataForReport.getSludgeEntryData("Incineration of sludge C 12.3",
						slotDatesList, con);

				JSONObject boilData = EntryDataForReport.getSludgeEntryData("Sludge Burning In Boiler C_12.4",
						slotDatesList, con);

				inciburnData.put("date_" + k, inciData.getDouble("date_" + k) + boilData.getDouble("date_" + k));
			}
			// System.out.println(totalSludgeQuan);

			// System.out.println("First Date - " + slotDatesList.get(0));
			LocalDate currentdate = LocalDate.parse(slotDatesList.get(0));

			// Start Date And End Date Format Should Be Like 2019-12-31
			LocalDate start = LocalDate.parse(currentdate.minusDays(1).toString());

			List<String> preDate = new ArrayList<>();
			preDate.add(start.toString());
			// System.out.println(preDate);

			for (int j = 0; j < tankId.size(); j++) {

				int id = tankId.get(j);

				JSONObject sludgeTnkData = GetQuantityForSludgeReport.getTankData(id, slotDatesList, con);
				JSONObject sludgeTnkDataPre = GetQuantityForSludgeReport.getTankData(id, preDate, con);

				alldata.put(sludgeTnkData);

				totalTnkCapacity += sludgeTnkData.getDouble("tank_capacity");

				for (int k = 1; k <= slots.get(i); k++) {

					totalSludgeQuanToday.put("date_" + k,
							totalSludgeQuanToday.getDouble("date_" + k) + sludgeTnkData.getDouble("date_" + k));

				}
				totalSludgeQuanPreDate.put("date_1",
						totalSludgeQuanPreDate.getDouble("date_1") + sludgeTnkDataPre.getDouble("date_1"));

				// System.out.println(tnkData);

			}

			// For Calculate Total Quantity Between User Given Dates
			for (int k = 1; k <= slotDates.length(); k++) {

				totalSludgeQuan += totalSludgeQuanToday.getDouble("date_" + k);

				totalSludgeQuanPercent.put("date_" + k,
						((totalSludgeQuanToday.getDouble("date_" + k) / totalTnkCapacity) * 100));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + evapData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + shoreData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + drainData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + trnfrSlopTankData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + fuelRegnerationData.getDouble("date_" + k));

				totalEntryQuanData.put("date_" + k,
						totalEntryQuanData.getDouble("date_" + k) + inciburnData.getDouble("date_" + k));

			}

			JSONObject totalSludgePerDay = new JSONObject();
			totalSludgePerDay.put("tank_name", "TOTAL SLUDGE GENERATED PER DAY");

			JSONObject totalSludgePerDayPercent = new JSONObject();
			totalSludgePerDayPercent.put("tank_name", "TOTAL SLUDGE GENERATED PER DAY IN %");

			for (int k = 1; k <= slots.get(i); k++) {

				if (k == 1) {

					// IF USER GIVEN STARTDATE IS SOFTWARE START DATE THEN SLUDGE GENERATED PER DAY
					// WILL EMPTY
					if (slotDatesList.get(0).equalsIgnoreCase(new GetFirstTankAddTime().getDate())) {

						// System.out.println("Software Start Date");
						totalSludgePerDay.put("date_" + k, "");

					}

					// CALCULATE SLUDGE GENERATED PER DAY
					else {
						totalSludgePerDay.put("date_" + k,
								(totalSludgeQuanToday.getDouble("date_" + k)
										- totalSludgeQuanPreDate.getDouble("date_" + k))
										+ totalEntryQuanData.getDouble("date_" + k));

					}
				}

				else {
					totalSludgePerDay.put("date_" + k,
							(totalSludgeQuanToday.getDouble("date_" + k)
									- totalSludgeQuanToday.getDouble("date_" + (k - 1)))
									+ totalEntryQuanData.getDouble("date_" + k));
				}

				if (!slotDatesList.get(0).equalsIgnoreCase(new GetFirstTankAddTime().getDate())) {

					totalQuantitySludgePerDay += totalSludgePerDay.getDouble("date_" + k);
				}

				try {

					if (k == 1) {

						// IF USER GIVEN STARTDATE IS SOFTWARE START DATE THEN SLUDGE GENERATED PER DAY
						// WILL EMPTY
						if (slotDatesList.get(0).equalsIgnoreCase(new GetFirstTankAddTime().getDate())) {

							totalSludgePerDayPercent.put("date_" + k, "");

						}

						// CALCULATE SLUDGE GENERATED PER DAY IN PERCENTAGE
						else {
							totalSludgePerDayPercent.put("date_" + k, (totalSludgePerDay.getDouble("date_" + k)
									/ fuelConsumptionTnkData.getDouble("date_" + k)) * 100);
						}
					}

					else {
						totalSludgePerDayPercent.put("date_" + k, (totalSludgePerDay.getDouble("date_" + k)
								/ fuelConsumptionTnkData.getDouble("date_" + k)) * 100);
					}

				} catch (Exception e) {

					totalSludgePerDayPercent.put("date_" + k, 0);
					// e.printStackTrace();

				}
			}
			// System.out.println(totalSludgePerDay);
			// System.out.println(totalEntryQuanData);
			// System.out.println(totalSludgeQuan);

			alldata.put(totalSludgeQuanToday);
			alldata.put(totalSludgeQuanPercent);

			alldata.put(totalSludgePerDay);
			alldata.put(totalSludgePerDayPercent);

			alldata.put(inciburnData);
			alldata.put(evapData);

			alldata.put(shoreData);
			alldata.put(drainData);

			alldata.put(trnfrSlopTankData);
			alldata.put(fuelRegnerationData);

			alldata.put(fuelConsumptionTnkData);
			// System.out.println(alldata);

			totalData.put(alldata);

			// System.out.println("Total Data " + totalData);

		}

		for (int i = 1; i < totalData.getJSONArray(0).length(); i++) {

			JSONObject obj = totalData.getJSONArray(0).getJSONObject(i);
			if (obj.getString("tank_name").equalsIgnoreCase("TOTAL SLUDGE GENERATED PER DAY")) {
				totalData.getJSONArray(0).getJSONObject(i).put("total", totalQuantitySludgePerDay);
				totalData.getJSONArray(0).getJSONObject(i).put("average", totalQuantitySludgePerDay / days);
				break;

			} else if (obj.getString("tank_name").equalsIgnoreCase("TOTAL SLUDGE QUANTITY")) {
				totalData.getJSONArray(0).getJSONObject(i).put("total", totalSludgeQuan);
				totalData.getJSONArray(0).getJSONObject(i).put("average", totalSludgeQuan / days);

			} else if (obj.getString("tank_name").equalsIgnoreCase("TOTAL SLUDGE QUANTITY IN %")) {
				totalData.getJSONArray(0).getJSONObject(i).put("total", (totalSludgeQuan / days) * 100);
				totalData.getJSONArray(0).getJSONObject(i).put("average", ((totalSludgeQuan / days) * 100) / days);

			}

		}

		System.out.println(totalData);
		return totalData;

	}

	public static void main(String[] args) throws Exception {

		JSONArray j1 = SludgeReportData.getReportData("2019-10-01", "2019-10-24");
		System.out.println(j1);
	}
}
