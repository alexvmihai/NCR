package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BackEndLoginPageObject extends BasePageObject<BackEndLoginPageObject> {
    private By usernameF = By.cssSelector("#username");
    private By passwordF = By.cssSelector("#login");
    private By loginButton = By.cssSelector(".form-button");
    private By forgotPassButton = By.cssSelector(".left");
    private By header = By.cssSelector(".login-form > h2:nth-child(2)");

    public BackEndLoginPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void openLoginPage() throws IOException {
        getPage(AdminURL());
        System.out.println("Opening the Admin page...");
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(usernameF);
        waitForVisibilityOf(passwordF);
        waitForVisibilityOf(loginButton);
        waitForVisibilityOf(forgotPassButton);
    }

    public void typeUsername(String username){
        type(username, usernameF);
    }

    public void typePassword(String password){
        type(password, passwordF);
    }

    public BackEndDashboardPageObject login () throws IOException {
        clickOn(loginButton);
        return new BackEndDashboardPageObject(driver);
    }

}
