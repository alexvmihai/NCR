package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegimenSavedPageObject extends BasePageObject<RegimenSavedPageObject> {
    private By header = By.cssSelector(".a-left");
    private By backB = By.cssSelector(".button");
    private By confirmationText = By.cssSelector(".fieldset > p:nth-child(3)");
    private By printButton = By.cssSelector(".patient-detail-print");

    public RegimenSavedPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(backB);
        waitForVisibilityOf(confirmationText);
    }

    public String getConfirmationText(){
        return getText(confirmationText);
    }

    public PDFPageObject print() throws IOException{
        waitForVisibilityOf(printButton);
        clickOn(printButton);
        return new PDFPageObject(driver);
    }



}
