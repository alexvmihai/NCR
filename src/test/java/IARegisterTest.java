import com.ncr.base.BaseTest;
import com.ncr.pages.DashboardIAPageObject;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.LoginPageObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class IARegisterTest extends BaseTest {
    @Test
    public void IARegisterTest() throws IOException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.waitForPageToLoad();
        LoginPageObject loginPage = homepage.openLoginPage();
        loginPage.waitForPageToLoad();
        String[] credentials = loginPage.getCredentials();
        loginPage.typeEmail(credentials[0]);
        loginPage.typePass(credentials[1]);
        DashboardIAPageObject dashboard = loginPage.submitIA();

    }
}
