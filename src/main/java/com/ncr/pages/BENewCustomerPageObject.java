package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class BENewCustomerPageObject extends BasePageObject<BENewCustomerPageObject> {
    private By header = By.cssSelector("div.content-header:nth-child(2) > h3:nth-child(1)");
    private By websiteF = By.cssSelector("#_accountwebsite_id");
    private By customerTypeF = By.cssSelector("#_accountcustomer_type");
    private By institutionF = By.cssSelector("#_accountinstitution_id");
    private By firstNameF = By.cssSelector("#_accountfirstname");
    private By lastNameF = By.cssSelector("#_accountlastname");
    private By emailF = By.cssSelector("#_accountemail");
    private By status = By.cssSelector("#_accountstatus");
    private By passwordF = By.cssSelector("#_accountpassword");
    private By saveContinueB = By.cssSelector("div.content-header:nth-child(2) > p:nth-child(2) > button:nth-child(4)");
    private By saveCustomerB = By.cssSelector("div.content-header:nth-child(2) > p:nth-child(2) > button:nth-child(3)");



    protected BENewCustomerPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void fillForm (String site, String customer, String hospital, String firstname, String lastname, String email, String password){
        Select website = new Select(driver.findElement(By.cssSelector("#_accountwebsite_id")));
        website.selectByVisibleText(site);
        Select type = new Select(driver.findElement(By.cssSelector("#_accountcustomer_type")));
        type.selectByVisibleText(customer);
        Select institution = new Select(driver.findElement(By.cssSelector("#_accountinstitution_id")));
        institution.selectByVisibleText(hospital);
        type(firstname, firstNameF);
        type(lastname, lastNameF);
        type(email, emailF);
        Select status = new Select(driver.findElement(By.cssSelector("#_accountstatus")));
        status.selectByVisibleText("Active");
        type(password, passwordF);

    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(websiteF);
        waitForVisibilityOf(saveContinueB);
    }

    public String getHeaderText(){
        return getText(header);
    }

    public BECustomerInfoPageObject saveContinue() throws IOException{
        clickOn(saveContinueB);
        return new BECustomerInfoPageObject(driver);
    }


}
