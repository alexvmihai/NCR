package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class PDFPageObject extends BasePageObject<PDFPageObject> {
    private By textLayer = By.cssSelector("div.page:nth-child(1) > div:nth-child(2)");
    private By pdfViewer = By.cssSelector("#viewer");

    protected PDFPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public boolean checkPDFContent(String url, String text){
        boolean flag = false;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        String parsedText = null;
    }
}
