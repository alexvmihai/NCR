package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class DashboardIAPageObject extends BasePageObject<DashboardIAPageObject> {
    private By welcome = By.cssSelector(".welcome-text");
    private By logoutB = By.cssSelector(".first > a:nth-child(1)");
    private By createPatientB = By.cssSelector("button.button:nth-child(1)");
    private By createRegimenB = By.cssSelector("button.button:nth-child(1)");
    private By profileTab = By.cssSelector(".item-profile > a:nth-child(1)");
    private By totalRegimensHeader = By.cssSelector("div.small-6:nth-child(1) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1)");
    private By totalHCPHeader = By.cssSelector("div.small-6:nth-child(2) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1)");

    protected DashboardIAPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(welcome);
        waitForVisibilityOf(logoutB);
        waitForVisibilityOf(createPatientB);
        waitForVisibilityOf(createRegimenB);
        waitForVisibilityOf(profileTab);
        waitForVisibilityOf(totalHCPHeader);
        System.out.println("Dashboard page loaded successfully !");
    }

    public IAProfilePageObject clickProfile() throws IOException {
        clickOn(profileTab);
        System.out.println("Clicking on profile tab...");
        return new IAProfilePageObject(driver);
    }



}
