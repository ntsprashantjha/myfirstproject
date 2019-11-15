package com.nts.grb.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;
import java.net.MalformedURLException;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

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
		PdfPTable header = new PdfPTable(2);
		try {
			// set defaults
			header.setWidths(new int[] { 2, 24 });
			header.setTotalWidth(527);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(40);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

			// add image
			/*
			 * Image logo = Image.getInstance(HeaderFooterPageEvent.class.getResource(
			 * "/garbagecollector/logo.jpg")); header.addCell(logo);
			 */

			// add text
			PdfPCell text = new PdfPCell();
			text.setPaddingBottom(15);
			text.setPaddingLeft(10);
			text.setBorder(Rectangle.BOTTOM);
			text.setBorderColor(BaseColor.LIGHT_GRAY);
			text.addElement(new Phrase("GARBAGE RECORD BOOK Part_1", new Font(Font.FontFamily.HELVETICA, 12)));
			text.addElement(new Phrase("GARBAGE RECORD BOOK Part_1", new Font(Font.FontFamily.HELVETICA, 8)));
			header.addCell(text);

			// write content
			header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
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
			footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
			/*
			 * footer.addCell(new Phrase("Master e-Signature:______________________"));
			 */
			// add copyright
			footer.addCell(new Phrase("\u00A9 GARBAGE RECORD BOOK Part_1",
					new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_MIDDLE);
			footer.addCell(new Phrase("Master Signature :_____________" + "\n" + "Name of Master   :" + "\n"
					+ "Date                     :"));
			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()),
					new Font(Font.FontFamily.HELVETICA, 8)));

			// add placeholder for total page count
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
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
				new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)), totalWidth,
				6, 0);
	}
}