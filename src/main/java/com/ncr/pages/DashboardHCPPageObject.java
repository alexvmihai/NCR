package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by alex.mihai on 4/3/2018.
 */
public class DashboardHCPPageObject extends BasePageObject<DashboardHCPPageObject> {
    private By patientsHeader = By.cssSelector(".block-chart-patients > div:nth-child(1) > h2:nth-child(1)");
    private By regimensHeader = By.cssSelector("div.small-6:nth-child(2) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(1)");
    private By welcome = By.cssSelector(".welcome-text");
    private By logoutB = By.cssSelector(".first > a:nth-child(1)");
    private By createPatientB = By.cssSelector("button.button:nth-child(1)");
    private By createRegimenB = By.cssSelector("button.button:nth-child(2)");
    private By searchBox = By.cssSelector("#patientsearch");
    private By breadcrumbs = By.cssSelector(".breadcrumbs");



    protected DashboardHCPPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(patientsHeader);
        waitForVisibilityOf(regimensHeader);
        waitForVisibilityOf(welcome);
        waitForVisibilityOf(logoutB);
        waitForVisibilityOf(createPatientB);
        waitForVisibilityOf(createRegimenB);
        System.out.println("Dashboard page loaded correctly !");
    }

    public String getWelcomeText(){
        return getText(welcome);
    }

    public String welcomeMsg(){
        String welcomeMsg = "";
        if(setEnv() == "PROD"){
            welcomeMsg = "Welcome, Alex mihai!";
        } else if(setEnv() == "PPRD"){
            welcomeMsg = "Welcome, MR Alex Mihai!";
        }
        return welcomeMsg;
    }
}
