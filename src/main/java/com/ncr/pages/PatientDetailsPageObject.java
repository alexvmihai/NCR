package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PatientDetailsPageObject extends BasePageObject<PatientDetailsPageObject> {
    private By createCarerB = By.cssSelector("#carer-form-update-btn");
    private By disablePatientB = By.cssSelector(".patient-detail-delete");
    private By transferPatientB = By.cssSelector(".patient-detail-transfer");
    private By printB = By.cssSelector(".patient-detail-print");
    private By detailsHeader = By.cssSelector("#patient-form > div:nth-child(1) > h2:nth-child(2)");
    private By patientName = By.cssSelector(".hcp-title");
    private By saveCarerB = By.cssSelector("div.clearfix:nth-child(3) > button:nth-child(2)");
    private By editCarerB = By.cssSelector("#carer-form-update-btn");
    private By successMsg = By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1)");
    private By emailF = By.cssSelector(".carer_email > label:nth-child(1)");

    //Carer form
    private By firstnameF = By.cssSelector(".carer_firstname > input:nth-child(1)");
    private By lastnameF = By.cssSelector(".carer_lastname > input:nth-child(1)");
    private By numberF = By.cssSelector(".carer_mobile > input:nth-child(1)");
    private By relationF = By.cssSelector(".carer_relationship > input:nth-child(1)");


    protected PatientDetailsPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(createCarerB);
        waitForVisibilityOf(disablePatientB);
        waitForVisibilityOf(printB);
    }

    public String getPatientName (){
        return getText(patientName);
    }

    public void createCarer() throws InterruptedException{
        clickOn(createCarerB);
        Thread.sleep(3000);
    }

    public void fillCarerDetails(String firstname, String lastname, String number, String relation){
        type(firstname, firstnameF);
        type(lastname, lastnameF);
        type(number, numberF);
        type(relation, relationF);
    }

    public void saveCarer(){
        clickOn(saveCarerB);
        waitForVisibilityOf(editCarerB);
        waitForVisibilityOf(successMsg);
    }

    public String getConfirmation (){
        return getText(successMsg);
    }

    public String getEmail (){
        return getText(emailF);
    }


}
