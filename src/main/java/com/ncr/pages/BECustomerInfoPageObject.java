package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BECustomerInfoPageObject extends BasePageObject<BECustomerInfoPageObject> {
    private By nameHeader = By.cssSelector("div.content-header:nth-child(2) > h3:nth-child(1)");
    private By deleteCustomerButton = By.cssSelector("div.content-header:nth-child(2) > p:nth-child(2) > button:nth-child(4)");

    protected BECustomerInfoPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(nameHeader);
        waitForVisibilityOf(deleteCustomerButton);
    }

    public String getHeaderText(){
        return getText(nameHeader);
    }
}
