import com.ncr.pages.PDFPageObject;
import com.ncr.pages.RegimenSavedPageObject;
import org.testng.annotations.Test;

import java.io.IOException;

public class PrintRegimenTest extends CreateRegimenTest {
    @Test(priority=4)
    public void PrintRegimenTest() throws IOException {
        RegimenSavedPageObject regimenSavedPage = new RegimenSavedPageObject(driver);
        regimenSavedPage.waitForPageToLoad();
        PDFPageObject pdfPage = regimenSavedPage.print();
        //Switch to new tab
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        pdfPage.
    }
}
