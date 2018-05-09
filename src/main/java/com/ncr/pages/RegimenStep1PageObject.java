package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegimenStep1PageObject extends BasePageObject<RegimenStep1PageObject> {
    private By header = By.cssSelector(".hcp-title");
    private By syringe = By.cssSelector("li.item:nth-child(2)");
    private By addSyringeB = By.cssSelector("li.item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)");
    private By continueB = By.cssSelector("button.button");
    private By popUpHeader = By.cssSelector(".modal-header");
    private By popUpAddB = By.cssSelector(".js-add-product-to-regimen");
    private By cartStatus = By.cssSelector(".block-subtitle");

    protected RegimenStep1PageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(syringe);
        waitForVisibilityOf(addSyringeB);
    }

    public void addProducts() throws InterruptedException {
        clickOn(addSyringeB);
        waitForVisibilityOf(popUpHeader);
        waitForVisibilityOf(popUpAddB);
        clickOn(popUpAddB);
        Thread.sleep(4000);
    }

    public RegimenStep2PageObject clickContinue() throws IOException {
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,100)");
        waitForVisibilityOf(continueB);
        clickOn(continueB);
        return new RegimenStep2PageObject(driver);
    }

    public String getCartStatus(){
        return getText(cartStatus);
    }
}
