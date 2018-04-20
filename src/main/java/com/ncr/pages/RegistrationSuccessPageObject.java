package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegistrationSuccessPageObject extends BasePageObject<RegistrationSuccessPageObject> {
    private By header = By.cssSelector(".page-title > h1:nth-child(1)");
    private By text = By.cssSelector(".message > p:nth-child(1)");
    private By backButton = By.cssSelector(".home-btn");
    protected RegistrationSuccessPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(text);
        waitForVisibilityOf(backButton);
        System.out.println("Registration Success Page loaded successfully !");
    }

    public String getHeaderText(){
        return getText(header);
    }

    public String getText(){
        return getText(text);
    }
}
