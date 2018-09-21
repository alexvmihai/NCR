import com.ncr.base.BaseTest;
import com.ncr.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SearchPatientTest extends BaseTest {
    @Test
    public void searchPatient() throws IOException, InterruptedException {

        driver.manage().deleteAllCookies();
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
        PatientsTabPageObject patientsTab = dashboard.selectPatientsTab();
        patientsTab.waitForPageToLoad();
        String patientName = patientsTab.getPatientName();
        DashboardHCPPageObject dashboard2 = patientsTab.selectDashboardTab();
        dashboard2.waitForPageToLoad();
        dashboard2.searchPatient(patientName);
        PatientDetailsPageObject patientDetails = dashboard2.clickAutocomplete();
        patientDetails.waitForPageToLoad();
        String expectedPatientName = patientDetails.getPatientName();
        Assert.assertTrue(patientName.equals(expectedPatientName), "The patient name is not correct !" + "\nExpected: " + expectedPatientName + "\nActual: " + patientName);
        //test the search button flow
        DashboardHCPPageObject dashboard3 = patientDetails.selectDashboardTab();
        dashboard3.waitForPageToLoad();
        dashboard3.searchPatient(patientName);
        PatientsTabPageObject patientsTab2 = dashboard3.clickSearch();
        patientsTab2.waitForPageToLoad();
        String actualResult = patientsTab2.getPatientName();
        Assert.assertTrue(actualResult.equals(patientName), "Patient name not correct on the result list !" + "\nExpected: " + patientName + "\nActual: " + actualResult);
        System.out.println("Test passed !");



    }
}
