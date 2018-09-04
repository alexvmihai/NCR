package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class BECustomerInfoPageObject extends BasePageObject<BECustomerInfoPageObject> {
    private By nameHeader = By.cssSelector("div.content-header:nth-child(2) > h3:nth-child(1)");
    private By deleteCustomerButton = By.cssSelector("div.content-header:nth-child(2) > p:nth-child(2) > button:nth-child(4)");
    private By accountInfo = By.cssSelector("#customer_info_tabs_account > span:nth-child(1)");
    private By saveContinueButton = By.cssSelector("button[title=\"Save and Continue Edit\"]");
    private By status = By.cssSelector("#_accountstatus");
    private By successMessage = By.cssSelector("#messages > ul:nth-child(1) > li:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)");
    private By typeF = By.cssSelector("#_accountcustomer_type");
    private By emailF = By.cssSelector("#_accountemail");


    protected BECustomerInfoPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(nameHeader);
        waitForVisibilityOf(deleteCustomerButton);
        waitForVisibilityOf(accountInfo);
    }

    public String getHeaderText(){
        return getText(nameHeader);
    }

    public void openAccountInfo() throws InterruptedException {
        System.out.println("Clicking on Account info...");
        clickOn(accountInfo);
        Thread.sleep(6000);
    }

    public void setActive(){
        Select select = new Select(driver.findElement(By.cssSelector("#_accountstatus")));
        select.selectByVisibleText("Active");
    }

    public void saveCustomer() throws InterruptedException {
        clickOn(saveContinueButton);
        Thread.sleep(15000);
    }

    public String getSuccessMessage(){
        waitForVisibilityOf(successMessage);
        return getText(successMessage);
    }

    public String getCustomerType(){
        waitForVisibilityOf(typeF);
        Select select = new Select(driver.findElement(By.cssSelector("#_accountcustomer_type")));
        WebElement type = select.getFirstSelectedOption();
        String customerType = type.getText();
        return customerType;
    }

    public String getBEemail(){
        WebElement email = driver.findElement(By.cssSelector("#_accountemail"));
        String value = email.getAttribute("value");
        return value;
    }

}
