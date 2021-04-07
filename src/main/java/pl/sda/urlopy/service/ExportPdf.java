package pl.sda.urlopy.service;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DrawInterface;
import lombok.Data;


import pl.sda.urlopy.model.Holiday;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.pdf.PdfWriter;


@Data
public class ExportPdf {

    public static ByteArrayInputStream holidaysReport(List<Holiday> holidays, UserService userService, HolidayService holidayService) {

        String accepted = "AKCEPTACJA";
        String reject = "ODRZUCENIE";
        String noDecision = "BRAK DECYZJI";
        LocalDate today = LocalDate.now();
        LocalTime timeLabel = LocalTime.now();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
//        Label timeLabel = new Label(LocalTime.now().format(dtf));

        Document document;
        document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
            Paragraph p = new Paragraph();
            Paragraph header = new Paragraph();
            Paragraph tableHeader = new Paragraph();
            Chunk owner = new Chunk(userService.actualLoginUser());
            Chunk allApplication = new Chunk(holidayService.countAll().toString());
            Chunk allDays = new Chunk(holidayService.allDays().toString());
            Chunk acceptedAplication = new Chunk(holidayService.count(accepted).toString());
            Chunk rejectAplication = new Chunk(holidayService.count(reject).toString());
            Chunk noDecisionAplication = new Chunk(holidayService.count(noDecision).toString());


            p.setAlignment(Element.ALIGN_BOTTOM);
            header.setFont(headFont);
            header.setAlignment(Element.ALIGN_CENTER);

            tableHeader.setFont(headFont);
            tableHeader.setAlignment(Element.ALIGN_CENTER);

            header.add("Raport urlopowy pracownika: " + owner + " ".replaceAll("\\s+", "\n"));
            header.add(Chunk.NEWLINE);
            header.add(Chunk.NEWLINE);
            p.add("Raport wygenerowany o godzinie " +timeLabel.getHour()+":"+timeLabel.getMinute()+":"+timeLabel.getSecond()+ " dnia "+ today.toString() + " ".replaceAll("\\s+", "\n"));
            p.add("Ilosc zlozonych wioskow: " + allApplication + " ".replaceAll("\\s+", "\n"));
            p.add("Ilosc zaakceptowanych wnioskow: " + acceptedAplication + " ".replaceAll("\\s+", "\n"));
            p.add("Ilosc odrzuconych wnioskow: " + rejectAplication + " ".replaceAll("\\s+", "\n"));
            p.add("Ilosc wnioskow oczekujacych: " + noDecisionAplication + " ".replaceAll("\\s+", "\n"));
            p.add("Ilosc wykorzystanego urlopu: " + allDays + " dni." + " ".replaceAll("\\s+", "\n"));
            p.add(Chunk.NEWLINE);
            p.add(Chunk.NEWLINE);
            tableHeader.add("Spis wnioskow");
            tableHeader.add(Chunk.NEWLINE);
            tableHeader.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{4, 4, 3, 5});


            PdfPCell hcell;


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
            document.add(header);
            document.add(p);
            document.add(tableHeader);
            document.add(table);
            document.close();

        } catch (DocumentException ex) {

        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
