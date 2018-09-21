package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PatientsTabPageObject extends BasePageObject<PatientsTabPageObject> {

    private By managementHeader = By.cssSelector(".page-title > h1:nth-child(1)");
    private By createPatientButton = By.cssSelector(".button");
    private By firstPatientName = By.cssSelector("tr.even:nth-child(1) > td:nth-child(3) > a:nth-child(1)");
    private By dashboardTab = By.cssSelector(".item-dashboard > a:nth-child(1)");


    protected PatientsTabPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(managementHeader);
        waitForVisibilityOf(createPatientButton);
    }

    public String getPatientName(){
        return getText(firstPatientName);
    }

    public DashboardHCPPageObject selectDashboardTab () throws IOException {
        clickOn(dashboardTab);
        return new DashboardHCPPageObject(driver);
    }
}
