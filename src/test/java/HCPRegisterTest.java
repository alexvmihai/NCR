import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HCPRegisterTest extends BaseTest {
    @Test
    public void HCPRegister() throws IOException, InterruptedException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        RegisterPageObject registerPage = homepage.openRegisterPage();
        registerPage.waitForPageToLoad();
        registerPage.clickTerms();
        //Generate random email
        long Random = Math.round(Math.random() * 1357987.0D);
        String email = "amihai_test" + Random + "@mailinator.com";
        String title = "Mr";
        String firstname = "Alex";
        String lastname = "Automated";
        registerPage.fillForm(title, firstname, lastname, email, "NCare Test Hospital", "Smoketest1234/");
        RegistrationSuccessPageObject succesPage = registerPage.submitForm();
        succesPage.waitForPageToLoad();
        String expectedHeaderText = "REGISTRATION COMPLETED";
        String expectedText = "Thanks for registering.\n" +
                "Your account will be reviewed shortly.\n" +
                "You will receive an email upon approval.";
        String actualHeaderText = succesPage.getHeaderText();
        String actualText = succesPage.getText();
        Assert.assertTrue(actualHeaderText.equals(expectedHeaderText), "Header does not match !" + "\nExpected: " + expectedHeaderText + "\nActual: " + actualHeaderText);
        Assert.assertTrue(expectedText.equals(actualText), "Text does not match !" + "\nExpected: " + expectedText + "\nActual: " + actualText);

        //Activate the account from BE admin
        BackEndLoginPageObject beLoginPage = new BackEndLoginPageObject(driver);
        beLoginPage.openLoginPage();
        String[] credentials = beLoginPage.getAdminCredentials();
        beLoginPage.typeUsername(credentials[0]);
        beLoginPage.typePassword(credentials[1]);
        BackEndDashboardPageObject dashboard = beLoginPage.login();
        dashboard.waitForPageToLoad();
        dashboard.mouseOverCustomers();
        BEManageCustomersPageObject manageCustomers = dashboard.clickManageCustomers();
        manageCustomers.waitForPageToLoad();
        manageCustomers.searchByEmail(email);
        BECustomerInfoPageObject customerInfoPage = manageCustomers.editCustomer();
        customerInfoPage.waitForPageToLoad();
        String expectedName = title + " " + firstname + " " + lastname;
        String actualName = customerInfoPage.getHeaderText();
        Assert.assertTrue(expectedName.equals(actualName), "Customer Name not correct !" + "\nExpected: " + expectedName + "\nActual: " + actualName);
        System.out.println("The customer name is correct !!");


    }
}
