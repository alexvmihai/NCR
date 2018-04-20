package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.apache.bcel.generic.RETURN;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class BEManageCustomersPageObject extends BasePageObject<BEManageCustomersPageObject> {
    private By header = By.cssSelector("div.content-header:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > h3:nth-child(1)");
    private By addCustomerButton = By.cssSelector("div.content-header:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > button:nth-child(1)");
    private By emailF = By.cssSelector("#customerGrid_filter_email");
    private By result = By.cssSelector("td.last > a:nth-child(1)");


    protected BEManageCustomersPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(addCustomerButton);
        waitForVisibilityOf(emailF);
    }

    public void searchByEmail(String email) throws InterruptedException {
        type(email, emailF);
        System.out.println("Typing email...");
        WebElement element = driver.findElement(By.cssSelector("#customerGrid_filter_email"));
        element.sendKeys(Keys.RETURN);
        Thread.sleep(4000);
    }

    public BECustomerInfoPageObject editCustomer() throws IOException {
        clickOn(result);
        System.out.println("Opening the Customer Info Page...");
        return new BECustomerInfoPageObject(driver);
    }



}
