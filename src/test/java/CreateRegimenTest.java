import com.ncr.pages.PatientConfirmationPageObject;
import com.ncr.pages.RegimenSavedPageObject;
import com.ncr.pages.RegimenStep1PageObject;
import com.ncr.pages.RegimenStep2PageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateRegimenTest extends CreatePatientTest{
    @Test(priority = 3)
    public void CreateRegimenTest() throws IOException, InterruptedException {
        PatientConfirmationPageObject patientConfirmPage = new PatientConfirmationPageObject(driver);
        patientConfirmPage.waitForPageToLoad();
        RegimenStep1PageObject regimenStep1 = patientConfirmPage.createRegimen();
        regimenStep1.waitForPageToLoad();
        System.out.println("Regimen Step 1 page loaded successfully !");
        regimenStep1.addProducts();
        String expectedCartStatus = "1 PRODUCTS SELECTED";
        String actualCartStatus = regimenStep1.getCartStatus();
        Assert.assertTrue(expectedCartStatus.equals(actualCartStatus), "The cart status is not correct !" + "\nExpected: " + expectedCartStatus + "\nActual: " + actualCartStatus);
        System.out.println("Cart Status correct ! The product was successfully added !");
        RegimenStep2PageObject regimenStep2 = regimenStep1.clickContinue();
        regimenStep2.waitForPageToLoad();
        System.out.println("RegimenStep2 page successfully loaded !");
        regimenStep2.selectShipping();
        regimenStep2.selectStartDate();
        if(regimenStep2.weekendPopExists){
            regimenStep2.closeWeekendPopup();
        } else {
            System.out.println("Moving on..");
        }
        RegimenSavedPageObject regimenSavedPage = regimenStep2.submitRegimen();
        regimenSavedPage.waitForPageToLoad();
        String expectedText = "This regimen is complete and active.\n" +
                "You will be able to edit it later if required.";
        String actualText = regimenSavedPage.getConfirmationText();
        Assert.assertTrue(expectedText.equals(actualText), "The confirmation message is not correct !" + "\nExpected: " + expectedText + "\nActual: " + actualText);
        System.out.println("Regimen created successfully, going back to the patient...");

        //To add the patient page part after the button is fixed


    }
}
