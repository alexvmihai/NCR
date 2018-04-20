package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class BackEndDashboardPageObject extends BasePageObject<BackEndDashboardPageObject> {
    private By customers = By.cssSelector("li.level0:nth-child(4) > a:nth-child(1) > span:nth-child(1)");
    private By manageCustomers = By.cssSelector("li.level0:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1) > span:nth-child(1)");


    protected BackEndDashboardPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(customers);
    }

    public void mouseOverCustomers() throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector("li.level0:nth-child(4) > a:nth-child(1) > span:nth-child(1)"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        Thread.sleep(3000);
    }

    public BEManageCustomersPageObject clickManageCustomers() throws IOException {
        waitForVisibilityOf(manageCustomers);
        clickOn(manageCustomers);
        System.out.println("Opening the Manage Customers Page...");
        return new BEManageCustomersPageObject(driver);
    }
}
