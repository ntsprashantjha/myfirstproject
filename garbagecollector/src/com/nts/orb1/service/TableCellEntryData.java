package com.nts.orb1.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TableCellEntryData {

	static PdfPTable testTable;

	public static void addMissedEntry(Font textfont, JSONObject obj) {

		PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
		cell1.setBorderWidthLeft(0f);
		cell1.setBorderWidthTop(0f);
		testTable.addCell(cell1);

		PdfPCell cell2 = new PdfPCell(new Phrase("ENTRY PERTAINING TO AN EARLIER MISSED OPERATIONAL ENTRY", textfont));
		cell2.setBorderWidthLeft(0f);
		cell2.setBorderWidthTop(0f);
		cell2.setBorderWidthRight(0f);
		testTable.addCell(cell2);

		PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
		cell3.setBorderWidthLeft(0f);
		cell3.setBorderWidthTop(0f);
		testTable.addCell(cell3);

		try {

			PdfPCell cell4 = new PdfPCell(new Phrase(
					obj.getString("missed_user_name").toUpperCase() + ", " + obj.getString("missed_user_rank"),
					textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthRight(0f);
			testTable.addCell(cell4);

		} catch (Exception e) {

			e.printStackTrace();
			testTable.addCell("");
		}

	}

	public static PdfPTable getCellData(JSONObject obj, String entryType, Font textfont)
			throws JSONException, ParseException, DocumentException, IOException {

		DecimalFormat df = new DecimalFormat("00.00");

		switch (entryType) {

		case "DisposalOfSludgeViaShoreConnection C 12.1": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(
					df.format(obj.getDouble("quantity_transferred")) + " m3 FROM " + obj.getString("source_tank") + ", "
							+ df.format(obj.getDouble("quantity_retainin_source_tank")) + " m3 RETAINED",
					textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			cell3.setBorderWidthBottom(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase(
					"TO " + obj.getString("identify_sludge_receiver") + " DURING STAY AT " + obj.getString("port"),
					textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			return testTable;

		}

		case "Daily Tank Sounding Sheet Updation": {

			testTable = new PdfPTable(1);
			PdfPCell cell1 = new PdfPCell(new Phrase("Daily Tank Sounding Sheet Updated", textfont));

			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			cell1.setBorderWidthBottom(0f);
			cell1.setBorderWidthRight(0f);

			testTable.addCell(cell1);

			return testTable;

		}

		case "Sludge Collected by manual operation C11.4": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("11.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("destination_tank"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("11.2", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("CAPACITY - " + df.format(obj.getDouble("tank_capacity")) + " m3", textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("11.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase(
					"RETAINED - " + df.format(obj.getDouble("quantity_retainin_destination_tank")) + " m3", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("11.4", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase(
					df.format(obj.getDouble("quantity_transferred")) + " m3 COLLECTED FROM " + obj.getString("source"),
					textfont));
			cell8.setBorderWidthRight(0f);
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthBottom(0f);
			testTable.addCell(cell8);

			return testTable;

		}

		case "Automatic Entry": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("11.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("tank_name"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("11.2", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("CAPACITY - " + df.format(obj.getDouble("tank_capacity")) + " m3", textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("11.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase(
					"RETAINED - " + df.format(obj.getDouble("quantity_retainin_destination_tank")) + " m3", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("11.4", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase(df.format(obj.getDouble("quantity_transferred"))
					+ " m3 COLLECTED FROM " + obj.getString("source_name"), textfont));
			cell8.setBorderWidthRight(0f);
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthBottom(0f);
			testTable.addCell(cell8);

			return testTable;

		}

		case "Disposal of Bilge From Bilge Tank to Sludge Tank": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("13", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("qty_transfered")))
					+ " m3 BILGE WATER FROM " + obj.getString("source_tank") + ", NOW "
					+ df.format(obj.getDouble("qty_retained_src")) + " m3", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("14", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("15.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("COLLECTED IN " + obj.getString("destination_tank")
					+ ", RETAINED IN TANK " + df.format(obj.getDouble("qty_retained_dest")) + " m3", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "Disposal Of Bilge Via Shore Connection D13": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("13", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("qty_transfered")))
					+ " m3 BILGE WATER FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("qty_retained")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("14", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("15.2", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("TO " + obj.getString("identify_sludge_receiver").toUpperCase()
					+ " DURING PORT STAY " + obj.getString("port"), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "Automatic Bilge Overboard Through Equipment D16,D18": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("16", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("PUMP START : " + df.format((obj.getDouble("latdeg"))) + " DEG "
					+ df.format((obj.getDouble("latmin"))) + " MIN " + obj.getString("latside") + ", "
					+ df.format((obj.getDouble("longdeg"))) + " DEG " + df.format((obj.getDouble("longmin"))) + " MIN "
					+ obj.getString("longside"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("FROM " + obj.getString("source_tank"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("18", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("PUMP STOP " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "ACCIDENTAL POLLUTION (G 22,23,24,25)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("22", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(
					CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " " + obj.getString("time"),
					textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("23", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase(obj.getString("place") + ", "
					+ df.format((obj.getDouble("latdeg"))) + " DEG " + df.format((obj.getDouble("latmin"))) + " MIN "
					+ obj.getString("latside") + ", " + df.format((obj.getDouble("longdeg"))) + " DEG "
					+ df.format((obj.getDouble("longmin"))) + " MIN " + obj.getString("longside"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("24", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("WASTE " + df.format(obj.getDouble("quantity_of_oil")) + " m3", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("25", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(
					new Phrase(obj.getString("circumtances_of_discharge").toUpperCase(), textfont));
			cell8.setBorderWidthRight(0f);
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthBottom(0f);
			testTable.addCell(cell8);

			return testTable;

		}

		case "FUEL OIL BUNKERING (H 26.1,26.2,26.3)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("26.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("port"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("26.2", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("26.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("total_quantity")))
					+ " MT OF ISO-" + obj.getString("grade_of_oil") + " HFO "
					+ df.format(obj.getDouble("percentage_sulphur")) + " % S BUNKERED IN TANK", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			JSONArray arr1 = obj.getJSONArray("tank_details");
			for (int i = 0; i < arr1.length() - 1; i++) {

				JSONObject obj2 = arr1.getJSONObject(i);
				PdfPCell cell7 = new PdfPCell(new Phrase("", textfont));
				cell7.setBorderWidthLeft(0f);
				cell7.setBorderWidthTop(0f);
				testTable.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Phrase(String.valueOf(df.format(obj2.getDouble("qty_added")))
						+ " MT ADDED TO " + obj2.getString("tank_name") + " NOW CONTAINING "
						+ df.format(obj2.getDouble("qty_retained")) + " MT", textfont));
				cell8.setBorderWidthRight(0f);
				cell8.setBorderWidthLeft(0f);
				cell8.setBorderWidthTop(0f);
				testTable.addCell(cell8);

			}

			JSONObject obj2 = arr1.getJSONObject(arr1.length() - 1);
			PdfPCell cell7 = new PdfPCell(new Phrase("", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase(String.valueOf(df.format(obj2.getDouble("qty_added")))
					+ " MT ADDED TO " + obj2.getString("tank_name") + " NOW CONTAINING "
					+ df.format(obj2.getDouble("qty_retained")) + " MT", textfont));
			cell8.setBorderWidthRight(0f);
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthBottom(0f);
			testTable.addCell(cell8);

			return testTable;

		}

		case "de_bunkering_of_fuel_oil": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(
					String.valueOf(df.format(obj.getDouble("total_quantity"))) + " MT OF ISO-"
							+ obj.getString("grade_of_oil").toUpperCase() + " HFO "
							+ df.format(obj.getDouble("percentage_sulphur")) + " % S DE-BUNKERED FROM TANKS",
					textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthLeft(0f);
			cell2.setBorderWidthTop(0f);
			testTable.addCell(cell2);

			JSONArray arr1 = obj.getJSONArray("tank_details");
			for (int i = 0; i < arr1.length(); i++) {

				JSONObject obj2 = arr1.getJSONObject(i);
				PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
				cell4.setBorderWidthLeft(0f);
				cell4.setBorderWidthTop(0f);
				testTable.addCell(cell4);

				PdfPCell cell5 = new PdfPCell(new Phrase(String.valueOf(df.format(obj2.getDouble("qty_added")))
						+ " MT REMOVE FROM " + obj2.getString("tank_name") + " NOW CONTAINING "
						+ df.format(obj2.getDouble("qty_retained")) + " MT", textfont));
				cell5.setBorderWidthRight(0f);
				cell5.setBorderWidthLeft(0f);
				cell5.setBorderWidthTop(0f);
				testTable.addCell(cell5);

			}

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase(
					"DE-BUNKERED TO I.E BARGE, TANK TRUCK OR SHORE FACILITY IN " + obj.getString("port").toUpperCase(),
					textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthRight(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "LUB OIL BUNKERING (H 26.1,26.2,26.4)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("26.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("port").toUpperCase(), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("26.2", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("26.4", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("total_quantity"))) + " MT "
					+ obj.getString("type_of_oil") + " BUNKERED IN TANKS", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			JSONArray arr1 = obj.getJSONArray("tank_details");
			for (int i = 0; i < arr1.length() - 1; i++) {

				JSONObject obj2 = arr1.getJSONObject(i);
				PdfPCell cell7 = new PdfPCell(new Phrase("", textfont));
				cell7.setBorderWidthLeft(0f);
				cell7.setBorderWidthTop(0f);
				testTable.addCell(cell7);

				PdfPCell cell8 = new PdfPCell(new Phrase(String.valueOf(df.format(obj2.getDouble("qty_added")))
						+ " MT ADDED TO " + obj2.getString("tank_name") + " NOW CONTAINING "
						+ df.format(obj2.getDouble("qty_retained")) + " MT", textfont));
				cell8.setBorderWidthRight(0f);
				cell8.setBorderWidthLeft(0f);
				cell8.setBorderWidthTop(0f);
				testTable.addCell(cell8);

			}

			JSONObject obj2 = arr1.getJSONObject(arr1.length() - 1);
			PdfPCell cell7 = new PdfPCell(new Phrase("", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase(String.valueOf(df.format(obj2.getDouble("qty_added")))
					+ " MT ADDED TO " + obj2.getString("tank_name") + " NOW CONTAINING "
					+ df.format(obj2.getDouble("qty_retained")) + " MT", textfont));
			cell8.setBorderWidthRight(0f);
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthBottom(0f);
			testTable.addCell(cell8);

			return testTable;

		}

		case "Retention of Bilge Tank (i)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("11.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("WEEKLY ENTRY OF BILGE WATER TANKS", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("11.2", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase(obj.getString("tank_name"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("11.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("CAPACITY : " + df.format(obj.getDouble("tank_capacity")) + " m3, "
					+ df.format(obj.getDouble("tank_qty")) + " m3 RETAINED", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}
		case "CARGO HOLD BILGE TANK TO BILGE TANK (i)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("qty_transfered")))
					+ " m3 OILY BILGE WATER FROM CARGO HOLD BILGE HOLDING TANK", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			cell3.setBorderWidthBottom(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("TO " + obj.getString("destination_tank"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			return testTable;

		}

		case "Engine Room Bilge Wells To Bilge Tank D13": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("13", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("quantity_transferred")))
					+ " m3 BILGE WATER FROM ENGINE ROOM BILGE WELLS", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("14", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("15.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("TO " + obj.getString("destination_tank") + ", RETAINED IN TANK "
					+ df.format(obj.getDouble("quantity_retainin_destination_tank")) + " m3", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "Engine Room Bilge Wells To Bilge Tank Automatic D17,D18": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("17", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(
					new Phrase("TRANSFER START " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date"))
							+ " " + obj.getString("start_time"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("TO " + obj.getString("destination_tank"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("18", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("STOP : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "FAILURE OF OIL FILTERING EQUIPMENT (F 19,20,21)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("19", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(
					new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("time_of_failure"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("20", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			if (obj.getString("is_equipment_functional").equalsIgnoreCase("yes")) {

				PdfPCell cell4 = new PdfPCell(
						new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
								+ obj.getString("time_of_operation"), textfont));
				cell4.setBorderWidthRight(0f);
				cell4.setBorderWidthLeft(0f);
				cell4.setBorderWidthTop(0f);
				testTable.addCell(cell4);

			} else {

				PdfPCell cell4 = new PdfPCell(new Phrase("NOT KNOWN", textfont));
				cell4.setBorderWidthRight(0f);
				cell4.setBorderWidthLeft(0f);
				cell4.setBorderWidthTop(0f);
				testTable.addCell(cell4);
			}

			PdfPCell cell5 = new PdfPCell(new Phrase("21", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase(obj.getString("reason_of_failure").toUpperCase(), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "RESTORATION OF FAULTY EQUIPMENT (F 19,20,21)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("19", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(
					new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("time_of_failure"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("20", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("date_of_operational")) + " "
							+ obj.getString("time_of_operational"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("21", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase(obj.getString("reason_of_failure").toUpperCase(), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "Transfer Between Bilge Tanks D13": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("13", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("quantity_transferred")))
					+ " m3 BILGE WATER FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("quantity_retainin_source_tank")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("14", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("15.3", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("TO " + obj.getString("destination_tank") + ", RETAINED IN TANK "
					+ df.format(obj.getDouble("quantity_retainin_destination_tank")) + " m3", textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "Pumping Of Bilge Water Overboard Through OWS From Tank": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.4", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("qty_discharged")))
					+ " m3 BILGE WATER FROM " + obj.getString("source_tank"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("CAPACITY : " + df.format(obj.getDouble("tank_capacity")) + ", "
					+ "RETAINED : " + df.format(obj.getDouble("qty_retained")) + "m3", textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("14", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("15.1", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase("THROUGH 15 PPM EQUIPMENT OVERBOARD", textfont));
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthRight(0f);
			testTable.addCell(cell8);

			PdfPCell cell9 = new PdfPCell(new Phrase("", textfont));
			cell9.setBorderWidthLeft(0f);
			cell9.setBorderWidthTop(0f);
			testTable.addCell(cell9);

			PdfPCell cell10 = new PdfPCell(new Phrase(
					"POSITION START : " + df.format((obj.getDouble("start_latdeg"))) + " DEG "
							+ df.format((obj.getDouble("start_latmin"))) + " MIN " + obj.getString("start_latside")
							+ ", " + df.format((obj.getDouble("start_longdeg"))) + " DEG "
							+ df.format((obj.getDouble("start_longmin"))) + " MIN " + obj.getString("start_longside"),
					textfont));
			cell10.setBorderWidthRight(0f);
			cell10.setBorderWidthLeft(0f);
			cell10.setBorderWidthTop(0f);
			testTable.addCell(cell10);

			PdfPCell cell11 = new PdfPCell(new Phrase("", textfont));
			cell11.setBorderWidthLeft(0f);
			cell11.setBorderWidthTop(0f);
			cell11.setBorderWidthBottom(0f);
			testTable.addCell(cell11);

			PdfPCell cell12 = new PdfPCell(new Phrase(
					"POSITION STOP : " + df.format((obj.getDouble("stop_latdeg"))) + " DEG "
							+ df.format((obj.getDouble("stop_latmin"))) + " MIN " + obj.getString("stop_latside") + ", "
							+ df.format((obj.getDouble("stop_longdeg"))) + " DEG "
							+ df.format((obj.getDouble("stop_longmin"))) + " MIN " + obj.getString("stop_longside"),
					textfont));
			cell12.setBorderWidthRight(0f);
			cell12.setBorderWidthLeft(0f);
			cell12.setBorderWidthTop(0f);
			cell12.setBorderWidthBottom(0f);
			testTable.addCell(cell12);

			return testTable;

		}

		case "Pumping Of Bilge Water Overboard Through OWS From BWell": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.4", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(
					String.valueOf(df.format(obj.getDouble("qty_discharged"))) + " m3 FROM BILGE WELLS", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell5 = new PdfPCell(new Phrase("14", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont));
			cell6.setBorderWidthRight(0f);
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("15.1", textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase("THROUGH 15 PPM EQUIPMENT OVERBOARD", textfont));
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthRight(0f);
			testTable.addCell(cell8);

			PdfPCell cell9 = new PdfPCell(new Phrase("", textfont));
			cell9.setBorderWidthLeft(0f);
			cell9.setBorderWidthTop(0f);
			testTable.addCell(cell9);

			PdfPCell cell10 = new PdfPCell(new Phrase(
					"POSITION START : " + df.format((obj.getDouble("start_latdeg"))) + " DEG "
							+ df.format((obj.getDouble("start_latmin"))) + " MIN " + obj.getString("start_latside")
							+ ", " + df.format((obj.getDouble("start_longdeg"))) + " DEG "
							+ df.format((obj.getDouble("start_longmin"))) + " MIN " + obj.getString("start_longside"),
					textfont));
			cell10.setBorderWidthRight(0f);
			cell10.setBorderWidthLeft(0f);
			cell10.setBorderWidthTop(0f);
			testTable.addCell(cell10);

			PdfPCell cell11 = new PdfPCell(new Phrase("", textfont));
			cell11.setBorderWidthLeft(0f);
			cell11.setBorderWidthTop(0f);
			cell11.setBorderWidthBottom(0f);
			testTable.addCell(cell11);

			PdfPCell cell12 = new PdfPCell(new Phrase(
					"POSITION STOP : " + df.format((obj.getDouble("stop_latdeg"))) + " DEG "
							+ df.format((obj.getDouble("stop_latmin"))) + " MIN " + obj.getString("stop_latside") + ", "
							+ df.format((obj.getDouble("stop_longdeg"))) + " DEG "
							+ df.format((obj.getDouble("stop_longmin"))) + " MIN " + obj.getString("stop_longside"),
					textfont));
			cell12.setBorderWidthRight(0f);
			cell12.setBorderWidthLeft(0f);
			cell12.setBorderWidthTop(0f);
			cell12.setBorderWidthBottom(0f);
			testTable.addCell(cell12);

			return testTable;

		}

		case "Transfer Between Sludge Tanks C_12.2": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.2", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(df.format(obj.getDouble("quantity_transferred"))
					+ " m3 SLUDGE TRANSFER FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("quantity_retainin_source_tank")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			cell3.setBorderWidthBottom(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase("TO " + obj.getString("destination_tank") + ",RETAINED IN TANK  "
					+ df.format(obj.getDouble("quantity_retainin_destination_tank")) + " m3", textfont));
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			return testTable;

		}

		case "ENTRY UNDER CODE (i)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("description").toUpperCase(), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "INVENTORY OF SLUDGE TANK (C 11.1,11.2,11.3)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("11.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("tank_name").toUpperCase(), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("11.2", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(
					new Phrase(String.valueOf(df.format(obj.getDouble("tank_capacity"))), textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("11.3", textfont));
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("tank_qty"))), textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			cell7.setBorderWidthRight(0f);
			testTable.addCell(cell7);

			return testTable;

		}

		case "Voluntry Recording-OWS overboard Line Inspection": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(obj.getString("findings").toUpperCase(), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Evaportaion of Water From Sludge c12_4_orb1": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.4", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(obj.getDouble("quantity_evaporated"))
					+ " m3 WATER EVAPORATED FROM " + obj.getString("source_tank") + ", "
					+ obj.getDouble("quantity_retainin_source_tank") + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Regeneration Of Fuel From Sludge C_12.4": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("11.1", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("quan_of_sludge_disposed")))
					+ " m3 SLUDGE DISPOSED BY REGENERATION OF  " + df.format(obj.getDouble("quantity_fuel_gnerated"))
					+ " m3 FUEL IN " + obj.getString("destination_fuel_tank") + " AND "
					+ df.format(obj.getDouble("quan_of_blidge_water_generated")) + " m3 OF WATER IN "
					+ obj.getString("destination_tank"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "VOLUNTARY RECORDING-CALIBRATION OF 15PPM UNIT": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("OWS 15PPM UNIT CALIBRATION DONE", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase(obj.getString("findings"), textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Transfer Sludge To Deck Cargo Slop Tank": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.4", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("qty_transfered")))
					+ " m3 SLUDGE FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("qty_retained")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(
					new Phrase("TRANSFERRED TO " + obj.getString("deck_slope_tk").toUpperCase(), textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Sealing Of Marpol Annex1 Related Value Or Equipment Entity": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("OVERBOARD VALVE " + obj.getString("valve_no").toUpperCase()
					+ " FROM 15 PPM BILGE WATER SEPERATOR UNIT SEALED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("SEAL NO : " + obj.getString("seal_number"), textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Breaking Of Marpol Annex1 Related Value Or Equipment Entity": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase("OVERBOARD VALVE " + obj.getString("valve_no").toUpperCase()
					+ " FROM 15 PPM BILGE WATER SEPERATOR UNIT SEALED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("FOR NORMAL OPERATION OF 15 PPM UNIT", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("", textfont));
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Phrase("SEAL NO : " + obj.getString("seal_number"), textfont));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthBottom(0f);
			cell7.setBorderWidthRight(0f);
			testTable.addCell(cell7);

			return testTable;

		}

		case "Transfer Bildge To Deck Cargo Slop Tank": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.4", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("qty_transfered")))
					+ " m3 BILGE WATER FROM " + obj.getString("source_tank"), textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("CAPACITY : " + df.format(obj.getDouble("tank_capacity")) + " m3, "
					+ df.format(obj.getDouble("qty_retained")) + " m3 RETAINED", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Phrase("14", textfont));
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			testTable.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new PdfPCell(
					new Phrase("START : " + CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("start_date")) + " "
							+ obj.getString("start_time") + ", " + "STOP : "
							+ CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")) + " "
							+ obj.getString("stop_time"), textfont)));
			cell7.setBorderWidthLeft(0f);
			cell7.setBorderWidthTop(0f);
			cell7.setBorderWidthRight(0f);
			testTable.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Phrase("15.3", textfont));
			cell8.setBorderWidthLeft(0f);
			cell8.setBorderWidthTop(0f);
			cell8.setBorderWidthBottom(0f);
			testTable.addCell(cell8);

			PdfPCell cell9 = new PdfPCell(
					new Phrase("TRANFERRED TO " + obj.getString("deck_slope_tk").toUpperCase(), textfont));
			cell9.setBorderWidthLeft(0f);
			cell9.setBorderWidthTop(0f);
			cell9.setBorderWidthBottom(0f);
			cell9.setBorderWidthRight(0f);
			testTable.addCell(cell9);

			return testTable;

		}

		case "Incineration of sludge C 12.3": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.3", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("quantity_incinerated")))
					+ " m3 SLUDGE INCINERATED FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("quantity_retainin_source_tank")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("BURNED IN INCINERATOR FOR " + obj.getInt("hours") + " HOURS AND "
					+ obj.getInt("minutes") + " MINUTES", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Draining Water From Sludge To Blidge tank": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.3", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("quantity_transferred")))
					+ " m3 WATER DRAINED FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("quantity_retainin_source_tank")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(
					new Phrase(
							"TO " + obj.getString("destination_tank") + ", " + " RETAINED IN TANK "
									+ df.format(obj.getDouble("quantity_retainin_destination_tank")) + " m3",
							textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "Sludge Burning In Boiler C_12.4": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			PdfPCell cell1 = new PdfPCell(new Phrase("12.4", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(df.format(obj.getDouble("quantity_burned")))
					+ " m3 SLUDGE INCINERATED FROM " + obj.getString("source_tank") + ", "
					+ df.format(obj.getDouble("quantity_retainin_source_tank")) + " m3 RETAINED", textfont));
			cell2.setBorderWidthRight(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthLeft(0f);
			testTable.addCell(cell2);

			PdfPCell cell4 = new PdfPCell(new Phrase("", textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthTop(0f);
			cell4.setBorderWidthBottom(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase(
					"BURNED IN BOILER FOR " + obj.getInt("hours") + " HOURS AND " + obj.getInt("minutes") + " MINUTES",
					textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			cell5.setBorderWidthRight(0f);
			testTable.addCell(cell5);

			return testTable;

		}

		case "NEWLY JOINED ENGINEER FAMILIARIZATION(i)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Phrase(
					"NEWLY JOINED " + obj.getString("user_name").toUpperCase() + "," + obj.getString("user_rank"),
					textfont));
			cell2.setBorderWidthLeft(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthRight(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			testTable.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Phrase(
					"FAMILIARIZED BY " + obj.getString("officer_fisrt_name") + " ," + obj.getString("officer_rank"),
					textfont));
			cell4.setBorderWidthLeft(0f);
			cell4.setBorderWidthRight(0f);
			cell4.setBorderWidthTop(0f);
			testTable.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Phrase("", textfont));
			cell5.setBorderWidthLeft(0f);
			cell5.setBorderWidthTop(0f);
			cell5.setBorderWidthBottom(0f);
			testTable.addCell(cell5);

			int ows_operation = obj.getInt("ows_operation");
			int fifteen_ppm_alarm = obj.getInt("fifteen_ppm_alarm");
			int three_way_valve_operation = obj.getInt("three_way_valve_operation");
			int pump_auto_stop = obj.getInt("pump_auto_stop");
			int incinerator_operation_alarm = obj.getInt("incinerator_operation_alarm");

			String cell6data = "";

			if (ows_operation == 1) {
				cell6data += "OWS OPERATION";
			}

			if (fifteen_ppm_alarm == 1) {
				if (cell6data.length() == 0)
					cell6data += "15 PPM ALARM";
				else
					cell6data += ",15 PPM ALARM";
			}

			if (three_way_valve_operation == 1) {
				if (cell6data.length() == 0)
					cell6data += "WAY VALVE OPETATION";
				else
					cell6data += ",WAY VALVE OPETATION";
			}

			if (pump_auto_stop == 1) {
				if (cell6data.length() == 0)
					cell6data += "PUMP AUTO STOP";
				else
					cell6data += ",PUMP AUTO STOP";
			}

			if (incinerator_operation_alarm == 1) {
				if (cell6data.length() == 0)
					cell6data += "INCINERATOR OPERATION AND ALARM TESTING";
				else
					cell6data += ",INCINERATOR OPERATION AND ALARM TESTING";
			}

			PdfPCell cell6 = new PdfPCell(new Phrase(cell6data, textfont));
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			cell6.setBorderWidthRight(0f);
			testTable.addCell(cell6);

			return testTable;

		}

		case "VOLUNTARY RECORDINGS (TESTING OF EQUIPMENT)": {

			testTable = new PdfPTable(2);
			float[] colwidth = { 1.0f, 7.0f };

			testTable.setWidths(colwidth);

			if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

				addMissedEntry(textfont, obj);

			}

			PdfPCell cell1 = new PdfPCell(new Phrase("", textfont));
			cell1.setBorderWidthLeft(0f);
			cell1.setBorderWidthTop(0f);
			testTable.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(
					new Phrase("TESTING OF FOLLOWING EQUIPMENTS DONE AND FOUND SATISFACTORY", textfont));
			cell2.setBorderWidthLeft(0f);
			cell2.setBorderWidthTop(0f);
			cell2.setBorderWidthRight(0f);
			testTable.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Phrase("", textfont));
			cell3.setBorderWidthLeft(0f);
			cell3.setBorderWidthTop(0f);
			cell3.setBorderWidthBottom(0f);
			testTable.addCell(cell3);

			int ows_operation = obj.getInt("ows_operation");
			int fifteen_ppm_alarm = obj.getInt("fifteen_ppm_alarm");
			int three_way_valve_operation = obj.getInt("three_way_valve_operation");
			int pump_auto_stop = obj.getInt("pump_auto_stop");

			String cell6data = "";

			if (ows_operation == 1) {
				cell6data += "OWS OPERATION";
			}

			if (fifteen_ppm_alarm == 1) {
				if (cell6data.length() == 0)
					cell6data += "15 PPM ALARM";
				else
					cell6data += ",15 PPM ALARM";
			}

			if (three_way_valve_operation == 1) {
				if (cell6data.length() == 0)
					cell6data += "WAY VALVE OPETATION";
				else
					cell6data += ",WAY VALVE OPETATION";
			}

			if (pump_auto_stop == 1) {
				if (cell6data.length() == 0)
					cell6data += "PUMP AUTO STOP";
				else
					cell6data += ",PUMP AUTO STOP";
			}

			PdfPCell cell6 = new PdfPCell(new Phrase(cell6data, textfont));
			cell6.setBorderWidthLeft(0f);
			cell6.setBorderWidthTop(0f);
			cell6.setBorderWidthBottom(0f);
			cell6.setBorderWidthRight(0f);

			testTable.addCell(cell6);

			return testTable;

		}
		}
		return null;
	}
}
