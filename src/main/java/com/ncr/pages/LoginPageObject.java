package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by alex.mihai on 4/3/2018.
 */
public class LoginPageObject extends BasePageObject<LoginPageObject> {
    private By emailF = By.cssSelector("#email");
    private By passwordF = By.cssSelector("#pass");
    private By forgotPass = By.cssSelector("a.right");
    private By loginB = By.cssSelector("#send2");
    private By backB = By.cssSelector(".back-link > a:nth-child(1)");
    private By header = By.cssSelector(".page-title > h1:nth-child(1)");
    private By leftBlock = By.cssSelector(".why-register");
    private By errorMsg = By.cssSelector(".error-msg > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)");

    protected LoginPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(emailF);
        waitForVisibilityOf(passwordF);
        waitForVisibilityOf(forgotPass);
        waitForVisibilityOf(loginB);
        waitForVisibilityOf(backB);
        waitForVisibilityOf(header);
        waitForVisibilityOf(leftBlock);
        System.out.println("Login Page loaded successfully !");
    }

    public void typeEmail(String email){
        type(email, emailF);
    }

    public void typePass (String pass){
        type(pass, passwordF);
    }

    public DashboardHCPPageObject submit() throws IOException {
        clickOn(loginB);
        return new DashboardHCPPageObject(driver);
    }

    public DashboardIAPageObject submitIA() throws IOException{
        clickOn(loginB);
        return new DashboardIAPageObject(driver);
    }

    public String getErrorText(){
        waitForVisibilityOf(errorMsg);
        return getText(errorMsg);
    }



}
