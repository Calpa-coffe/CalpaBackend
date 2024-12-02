package pe.edu.upc.calpabackend.serviceimplements;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;

import java.io.OutputStream;

public class PDFgenerator {

    public static void generatePDF(TicketsDTO ticketDTO, OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("Boleta de Pago"));
        document.add(new Paragraph("Número de boleta: " + ticketDTO.getId()));
        document.add(new Paragraph("Vendedor: " + ticketDTO.getUsers().getUsername()));
        String clientName = ticketDTO.getClientname() != null && !ticketDTO.getClientname().isEmpty()
                ? ticketDTO.getClientname()
                : ticketDTO.getMembers().getClientname();
        document.add(new Paragraph("Cliente: " + clientName));
        document.add(new Paragraph("Producto: " + ticketDTO.getProduct().getNameproduct()));
        document.add(new Paragraph("Total: S/ " + ticketDTO.getTotal()));
        document.add(new Paragraph("Fecha de Pago: " + ticketDTO.getDatepay()));

        document.close();
    }
}
