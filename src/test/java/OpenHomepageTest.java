import com.ncr.base.BasePageObject;
import com.ncr.base.BaseTest;
import com.ncr.pages.HomepageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by alex.mihai on 4/3/2018.
 */
public class OpenHomepageTest extends BaseTest {
    @Test
    public void openHomepage() throws InterruptedException, IOException{
        HomepageObject homePage = new HomepageObject(driver);
        homePage.openHomepage();
        homePage.acceptPrompt();
        homePage.waitForPageToLoad();
        String expectedHCPText = "A healthcare professional is a suitably qualified person who is responsible for managing a patient’s nutrition requirements in the " +
                "community. For a healthcare professional to be involved with the NCare™ Program they first have to be registered" +
                " and approved by Nestlé Health Science NCare™ Program administrators.";
        String actualHCPText = homePage.getHCPCarouselText();
        String expectedPatientText = "A patient can be anyone who requires nutrition supplementation in the community. Usually a patient has had an acute or long term " +
                "requirement for nutrition supplementation and is under the care of a healthcare professional or institution.";
        String actualPatientText = homePage.getPatienCarouselText();
        String expectedInstitutionText = "An institution can be any facility that manages the health and wellbeing of a patient. An example is a local hospital or health centre." +
                " Within an institution there can be one or many healthcare professionals who work to provide patients the best possible nutritional care and management inside the " +
                "institution and upon entering the community. An institution may have nominated an institution administrator who has certain controls over the NCare™ Program within " +
                "the facility.";
        String actualInstitutionText = homePage.getInstitutionCarouselText();
        String expectedProductRangeText = "Nestlé Health Science offers nutritional solutions for people with specific dietary needs related to illnesses, disease states or the special " +
                "challenges of different life stages. We aim to pioneer the development and application of evolving science to create a new role for nutrition in disease management.";
        String actualProductRangeText = homePage.getProductRangeText();

        Assert.assertTrue(expectedHCPText.equals(actualHCPText), "The HCP text does not match !" + "\nExpected: " + expectedHCPText + "\nActual: " + actualHCPText);
        Assert.assertTrue(expectedPatientText.equals(actualPatientText), "The Patient text does not match!" + "\nExpected: " + expectedPatientText + "\nActual: " + actualPatientText);
        Assert.assertTrue(expectedInstitutionText.equals(actualInstitutionText), "The Institution text does not match !" + "\nExpected: " + expectedInstitutionText + "\nActual: " + actualInstitutionText);
        System.out.println("The carousel works fine !");
        Assert.assertTrue(expectedProductRangeText.equals(actualProductRangeText), "The Product Range text does not match !" + "\nExpected: " + expectedProductRangeText + "\nActual: " + actualProductRangeText);
        Assert.assertTrue(homePage.productsVisibility(), "Some products are not displayed !");
        String expectedFooterCopyrightText ="Medtronic, the Medtronic Logo and all other Medtronic brands and logos are trademarks owned by either Medtronic or a related company.\n" +
                "© 2017 Nestlé Health Science. All rights reserved.";

        String actualFooterText = homePage.getFooterCopyText();
        Assert.assertTrue(expectedFooterCopyrightText.equals(actualFooterText), "Footer Copyright text doesn't match !" + "\nExpected: " + expectedFooterCopyrightText + "\nActual: " + actualFooterText);
        String actualTitle = homePage.getTitle();
        String expectedTitle = "NCare";
        Assert.assertTrue(actualTitle.equals(expectedTitle), "The page title is not correct !" + "\nExpected: " + expectedTitle + "\nActual: " + actualTitle);
        System.out.println("The page title is correct !");
    }

}
