/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.converter_himel1;

/**
 *
 * @author abulh
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author himel
 */

public class ConvertToPDF {
    public String pdfPath,docPath;
    
    public void docxToPDF(String docPath, String pdfPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfPath));
            PdfConverter.getInstance().convert(document, out, options);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void createPdf(String sourceFile, String dest) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));

        BufferedReader br;
        try (Document document = new Document(pdf)) {
            document.setTextAlignment(TextAlignment.LEFT);
            document.setFontSize((float) 8.0);
            document.setLeftMargin((float) 40.0);
            document.setRightMargin((float) 40.0);
            //BufferedReader br = new BufferedReader(new FileReader(sourceFile));
            br = new BufferedReader( new InputStreamReader( new FileInputStream(sourceFile), "UTF8"));
            String line;
            PdfFont normal = PdfFontFactory.createFont(StandardFonts.COURIER);
            Paragraph para = new Paragraph();
            para.setFont(normal);
            while ((line = br.readLine()) != null) {
                line = line.replace("\u0020", "\u00A0");
                para.add(line + "\n");    
            }   document.add(para);
        }
        br.close();
    }
    public void pdfToDocx(String inputFilePath,String outputFilePath) throws IOException
    {
        

        try {
            // Load the PDF file
            File inputFile = new File(inputFilePath);
            FileInputStream fis = new FileInputStream(inputFile);

            // Create a new Word Document
            XWPFDocument document = new XWPFDocument();

            // Create a new paragraph for the text content
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            // Extract text from the PDF and add it to the Word Document
            String extractedText = extractTextFromPdf(fis);
            run.setText(extractedText);

            // Save the Word Document to the specified output file path
            FileOutputStream fos = new FileOutputStream(outputFilePath);
            document.write(fos);

            // Close the streams
            fis.close();
            fos.close();

            System.out.println("PDF converted to DOC successfully.");
    }
        catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
    
    public void pdfToText(String sourceFile, String destFile) {
        try{
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = PDDocument.load(new File(sourceFile));
            String parsedText = pdfStripper.getText(pdDoc);
            try (FileWriter file = new FileWriter( destFile)) {
                file.write(parsedText);
            }
        } catch (IOException ex){
           Logger.getLogger(Converter_himel1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String extractTextFromPdf(FileInputStream fis) throws IOException, InvalidFormatException {
        // Extract the text content from the PDF using your preferred library
        // Replace this with your code to extract text from the PDF
        String extractedText = "This is a sample text extracted from the PDF.";
        return extractedText;
    }
    public static void convertPDFToDOCX(String pdfFilePath, String docxFilePath) {
        try {
            // Load the PDF document
            PDDocument pdfDoc = PDDocument.load(new FileInputStream(pdfFilePath));

            // Create a new Word document
            XWPFDocument docxDoc = new XWPFDocument();

            // Create a PDF text stripper
            PDFTextStripper stripper = new PDFTextStripper();

            // Iterate through each page of the PDF document
            for (int i = 0; i < pdfDoc.getNumberOfPages(); i++) {
                // Extract text from the PDF page
                stripper.setStartPage(i + 1);
                 stripper.setEndPage(i + 1);
                String text = stripper.getText(pdfDoc);

                // Create a new paragraph in the Word document
                XWPFParagraph paragraph = docxDoc.createParagraph();

                // Create a run and set the text from the PDF page
                XWPFRun run = paragraph.createRun();
                run.setText(text);
            }
            FileOutputStream out = new FileOutputStream(docxFilePath);
            docxDoc.write(out);
            out.close();

            // Close the PDF document
            pdfDoc.close();

            System.out.println("PDF converted to DOCX successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public static void convertPNGtoJPEG(String pngFilePath, String jpgFilePath) {
        try {
            // Read the PNG image from file
            BufferedImage pngImage = ImageIO.read(new File(pngFilePath));

            // Create a blank BufferedImage with TYPE_INT_RGB for JPEG conversion
            BufferedImage jpgImage = new BufferedImage(pngImage.getWidth(), pngImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            // Copy the PNG image to the JPEG image
            jpgImage.createGraphics().drawImage(pngImage, 0, 0, null);

            // Write the JPEG image to file
            ImageIO.write(jpgImage, "jpg", new File(jpgFilePath));

            System.out.println("PNG converted to JPEG successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void convertToJpg(String inputfile, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
