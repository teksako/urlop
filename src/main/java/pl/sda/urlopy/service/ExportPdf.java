package pl.sda.urlopy.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DrawInterface;
import lombok.Data;


import pl.sda.urlopy.model.Holiday;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.pdf.PdfWriter;


@Data
public class ExportPdf {

    public static ByteArrayInputStream holidaysReport(List<Holiday> holidays, UserService userService, HolidayService holidayService) {
    

        Document document;
        document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
            Paragraph p = new Paragraph();
            Chunk owner = new Chunk(userService.actualLoginUser());
            Chunk allApplication = new Chunk (holidayService.countAll().toString());
            p.add("Raport urlopowy pracownika: " + owner+ " ".replaceAll("\\s+","\n"));
            p.add("Ilosc zlozonych wioskow: "+ allApplication+  " ".replaceAll("\\s+","\n"));
            p.add("Ilosc wykorzystanego urlopu: "+ allApplication+ " dni." + " ".replaceAll("\\s+","\n"));

            p.setAlignment(1);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{4, 4, 2, 5});




            PdfPCell hcell;


//            hcell = new PdfPCell(new Phrase("ID", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

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
//                cell = new PdfPCell(new Phrase(holiday.getActualLoggedUser()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                tableHead.addCell(cell);

//                cell = new PdfPCell(new Phrase(String.valueOf(holiday.getId())));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell);

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
            document.add(p);
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
