import com.ncr.pages.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class PrintRegimenTest extends CreatePatientTest {
    @Test(priority=4)
    public void PrintRegimenTest() throws IOException, InterruptedException, ParseException {
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
        regimenStep2.setFrequency();
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,500)");
        regimenStep2.selectShipping();
        String startDate = regimenStep2.selectStartDate();
        Thread.sleep(5000);

        //Get the expiry date from the regimen page
        String expiryDate = regimenStep2.getExpiry();
        String expiry2 = expiryDate.substring(14);
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(expiry2);
        System.out.println(date1 + " asta e data");
        Format formatter2 = new SimpleDateFormat("yyyy/MM/dd");
        String finalDate = formatter2.format(date1);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(finalDate + " asta e final");

        //Get the start date from the regimen page
        System.out.println(startDate + "this is the start date");
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        System.out.println(date2 + " asta e start date");
        Format formatter3 = new SimpleDateFormat("yyyy/MM/dd");
        String finalDate2 = formatter3.format(date2);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(finalDate2 + " asta e start final");

        //Get the frequency from the regimen page
        String freq1 = regimenStep2.getFrequency();
        String freq2 = freq1.substring(0,6);
        String finalFreq = freq2.toLowerCase();
        System.out.println("Frequency is : " + freq2);



        RegimenSavedPageObject regimenSavedPage = regimenStep2.submitRegimen();
        System.out.println("Clicked !!");
        regimenSavedPage.waitForPageToLoad();
        String expectedText = "This regimen is complete and active.\n" +
                "You will be able to edit it later if required.";
        String actualText = regimenSavedPage.getConfirmationText();
        Assert.assertTrue(expectedText.equals(actualText), "The confirmation message is not correct !" + "\nExpected: " + expectedText + "\nActual: " + actualText);
        System.out.println("Regimen created successfully !");

        //Print
        PDFPageObject pdfPage = regimenSavedPage.print();
        Thread.sleep(9000);
        //Switch to new tab
//        for(String winHandle : driver.getWindowHandles()){
//            driver.switchTo().window(winHandle);
//        }
//        String currentURL = driver.getCurrentUrl();
//        final String loginPass = "optaros:opt1234@";
//        final String login = "optaros";
//        final String pass = "opt1234";
//        Authenticator.setDefault(new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(login, pass.toCharArray());
//            }
//        });
//        pdfPage.clickDownload();
//        System.out.println(a + "-----" + b);
//        pdfPage.savePDF(finalUrl);
//        pdfPage.savePDF(currentURL);
//        System.out.println(currentURL);
        File lastFile = pdfPage.lastModified("D:\\PDF");
        Thread.sleep(5000);
        String pdfCOntent = pdfPage.checkPDFContent(lastFile);
        Assert.assertTrue(pdfCOntent.contains("Expiry Date: " + finalDate), "Expiry date not correct on pdf !");
        System.out.println("Expiry date appears on the pdf !");
        Assert.assertTrue(pdfCOntent.contains("Set Start/Delivery Date: " + finalDate2));
        System.out.println("Start date is correct on the pdf !");
        Assert.assertTrue(pdfCOntent.contains("Length: " + finalFreq +"(s)"));
        System.out.println("Frequency is correct on the pdf !");

    }
}
