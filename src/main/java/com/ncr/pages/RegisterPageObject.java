package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.*;

import java.io.IOException;

public class RegisterPageObject extends BasePageObject<RegisterPageObject> {
    private By hcpHeader = By.cssSelector(".page-title > h1:nth-child(1)");
    private By institutionInfo = By.cssSelector(".ia-info-text > p:nth-child(1)");
    private By titleF = By.cssSelector("#prefix");
    private By firstnameF = By.cssSelector("#firstname");
    private By middlenameF = By.cssSelector("#middlename");
    private By lastnameF = By.cssSelector("#lastname");
    private By emailF = By.cssSelector("#email_address");
    private By institutionF = By.cssSelector("#ia");
    private By passwordF = By.cssSelector("#password");
    private By passwordConfirmF = By.cssSelector("#confirmation");
    private By termsF = By.cssSelector("#privacy-policy");
    private By institutionSuggestF = By.cssSelector("#\\31 243");
    private By submitButton = By.cssSelector("div.buttons-set:nth-child(7) > button:nth-child(1)");



    protected RegisterPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(institutionF);
        waitForVisibilityOf(emailF);
        waitForVisibilityOf(titleF);
        waitForVisibilityOf(firstnameF);
        System.out.println("Registration page loaded successfully !");
    }

    public void clickTerms(){
        WebElement checkbox = driver.findElement(By.cssSelector("#privacy-policy"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", checkbox);
    }

    public void fillForm(String title, String firstname, String lastname, String email, String institution, String password) throws InterruptedException {
        type(title, titleF);
        type(firstname, firstnameF);
        type(lastname, lastnameF);
        type(email, emailF);
        type(institution, institutionF);
        Thread.sleep(5000);
        clickOn(institutionSuggestF);
        type(password,passwordF);
        type(password, passwordConfirmF);
    }

    public RegistrationSuccessPageObject submitForm() throws IOException {
        clickOn(submitButton);
        return new RegistrationSuccessPageObject(driver);
    }


}
