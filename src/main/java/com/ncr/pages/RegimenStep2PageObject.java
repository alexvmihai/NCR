package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegimenStep2PageObject extends BasePageObject<RegimenStep2PageObject> {
    private By step2Bread = By.cssSelector(".create-regimen-step-2 > span:nth-child(1)");
    private By addMoreProductsB = By.cssSelector(".cr-action-add");
    private By stepNumber = By.cssSelector("li.active:nth-child(2)");

    protected RegimenStep2PageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(step2Bread);
        waitForVisibilityOf(addMoreProductsB);
        waitForVisibilityOf(stepNumber);
    }
}
