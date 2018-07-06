package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ForgotPasswordPageObject extends BasePageObject<ForgotPasswordPageObject> {
    private By submitB = By.cssSelector(".btn");
    private By emailF = By.cssSelector("#email_address");
    private By header = By.cssSelector(".page-title > h1:nth-child(1)");
    private By newPassF = By.cssSelector("#password");
    private By confirmPassF = By.cssSelector("#confirmation");
    private By submitPassB = By.cssSelector(".btn");


    protected ForgotPasswordPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void typeEmail(String email){
        type(email, emailF);
    }

    public void typePass(String pass){
        type(pass, newPassF);
        type(pass, confirmPassF);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
    }

    public String getHeader(){
        return getText(header);
    }

    public LoginPageObject submit() throws IOException {
        clickOn(submitB);
        return new LoginPageObject(driver);
    }


}
