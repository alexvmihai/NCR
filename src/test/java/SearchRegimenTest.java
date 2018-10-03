import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
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
        RegimenDetailsPageObject regimenDetailsPage = dashboard.clickRegimenAutocomplete();
        regimenDetailsPage.waitForPageToLoad();
        String actualTitle = regimenDetailsPage.getTitle();
        String expectedTitle = "Regimen Details";
        Assert.assertTrue(actualTitle.equals(expectedTitle), "The header title is not correct !" + "\nExpected: " + expectedTitle + "\nActual: " + actualTitle);
        String actualName = regimenDetailsPage.getPatientName();
        String expectedName = patientName;
        Assert.assertTrue(expectedName.equals(actualName), "The patient name does not match !" + "\nExpected: " + expectedName + "\nActual: " + actualName);
        //test search button functionality
        DashboardHCPPageObject dashboard2 = regimenDetailsPage.clickDashboardTab();
        dashboard2.waitForPageToLoad();
        dashboard2.searchRegimen(patientName);
        RegimenManagementPageObject regimenManagementPage = dashboard2.clickSearchRegimenButton();
        regimenManagementPage.waitForPageToLoad();
        String expectedHeaderTitle = "Regimen Management";
        String actualHeaderTitle = regimenManagementPage.getHeaderTitle();
        Assert.assertTrue(expectedHeaderTitle.equals(actualHeaderTitle), "The header title is not correct !" + "\nExpected: " + expectedHeaderTitle + "\nActual: " + actualHeaderTitle);
        RegimenDetailsPageObject regimenDetailsPage2 = regimenManagementPage.viewRegimen();
        regimenDetailsPage2.waitForPageToLoad();
        String actualTitle2 = regimenDetailsPage.getTitle();
        String expectedTitle2 = "Regimen Details";
        Assert.assertTrue(actualTitle2.equals(expectedTitle2), "The header title is not correct !" + "\nExpected: " + expectedTitle2 + "\nActual: " + actualTitle2);
        String actualName2 = regimenDetailsPage.getPatientName();
        String expectedName2 = patientName;
        Assert.assertTrue(expectedName2.equals(actualName2), "The patient name does not match !" + "\nExpected: " + expectedName2 + "\nActual: " + actualName2);
        System.out.println("Test passed !");


    }
}
