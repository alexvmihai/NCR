import com.ncr.base.BaseTest;
import com.ncr.pages.DashboardHCPPageObject;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.LoginPageObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SearchRegimenTest extends BaseTest {

    @Test
    public void SearchRegimenTest() throws InterruptedException, IOException {
        driver.manage().deleteAllCookies();
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        File latestFile = loginpage.lastModified("D:\\Access Credentials\\HCP_email\\");
        String username = loginpage.readCustomer(latestFile, "hcp_email");
        loginpage.typeEmail(username);
        loginpage.typePass("Smoketest1234/");
        DashboardHCPPageObject dashboard = loginpage.submit();
        dashboard.waitForPageToLoad();
        File lastModified = dashboard.lastModified("D:\\Access Credentials\\Patient_fullname\\");
        String patientName = dashboard.readCustomer(lastModified, "fullname");
        dashboard.searchRegimen(patientName);
    }
}
