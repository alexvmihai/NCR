import com.ncr.pages.PDFPageObject;
import com.ncr.pages.RegimenSavedPageObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

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
        pdfPage.savePDF(currentURL);
        File lastFile = pdfPage.lastModified("D:\\PDF\\");
        pdfPage.checkPDFContent(lastFile);
    }
}
