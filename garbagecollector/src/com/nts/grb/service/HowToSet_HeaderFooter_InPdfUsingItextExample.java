package com.nts.grb.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * How To Set Header and Footer in pdf Using Itext Example - Using iText library
 * - core java tutorial
 *
 */

class HeaderAndFooterPdfPageEventHelper extends PdfPageEventHelper {
	public void onStartPage(PdfWriter pdfWriter, Document document) {
		System.out.println("onStartPage() method > Writing header in file");
		Rectangle rect = pdfWriter.getBoxSize("rectangle");

		// TOP LEFT
		ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_CENTER, new Phrase("TOP LEFT"),
				rect.getLeft(), rect.getTop(), 0);

		// TOP MEDIUM
		ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_CENTER, new Phrase("TOP MEDIUM"),
				rect.getRight() / 2, rect.getTop(), 0);

		// TOP RIGHT
		ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_CENTER, new Phrase("TOP RIGHT"),
				rect.getRight(), rect.getTop(), 0);
	}

	public void onEndPage(PdfWriter pdfWriter, Document document) {
		System.out.println("onEndPage() method > Writing footer in file");
		Rectangle rect = pdfWriter.getBoxSize("rectangle");
		// BOTTOM LEFT
		ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_CENTER, new Phrase("BOTTOM LEFT"),
				rect.getLeft() + 15, rect.getBottom(), 0);

		// BOTTOM MEDIUM
		ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_CENTER, new Phrase("BOTTOM MEDIUM"),
				rect.getRight() / 2, rect.getBottom(), 0);

		// BOTTOM RIGHT
		ColumnText.showTextAligned(pdfWriter.getDirectContent(), Element.ALIGN_CENTER, new Phrase("BOTTOM RIGHT"),
				rect.getRight() - 10, rect.getBottom(), 0);
	}
}

public class HowToSet_HeaderFooter_InPdfUsingItextExample extends PdfPageEventHelper {

	public static void main(String[] args) throws DocumentException, IOException {
		String pdfFilePath = "e:/Set Header and Footer in Pdf Using Itext Example.pdf";
		OutputStream fos = new FileOutputStream(new File(pdfFilePath));
		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
		Document d = new Document();
		PdfWriter pf = PdfWriter.getInstance(d, new FileOutputStream("vinay.pdf"));
		d.open();

		// d.add((Element) new Line());
		d.add(new Paragraph("voian"));
		PdfPTable tble = new PdfPTable(6);
		tble.setWidthPercentage(110);
		tble.setSpacingBefore(20f);
		tble.setSpacingAfter(20f);
		float[] colwidth = { 1f, 3f, 1f, 2f, 2f, 2f };
		tble.setWidths(colwidth);
		PdfPCell c1 = new PdfPCell(new Paragraph("1.Date/Time"));
		PdfPCell c2 = new PdfPCell(new Paragraph(
				"2.Position of the ship(latitude and longitude),port or facility. Remarks e.g. accidental loss and reason"));
		PdfPCell c3 = new PdfPCell(new Paragraph("3.Garbege Category"));
		PdfPCell c4 = new PdfPCell(new Paragraph("4.Estimated amount discharged(m3)"));
		PdfPCell c5 = new PdfPCell(new Paragraph("5.Incinerated amount(m3) and remarks"));
		PdfPCell c6 = new PdfPCell(new Paragraph("6.Signature offecer in charge"));
		tble.addCell(c1);
		tble.addCell(c2);
		tble.addCell(c3);
		tble.addCell(c4);
		tble.addCell(c5);
		tble.addCell(c6);
		for (int i = 0; i <= 50; i++) {
			tble.addCell("iiis");
		}

		d.add(tble);
		d.close();
		pf.close();
		Rectangle rectangle = new Rectangle(30, 30, 550, 800);
		pdfWriter.setBoxSize("rectangle", rectangle);
		HeaderAndFooterPdfPageEventHelper headerAndFooter = new HeaderAndFooterPdfPageEventHelper();
		pdfWriter.setPageEvent(headerAndFooter);
		document.open();
		document.add(new Paragraph("This is Header and Footer in Pdf Using Itext Example"));
		document.add(tble);
		document.close();
		fos.close();
		System.out.println("PDF created in >> " + pdfFilePath);
	}
}