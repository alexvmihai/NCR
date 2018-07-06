package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class GmailPageObject extends BasePageObject<GmailPageObject> {

    public GmailPageObject(WebDriver driver) throws IOException {
        super(driver);
    }


    private String url = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    private By header = By.cssSelector("#headingText");
    private By goButton = By.cssSelector(".btn.btn-dark");
    private By emailSubject = By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div/div[6]/div/div[1]/div[2]/div/table/tbody/tr[1]/td[6]/div/div/div/span[1]/b");
    private By clickToActivate = By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td[1]/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[2]/div[2]/table/tbody/tr/td/table[2]/tbody/tr[1]/td/span/a[1]");

    private By resetPass = By.cssSelector("body > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > span:nth-child(1) > a:nth-child(1) > span:nth-child(1)");
    private By emailF = By.cssSelector("#identifierId");
    private By next1B = By.cssSelector("#identifierNext > content:nth-child(3) > span:nth-child(1)");
    private By passwordF = By.cssSelector("#password > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
    private By next2B = By.cssSelector("#passwordNext > content:nth-child(3) > span:nth-child(1)");
    private By doneB = By.cssSelector(".yKBrKe > div:nth-child(1) > content:nth-child(3)");
    private By composeB = By.cssSelector(".T-I-KE");
    private By gmailHeader = By.cssSelector("#\\:i > span:nth-child(1)");





    public void openGmail(){
        System.out.println("Opening homepage...");
        getPage(url);
    }

    public void waitForHomepageToLoad(){
        waitForVisibilityOf(header);
    }

    public void submitEmail() throws IOException, InterruptedException {
        System.out.println("Submitting email...");
        waitForVisibilityOf(emailF);
        String[] credentials = getMemberCredentials("D:\\Access Credentials\\opt_login.txt");
        type(credentials[0], emailF);
        clickOn(next1B);
        waitForVisibilityOf(passwordF);
        type(credentials[1], passwordF);
        Thread.sleep(3000);
        clickOn(next2B);
    }

    public ForgotPasswordPageObject clickToActivate() throws InterruptedException, IOException {
        waitForVisibilityOf(composeB);
        waitForVisibilityOf(gmailHeader);
        clickOn(emailSubject);
        Thread.sleep(4000);
        clickOn(clickToActivate);
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(5000);
        return new ForgotPasswordPageObject(driver);
    }


}
