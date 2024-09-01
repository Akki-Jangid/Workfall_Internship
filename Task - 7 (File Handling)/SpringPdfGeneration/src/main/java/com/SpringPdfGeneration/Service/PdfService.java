package com.SpringPdfGeneration.Service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface PdfService {

    public byte[] generatePdf() throws IOException, DocumentException;

    public void addTableHeader(PdfPTable table, String name);
}
