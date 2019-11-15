package com.nts.orb1.service;

import java.io.FileOutputStream;
import java.util.Date;

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
import com.nts.grb.connection.dbConnection;
import com.nts.grb.service.ReportsStrtEndDate;
import com.nts.orb1.query.HomeDataFullReportById_orb1;

public class Orb1HomePdf {
	static final String Ip = "http://192.168.1.101:2424";
	static final String PDF_FILE_PATH = "//abc//";
	static final String PDF_FILE_PATH_1 = "C://abc//";
	static final String PDF_FILE_NAME = "orb1_home.pdf";
	static final JSONObject jsonob = new JSONObject();

	public static void orb1FileGnrt() throws Exception {


		// create document
		Document document = new Document(PageSize.A4.rotate(), 20, 20, 20, 40);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FILE_PATH_1 + "" + PDF_FILE_NAME));

		document.open();

		BaseFont textBaseFont = BaseFont.createFont(

				BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.EMBEDDED);

		BaseFont headBaseFont = BaseFont.createFont(

				BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED);

		HeaderFooterPageEventDashboardOrb1 event = new HeaderFooterPageEventDashboardOrb1();
		writer.setPageEvent(event);

		Paragraph preface = new Paragraph();
		Font headfont = new Font(headBaseFont, 12, Font.NORMAL);
		preface.add(new Paragraph("Name Of Ship : MSC ANAYA                                    "
				+ "        Distinctive Number Or Letter : 101", headfont));
		document.add(preface);

		Font textfont = new Font(textBaseFont, 9, Font.NORMAL);
		Font strikefont = new Font(textBaseFont, 9, Font.STRIKETHRU);

		PdfPTable table = OrbHomeHeader.addHeader(document);

		Date date = new Date();
		final int MONTH = -30;
		String crntEnddt = ReportsStrtEndDate.getPastDate(date, MONTH);
		String[] strtEndDtCombo = crntEnddt.split("and", 2);

		JSONArray homeData = HomeDataFullReportById_orb1.entry_id(crntEnddt, strtEndDtCombo[1],
				dbConnection.getConnection());

		for (int i = 0; i < homeData.length(); i++) {

			Font font = new Font(textBaseFont, 9, Font.NORMAL);
			try {

				JSONObject obj = homeData.getJSONObject(i);

				if (obj.getInt("strike_value") == 1) {

					font = strikefont;

				} else {

					font = textfont;
				}

				try {

					PdfPCell entry_id = new PdfPCell(new Phrase(obj.getString("entry_id"), textfont));
					entry_id.setHorizontalAlignment(Element.ALIGN_CENTER);
					entry_id.setVerticalAlignment(Element.ALIGN_MIDDLE);
					entry_id.setFixedHeight(24);
					table.addCell(entry_id);

				} catch (Exception e) {
					table.addCell("");
				}

				// In Case Missed Entry Not Found In JSONObject
				try {

					if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

						PdfPTable table2 = new PdfPTable(1);
						float[] colwidth = { 2.2f };
						table2.setWidths(colwidth);

						PdfPCell dateHeader = new PdfPCell(
								new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")), font));
						dateHeader.setBorderWidthLeft(0f);
						dateHeader.setBorderWidthTop(0f);
						dateHeader.setBorderWidthBottom(0f);
						dateHeader.setBorderWidthRight(0f);
						table2.addCell(dateHeader);

						PdfPCell dateHeader3 = new PdfPCell(new Phrase("", font));
						dateHeader3.setFixedHeight(24);
						dateHeader3.setBorderWidthLeft(0f);
						dateHeader3.setBorderWidthTop(0f);
						dateHeader3.setBorderWidthBottom(0f);
						dateHeader3.setBorderWidthRight(0f);
						table2.addCell(dateHeader3);

						PdfPCell dateHeader2 = new PdfPCell(new Phrase(
								CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("missed_date")), font));
						dateHeader2.setBorderWidthLeft(0f);
						dateHeader2.setBorderWidthTop(0f);
						dateHeader2.setBorderWidthBottom(0f);
						dateHeader2.setBorderWidthRight(0f);
						table2.addCell(dateHeader2);

						table.addCell(table2);

					} else {

						PdfPCell dateHeader = new PdfPCell(
								new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")), font));
						dateHeader.setFixedHeight(24);
						table.addCell(dateHeader);

					}

				} catch (Exception e) {

					PdfPCell dateHeader = new PdfPCell(
							new Phrase(CommonOtherServices.getDD_MMM_YYYYFormat(obj.getString("stop_date")), font));
					dateHeader.setFixedHeight(24);
					table.addCell(dateHeader);
				}

				try {

					// In Case Missed Entry Not Found In JSONObject
					try {

						if (obj.getString("missed_entry").equalsIgnoreCase("yes")) {

							PdfPTable table2 = new PdfPTable(1);
							float[] colwidth = { 2.2f };
							table2.setWidths(colwidth);

							PdfPCell cell1 = new PdfPCell(new Phrase("I", font));
							cell1.setBorderWidthLeft(0f);
							cell1.setBorderWidthTop(0f);
							cell1.setBorderWidthBottom(0f);
							cell1.setBorderWidthRight(0f);
							cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
							table2.addCell(cell1);

							PdfPCell cell2 = new PdfPCell(new Phrase("", font));
							cell2.setBorderWidthLeft(0f);
							cell2.setBorderWidthTop(0f);
							cell2.setBorderWidthBottom(0f);
							cell2.setBorderWidthRight(0f);
							table2.addCell(cell2);

							PdfPCell cell3 = new PdfPCell(new Phrase(obj.getString("entry_code"), font));
							cell3.setBorderWidthLeft(0f);
							cell3.setBorderWidthTop(0f);
							cell3.setBorderWidthBottom(0f);
							cell3.setBorderWidthRight(0f);
							cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
							cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

							table2.addCell(cell3);

							table.addCell(table2);

						} else {

							PdfPCell entry_code = new PdfPCell(new Phrase(obj.getString("entry_code"), font));
							entry_code.setHorizontalAlignment(Element.ALIGN_CENTER);
							entry_code.setVerticalAlignment(Element.ALIGN_MIDDLE);
							entry_code.setFixedHeight(24);
							table.addCell(entry_code);

						}

					} catch (Exception e) {

						PdfPCell entry_code = new PdfPCell(new Phrase(obj.getString("entry_code"), font));
						entry_code.setHorizontalAlignment(Element.ALIGN_CENTER);
						entry_code.setVerticalAlignment(Element.ALIGN_MIDDLE);
						entry_code.setFixedHeight(24);
						table.addCell(entry_code);
					}

				} catch (Exception e) {
					table.addCell("");
				}

				try {
					table.addCell(TableCellEntryData.getCellData(obj, obj.getString("entry_type"), font));
				} catch (Exception e) {
//					e.printStackTrace();
					table.addCell("");
				}

				table.addCell("");

				try {

					String datetime[] = obj.getString("entry_timestamp").split(" ");

					table.addCell(new Phrase(
							obj.getString("officer_fisrt_name").toUpperCase() + " " + obj.getString("officer_rank")
									+ "       " + CommonOtherServices.getDD_MMM_YYYYFormat(datetime[0]) + "      "
									+ CommonOtherServices.getTimeOnly(obj.getString("entry_timestamp")),
							font));
				} catch (Exception e) {
//					e.printStackTrace();
					table.addCell("");
					System.out.println("Entry id -" + i);

				}

				try {
					String masterAprove = obj.getString("master_approval");

					if (masterAprove.equalsIgnoreCase("pending")) {
						table.addCell(new Phrase("", font));

					} else {

						String datetime[] = obj.getString("master_time").split(" ");

						if (obj.getInt("strike_value") == 1 && obj.getInt("strike_value") == 1) {

							int x = GetTimeDifference.compareStrikeTime(obj.getString("strike_time"),
									obj.getString("master_time"));

							if (x >= 0) {

								table.addCell(new Phrase(obj.getString("master_name").toUpperCase() + " "
										+ obj.getString("master_rank") + "       "
										+ CommonOtherServices.getDDMMMYYYYFormat(datetime[0]) + "      "
										+ CommonOtherServices.getTimeOnly(obj.getString("master_time")), font));

							} else {

								table.addCell(new Phrase(
										obj.getString("master_name").toUpperCase() + " " + obj.getString("master_rank")
												+ "       " + CommonOtherServices.getDDMMMYYYYFormat(datetime[0])
												+ "      "
												+ CommonOtherServices.getTimeOnly(obj.getString("master_time")),
										textfont));
							}

						} else {
							table.addCell(new Phrase(
									obj.getString("master_name").toUpperCase() + " " + obj.getString("master_rank")
											+ "       " + CommonOtherServices.getDDMMMYYYYFormat(datetime[0]) + "      "
											+ CommonOtherServices.getTimeOnly(obj.getString("master_time")),
									textfont));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					table.addCell("");
				}

				int strikeValue = obj.getInt("strike_value");
				if (strikeValue == 0) {
					table.addCell(new Phrase("", textfont));
					table.addCell("");

				} else {

					String datetime[] = obj.getString("strike_time").split(" ");

					table.addCell(new Phrase(obj.getString("strike_name") + " " + obj.getString("striker_rank")
							+ "       " + CommonOtherServices.getDDMMMYYYYFormat(datetime[0]) + "      "
							+ CommonOtherServices.getTimeOnly(obj.getString("strike_time")), textfont));

					table.addCell(new Phrase(obj.getString("strike_comment"), textfont));
				}

				try {
					String masterReAprove = obj.getString("master_reapproval");
					if (masterReAprove.equalsIgnoreCase("pending")) {
						table.addCell("");
					} else {
						String datetime[] = obj.getString("master_retime").split(" ");

						table.addCell(new Phrase(obj.getString("master_rename") + " " + obj.getString("master_rerank")
								+ "       " + CommonOtherServices.getDDMMMYYYYFormat(datetime[0]) + "      "
								+ CommonOtherServices.getTimeOnly(obj.getString("master_retime")), textfont));
					}
				} catch (Exception e) {
//					e.printStackTrace();
					table.addCell("");
				}

			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("Entry id -" + i);
			}

		}

		table.setHeaderRows(1);

		document.add(table);
		document.newPage();

		document.close();

	}

	public static void main(String[] args) throws Exception {
		Orb1HomePdf.orb1FileGnrt();
	}
}