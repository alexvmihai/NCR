import com.ncr.base.BaseTest;
import com.ncr.pages.DashboardHCPPageObject;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.LoginPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
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
    }
}
