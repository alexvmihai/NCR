package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class IAProfilePageObject extends BasePageObject<IAProfilePageObject> {
    private By personalInfoTab = By.cssSelector(".tab-personal > a:nth-child(1)");
    private By manageIA = By.cssSelector(".tab-profile > a:nth-child(1)");
    private By archivedHCP = By.cssSelector(".tab-archive > a:nth-child(1)");
    private By changeAvatar = By.cssSelector("div.pseudo-file:nth-child(2) > label:nth-child(1) > span:nth-child(1)");
    private By createNewIA = By.cssSelector(".ia-manage > div:nth-child(1) > button:nth-child(2)");
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
}
