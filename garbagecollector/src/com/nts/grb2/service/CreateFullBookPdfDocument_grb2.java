package com.nts.grb2.service;

import java.io.FileOutputStream;
import java.util.Date;

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
import com.nts.grb.connection.dbConnection;
import com.nts.grb.query.FullookReport;
import com.nts.grb.query.HomeDataFullReport;
import com.nts.grb.service.ReportsStrtEndDate;
import com.nts.grb.service.StringToJsonConverter;
import com.nts.grb2.query.HomeDataFullReportById;

public class CreateFullBookPdfDocument_grb2 {
	static final String Ip = "http://192.168.1.101:3030";
	static final String PDF_FILE_PATH = "//abc//";
	static final String PDF_FILE_PATH_1 = "C://abc//";
	static final String PDF_FILE_NAME = "pdffile_grb2.pdf";
	static final JSONObject jsonob = new JSONObject();

//public static void main(String... args) throws Exception {
	public static JSONArray filegnrate() throws Exception {
		String[] dateformater = {};
		String dateOfPdf = "";

		// create document
		Document document = new Document(PageSize.A4, 36, 36, 140, 45);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_PATH_1 + "" + PDF_FILE_NAME));

		// add header and footer
		HeaderFooterForFullBooKEvent_GRB2 event = new HeaderFooterForFullBooKEvent_GRB2();
		writer.setPageEvent(event);

		// write to document
		document.open();

		// document.add(new Paragraph("Ship Name:M.V.NUEVO TECHSOLUTUIONS"));
		PdfPTable tble = new PdfPTable(6);
		tble.setWidthPercentage(100);
		tble.setSpacingBefore(50f);
		tble.setSpacingAfter(20f);

		// here define the number of columns
		float[] colwidth = { 2.5f, 4.0f, 2.5f, 1.5f, 1.5f, 4f };
		tble.setWidths(colwidth);

		Date date = new Date();
		final int MONTH = -30;
		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);

		String[] strtEndDtCombo = crntEnddt.split("and", 2);

		String all_data_dtls = HomeDataFullReportById
				.entry_id(strtEndDtCombo[0], strtEndDtCombo[1], dbConnection.getConnection()).toString();

		JSONArray cnvrtd_dta_dtls1 = StringToJsonConverter.convrt(all_data_dtls);
		JSONArray cnvrtd_dta_dtls = new JSONArray();

		
		for (int i = cnvrtd_dta_dtls1.length() - 1; i >= 0; i--) {
			cnvrtd_dta_dtls.put(cnvrtd_dta_dtls1.get(i));
		}

		Font font = new Font(FontFamily.HELVETICA, 12f, Font.STRIKETHRU, BaseColor.BLACK);

		for (int i = 0; i <= cnvrtd_dta_dtls.length() - 1; i++) {

			JSONObject jsonObject1 = cnvrtd_dta_dtls.getJSONObject(i);
			dateformater = jsonObject1.optString("entry_date").split("-");
			dateOfPdf = dateformater[2] + "-" + dateformater[1] + "-" + dateformater[0];

			if (jsonObject1.optInt("strike_value") == 1) {

				tble.addCell(new Phrase(jsonObject1.optString("enrtrydate_month") + "\n" + jsonObject1.optString("entry_time") , font));// 1 st cell

				tble.addCell(new Phrase(jsonObject1.optString("lat_deg") + " DEG " + jsonObject1.optString("lat_min")
						+ " MIN " + jsonObject1.optString("left_side") + "\n" + jsonObject1.optString("long_deg")
						+ " DEG " + jsonObject1.optString("long_min") + " MIN " + jsonObject1.optString("long_side")
						+ "\n" + jsonObject1.optString("portname") + "\n" + jsonObject1.optString("entry_remark"),
						font));// 2nd cells

				tble.addCell(new Phrase(jsonObject1.optString("garbage_category"), font));
				tble.addCell(new Phrase(jsonObject1.optString("garbage_quantity_sea"), font));

				tble.addCell(new Phrase(jsonObject1.optString("garbage_quantity_ashore"), font));
				// tble.addCell(new Phrase(jsonObject1.optString("masterName"), font));

				tble.addCell(new Phrase(jsonObject1.optString("officer_fisrt_name").toUpperCase() + ","
						+ jsonObject1.optString("officer_rank"), font));

			}

			else {

				tble.addCell(jsonObject1.optString("enrtrydate_month") + "\n" + jsonObject1.optString("entry_time") );// 1 st cell

				tble.addCell(jsonObject1.optString("lat_deg") + " DEG " + jsonObject1.optString("lat_min") + " MIN "
						+ jsonObject1.optString("lat_side") + "\n" + jsonObject1.optString("long_deg") + " DEG "
						+ jsonObject1.optString("long_min") + " MIN " + jsonObject1.optString("long_side") + "\n"
						+ jsonObject1.optString("portname") + "\n"
						+ jsonObject1.optString("entry_remark").toUpperCase());

				tble.addCell(jsonObject1.optString("garbage_category").toUpperCase());
				tble.addCell(jsonObject1.optString("garbage_quantity_sea"));

				tble.addCell(jsonObject1.optString("garbage_quantity_ashore"));

				// tble.addCell(jsonObject1.optString("master_name"));
				tble.addCell(new Phrase(jsonObject1.optString("officer_fisrt_name").toUpperCase() + ","
						+ jsonObject1.optString("officer_rank")));
			}
		}
		document.add(tble);
		document.close();

		jsonob.put("filename", Ip + PDF_FILE_PATH + PDF_FILE_NAME);
		return new JSONArray().put(jsonob);

	}
}