package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class IAProfilePageObject extends BasePageObject<IAProfilePageObject> {
    private By personalInfoTab = By.cssSelector(".tab-personal > a:nth-child(1)");
    private By manageIA = By.cssSelector(".tab-profile > a:nth-child(1)");
    private By archivedHCP = By.cssSelector(".tab-archive > a:nth-child(1)");
    private By changeAvatar = By.cssSelector("div.pseudo-file:nth-child(2) > label:nth-child(1) > span:nth-child(1)");
    private By createNewIA = By.cssSelector(".ia-manage > div:nth-child(1) > button:nth-child(2)");
    private By successMsg = By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)");
    private By logoutB = By.cssSelector("li.first > a:nth-child(1)");
    //RegisterForm
    private By titleF = By.cssSelector("#prefix");
    private By firstnameF = By.cssSelector("#firstname");
    private By lastnameF = By.cssSelector("#lastname");
    private By emailF = By.cssSelector("#email");
    private By contactF = By.cssSelector("#mobile");
    private By jobF = By.cssSelector("#job_title");
    private By submitB = By.cssSelector("#billing-buttons-container > button:nth-child(2)");



    protected IAProfilePageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
//        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,-200)");
        waitForVisibilityOf(personalInfoTab);
        waitForVisibilityOf(manageIA);
        waitForVisibilityOf(archivedHCP);
        waitForVisibilityOf(changeAvatar);
        System.out.println("Profile Page loaded successfully !");
    }

    public void clickManageIA () throws InterruptedException {
        clickOn(manageIA);
        Thread.sleep(3000);
    }

    public void createIA() throws InterruptedException {
        waitForVisibilityOf(createNewIA);
        clickOn(createNewIA);
        Thread.sleep(4000);
        System.out.println("IA register form loaded successfully !");
    }

    public void fillForm(String firstname, String lastname, String email, String number, String job){
        waitForVisibilityOf(firstnameF);
        waitForVisibilityOf(lastnameF);
        type(firstname, firstnameF);
        type(lastname, lastnameF);
        type(email, emailF);
        type(number, contactF);
        type(job, jobF);
    }

    public void submitForm() throws InterruptedException {
        clickOn(submitB);
        Thread.sleep(10000);
    }

    public String getSuccessMsg(){
        return getText(successMsg);
    }

    public HomepageObject logout() throws InterruptedException, IOException {
        clickOn(logoutB);
        Thread.sleep(10000);
        return new HomepageObject(driver);
    }
}
