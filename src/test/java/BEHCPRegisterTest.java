import com.ncr.base.BaseTest;
import com.ncr.pages.BEManageCustomersPageObject;
import com.ncr.pages.BackEndDashboardPageObject;
import com.ncr.pages.BackEndLoginPageObject;
import org.testng.annotations.Test;

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

    }
}
