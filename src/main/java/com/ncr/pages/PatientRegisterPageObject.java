package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.eclipse.jetty.util.IO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PatientRegisterPageObject extends BasePageObject<PatientRegisterPageObject> {
    private By header = By.cssSelector(".hcp-title");
    private By firstnameF = By.cssSelector("#firstname");
    private By lastnameF = By.cssSelector("#lastname");
    private By dobF = By.cssSelector("#dob");
    private By emailF = By.cssSelector("#email");
    private By contactF = By.cssSelector("#mobile");
    private By addressF = By.cssSelector("#shipping\\:street1");
    private By townF = By.cssSelector("#shipping\\:city");
    private By stateF = By.cssSelector("#shipping\\:region_id");
    private By postcodeF = By.cssSelector("#shipping\\:postcode");
    private By countryF = By.cssSelector("#shipping\\:country_id");
    private By consent = By.cssSelector("#label-privacy-policy > p:nth-child(1)");
    private By confirmB = By.cssSelector("button.button:nth-child(2)");




    protected PatientRegisterPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void waitForPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(firstnameF);
        waitForVisibilityOf(lastnameF);
    }

    public void fillRegisterForm(String firstname, String lastname, String dob, String email, String number, String address, String town, String state,
                                 String postcode, String country){
        System.out.println("Typing patient details...");
        type(firstname, firstnameF);
        type(lastname, lastnameF);
        clickOn(dobF);
        type(dob, dobF);
        type(email, emailF);
        type(number, contactF);
        type(address, addressF);
        type(town, townF);
        type(state, stateF);
        type(postcode, postcodeF);
        type(country, countryF);
        clickOn(consent);
    }

    public PatientConfirmationPageObject submitForm() throws IOException {
        clickOn(confirmB);
        return new PatientConfirmationPageObject(driver);
    }

}
