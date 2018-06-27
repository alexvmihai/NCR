package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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
//    public boolean weekendPopExists = driver.findElement(By.id("weekend-popup")).isDisplayed();
    private By createB = By.cssSelector("#regime-save-button");
    private By orderNr = By.cssSelector("#purchase_order_number");
    private By frequency = By.cssSelector("#cr-length-select");
    private By expiry = By.cssSelector(".cr-expir-date");
    private By startDate = By.cssSelector("#startDateNodeID");




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

    public String selectStartDate() throws InterruptedException {
        String Date = getCurrentDate();
        System.out.println(Date);
        String futureDate = getFutureDate();
        System.out.println(futureDate);
        WebElement input = driver.findElement(By.cssSelector("#startDateNodeID"));
        input.sendKeys(Keys.BACK_SPACE);
        type(futureDate, deliveryDate);
        System.out.println("Current date is : " + Date + "\nDelivery date will be: " + futureDate);
        Thread.sleep(3000);
        return futureDate;
    }


    public void closeWeekendPopup(){
        waitForVisibilityOf(weekendPopUp);
        clickOn(weekendPopUpClose);
    }

    public RegimenSavedPageObject submitRegimen() throws IOException, InterruptedException {
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,100)");
        clickOn(createB);
//        boolean isPresent = driver.findElement(By.cssSelector(".reveal-modal-bg")).isDisplayed();
//        if(isPresent){
//            regimenStep2.closeWeekendPopup();
//        } else {
//            System.out.println("Moving on..");
//        }
        try{
            closeWeekendPopup();
            System.out.println("Closed pop-up");
            Thread.sleep(2000);
            clickOn(createB);
        } catch (NoSuchElementException ex){
            System.out.println("Moving on !");
        } catch (TimeoutException ex2){
            System.out.println("Moving on...");
        }
        return new RegimenSavedPageObject(driver);
    }

    public void clickOrderNr(){
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,300)");
        clickOn(orderNr);
    }

    public void setFrequency(){
        Select select = new Select(driver.findElement(By.cssSelector(".regimenDetails > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(1) > select:nth-child(1)")));
        select.selectByVisibleText("Every 4 Weeks");
    }

    public String getExpiry(){
        return getText(expiry);
    }

    public String getStartDate(){
        return getText(startDate);
    }

    public String getFrequency(){
        return getText(frequency);
    }
}
