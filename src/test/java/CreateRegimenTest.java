import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CreateRegimenTest extends BaseTest {
    @Test(priority = 3)
    public void CreateRegimenTest() throws IOException, InterruptedException {
        driver.manage().deleteAllCookies();
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        File latestFile = loginpage.lastModified("D:\\Access Credentials\\HCP_email\\");
        String username = loginpage.readCustomer(latestFile, "hcp_email");
        loginpage.typeEmail(username);
        loginpage.typePass("Smoketest1234/");
        DashboardHCPPageObject dashboard = loginpage.submit();
        dashboard.waitForPageToLoad();
        File lastModified = dashboard.lastModified("D:\\Access Credentials\\HCP_lastname\\");
        String hcpLastname = dashboard.readCustomer(lastModified, "lastname");
        String expectedMsg = "Welcome, Mr Alex " + hcpLastname + "!";
        String actualMsg = dashboard.getWelcomeText();
        Assert.assertTrue(expectedMsg.equals(actualMsg), "Welcome message not correct !" + "\nExpected: " + expectedMsg + "\nActual: " + actualMsg);
        System.out.println("Test passed !");
        DashboardHCPPageObject dashboard2 = new DashboardHCPPageObject(driver);
        dashboard2.waitForPageToLoad();
        PatientRegisterPageObject patientRegisterPage = dashboard2.clickPatientCreate();
        patientRegisterPage.waitForPageToLoad();
        //Generate randomEmail
        long Random = Math.round(Math.random() * 1357987.0D);
        String email = "amihai_test" + Random + "@mailinator.com";
        String firstname = "Firstname" + Random;
        String lastname = "Lastname" + Random;
        String fullName = firstname + " " + lastname;
        String postcode = "";
        if(setEnv() == "PROD") {
            postcode = "0150";
        }else{
            postcode = "2000";
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%% Postcode is: " + postcode);
        patientRegisterPage.fillRegisterForm(firstname, lastname, "10101910", email, "2039192931", "Red Hill 24", "Sidney",
                "New South Wales", postcode, "Australia");
        PatientConfirmationPageObject patientConfirmPage = patientRegisterPage.submitForm();
        patientConfirmPage.waitForPageToLoad();

        String actualSuccessMsg = patientConfirmPage.getSuccessMsg();
        String actualConfirmText = patientConfirmPage.getConfirmationText();
        System.out.println(actualConfirmText);
        System.out.println(actualSuccessMsg);
        String expectedSuccessMsg = "PATIENT WAS SUCCESSFULLY CREATED";
        String expectedConfirmText = "This patient " + firstname + " " + lastname + " has been created.\n" +
                "Now, you can view the profile or create a regimen for this patient.";
        Assert.assertTrue(actualSuccessMsg.equals(expectedSuccessMsg), "The success message does not match !" + "\nExpected: " + expectedSuccessMsg + "\nActual: " + actualSuccessMsg);
        Assert.assertTrue(actualConfirmText.equals(expectedConfirmText), "The confirmation text does not match !" + "\nExpected: " + expectedConfirmText + "\nActual: " + actualConfirmText);
        System.out.println("Patient Created successfully !");

        PatientConfirmationPageObject patientConfirmPage2 = new PatientConfirmationPageObject(driver);
        patientConfirmPage2.waitForPageToLoad();
        RegimenStep1PageObject regimenStep1 = patientConfirmPage2.createRegimen();
        regimenStep1.waitForPageToLoad();
        System.out.println("Regimen Step 1 page loaded successfully !");
        regimenStep1.addProducts();
        String expectedCartStatus = "1 PRODUCTS SELECTED";
        String actualCartStatus = regimenStep1.getCartStatus();
        Assert.assertTrue(expectedCartStatus.equals(actualCartStatus), "The cart status is not correct !" + "\nExpected: " + expectedCartStatus + "\nActual: " + actualCartStatus);
        System.out.println("Cart Status correct ! The product was successfully added !");
        RegimenStep2PageObject regimenStep2 = regimenStep1.clickContinue();
        regimenStep2.waitForPageToLoad();
        System.out.println("RegimenStep2 page successfully loaded !");
        regimenStep2.selectShipping();
        regimenStep2.selectStartDate();
        Thread.sleep(5000);
        System.out.println("Clicked first !");

        RegimenSavedPageObject regimenSavedPage = regimenStep2.submitRegimen();
        System.out.println("Clicked !!");
        regimenSavedPage.waitForPageToLoad();
        String expectedText = "This regimen is complete and active.\n" +
                "You will be able to edit it later if required.";
        String actualText = regimenSavedPage.getConfirmationText();
        patientConfirmPage.writeCustomer(fullName, "Patients", "D:\\Access Credentials\\Patient_fullname\\", "fullname");
        Assert.assertTrue(expectedText.equals(actualText), "The confirmation message is not correct !" + "\nExpected: " + expectedText + "\nActual: " + actualText);
        System.out.println("Regimen created successfully !");

    }
}
