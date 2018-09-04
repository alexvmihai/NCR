import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;

public class BEHCPRegisterTest extends BaseTest {
    @Test
    public void BEHCPRegisterTest() throws InterruptedException, IOException {
        BackEndLoginPageObject beLoginPage = new BackEndLoginPageObject(driver);
        beLoginPage.openLoginPage();
        String[] credentials = beLoginPage.getAdminCredentials();
        beLoginPage.typeUsername(credentials[0]);
        beLoginPage.typePassword(credentials[1]);
        BackEndDashboardPageObject dashboard = beLoginPage.login();
        Thread.sleep(5000);
        if(dashboard.isPopUpPresent()){
            dashboard.closePopUp();
        } else {
            System.out.println("No pop-up here !");
        }
        dashboard.waitForPageToLoad();
        dashboard.mouseOverCustomers();
        BEManageCustomersPageObject manageCustomers = dashboard.clickManageCustomers();
        manageCustomers.waitForPageToLoad();
        BENewCustomerPageObject newHCP = manageCustomers.addCustomer();
        newHCP.waitForPageToLoad();
        String expectedHeader = "New Customer";
        String actualHeader = newHCP.getHeaderText();
        Assert.assertTrue(expectedHeader.equals(actualHeader), "The header is not correct !" + "\nExpected: " + expectedHeader + "\nActual: " + actualHeader);
        long Random = Math.round(Math.random() * 1357987.0D);
        String email = "alex.mihai.optaros+" + Random + "@gmail.com";
        String firstname = "Auto_Firstname" + Random;
        String lastname = "Auto_Lastname" + Random;
        String password = "Ncare1234/";
        newHCP.fillForm("Main Website", "HCP", "NCare Test Hospital", firstname, lastname, email, password);
        BECustomerInfoPageObject infoPage = newHCP.saveContinue();
        infoPage.waitForPageToLoad();
        String expectedSaveMsg = "The customer has been saved.";
        String actualSaveMsg = infoPage.getSuccessMessage();
        Assert.assertTrue(expectedSaveMsg.equals(actualSaveMsg), "Success Msg not correct !" + "\nExpected: " + expectedSaveMsg + "\nActual: " + actualSaveMsg);
        String expectedName = firstname + " " + lastname;
        String actualName = infoPage.getHeaderText();
        Assert.assertTrue(expectedName.equals(actualName), "The name is not correct !" + "\nExpected: " + expectedName +"\nActual: " + actualName);
        String expectedType = "HCP";
        String actualType = infoPage.getCustomerType();
        Assert.assertTrue(expectedType.equals(actualType), "The type is not correct !" + "\nExpected: " + expectedType + "\nActual: " + actualType);
        System.out.println("The account is: " + email);

        //FE validation

        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        loginpage.typeEmail(email);
        loginpage.typePass(password);
        DashboardHCPPageObject dashboardFE = loginpage.submit();
        dashboardFE.waitForPageToLoad();
        String welcomeMsg = dashboardFE.getWelcomeText();
        Assert.assertTrue(welcomeMsg.contains(firstname), "The firstname is not present in the welcome msg !");
        Assert.assertTrue(welcomeMsg.contains(lastname), "The lastname is not present in the welcome msg !");
        String url = dashboardFE.getCurrentURL();
        String url2 = url.substring(url.length() - 4);
        Assert.assertTrue(url2.equalsIgnoreCase("hcp/"), "The url is not correct !");




    }
}
