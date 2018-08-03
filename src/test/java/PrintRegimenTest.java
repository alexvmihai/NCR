import com.ncr.base.BaseTest;
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



public class PrintRegimenTest extends BaseTest {
    @Test(priority=4)
    public void PrintRegimenTest() throws IOException, InterruptedException, ParseException {
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
        File lastModified = dashboard.lastModified("D:\\Access Credentials\\HCP_lastname\\");
        String hcpLastname = dashboard.readHCPlastname(lastModified);
        String expectedMsg = "Welcome, Mr Alex " + hcpLastname + "!";
        String actualMsg = dashboard.getWelcomeText();
        Assert.assertTrue(expectedMsg.equals(actualMsg), "Welcome message not correct !" + "\nExpected: " + expectedMsg + "\nActual: " + actualMsg);
        System.out.println("Test passed !");
        DashboardHCPPageObject dashboard2 = new DashboardHCPPageObject(driver);
        dashboard2.waitForPageToLoad();
        PatientRegisterPageObject patientRegisterPage = dashboard2.clickPatientCreate();
        patientRegisterPage.waitForPageToLoad();
        //Generate randomEmail
        long Random = Math.round(Math.random() * 1357987.0D);
        String email = "amihai_test" + Random + "@mailinator.com";
        String firstname = "Firstname" + Random;
        String lastname = "Lastname" + Random;
        String postcode = "";
        if(setEnv() == "PROD") {
            postcode = "0150";
        }else{
            postcode = "3500";
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%% Postcode is: " + postcode);
        patientRegisterPage.fillRegisterForm(firstname, lastname, "10101910", email, "2039192931", "Red Hill 24", "Sidney",
                "New South Wales", postcode, "Australia");
        PatientConfirmationPageObject patientConfirmPage = patientRegisterPage.submitForm();
        patientConfirmPage.waitForPageToLoad();

        String actualSuccessMsg = patientConfirmPage.getSuccessMsg();
        String actualConfirmText = patientConfirmPage.getConfirmationText();
        System.out.println(actualConfirmText);
        System.out.println(actualSuccessMsg);
        String expectedSuccessMsg = "PATIENT WAS SUCCESSFULLY CREATED";
        String expectedConfirmText = "This patient " + firstname + " " + lastname + " has been created.\n" +
                "Now, you can view the profile or create a regimen for this patient.";
        Assert.assertTrue(actualSuccessMsg.equals(expectedSuccessMsg), "The success message does not match !" + "\nExpected: " + expectedSuccessMsg + "\nActual: " + actualSuccessMsg);
        Assert.assertTrue(actualConfirmText.equals(expectedConfirmText), "The confirmation text does not match !" + "\nExpected: " + expectedConfirmText + "\nActual: " + actualConfirmText);
        System.out.println("Patient Created successfully !");
        PatientConfirmationPageObject patientConfirmPage2 = new PatientConfirmationPageObject(driver);
        patientConfirmPage2.waitForPageToLoad();
        RegimenStep1PageObject regimenStep1 = patientConfirmPage2.createRegimen();
        regimenStep1.waitForPageToLoad();

        //Get patient credentials
        String info = regimenStep1.getPatientInfo();
        System.out.println("Patient info are : " + info);
        String[] info2 = info.split("\n");
        System.out.println(info2);
        String patientName = info2[0];
        String patientName2 = patientName.substring(5);
        String[] patientName3 = patientName2.split(" ");
        String firstnamePatient = patientName3[0];
        String lastnamePatient = patientName3[1];

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
        Format formatter2 = new SimpleDateFormat("yyyy/MM/dd");
        String finalDate = formatter2.format(date1);

        //Get the start date from the regimen page

        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Format formatter3 = new SimpleDateFormat("yyyy/MM/dd");
        String finalDate2 = formatter3.format(date2);

        //Get the frequency from the regimen page
        String freq1 = regimenStep2.getFrequency();
        String freq2 = freq1.substring(0,6);
        String finalFreq = freq2.toLowerCase();
        System.out.println("Frequency is : " + freq2);

        //Get the price
        String price = regimenStep2.getPrice();
        System.out.println("The price is: " + price);

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
        Thread.sleep(30000);
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
        Assert.assertTrue(pdfCOntent.contains("Set Start/Delivery Date: " + finalDate2), "Delivery Date is not correct on pdf !");
        System.out.println("Start date is correct on the pdf !");
        Assert.assertTrue(pdfCOntent.contains("Length: " + finalFreq +"(s)"), "Length of regimen is not correct on pdf !");
        System.out.println("Frequency is correct on the pdf !");
        Assert.assertTrue(pdfCOntent.contains("FIRST ORDER TOTAL: AUD " + price), "Price is not correct on pdf !");
        System.out.println("The price is correct on the pdf !");
        Assert.assertTrue(pdfCOntent.contains(firstnamePatient), "Patient firstname is not correct !");
        Assert.assertTrue(pdfCOntent.contains(lastnamePatient), "Patient lastname is not correct !");
        System.out.println("The patient name is also correct on the pdf !");

    }
}
