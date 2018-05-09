import com.ncr.base.BaseTest;
import com.ncr.pages.DashboardHCPPageObject;
import com.ncr.pages.PatientConfirmationPageObject;
import com.ncr.pages.PatientRegisterPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreatePatientTest extends LoginTestPositiveHCP {
    @Test(priority=2)
    public void CreatePatientTest() throws IOException, InterruptedException {
        DashboardHCPPageObject dashboard = new DashboardHCPPageObject(driver);
        dashboard.waitForPageToLoad();
        PatientRegisterPageObject patientRegisterPage = dashboard.clickPatientCreate();
        patientRegisterPage.waitForPageToLoad();
        //Generate randomEmail
        long Random = Math.round(Math.random() * 1357987.0D);
        String email = "amihai_test" + Random + "@mailinator.com";
        String firstname = "Firstname" + Random;
        String lastname = "Lastname" + Random;
        patientRegisterPage.fillRegisterForm(firstname, lastname, "10101910", email, "2039192931", "Red Hill 24", "Sidney",
                "New South Wales", "0150", "Australia");
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
