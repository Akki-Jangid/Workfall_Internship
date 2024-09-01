package com.SpringPdfGeneration.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestController {

    @GetMapping("/generatePdf")
    public ResponseEntity<InputStreamResource> generatePdf() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

//        PdfWriter writer = new PdfWriter(out);
//        PdfDocument pdfDoc = new PdfDocument(writer);
//        Document document = new Document(pdfDoc);
//
//        document.add(new Paragraph("RECEIPT"));
//        document.add(new Paragraph("This serves as proof for any payment made."));
//        document.add(new Paragraph("Date: 14/08/2024"));
//        document.add(new Paragraph("The below payment is made pertaining to the vehicle bearing registration number: 120345678"));
//
//        document.add(new Paragraph("PAYMENT DETAILS"));
//        document.add(new Paragraph("Amount: 120,145.0"));
//        document.add(new Paragraph("Purpose: dfghnjm"));
//        document.add(new Paragraph("Mode of payment: 124563789"));
//        document.add(new Paragraph("Payment reference No: 1254789630"));
//
//        document.add(new Paragraph("PAID BY"));
//        document.add(new Paragraph("Name: swathi"));
//        document.add(new Paragraph("NRIC No.: 8527410"));
//        document.add(new Paragraph("Signature"));
//
//        document.add(new Paragraph("RECEIVED BY"));
//        document.add(new Paragraph("Name: swathidd"));
//        document.add(new Paragraph("NRIC No.: 123456789"));
//        document.add(new Paragraph("Signature"));
//
//        document.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=receipt.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
