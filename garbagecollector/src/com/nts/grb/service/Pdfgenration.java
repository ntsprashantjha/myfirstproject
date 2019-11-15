package com.nts.grb.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdfgenration extends PdfPageEventHelper {
	private PdfTemplate t;
	private Image total;

	public static void main(String arg[]) throws FileNotFoundException, DocumentException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Document d = new Document(PageSize.A4, 20, 20, 50, 25);
		PdfWriter writer = PdfWriter.getInstance(d, bos);
		HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		writer.setPageEvent(event);
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
		for (int i = 0; i <= 12; i++) {
			tble.addCell("iiis");
		}

		d.add(tble);
		d.close();
		pf.close();
	}

	
}
