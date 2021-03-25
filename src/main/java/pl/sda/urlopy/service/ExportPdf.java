package pl.sda.urlopy.service;


import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.Data;


import pl.sda.urlopy.model.Holiday;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;


@Data
public class ExportPdf {

    public static ByteArrayInputStream holidaysReport(List<Holiday> holidays) {

        Document document;
        document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{2, 4, 4, 2, 5});
            PdfPTable tableHead = new PdfPTable(1);
            tableHead.setWidthPercentage(80);
            tableHead.setWidths(new int[]{15});

            Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Wnioski urlopowe", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableHead.addCell(hcell);


            hcell = new PdfPCell(new Phrase("ID", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Data rozpoczęcia", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Data zakończenia", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Liczba dni", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Decyzja", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Holiday holiday : holidays) {

                PdfPCell cell;
                cell = new PdfPCell(new Phrase(holiday.getActualLoggedUser()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tableHead.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(holiday.getId())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(holiday.getStartDate())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(holiday.getEndDate())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(holiday.getCountOfDays())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(holiday.getAccepted()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
