import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class CreateCarerTest extends CreatePatientTest {
    @Test(priority=3)
    public void CreateCarerTest() throws IOException, InterruptedException {
        PatientConfirmationPageObject patientConfirmPage = new PatientConfirmationPageObject(driver);
        patientConfirmPage.waitForPageToLoad();
        String confirmPatientName = patientConfirmPage.getPatientName();
        System.out.println(confirmPatientName);
        PatientDetailsPageObject patientDetails = patientConfirmPage.clickPatientDetails();
        patientDetails.waitForPageToLoad();
        String detailsPatientName = patientDetails.getPatientName();
        System.out.println(detailsPatientName);
        Assert.assertTrue(confirmPatientName.equals(detailsPatientName), "Patient name not correct !" + "\nExpected: " + confirmPatientName
        + "\nActual: " + detailsPatientName);
        patientDetails.createCarer();
        //Generate randomName
        long Random = Math.round(Math.random() * 1357987.0D);
        String firstname = "carerFirstname" + Random ;
        String lastname = "carerLastname" + Random;
        String number = "1231131232";
        String relation = "Husband";
        patientDetails.fillCarerDetails(firstname, lastname, number, relation);
        patientDetails.saveCarer();
        String expectedMsg = "Patient's carer has been successfully saved".toLowerCase();
        String actualMsg = patientDetails.getConfirmation().toLowerCase();
        Assert.assertTrue(expectedMsg.equals(actualMsg), "Confirmation message not correct !" + "\nExpected: " + expectedMsg
        + "\nActual: " + actualMsg);

        //Check the account on BE
        BackEndLoginPageObject loginPage = new BackEndLoginPageObject(driver);
        loginPage.openLoginPage();
        loginPage.waitForPageToLoad();
        String[] credentials = loginPage.getAdminCredentials();
        loginPage.typeUsername(credentials[0]);
        loginPage.typePassword(credentials[1]);
        BackEndDashboardPageObject dashboard = loginPage.login();
        dashboard.waitForPageToLoad();
        dashboard.mouseOverCustomers();
        BEManageCustomersPageObject manageCustomers = dashboard.clickManageCustomers();
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



    }
}
