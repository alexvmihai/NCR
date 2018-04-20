import com.ncr.base.BaseTest;
import com.ncr.pages.DashboardHCPPageObject;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.LoginPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by alex.mihai on 4/3/2018.
 */
public class LoginTestPositiveHCP extends BaseTest{
    @Test
    public void loginTestPositive() throws IOException, InterruptedException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        String[] credentials = loginpage.getCredentials();
        loginpage.typeEmail(credentials[0]);
        loginpage.typePass(credentials[1]);
        DashboardHCPPageObject dashboard = loginpage.submit();
        dashboard.waitForPageToLoad();
        String expectedMsg = dashboard.welcomeMsg();
        String actualMsg = dashboard.getWelcomeText();
        Assert.assertTrue(expectedMsg.equals(actualMsg), "Welcome message not correct !" + "\nExpected: " + expectedMsg + "\nActual: " + actualMsg);
        System.out.println("Test passed !");
    }
}
