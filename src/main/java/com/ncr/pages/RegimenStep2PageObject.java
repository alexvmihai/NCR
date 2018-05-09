package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.*;

import java.io.IOException;
import java.util.NoSuchElementException;

public class RegimenStep2PageObject extends BasePageObject<RegimenStep2PageObject> {
    private By step2Bread = By.cssSelector(".create-regimen-step-2 > span:nth-child(1)");
    private By addMoreProductsB = By.cssSelector(".cr-action-add");
    private By stepNumber = By.cssSelector("li.active:nth-child(2)");
    private By standardShip = By.cssSelector("#delivery-list-radio > li:nth-child(1) > div:nth-child(1) > label:nth-child(2)");
    private By deliveryDate = By.cssSelector("#startDateNodeID");
    private By weekendPopUpClose = By.cssSelector("#weekend-popup > a:nth-child(3)");
    private By weekendPopUp = By.cssSelector(".reveal-modal-bg");
    public boolean weekendPopExists = driver.findElements(By.cssSelector(".reveal-modal-bg")).size() !=0;
    private By createB = By.cssSelector("#regime-save-button");



    protected RegimenStep2PageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(step2Bread);
        waitForVisibilityOf(addMoreProductsB);
        waitForVisibilityOf(stepNumber);
    }

    public void selectShipping() throws InterruptedException {
        clickOn(standardShip);
        System.out.println("Selected shipping method !");
        Thread.sleep(2000);
    }

    public void selectStartDate(){
        String Date = getCurrentDate();
        System.out.println(Date);
        String futureDate = getFutureDate();
        System.out.println(futureDate);
        WebElement input = driver.findElement(By.cssSelector("#startDateNodeID"));
        input.sendKeys(Keys.BACK_SPACE);
        type(futureDate, deliveryDate);
        System.out.println("Current date is : " + Date + "\nDelivery date will be: " + futureDate);
    }


    public void closeWeekendPopup(){
        waitForVisibilityOf(weekendPopUpClose);
        clickOn(weekendPopUpClose);
    }

    public RegimenSavedPageObject submitRegimen() throws IOException {
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,100)");
        clickOn(createB);
        return new RegimenSavedPageObject(driver);
    }
}
