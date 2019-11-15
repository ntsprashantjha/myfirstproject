package com.nts.orb1.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterForFullBooKEvent_orb2 extends PdfPageEventHelper {

	private PdfTemplate t;
	private Image total;

	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(30, 16);
		try {
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			addHeader(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		addFooter(writer);
	}

	private void addHeader(PdfWriter writer) throws MalformedURLException, IOException {
		PdfPTable headerTable = new PdfPTable(6);
		try {
			// set defaults
		
			headerTable.setTotalWidth(525);
     		headerTable.setWidthPercentage(100);
			headerTable.setSpacingBefore(20f);
			headerTable.setSpacingAfter(60f);
			// here define the number of columns
			float[] colwidth = { 2.5f, 4.0f, 2.5f, 1.5f, 1.5f,4f };
			headerTable.setWidths(colwidth);
		
			PdfPCell c1 = new PdfPCell(new Paragraph("Date/Time" + "\n" + "LT" + "\n" + "(TimeZone)"));
			PdfPCell c2 = new PdfPCell(new Paragraph(
					"Position of the ship (latitude and longitude), Port or facility. Remarks e.g. accidental loss and reason"));
			PdfPCell c3 = new PdfPCell(new Paragraph("3.Garbage Category"));
			PdfPCell c4 = new PdfPCell(
					new Phrase("Estimated amount discharged  (m3)" + "\n" + " " + "\n" + "\n" + "Sea          Ashore"));
			c4.setColspan(2);
			PdfPCell c6 = new PdfPCell(new Paragraph("Signature officer in charge"));

			// here adding cells
			headerTable.addCell(c1);
			headerTable.addCell(c2);
			headerTable.addCell(c3);
			headerTable.addCell(c4);
			headerTable.addCell(c6);	
					
			// write content
			headerTable.writeSelectedRows(0, -1, 34, 804, writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	private void addFooter(PdfWriter writer) {
		PdfPTable footer = new PdfPTable(4);
		try {
			// set defaults
			footer.setWidths(new int[] { 15, 16, 5, 5 });
			footer.setTotalWidth(600);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(100);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(BaseColor.WHITE);
			// add copyright
			footer.addCell(new Phrase("\u00A9 OIL RECORD BOOK Part_1",
					new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
			footer.addCell(new Phrase("Master Signature :           " + "\n" + "Name of Master   :" + "\n"
					+ "Date                     :"));
			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			String num = String.format("Page %d", writer.getCurrentPageNumber());
			footer.addCell(new Phrase(num, new Font(Font.FontFamily.HELVETICA, 8)));
			// add placeholder for total page count
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			totalPageCount.setBorderColor(BaseColor.WHITE);
			footer.addCell(totalPageCount);
			// write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 34, 50, canvas);
			canvas.endMarkedContentSequence();
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		int totalLength = String.valueOf(writer.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
				new Phrase(String.valueOf(" "), new Font(Font.FontFamily.HELVETICA, 8)), totalWidth, 6, 0);
	}
}