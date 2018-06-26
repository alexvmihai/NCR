import com.ncr.pages.PDFPageObject;
import com.ncr.pages.RegimenSavedPageObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;


public class PrintRegimenTest extends CreateRegimenTest {
    @Test(priority=4)
    public void PrintRegimenTest() throws IOException, InterruptedException {
        RegimenSavedPageObject regimenSavedPage = new RegimenSavedPageObject(driver);
        regimenSavedPage.waitForPageToLoad();
        PDFPageObject pdfPage = regimenSavedPage.print();
        Thread.sleep(9000);
        //Switch to new tab
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        String currentURL = driver.getCurrentUrl();
        final String loginPass = "optaros:opt1234@";
        final String login = "optaros";
        final String pass = "opt1234";
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, pass.toCharArray());
            }
        });

        pdfPage.clickDownload();


//        System.out.println(a + "-----" + b);
//        pdfPage.savePDF(finalUrl);
//        pdfPage.savePDF(currentURL);
        System.out.println(currentURL);
        File lastFile = pdfPage.lastModified("D:\\PDF");
        Thread.sleep(5000);
        pdfPage.checkPDFContent(lastFile);
    }
}
