package com.nts.orb1.service;

import java.io.FileOutputStream;
import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SludgeReportPdf {
	static final String Ip = "http://192.168.1.101:2424";
	static final String PDF_FILE_PATH = "//abc//";
	static final String PDF_FILE_PATH_1 = "C://abc//";
	static final String PDF_FILE_NAME = "orb1_sludge.pdf";
	static final JSONObject jsonob = new JSONObject();

	// public static void main(String... args) throws Exception {
	public static void orb1FileGnrt() throws Exception {

		int count = 0;
		int firstSlot = 7, secondSlot = 9;

//		String[] dateformater = {};
//		String dateOfPdf = "";

		// create document
		Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 0);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_PATH_1 + "" + PDF_FILE_NAME));

		HeaderFooterForSludgeEvent_orb1 event = new HeaderFooterForSludgeEvent_orb1();

		writer.setPageEvent(event);

		document.open();

		BaseFont headBaseFont = BaseFont.createFont(

				BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED);
		BaseFont textBaseFont = BaseFont.createFont(

				BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);

		Font headfont = new Font(headBaseFont, 12, Font.NORMAL);
		Font textfont = new Font(textBaseFont, 9, Font.NORMAL);

		JSONArray j1 = SludgeReportData.getReportData("2019-10-01", "2019-10-18");

		for (int c = 0; c <= j1.length() - 1; c++) {

			JSONArray slot = j1.getJSONArray(c);
			JSONObject obj = slot.getJSONObject(0);

			Paragraph preface = new Paragraph();
			Font hefont = new Font(headBaseFont, 12, Font.NORMAL);
			preface.add(new Paragraph("Name Of Ship : MSC ANAYA", hefont));
			preface.add(new Paragraph("Distinctive Number Or Letter : 101                                             "
					+ "              SLUDGE REPORT", hefont));

			document.add(preface);

			PdfPTable tble = new PdfPTable(10);
			tble.setWidthPercentage(100);
			tble.setSpacingBefore(10f);
			tble.setSpacingAfter(10f);

			// here define the number of columns
			float[] colwidth = { 6.5f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f };

			tble.setWidths(colwidth);

			PdfPCell tnkNameHeader = new PdfPCell(new Phrase("Tank Name", headfont));
			tnkNameHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			tnkNameHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			tnkNameHeader.setFixedHeight(24);
			tble.addCell(tnkNameHeader);

			if (count == 0) {

				PdfPCell totalHeader = new PdfPCell(new Phrase("Total", headfont));
				totalHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				totalHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
				totalHeader.setFixedHeight(24);
				tble.addCell(totalHeader);

				PdfPCell averageHeader = new PdfPCell(new Phrase("Average", headfont));
				averageHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				averageHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
				averageHeader.setFixedHeight(24);
				tble.addCell(averageHeader);

			}

			for (int i = 1; i <= obj.length(); i++) {

				PdfPCell dateHeader = new PdfPCell(
						new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("date_" + i)), headfont));
				dateHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				dateHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
				dateHeader.setFixedHeight(24);
				tble.addCell(dateHeader);

				if (count != 0) {
					if (obj.length() < secondSlot) {

						if (i == obj.length()) {

							int rem = secondSlot - obj.length();
							for (int j = 0; j < rem; j++) {
								tble.addCell("");
							}

						}

					}

				}
			}

			DecimalFormat df = new DecimalFormat("#.##");

			int slotCount = 0;
			if (count == 0)
				slotCount = firstSlot;
			else
				slotCount = secondSlot;
			for (int i = 1; i <= slot.length() - 1; i++) {

				obj = slot.getJSONObject(i);

				PdfPCell tankName = new PdfPCell(new Phrase(obj.getString("tank_name"), textfont));
				tankName.setVerticalAlignment(Element.ALIGN_MIDDLE);
				tankName.setFixedHeight(24);
				tble.addCell(tankName);

				if (count == 0) {

					try {

						String totalQuan = String.valueOf(df.format(obj.getDouble("total")));
						PdfPCell total = new PdfPCell(new Phrase(totalQuan, headfont));
						total.setHorizontalAlignment(Element.ALIGN_CENTER);
						total.setVerticalAlignment(Element.ALIGN_MIDDLE);
						total.setFixedHeight(24);
						tble.addCell(total);

					} catch (Exception e) {
						// e.printStackTrace();
						tble.addCell("");
					}

					try {
						String avgQuan = String.valueOf(df.format(obj.getDouble("average")));
						PdfPCell average = new PdfPCell(new Phrase(avgQuan, textfont));
						average.setHorizontalAlignment(Element.ALIGN_CENTER);
						average.setVerticalAlignment(Element.ALIGN_MIDDLE);
						average.setFixedHeight(24);
						tble.addCell(average);

					} catch (Exception e) {
						// e.printStackTrace();
						tble.addCell("");
					}

				}

				for (int j = 1; j <= slotCount; j++) {

					PdfPCell cellData = null;
					try {
						cellData = new PdfPCell(
								new Phrase(String.valueOf(df.format(obj.getDouble("date_" + j))), textfont));
						cellData.setHorizontalAlignment(Element.ALIGN_CENTER);
						cellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cellData.setFixedHeight(24);
						tble.addCell(cellData);

					} catch (Exception e) {
						// e.printStackTrace();
						cellData = new PdfPCell(new Phrase("", textfont));
						tble.addCell(cellData);
					}

				}

			}

			document.add(tble);
			document.newPage();
			count = 1;
		}
		document.close();

	}

	public static void main(String[] args) throws Exception {
		SludgeReportPdf.orb1FileGnrt();
	}
}