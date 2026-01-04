package pe.edu.upc.calpabackend.serviceimplements;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import pe.edu.upc.calpabackend.dtos.TicketsDTO;
import pe.edu.upc.calpabackend.dtos.TicketProductDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class PDFGenerator {

    public static void generatePDF(TicketsDTO ticketDTO, OutputStream outputStream) throws Exception {
        Rectangle ticketSize = new Rectangle(227, 700);
        Document document = new Document(ticketSize, 10, 10, 10, 10);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Logo
        try {
            String imagePath = PDFGenerator.class.getClassLoader().getResource("img/logo.jpg").getPath();
            Image logo = Image.getInstance(imagePath);
            logo.scaleToFit(100, 100);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
        } catch (Exception e) {
            System.err.println("Error cargando el logo: " + e.getMessage());
        }

        // Información de la empresa
        addCompanyInfo(document);

        // Validar ticketDTO
        if (ticketDTO == null) {
            throw new IllegalArgumentException("Datos del ticket no válidos.");
        }

        // Título, cliente, detalles del pago y productos
        addClientInfo(document, ticketDTO);
        addPaymentDetails(document, ticketDTO);
        addProductTable(document, ticketDTO);
        addTotals(document, ticketDTO);
        addAdditionalInfo(document, ticketDTO);

        // QR
        try {
            String qrContent = generateQRContent(ticketDTO);
            Image qrImage = generateQRCodeImage(qrContent, 100, 100);
            if (qrImage != null) {
                qrImage.setAlignment(Element.ALIGN_CENTER);
                document.add(qrImage);
            }
        } catch (Exception e) {
            System.err.println("Error generando el QR: " + e.getMessage());
        }

        document.close();
    }


    private static void addCompanyInfo(Document document) throws DocumentException {
        Paragraph companyInfo = new Paragraph("Calpa E.I.R.L \nAv. San German, calle 3",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
        companyInfo.setAlignment(Element.ALIGN_CENTER);
        companyInfo.setSpacingAfter(5);
        document.add(companyInfo);
    }

    private static void addClientInfo(Document document, TicketsDTO ticketDTO) throws DocumentException {
        PdfPTable clientTable = new PdfPTable(2);
        clientTable.setWidthPercentage(100);
        clientTable.setSpacingBefore(10f);
        clientTable.setSpacingAfter(10f);

        clientTable.addCell(createCell("Cliente:", true));
        String clientName = (ticketDTO.getClientname() != null && !ticketDTO.getClientname().isEmpty())
                ? ticketDTO.getClientname()
                : (ticketDTO.getMembers() != null && ticketDTO.getMembers().getClientname() != null
                ? ticketDTO.getMembers().getClientname()
                : "N/A");
        clientTable.addCell(createCell(clientName, false));
        clientTable.addCell(createCell("RUC/DNI:", true));
        clientTable.addCell(createCell(ticketDTO.getMembers() != null ? ticketDTO.getMembers().getDocument() : "N/A", false));
        clientTable.addCell(createCell("Dirección:", true));
        clientTable.addCell(createCell("Av. San German, calle 3", false)); // Cambiar según datos reales

        document.add(clientTable);
    }

    private static void addPaymentDetails(Document document, TicketsDTO ticketDTO) throws DocumentException {
        PdfPTable paymentTable = new PdfPTable(2);
        paymentTable.setWidthPercentage(100);
        paymentTable.setSpacingBefore(10f);
        paymentTable.setSpacingAfter(10f);

        paymentTable.addCell(createCell("Fecha de Emisión:", true));
        paymentTable.addCell(createCell(ticketDTO.getDatepay().toString(), false));
        paymentTable.addCell(createCell("Tipo de pago:", true));
        paymentTable.addCell(createCell(ticketDTO.getTypepayments().getType(), false));

        document.add(paymentTable);
    }

    private static void addProductTable(Document document, TicketsDTO ticketDTO) throws DocumentException {
        PdfPTable productTable = new PdfPTable(4); // Cantidad, Descripción, P/U, Total
        productTable.setWidthPercentage(100);
        productTable.setWidths(new float[]{10, 50, 20, 20});

        // Agregar encabezados
        productTable.addCell(createHeaderCell("Cantidad"));
        productTable.addCell(createHeaderCell("Descripción"));
        productTable.addCell(createHeaderCell("P/U"));
        productTable.addCell(createHeaderCell("Total"));

        // Iterar sobre los productos
        if (ticketDTO.getTicketProducts() != null && !ticketDTO.getTicketProducts().isEmpty()) {
            for (TicketProductDTO tp : ticketDTO.getTicketProducts()) {
                int quantity = tp.getQuantity();
                double price = tp.getProduct().getPrice();

                productTable.addCell(createCell(String.valueOf(quantity), false));
                productTable.addCell(createCell(tp.getProduct().getNameproduct(), false));
                productTable.addCell(createCell("S/ " + String.format("%.2f", price), false));
                productTable.addCell(createCell("S/ " + String.format("%.2f", price * quantity), false));
            }
        } else {
            // Si no hay productos, mostrar una fila indicando que no hay productos
            PdfPCell noDataCell = new PdfPCell(new Phrase("No hay productos seleccionados"));
            noDataCell.setColspan(4);
            noDataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            noDataCell.setPadding(10);
            productTable.addCell(noDataCell);
        }

        document.add(productTable);
    }

    private static void addTotals(Document document, TicketsDTO ticketDTO) throws DocumentException {
        double IGV = ticketDTO.getTotal() * 0.18;
        double gravada = ticketDTO.getTotal() - IGV;

        PdfPTable totalTable = new PdfPTable(2);
        totalTable.setWidthPercentage(100);
        totalTable.setSpacingBefore(10f);

        totalTable.addCell(createCell("Sub total:", true));
        totalTable.addCell(createCell("S/ " + String.format("%.2f", gravada), false));
        totalTable.addCell(createCell("IGV:", true));
        totalTable.addCell(createCell("S/ " + String.format("%.2f", IGV), false));
        totalTable.addCell(createCell("Total:", true));
        totalTable.addCell(createCell("S/ " + String.format("%.2f", ticketDTO.getTotal()), false));

        document.add(totalTable);
    }

    private static void addAdditionalInfo(Document document, TicketsDTO ticketDTO) throws DocumentException {
        double turned = ticketDTO.getAmountpayment() - ticketDTO.getTotal();

        // Mostrar monto pagado
        Paragraph paymentQuantity = new Paragraph("Pagó con: S/ " + String.format("%.2f", ticketDTO.getAmountpayment()),
                FontFactory.getFont(FontFactory.HELVETICA, 8));
        paymentQuantity.setAlignment(Element.ALIGN_LEFT);
        paymentQuantity.setSpacingBefore(10);
        document.add(paymentQuantity);

        // Condicional para mostrar vuelto o saldo insuficiente
        if (turned < 0) {
            Paragraph insufficientBalance = new Paragraph("Saldo insuficiente",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.RED));
            insufficientBalance.setAlignment(Element.ALIGN_LEFT);
            insufficientBalance.setSpacingBefore(10);
            document.add(insufficientBalance);
        } else {
            Paragraph additionalInfo = new Paragraph("Vuelto: S/ " + String.format("%.2f", turned),
                    FontFactory.getFont(FontFactory.HELVETICA, 8));
            additionalInfo.setAlignment(Element.ALIGN_LEFT);
            additionalInfo.setSpacingBefore(10);
            document.add(additionalInfo);
        }
    }

    private static String generateQRContent(TicketsDTO ticketDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append("Boleta: BBB1-").append(ticketDTO.getId()).append("\n")
                .append("Cliente: ").append(ticketDTO.getClientname() != null ? ticketDTO.getClientname() : "N/A").append("\n")
                .append("Total: S/ ").append(String.format("%.2f", ticketDTO.getTotal()));

        return sb.toString();
    }

    private static Image generateQRCodeImage(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrBufferedImage, "PNG", baos);

        return Image.getInstance(baos.toByteArray());
    }

    private static PdfPCell createCell(String content, boolean isHeader) {
        Font font = isHeader ? FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8) : FontFactory.getFont(FontFactory.HELVETICA, 8);
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5);
        cell.setBorderWidth(0.5f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    private static PdfPCell createHeaderCell(String content) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8);
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return cell;
    }
}
