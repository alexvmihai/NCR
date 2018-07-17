import com.ncr.base.BaseTest;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.NutritionProductsPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NutritionProductsTest extends BaseTest {

    @Test
    public void NutritionProductsTest() throws IOException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.waitForPageToLoad();
        NutritionProductsPageObject nutritionPage = homepage.openNutritionProductsPage();
        nutritionPage.waitForNutritionPageToLoad();
        String expectedPageTitle = "Nutrition Products";
        String actualTitle = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle.equals(actualTitle), "The page title is not correct !" + "\nExpected: " + expectedPageTitle + "\nActual: " + actualTitle);
        System.out.println("Page title correct !!");

    }
}
