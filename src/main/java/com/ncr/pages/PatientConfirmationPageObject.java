package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PatientConfirmationPageObject extends BasePageObject<PatientConfirmationPageObject> {
    private By successMsg = By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)");
    private By confirmationText = By.cssSelector(".fieldset > p:nth-child(4)");
    private By patientDetailsB = By.cssSelector("button.button:nth-child(1)");
    private By createRegimenB = By.cssSelector("button.button:nth-child(2)");


    public PatientConfirmationPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(successMsg);
        waitForVisibilityOf(confirmationText);
        waitForVisibilityOf(patientDetailsB);
        waitForVisibilityOf(createRegimenB);
    }

    public String getSuccessMsg(){
        return getText(successMsg);
    }

    public String getConfirmationText(){
        return getText(confirmationText);
    }

    public RegimenStep1PageObject createRegimen() throws IOException {
        clickOn(createRegimenB);
        return new RegimenStep1PageObject(driver);
    }
}
