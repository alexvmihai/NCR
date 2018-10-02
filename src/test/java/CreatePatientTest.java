import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CreatePatientTest extends BaseTest {
    @Test(priority=2)
    public void CreatePatientTest() throws IOException, InterruptedException {
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
            postcode = "3500";
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



    }
}
