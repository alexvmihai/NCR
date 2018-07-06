import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class IARegisterTest extends BaseTest {
    @Test
    public void IARegisterTest() throws IOException, InterruptedException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.waitForPageToLoad();
        LoginPageObject loginPage = homepage.openLoginPage();
        loginPage.waitForPageToLoad();
        String[] credentials = loginPage.getCredentials();
        loginPage.typeEmail(credentials[0]);
        loginPage.typePass(credentials[1]);
        DashboardIAPageObject dashboard = loginPage.submitIA();
        dashboard.waitForPageToLoad();
        IAProfilePageObject profilePage = dashboard.clickProfile();
        profilePage.waitForPageToLoad();
        profilePage.clickManageIA();
        profilePage.createIA();
        long Random = Math.round(Math.random() * 1357987.0D);
        String email = "amihai_IA" + Random + "@mailinator.com";
        profilePage.fillForm("Auto", "IA", email, "123231231231", "Institution Admin");
        profilePage.submitForm();
        profilePage.waitForPageToLoad();
        String actualMsg = profilePage.getSuccessMsg();
        String expectedMsg = "IA WAS CREATED SUCCESSFULLY";
        Assert.assertTrue(actualMsg.equals(expectedMsg), "The messages do not match !" + "\nExpected: " + expectedMsg + "\nActual: " + actualMsg);

        //reset pass
        HomepageObject homepage2 = profilePage.logout();
        homepage2.waitForPageToLoad();
        LoginPageObject loginPage2 = homepage2.openLoginPage();
        loginPage2.waitForPageToLoad();
        ForgotPasswordPageObject forgotPassPage = loginPage2.clickResetPass();
        forgotPassPage.waitForPageToLoad();
        String actualHeader = forgotPassPage.getHeader().toUpperCase();
        String expectedHeader = "Forgot Your Password".toUpperCase();
        Assert.assertTrue(expectedHeader.equals(actualHeader), "The header is not correct !" + "\nExpected: " + expectedHeader + "\nActual: " + actualHeader);
        forgotPassPage.typeEmail(email);
        LoginPageObject loginPage3 = forgotPassPage.submit();
        loginPage3.waitForPageToLoad();
        String actualResetPassMsg = loginPage3.getPassResetMsg().toUpperCase();
        String expectedResetPassMsg = "If there is an account associated with this email address you will receive an email with a link to reset your password.".toUpperCase();
        Assert.assertTrue(actualResetPassMsg.equals(expectedResetPassMsg), "The reset pass success msg is not correct !" + "\nExpected: " + expectedResetPassMsg + "\nActual: " + actualResetPassMsg);


        //open link from email
    }
}
