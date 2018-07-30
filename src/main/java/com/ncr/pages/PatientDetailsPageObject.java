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


    protected PatientDetailsPageObject(WebDriver driver) throws IOException {
        super(driver);
    }
}
