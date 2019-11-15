package testing;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	public void onEndPage(PdfWriter writer, Document document) {
		try {
			BaseFont headBaseFont = BaseFont.createFont(

					BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED);
			Font headfont = new Font(headBaseFont, 12, Font.NORMAL);

			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
					new Phrase("\u00A9 OIL RECORD BOOK Part_1"
							+ "                                                                                            "
							+ "                                                                                    "
							+ "                   Page " + document.getPageNumber(), headfont),
					20, 20, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}