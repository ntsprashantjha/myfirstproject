package com.nts.grb.service;

import java.io.FileOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nts.grb.query.FullookReport;
import com.nts.grb.query.HomeDataFullReport;

public class CreateFullBookPdfDocument {
	static final String Ip = "http://192.168.1.101:3030";
	static final String PDF_FILE_PATH = "//abc//";
	static final String PDF_FILE_PATH_1 = "C://abc//";
	static final String PDF_FILE_NAME = "pdffile212.pdf";
	static final JSONObject jsonob = new JSONObject();
	static final String[] dateformater = {};
	private static String dateOfPdf;

	// public static void main(String... args) throws Exception {
	public static JSONArray filegnrate() throws Exception {

		// create document
		Document document = new Document(PageSize.A4, 36, 36, 140, 45);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_PATH_1 + "" + PDF_FILE_NAME));

		// add header and footer
		HeaderFooterForFullBooKEvent event = new HeaderFooterForFullBooKEvent();
		writer.setPageEvent(event);

		// write to document
		document.open();

		// document.add(new Paragraph("Ship Name:M.V.NUEVO TECHSOLUTUIONS"));
		PdfPTable tble = new PdfPTable(7);
		tble.setWidthPercentage(100);
		tble.setSpacingBefore(50f);
		tble.setSpacingAfter(20f);

		// here define the number of columns
		float[] colwidth = { 2.5f, 4.0f, 2f, 1.5f, 1.5f, 3f, 3.5f };
		tble.setWidths(colwidth);
		/*
		 * PdfPCell c1 = new PdfPCell(new Paragraph("Date/Time" + "\n" + "LT" + "\n" +
		 * "(TimeZone)")); PdfPCell c2 = new PdfPCell(new Paragraph(
		 * "Position of the ship (latitude and longitude), Port or facility. Remarks e.g. accidental loss and reason"
		 * )); PdfPCell c3 = new PdfPCell(new Paragraph("3.Garbage Category")); PdfPCell
		 * c4 = new PdfPCell( new Phrase("Estimated amount discharged  (m3)" + "\n" +
		 * " " + "\n" + "\n" + "Sea       Ashore")); c4.setColspan(2); PdfPCell c5 = new
		 * PdfPCell(new Paragraph("Incinerated amount (m3) and remarks")); PdfPCell c6 =
		 * new PdfPCell(new Paragraph("Signature officer in charge"));
		 * 
		 * // here adding cells tble.addCell(c1); tble.addCell(c2); tble.addCell(c3);
		 * tble.addCell(c4); tble.addCell(c5); tble.addCell(c6);
		 */
		String all_data_dtls = FullookReport.getHomeRprtDtls();
		// String as = HomeDataFullReport.getHomeRprtDtls(strtdate, endDate);

		JSONArray cnvrtd_dta_dtls1 = StringToJsonConverter.convrt(all_data_dtls);
		JSONArray cnvrtd_dta_dtls = new JSONArray();

		for (int i=0;i <= cnvrtd_dta_dtls1.length() - 1; i++) {
			cnvrtd_dta_dtls.put(cnvrtd_dta_dtls1.get(i));
		}
		Font font = new Font(FontFamily.HELVETICA, 12f, Font.STRIKETHRU, BaseColor.BLACK);

		for (int i = 0; i <= cnvrtd_dta_dtls.length() - 1; i++) {

			JSONObject jsonObject1 = cnvrtd_dta_dtls.getJSONObject(i);
			// condtion check is entry striked or not striked (if for strike or esle not
			// strike)
			if (jsonObject1.optInt("strike_value") == 1) {

				tble.addCell(new Phrase(
						jsonObject1.optString("enrtrydate_month") + "\n" + jsonObject1.optString("entry_time"), font));// 1
																														// st
																														// cell

				tble.addCell(new Phrase(jsonObject1.optString("lat_deg") + " DEG " + jsonObject1.optString("lat_min")
						+ " MIN " + jsonObject1.optString("left_side") + "\n" + jsonObject1.optString("long_deg")
						+ " DEG " + jsonObject1.optString("long_min") + " MIN " + jsonObject1.optString("long_side")
						+ "\n" + jsonObject1.optString("portname") + "\n"
						+ jsonObject1.optString("entry_remark").toUpperCase(), font));// 2nd cells

				tble.addCell(new Phrase(jsonObject1.optString("garbage_category").toUpperCase(), font));
				tble.addCell(new Phrase(jsonObject1.optString("garbage_quantity_sea"), font));

				tble.addCell(new Phrase(jsonObject1.optString("garbage_quantity_ashore"), font));
				tble.addCell(new Phrase(jsonObject1.optString("entry_remark").toUpperCase(), font));
				// tble.addCell(new Phrase(jsonObject1.optString("masterName"), font));

				tble.addCell(new Phrase(jsonObject1.optString("master_name").toUpperCase() + ","
						+ jsonObject1.optString("officer_rank").toUpperCase(), font));

			}

			else {

				tble.addCell(jsonObject1.optString("enrtrydate_month") + "\n" + jsonObject1.optString("entry_time"));// 1
																														// st
																														// cell

				tble.addCell(jsonObject1.optString("lat_deg") + " DEG " + jsonObject1.optString("lat_min") + " MIN "
						+ jsonObject1.optString("left_side") + "\n" + jsonObject1.optString("long_deg") + " DEG "
						+ jsonObject1.optString("long_min") + " MIN " + jsonObject1.optString("long_side") + "\n"
						+ jsonObject1.optString("portname") + "\n"
						+ jsonObject1.optString("entry_remark").toUpperCase());

				tble.addCell(jsonObject1.optString("garbage_category").toUpperCase());
				tble.addCell(jsonObject1.optString("garbage_quantity_sea").toUpperCase());

				tble.addCell(jsonObject1.optString("garbage_quantity_ashore"));

				tble.addCell(jsonObject1.optString("entry_remark").toUpperCase());
				tble.addCell(jsonObject1.optString("master_name").toUpperCase());
			}
		}
		document.add(tble);
		// document.newPage();
		// document.add(new Paragraph("********last page********."));
		document.close();
		jsonob.put("filename", Ip + PDF_FILE_PATH + PDF_FILE_NAME);

		return new JSONArray().put(jsonob);

	}
}