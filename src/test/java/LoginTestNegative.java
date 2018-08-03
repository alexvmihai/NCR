import com.ncr.base.BaseTest;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.LoginPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestNegative extends BaseTest {
    @Test
    public void LoginTestNegative() throws IOException, InterruptedException {
        driver.manage().deleteAllCookies();
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        loginpage.typeEmail("wrong@email.com");
        loginpage.typePass("notgonnawork");
        loginpage.submit();
        String expectedError = "the entered email address and password do not match our records. please check your details and try again.";
        String actualError = loginpage.getErrorText().toLowerCase();
        Assert.assertTrue(expectedError.equals(actualError), "The error message is not correct !" + "\nExpected: " + expectedError + "\nActual: " + actualError);
        System.out.println("Test passed !");

    }
}
