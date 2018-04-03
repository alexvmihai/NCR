import com.ncr.base.BaseTest;
import com.ncr.pages.DashboardPageObject;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.LoginPageObject;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by alex.mihai on 4/3/2018.
 */
public class LoginTestPositive extends BaseTest{
    @Test
    public void loginTestPositive() throws IOException, InterruptedException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.acceptPrompt();
        homepage.waitForPageToLoad();
        LoginPageObject loginpage = homepage.openLoginPage();
        loginpage.waitForPageToLoad();
        String[] credentials = loginpage.getCredentials("D:\\Access Credentials\\ncare_login.txt");
        loginpage.typeEmail(credentials[0]);
        loginpage.typePass(credentials[1]);
        DashboardPageObject dashboard = loginpage.submit();




    }
}
