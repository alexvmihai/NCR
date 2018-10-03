package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegimenDetailsPageObject extends BasePageObject<RegimenDetailsPageObject> {

    private By titleHeader = By.cssSelector(".hcp-title");
    private By editButton = By.cssSelector("div.cr-row-buttons:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(3)");
    private By backButton = By.cssSelector("div.cr-row-buttons:nth-child(1) > div:nth-child(1) > div:nth-child(1) > p:nth-child(1) > a:nth-child(1)");
    private By cancelButton = By.cssSelector("div.cr-row-buttons:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(2)");
    private By regimenStatus = By.cssSelector(".rd-status");
    private By patientName = By.cssSelector(".patient-details > div:nth-child(1)");

    protected RegimenDetailsPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public String getTitle(){
        return getText(titleHeader);
    }

    public String getPatientName(){
        return getText(patientName);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(editButton);
        waitForVisibilityOf(backButton);
        waitForVisibilityOf(cancelButton);
        System.out.println("Regimen Details page loaded successfully.");
    }

    public String getRegimenStatus(){
        return getText(regimenStatus);
    }

}
