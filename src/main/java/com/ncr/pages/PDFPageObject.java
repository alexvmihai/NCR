package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PDFPageObject extends BasePageObject<PDFPageObject> {
    private By textLayer = By.cssSelector("div.page:nth-child(1) > div:nth-child(2)");
    private By pdfViewer = By.cssSelector("#viewer");
    private By download = By.cssSelector("#download");

    protected PDFPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

//    File lastFile = lastModified("D:\\PDF\\");

    public void checkPDFContent(File file) throws IOException {

        try{
            PDDocument doc = PDDocument.load(file);
            int totalPages = doc.getNumberOfPages();
            System.out.println("Total pages: " + totalPages);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(totalPages);
            String extractedText = stripper.getText(doc);
            System.out.println(extractedText);
        } catch (IOException e) {
            System.out.println("Nu merge !");
            e.printStackTrace();
        }
    }

    public void savePDF(String url) throws IOException {
        byte[] ba1 = new byte[1024];
        URL url1 = new URL(url);
        int baLength;
        String date = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        FileOutputStream pdf1 = new FileOutputStream("D:\\PDF\\" + "regimen" + date +".pdf");

        try{
            System.out.println("Connecting to " + url1.toString() + "...");
            URLConnection urlConn = url1.openConnection();

            if(!urlConn.getContentType().equalsIgnoreCase("application/pdf")){
                System.out.println(urlConn.getContentType() + "======================");
                System.out.println("Failed, not a pdf");
            } else {
                try{
                    InputStream is1 = url1.openStream();
                    while ((baLength = is1.read(ba1)) != -1) {
                        pdf1.write(ba1, 0, baLength);
                    }
                    pdf1.flush();
                    pdf1.close();
                    is1.close();
                } catch (Exception e){
                    System.out.println("Failed. \n" + e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println("Failed. \n" + e.getMessage());
        }

    }

    public void clickDownload(){
        clickOn(download);
    }







}
