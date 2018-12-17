import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class HCPRegisterTest extends BaseTest {
    @Test
    public void HCPRegister() throws IOException, InterruptedException {
        driver.manage().deleteAllCookies();
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
        String lastname = "Automated" + Random;
        String password = "Smoketest1234/";
        registerPage.fillForm(title, firstname, lastname, email, "NCare Test Hospital", password);
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
        Thread.sleep(25000);
        if(dashboard.isPopUpPresent()){
            dashboard.closePopUp();
        } else {
            System.out.println("No pop-up here !");
        }
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
        customerInfoPage.openAccountInfo();
        customerInfoPage.setActive();
        customerInfoPage.saveCustomer();
        String expectedMessage = "The customer has been saved.";
        String actualMessage = customerInfoPage.getSuccessMessage();
        Assert.assertTrue(expectedMessage.equals(actualMessage), "The success message doesn't match !" + "\nExpected: " + expectedMessage + "\nActual: " + actualMessage);
        System.out.println("The success message matches !");

        //Log in with the new account
        HomepageObject homepage2 = new HomepageObject(driver);
        homepage2.openHomepage();
        homepage2.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginPage2 = homepage.openLoginPage();
        loginPage2.waitForPageToLoad();
        loginPage2.typePass(password);
        loginPage2.typeEmail(email);
        DashboardHCPPageObject dashboard2 = loginPage2.submit();
        dashboard2.waitForPageToLoad();
        String expectedHCPMsg = "Welcome, Mr Alex " + lastname +"!";
        String actualHCPMsg = dashboard2.getWelcomeText();
        Assert.assertTrue(expectedHCPMsg.equals(actualHCPMsg), "The dashboard message doesn't match !" + "\nExpected: " + expectedHCPMsg + "\nActual: " + actualHCPMsg);
        System.out.println("Your new account is: " + email + "\nPass: " + password);
        dashboard2.writeCustomer(email, "HCP_accounts", "D:\\Access Credentials\\HCP_email\\", "hcp_email");
        dashboard2.writeCustomer(lastname, "HCP_lastname", "D:\\Access Credentials\\HCP_lastname\\", "lastname");
        System.out.println("Test passed !");



    }
}
