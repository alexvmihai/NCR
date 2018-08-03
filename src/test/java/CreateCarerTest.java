import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class CreateCarerTest extends BaseTest {
    @Test(priority=3)
    public void CreateCarerTest() throws IOException, InterruptedException {
        driver.manage().deleteAllCookies();
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        File latestFile = loginpage.lastModified("D:\\Access Credentials\\HCP_email\\");
        String username = loginpage.readHCP(latestFile);
        loginpage.typeEmail(username);
        loginpage.typePass("Smoketest1234/");
        DashboardHCPPageObject dashboard = loginpage.submit();
        dashboard.waitForPageToLoad();
        File lastModified = dashboard.lastModified("D:\\Access Credentials\\HCP_lastname\\");
        String hcpLastname = dashboard.readHCPlastname(lastModified);
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

        PatientConfirmationPageObject patientConfirmPage2 = new PatientConfirmationPageObject(driver);
        patientConfirmPage2.waitForPageToLoad();
        String confirmPatientName = patientConfirmPage2.getPatientName();
        System.out.println(confirmPatientName);
        PatientDetailsPageObject patientDetails = patientConfirmPage.clickPatientDetails();
        patientDetails.waitForPageToLoad();
        String detailsPatientName = patientDetails.getPatientName();
        System.out.println(detailsPatientName);
        Assert.assertTrue(confirmPatientName.equals(detailsPatientName), "Patient name not correct !" + "\nExpected: " + confirmPatientName
        + "\nActual: " + detailsPatientName);
        patientDetails.createCarer();
        //Generate randomName
        long Random2 = Math.round(Math.random() * 1357987.0D);
        String firstname2 = "carerFirstname" + Random ;
        String lastname2 = "carerLastname" + Random;
        String number = "1231131232";
        String relation = "Husband";
        patientDetails.fillCarerDetails(firstname, lastname, number, relation);
        patientDetails.saveCarer();
        String expectedMsg2 = "Patient's carer has been successfully saved".toLowerCase();
        String actualMsg2 = patientDetails.getConfirmation().toLowerCase();
        Assert.assertTrue(expectedMsg.equals(actualMsg), "Confirmation message not correct !" + "\nExpected: " + expectedMsg
        + "\nActual: " + actualMsg);
        String email2 = patientDetails.getEmail();

        //Check the account on BE
        BackEndLoginPageObject loginPage = new BackEndLoginPageObject(driver);
        loginPage.openLoginPage();
        loginPage.waitForPageToLoad();
        String[] credentials = loginPage.getAdminCredentials();
        loginPage.typeUsername(credentials[0]);
        loginPage.typePassword(credentials[1]);
        BackEndDashboardPageObject dashboard3 = loginPage.login();
        dashboard3.waitForPageToLoad();
        dashboard3.mouseOverCustomers();
        BEManageCustomersPageObject manageCustomers = dashboard3.clickManageCustomers();
        manageCustomers.waitForPageToLoad();
        String fullName = firstname + " " + lastname;
        manageCustomers.searchByName(fullName);
        BECustomerInfoPageObject editCustomer = manageCustomers.editCustomer();
        editCustomer.openAccountInfo();
        String expectedType = "Carer";
        String actualType = editCustomer.getCustomerType();
        Assert.assertTrue(expectedType.equals(actualType), "Customer type not correct !" + "\nExpected: " + expectedType
        + "\nActual: " + actualType);
        String actualName = editCustomer.getHeaderText();
        System.out.println(actualName);
        Assert.assertTrue(fullName.equals(actualName), "Patient Name is not correct on BE !" + "\nExpected: " + fullName
        + "\nActual: " + actualName);

        //email validation
        String beEmail = editCustomer.getBEemail();
        System.out.println(beEmail);
        Assert.assertTrue(email2.equals(beEmail), "Email not correct !" + "\nFrontEnd: " + email2 + "\nBackEnd: " + beEmail);

    }
}
