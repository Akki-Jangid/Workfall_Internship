package com.SpringPdfGeneration.ServiceImpl;


import com.SpringPdfGeneration.Exception.CustomException;
import com.SpringPdfGeneration.Service.PdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.exceptions.InvalidImageException;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.stream.Stream;

@Service
public class PdfServiceImpl implements PdfService {

    public PdfServiceImpl() throws DocumentException, IOException {
    }
    private final Font headerFont = new Font(BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED), 20);
    private final Font headingFont = new Font(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 12);

    public byte[] generatePdf() throws IOException, DocumentException {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        // Open the document
        document.open();

        PdfPTable headerTable = createHeaderForPdf();
        document.add(headerTable);

        // Adding the Paragraph for Date
        Paragraph datePara = new Paragraph("Date : ");
        Chunk date = new Chunk("05/08/2024");
        date.setUnderline(1, -2);
        datePara.add(date);
        document.add(new Paragraph(datePara));

        // Paragraph to Add Content
        Paragraph paymentForPara = new Paragraph("The below payment is made for Online Shopping on: ");
        Chunk ecommerce = new Chunk("FLIPKART");
        ecommerce.setUnderline(1, -2);
        paymentForPara.add(ecommerce);
        document.add(new Paragraph(paymentForPara));

        // Add table with header "PAYMENT DETAIL"
        PdfPTable table = new PdfPTable(2);
        addTableHeader(table, "PAYMENT DETAILS");

        table.setSpacingBefore(30);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 3});

        // Column headers
        table.addCell(createCell("Amount", 5));
        table.addCell(createCell("1000", 5));

        table.addCell(createCell("Purpose", 5));
        table.addCell(createCell("Online Shopping", 5));

        table.addCell(createCell("Mode of Payment", 5));
        table.addCell(createCell("UPI", 5));

        table.addCell(createCell("Reference No.", 5));
        table.addCell(createCell("DP1312312AS", 5));

        // Add table to document
        document.add(table);


        PdfPTable table1 = new PdfPTable(2); // 2 columns
        table1.setSpacingBefore(30);
        table1.setWidthPercentage(100);
        table1.setWidths(new int[]{1, 3});
        addTableHeader(table1, "PAID BY");

        // Column headers
        table1.addCell(createCell("  Name", 5));
        table1.addCell(createCell("Aakash", 5));

        table1.addCell(createCell("  NRIC No", 5));
        table1.addCell(createCell("123123", 5));

        table1.addCell(createCell("Signature", 30));
        try{
            Image image = Image.getInstance("Images/MySignature.png");

            PdfPCell imageCell = new PdfPCell(image, true); // Add image and set fit to true
            imageCell.setFixedHeight(100); // Set fixed cell height
            imageCell.setPadding(5); // Set padding around the image, if needed
            imageCell.setHorizontalAlignment(Element.ALIGN_LEFT); // Center align the image horizontally
            imageCell.setVerticalAlignment(Element.ALIGN_LEFT); // Center align the image vertically

            table1.addCell(imageCell);    //       ADD IMAGE HERE
            document.add(table1);

        } catch (Exception e) {
            throw new CustomException("Check your image path || \n"+e.getMessage());
        }


        PdfPTable table2 = new PdfPTable(2);
        table2.setSpacingBefore(30);
        table2.setWidthPercentage(100);
        table2.setWidths(new int[]{1, 3});
        addTableHeader(table2, "RECEIVED BY");

        // Column headers
        table2.addCell(createCell("  Name", 5));
        table2.addCell(createCell("Akki", 5));

        table2.addCell(createCell("  NRIC No", 5));
        table2.addCell(createCell("32132112", 5));

        table2.addCell(createCell("Signature", 30));

        try{
            Image image1 = Image.getInstance("Images/AkkiSignature.png");

            PdfPCell imageCell1 = new PdfPCell(image1, true);
            imageCell1.setFixedHeight(100);
            imageCell1.setPadding(5);
            imageCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            imageCell1.setVerticalAlignment(Element.ALIGN_LEFT);
            table2.addCell(imageCell1);

            document.add(table2);

        } catch (Exception e) {
            throw new CustomException("Check your image path || \n"+e.getMessage());
        }

        // Close the document
        document.close();

        return baos.toByteArray();
    }


    private PdfPCell createCell(String content, float padding) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell(new Paragraph(content, new Font(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 12)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(padding); // Set padding for each cell
        return cell;
    }

    @Override
    public void addTableHeader(PdfPTable table, String name) {
        PdfPCell cell = new PdfPCell(new Paragraph(name, headingFont));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(10);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
    }

    public PdfPTable createHeaderForPdf(){
        PdfPTable tableDemo = new PdfPTable(1);
        tableDemo.setWidthPercentage(100);

        Chunk regularText = new Chunk("PAYMENT RECEIPT");
        regularText.setFont(headerFont);

        Chunk subScriptText = new Chunk("    Act as a proof for payment made");
        subScriptText.setTextRise(-2);
        subScriptText.setFont(headingFont);

        Paragraph headerPara = new Paragraph();
        headerPara.add(regularText);
        headerPara.add(subScriptText);

        PdfPCell cellDemo = new PdfPCell(new Paragraph(headerPara));
        cellDemo.setBackgroundColor(BaseColor.LIGHT_GRAY); // Set background color
        cellDemo.setPadding(5);
        cellDemo.setBorder(Rectangle.NO_BORDER);

        // Create shadow effect by adding a cell with an offset color
        PdfPCell shadowCell = new PdfPCell();
        shadowCell.setBackgroundColor(BaseColor.GRAY); // Shadow color
        shadowCell.setPadding(5);
        shadowCell.setBorder(Rectangle.NO_BORDER);
        shadowCell.setColspan(1);
        shadowCell.setMinimumHeight(6);
        shadowCell.setPaddingBottom(5); // Create space for shadow effect

        tableDemo.addCell(cellDemo);
        tableDemo.addCell(shadowCell);
        tableDemo.setSpacingAfter(30);

        return tableDemo;
    }
}
