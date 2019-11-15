package com.nts.grb.service;

import java.io.FileOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nts.grb.query.HomeDataFullReport;

public class FullPdfReportDetails {
	static String pdf_Filepath = "C:\\abc\\";
	static String pdf_FileName = "pdffile.pdf";
	static JSONObject jsonob = new JSONObject();
	static String[] dateformater = {};
	private static String dateOfPdf;

	public static void main(String... args) throws Exception {
		// public static JSONArray filegnrate(String strtdate, String endDate) throws
		// Exception {
		// create document
		Document document = new Document(PageSize.A4.rotate(), 36,36,80,36);
		
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_Filepath + "" + pdf_FileName));
		// add header and footer
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent(event);
		// write to document
		document.open();
		document.add(new Paragraph(
				"Garbage categories:A-Plastic;B-Food waste;C-Domestic wastes(e.g. paper products,rags,glass,metal,bottles,crockery etc);D-Cooking oil;E-Incineratot ashes;F-Operational wastes;G-Animal carcasses;H-Fishing gear;I-E-Waste"
						+ "\n"
						+ "Note 1: For incineration include start and stop times and ship's position in column 1 and 2"));
		PdfPTable tble = new PdfPTable(12);
		tble.setWidthPercentage(112);
		tble.setSpacingBefore(20f);
		tble.setSpacingAfter(20f);
		// here define the number of columns
		float[] colwidth = { 2.2f, 4.2f, 2f, 1.5f, 1.5f, 3f, 3.5f, 2f, 2f, 2f, 2f, 2f };
		tble.setWidths(colwidth);
		PdfPCell c1 = new PdfPCell(new Paragraph("1.Date/Time"));
		PdfPCell c2 = new PdfPCell(new Paragraph(
				"2.Position of the ship(latitude and longitude),port or facility. Remarks e.g. accidental loss and reason"));
		PdfPCell c3 = new PdfPCell(new Paragraph("3.Garbege Category"));
		c3.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell c4 = new PdfPCell(
				new Phrase("4.Estimated amount discharged  (m3)" + "\n" + "\n" + "\n" + "Sea          Ashore"));
		c4.setColspan(2);
		PdfPCell c5 = new PdfPCell(new Paragraph("5.Incinerated amount(m3) and remarks"));
		c5.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell c6 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		PdfPCell c7 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		PdfPCell c8 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		PdfPCell c9 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		PdfPCell c10 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		PdfPCell c11 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		PdfPCell c12 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		// here adding cells
		tble.addCell(c1);
		tble.addCell(c2);
		tble.addCell(c3);
		tble.addCell(c4);
		tble.addCell(c5);
		tble.addCell(c6);
		tble.addCell(c7);
		tble.addCell(c8);
		tble.addCell(c9);
		tble.addCell(c10);
		tble.addCell(c11);
		tble.addCell(c12);
		String as = HomeDataFullReport.getHomeRprtDtls("2019-05-20", "2019-04-20");
//String as = HomeDataFullReport.getHomeRprtDtls(strtdate, endDate);
		JSONArray ab = StringToJsonConverter.convrt(as);
		Font font = new Font(FontFamily.HELVETICA, 12f, Font.STRIKETHRU, BaseColor.BLACK);
		for (int i = 0; i <= ab.length() - 1; i++) {
			JSONObject jsonObject1 = ab.getJSONObject(i);
			// changing date formate YYMMDD to DDMMYY
			dateformater = jsonObject1.optString("entry_date").split("-");
			dateOfPdf = dateformater[2] + "-" + dateformater[1] + "-" + dateformater[0];
			System.out.println("***modifying date ymd to dmy*****" + dateOfPdf);
			// condtion check is entry striked or not striked (if for strike or esle not
			// strike)
			if (jsonObject1.optInt("strike_value") == 1) {
				tble.addCell(new Phrase(dateOfPdf + "\n" + jsonObject1.optString("entry_time") + "\n"
						+ jsonObject1.optString("entry_timezone"), font));// 1 st cell
				tble.addCell(new Phrase(jsonObject1.optString("lat_deg") + " DEG " + jsonObject1.optString("lat_min")
						+ " MIN " + jsonObject1.optString("left_side") + "\n" + jsonObject1.optString("long_deg")
						+ " DEG " + jsonObject1.optString("long_min") + " MIN " + jsonObject1.optString("long_side"),
						font));// 2nd cells
				tble.addCell(new Phrase(jsonObject1.optString("garbage_category").substring(0, 1), font));
				tble.addCell(new Phrase(jsonObject1.optString("garbage_quantity_sea"), font));
				tble.addCell(new Phrase(jsonObject1.optString("garbage_quantity_ashore"), font));
				tble.addCell(new Phrase(jsonObject1.optString("entry_remark"), font));
				// tble.addCell(new Phrase(jsonObject1.optString("masterName"), font));

				tble.addCell(new Phrase(
						jsonObject1.optString("master_name") + "," + jsonObject1.optString("officer_rank") + "\n"
								+ jsonObject1.optString("strike_name") + "," + jsonObject1.optString("striker_rank"),
						font));

			} else {
				tble.addCell(dateOfPdf + "\n" + jsonObject1.optString("entry_time") + "\n"
						+ jsonObject1.optString("entry_timezone"));// 1 st cell
				tble.addCell(jsonObject1.optString("lat_deg") + " DEG " + jsonObject1.optString("lat_min") + " MIN "
						+ jsonObject1.optString("left_side") + "\n" + jsonObject1.optString("long_deg") + " DEG "
						+ jsonObject1.optString("long_min") + " MIN " + jsonObject1.optString("long_side"));
				tble.addCell(jsonObject1.optString("garbage_category").substring(0, 1));
				tble.addCell(jsonObject1.optString("garbage_quantity_sea"));
				tble.addCell(jsonObject1.optString("garbage_quantity_ashore"));
				tble.addCell(jsonObject1.optString("entry_remark"));
				tble.addCell(jsonObject1.optString("master_name"));
			}
		}
		document.add(tble);
		// document.newPage();
		// document.add(new Paragraph("********last page********."));
		document.close();
		jsonob.put("filename", pdf_FileName);
		// JSONArray sj= new JSONArray();
		// return new JSONArray().put(jsonob);

	}

}
