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
        String expectedPageTitle1 = "Dysphagia - Nutrition Products";
        String actualPageTitle1 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle1.equals(actualPageTitle1), "Page title not correct !" + "\nExpected: " + expectedPageTitle1 + "\nActual: " + actualPageTitle1);
        String expectedCrumb1 = "Dysphagia";
        String actualCrumb1 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb1.equals(actualCrumb1), "Crumb text not correct !" + "\nExpected: " + expectedCrumb1 + "\nActual: " + actualCrumb1 );
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle1 + " category !");

        nutritionPage.clickOptifast();
        String expectedTitle2 = nutritionPage.getCategTitleText();
        String actualTitle2 = "Optifast";
        Assert.assertTrue(expectedTitle2.equals(actualTitle2), "Optifast title not correct !" + "\nExpected: " + expectedPageTitle1 + "\nActual: " + actualPageTitle1);
        String expectedPageTitle2 = "Optifast - Nutrition Products";
        String actualPageTitle2 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle2.equals(actualPageTitle2), "Page title not correct!" + "\nExpected: " + expectedPageTitle2 + "\nActual: " + actualPageTitle2);
        String expectedCrumb2 = "Optifast";
        String actualCrumb2 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb2.equals(actualCrumb2), "Crumb text not correct !" + "\nExpected: " + expectedCrumb2 + "\nActual: " + actualCrumb2);
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle2 + " category !");
        nutritionPage.sortName();
        String expectedName = "OPTIFAST® VLCD™ Bar 60g";
        String actualName = nutritionPage.getFirstProductText();
        Assert.assertTrue(expectedName.equals(actualName), "Name filter doesn't work !");



        nutritionPage.clickOral();
        String expectedTitle3 = nutritionPage.getCategTitleText();
        String actualTitle3 = "Oral Supplements";
        Assert.assertTrue(expectedTitle3.equals(actualTitle3), "Oral title not correct !" + "\nExpected: " + expectedTitle3 + "\nActual: " + actualTitle3);
        String expectedPageTitle3 = "Oral Supplements - Nutrition Products";
        String actualPageTitle3 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle3.equals(actualPageTitle3), "Page title not correct!" + "\nExpected: " + expectedPageTitle3 + "\nActual: " + actualPageTitle3);
        String expectedCrumb3 = "Oral Supplements";
        String actualCrumb3 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb3.equals(actualCrumb3), "Crumb text not correct !" + "\nExpected: " + expectedCrumb3 + "\nActual: " + actualCrumb3);
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle3 + " category !");


        nutritionPage.clickPaediatrics();
        String expectedTitle4 = nutritionPage.getCategTitleText();
        String actualTitle4 = "Paediatrics";
        Assert.assertTrue(expectedTitle4.equals(actualTitle4), "Paediatrics title not correct !" + "\nExpected: " + expectedTitle4 + "\nActual: " + actualTitle4);
        String expectedPageTitle4 = "Paediatrics - Nutrition Products";
        String actualPageTitle4 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle4.equals(actualPageTitle4), "Page title not correct !" + "\nExpected: " + expectedPageTitle4 + "\nActual: " + actualPageTitle4);
        String expectedCrumb4 = "Paediatrics";
        String actualCrumb4 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb4.equals(actualCrumb4), "Crumb text not correct !" + "\nExpected: " + expectedCrumb4 + "\nActual: " + actualCrumb4);
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle4 + " category !");


        nutritionPage.clickSustagen();
        String expectedTitle5 = nutritionPage.getCategTitleText();
        String actualTitle5 = "Sustagen";
        Assert.assertTrue(expectedTitle5.equals(actualTitle5), "Sustagen title not correct !" + "\nExpected: " + expectedTitle5 + "\nActual: " + actualTitle5);
        String expectedPageTitle5 = "Sustagen - Nutrition Products";
        String actualPageTitle5 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle5.equals(actualPageTitle5), "Page title not correct !" + "\nExpected: " + expectedPageTitle5 + "\nActual: " + actualPageTitle5);
        String expectedCrumb5 = "Sustagen";
        String actualCrumb5 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb5.equals(actualCrumb5), "Crumb text not correct !" + "\nExpected: " + expectedCrumb5 + "\nActual: " + actualCrumb5);
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle5 + " category !");


        nutritionPage.clickTube();
        String expectedTitle6 = nutritionPage.getCategTitleText();
        String actualTitle6 = "Tube Feeds";
        Assert.assertTrue(expectedTitle6.equals(actualTitle6), "Tube title not correct !" + "\nExpected: " + expectedTitle6 + "\nActual: " + actualTitle6);
        String expectedPageTitle6 = "Tube Feeds - Nutrition Products";
        String actualPageTitle6 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle6.equals(actualPageTitle6), "Page title not correct !" + "\nExpected: " + expectedPageTitle6 + "\nActual: " + actualPageTitle6);
        String expectedCrumb6 = "Tube Feeds";
        String actualCrumb6 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb6.equals(actualCrumb6), "Crumb text not correct !" + "\nExpected: " + expectedCrumb6 + "\nActual: " + actualCrumb6);
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle6 + " category !");


        nutritionPage.clickConsumables();
        String expectedTitle7 = nutritionPage.getCategTitleText();
        String actualTitle7 = "Consumables";
        Assert.assertTrue(expectedTitle7.equals(actualTitle7), "Consumables title not correct !" + "\nExpected: " + expectedTitle7 + "\nActual: " + actualTitle7);
        String expectedPageTitle7 = "Consumables - Nutrition Products";
        String actualPageTitle7 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle7.equals(actualPageTitle7), "Page title not correct !" + "\nExpected: " + expectedPageTitle7 + "\nActual: " + actualPageTitle7);
//        String expectedCrumb7 = "Nutrition Products / Consumables";
//        String actualCrumb7 = nutritionPage.getCrumbText();
//        Assert.assertTrue(expectedCrumb7.equals(actualCrumb7), "Crumb text not correct !" + "\nExpected: " + expectedCrumb7 + "\nActual: " + actualCrumb7);
//        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle7 + " category !");


        nutritionPage.clickGut();
        String expectedTitle8 = nutritionPage.getCategTitleText();
        String actualTitle8 = "Gut Health";
        Assert.assertTrue(expectedTitle8.equals(actualTitle8), "Gut title not correct !" + "\nExpected: " + expectedTitle8 + "\nActual: " + actualTitle8);
        String expectedPageTitle8 = "Gut Health - Nutrition Products";
        String actualPageTitle8 = nutritionPage.getPageTitle();
        Assert.assertTrue(expectedPageTitle8.equals(actualPageTitle8), "Page title not correct !" + "\nExpected: " + expectedPageTitle8 + "\nActual: " + actualPageTitle8);
        String expectedCrumb8 = "Gut Health";
        String actualCrumb8 = nutritionPage.getCrumbText();
        Assert.assertTrue(expectedCrumb8.equals(actualCrumb8), "Crumb text not correct !" + "\nExpected: " + expectedCrumb8 + "\nActual: " + actualCrumb8);
        Assert.assertTrue(nutritionPage.productsVisible(), "No products visible in the " + expectedTitle8 + " category !");


    }
}
