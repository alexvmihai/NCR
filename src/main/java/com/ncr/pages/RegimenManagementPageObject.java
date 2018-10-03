package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegimenManagementPageObject extends BasePageObject<RegimenManagementPageObject> {

    private By headerTitle = By.cssSelector(".hcp-title");
    private By createRegimenButton = By.cssSelector(".button");
    private By results = By.cssSelector(".customer-hcp");
    private By viewFirst = By.cssSelector("a.mngmnt-link:nth-child(1) > span:nth-child(1)");
    private By viewRegimen = By.cssSelector(".btn-middle");

    protected RegimenManagementPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(headerTitle);
        waitForVisibilityOf(createRegimenButton);
        waitForVisibilityOf(results);
        System.out.println("Regimen Management Page loaded !");
    }

    public String getHeaderTitle(){
        return getText(headerTitle);
    }

    public RegimenDetailsPageObject viewRegimen() throws IOException {
        clickOn(viewFirst);
        waitForVisibilityOf(viewRegimen);
        clickOn(viewRegimen);
        return new RegimenDetailsPageObject(driver);
    }
}
