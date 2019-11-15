package com.nts.orb1.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class OrbHomeHeader {
	
	public static PdfPTable addHeader(Document document){
		
		try {
		
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setSpacingAfter(10f);

		// here define the number of columns
		float[] colwidth = { 1.7f, 2.2f, 2.0f, 9.0f, 2.0f, 2.5f, 2.5f, 2.2f, 4.0f, 2.3f };

		table.setWidths(colwidth);
		
		BaseFont headBaseFont = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED);
		Font headfont = new Font(headBaseFont, 10, Font.NORMAL);
		
		PdfPCell entryNo = new PdfPCell(new Phrase("ENTRY NO", headfont));
		entryNo.setHorizontalAlignment(Element.ALIGN_CENTER);
		entryNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
		entryNo.setFixedHeight(50);
		table.addCell(entryNo);
		
		PdfPCell date = new PdfPCell(new Phrase("DATE", headfont));
		date.setHorizontalAlignment(Element.ALIGN_CENTER);
		date.setVerticalAlignment(Element.ALIGN_MIDDLE);
		date.setFixedHeight(50);
		table.addCell(date);
		
		PdfPCell code = new PdfPCell(new Phrase("CODE", headfont));
		code.setHorizontalAlignment(Element.ALIGN_CENTER);
		code.setVerticalAlignment(Element.ALIGN_MIDDLE);
		code.setFixedHeight(50);
		table.addCell(code);
		
		PdfPCell itemDescription = new PdfPCell(new Phrase("ITEM / DESCRIPTION", headfont));
		itemDescription.setHorizontalAlignment(Element.ALIGN_CENTER);
		itemDescription.setVerticalAlignment(Element.ALIGN_MIDDLE);
		itemDescription.setFixedHeight(50);
		table.addCell(itemDescription);
		
		PdfPCell viewFile = new PdfPCell(new Phrase("VIEW FILE", headfont));
		viewFile.setHorizontalAlignment(Element.ALIGN_CENTER);
		viewFile.setVerticalAlignment(Element.ALIGN_MIDDLE);
		viewFile.setFixedHeight(50);
		table.addCell(viewFile);
		
		PdfPCell officerIncharge = new PdfPCell(new Phrase("OFFICER INCHARGE", headfont));
		officerIncharge.setHorizontalAlignment(Element.ALIGN_CENTER);
		officerIncharge.setVerticalAlignment(Element.ALIGN_MIDDLE);
		officerIncharge.setFixedHeight(50);
		table.addCell(officerIncharge);
		
		PdfPCell masterApproval = new PdfPCell(new Phrase("MASTER APPROVAL", headfont));
		masterApproval.setHorizontalAlignment(Element.ALIGN_CENTER);
		masterApproval.setVerticalAlignment(Element.ALIGN_MIDDLE);
		masterApproval.setFixedHeight(50);
		table.addCell(masterApproval);
		
		PdfPCell strikeThrough = new PdfPCell(new Phrase("STRIKE THROUGH", headfont));
		strikeThrough.setHorizontalAlignment(Element.ALIGN_CENTER);
		strikeThrough.setVerticalAlignment(Element.ALIGN_MIDDLE);
		strikeThrough.setFixedHeight(50);
		table.addCell(strikeThrough);
		
		PdfPCell stComment = new PdfPCell(new Phrase("S/T COMMENT", headfont));
		stComment.setHorizontalAlignment(Element.ALIGN_CENTER);
		stComment.setVerticalAlignment(Element.ALIGN_MIDDLE);
		stComment.setFixedHeight(50);
		table.addCell(stComment);
		
		PdfPCell reapproval = new PdfPCell(new Phrase("RE   APPROVAL", headfont));
		reapproval.setHorizontalAlignment(Element.ALIGN_CENTER);
		reapproval.setVerticalAlignment(Element.ALIGN_MIDDLE);
		reapproval.setFixedHeight(50);
		table.addCell(reapproval);
		
		table.setHeaderRows(1);
		document.add(table);
		return table;
		
		}catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}

}
