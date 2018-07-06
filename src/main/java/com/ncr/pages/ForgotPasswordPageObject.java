package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ForgotPasswordPageObject extends BasePageObject<ForgotPasswordPageObject> {
    private By submitB = By.cssSelector(".btn");
    private By emailF = By.cssSelector("#email_address");
    private By header = By.cssSelector(".page-title > h1:nth-child(1)");

    protected ForgotPasswordPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void typeEmail(String email){
        type(email, emailF);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(submitB);
        waitForVisibilityOf(emailF);
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
