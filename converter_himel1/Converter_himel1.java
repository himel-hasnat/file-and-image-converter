/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.converter_himel1;

/**
 *
 * @author abulh
 */

import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Converter_himel1 {

    public static void main(String[] args) throws IOException {
     
//        System.out.println(convertToPng("C:/Users/abulh/Downloads/kev.jpg", "C:/Users/abulh/OneDrive/Desktop/project_tests/mojmmel.png"));
//        System.out.println(convertToJpeg("src/main/resources/image.png", "out.jpeg"));
//        System.out.println(convertToJpg("src/main/resources/image.png", "out.jpg"));
//        System.out.println(convertToBmp("src/main/resources/image.png", "out.bmp"));
//        System.out.println(convertToGif("C:/Users/abulh/OneDrive/Desktop/project_tests/mojmmel.png", "C:/Users/abulh/OneDrive/Desktop/project_tests/mjm.gif"));
//        System.out.println(convertToTiff("src/main/resources/image.png", "out.tiff"));
//        System.out.println(convertToTif("src/main/resources/image.png", "out.tif"));
//        System.out.println(convertToWbmp("src/main/resources/image.png", "out.wbmp"));

        ConvertToPDF cwoWord = new ConvertToPDF();
       cwoWord.docxToPDF("C:/Users/abulh/OneDrive/Desktop/project_tests/hl1.docx", "C:/Users/abulh/OneDrive/Desktop/project_tests/hl23.pdf");
       try {
          cwoWord.createPdf("src/main/resources/demo.txt", "demo.pdf");
      } catch (IOException ex) {
           Logger.getLogger(Converter_himel1.class.getName()).log(Level.SEVERE, null, ex);
      }
      cwoWord.pdfToText("demo.pdf", "demo.txt");
      cwoWord.pdfToDocx("C:/Users/abulh/OneDrive/Desktop/project_tests/hl1.pdf","C:/Users/abulh/OneDrive/Desktop/project_tests/pdftodoc.docx");
    }
    
    
    static boolean convertToJpeg(String inputPath, String outputPath) {
        return convertImage(inputPath, "jpeg", outputPath);
    }

    static boolean convertToJpg(String inputPath, String outputPath) {
        return convertImage(inputPath, "jpg", outputPath);
    }

    static boolean convertToPng(String inputPath, String outputPath) {
        return convertImage(inputPath, "png", outputPath);
    }

    static boolean convertToGif(String inputPath, String outputPath) {
        return convertImage(inputPath, "gif", outputPath);
    }

    static boolean convertToBmp(String inputPath, String outputPath) {
        return convertImage(inputPath, "bmp", outputPath);
    }

    static boolean convertToTiff(String inputPath, String outputPath) {
        return convertImage(inputPath, "tiff", outputPath);
    }

    static boolean convertToWbmp(String inputPath, String outputPath) {
        return convertImage(inputPath, "WBMP", outputPath);
    }

    static boolean convertToTif(String inputPath, String outputPath) {
        return convertImage(inputPath, "tif", outputPath);
    }

    static boolean convertImage(String inputPath, String formatName, String outputPath) {
        try {
            BufferedImage img;
            img = ImageIO.read(new File(inputPath));
            ImageIO.write(img, formatName, new File(outputPath));
            return true;
        } catch (IOException e) {
            Logger.getLogger(Converter_himel1.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}

