import com.ncr.base.BaseTest;
import com.ncr.pages.HomepageObject;
import com.ncr.pages.NutritionProductsPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NutritionProductsTest extends BaseTest {

    @Test
    public void NutritionProductsTest() throws IOException, InterruptedException {
        HomepageObject homepage = new HomepageObject(driver);
        homepage.openHomepage();
        homepage.waitForPageToLoad();
        NutritionProductsPageObject nutritionPage = homepage.openNutritionProductsPage();
        nutritionPage.waitForNutritionPageToLoad();
        String expectedPageTitle = "Nutrition Products";
        String actualTitle = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle.equals(actualTitle), "The page title is not correct !" + "\nExpected: " + expectedPageTitle + "\nActual: " + actualTitle);
        System.out.println("Page title correct !!");

        nutritionPage.clickDysphagia();
        String expectedTitle1 = nutritionPage.getCategTitleText();
        String actualTitle1 = "Dysphagia";
        Assert.assertTrue(expectedTitle1.equals(actualTitle1), "Dysphagia title not correct !" + "\nExpected: " + expectedTitle1 + "\nActual: " + actualTitle1);

        nutritionPage.clickOptifast();
        String expectedTitle2 = nutritionPage.getCategTitleText();
        String actualTitle2 = "Optifast";
        Assert.assertTrue(expectedTitle2.equals(actualTitle2), "Optifast title not correct !" + "\nExpected: " + expectedTitle2 + "\nActual: " + actualTitle2);

        nutritionPage.clickOral();
        String expectedTitle3 = nutritionPage.getCategTitleText();
        String actualTitle3 = "Oral Supplements";
        Assert.assertTrue(expectedTitle3.equals(actualTitle3), "Oral title not correct !" + "\nExpected: " + expectedTitle3 + "\nActual: " + actualTitle3);

        nutritionPage.clickPaediatrics();
        String expectedTitle4 = nutritionPage.getCategTitleText();
        String actualTitle4 = "Paediatrics";
        Assert.assertTrue(expectedTitle4.equals(actualTitle4), "Paediatrics title not correct !" + "\nExpected: " + expectedTitle4 + "\nActual: " + actualTitle4);

        nutritionPage.clickSustagen();
        String expectedTitle5 = nutritionPage.getCategTitleText();
        String actualTitle5 = "Sustagen";
        Assert.assertTrue(expectedTitle5.equals(actualTitle5), "Sustagen title not correct !" + "\nExpected: " + expectedTitle5 + "\nActual: " + actualTitle5);

        nutritionPage.clickTube();
        String expectedTitle6 = nutritionPage.getCategTitleText();
        String actualTitle6 = "Tube Feeds";
        Assert.assertTrue(expectedTitle6.equals(actualTitle6), "Tube title not correct !" + "\nExpected: " + expectedTitle6 + "\nActual: " + actualTitle6);

        nutritionPage.clickConsumables();
        String expectedTitle7 = nutritionPage.getCategTitleText();
        String actualTitle7 = "Consumables";
        Assert.assertTrue(expectedTitle7.equals(actualTitle7), "Consumables title not correct !" + "\nExpected: " + expectedTitle7 + "\nActual: " + actualTitle7);

        nutritionPage.clickGut();
        String expectedTitle8 = nutritionPage.getCategTitleText();
        String actualTitle8 = "Gut Health";
        Assert.assertTrue(expectedTitle8.equals(actualTitle8), "Gut title not correct !" + "\nExpected: " + expectedTitle8 + "\nActual: " + actualTitle8);

        //Add page title validation and breadcrumb validation



    }
}
