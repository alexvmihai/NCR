package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

/**
 * Created by alex.mihai on 4/3/2018.
 */
public class HomepageObject extends BasePageObject<HomepageObject> {
    private String URL = BaseURL();
    private By logo = By.cssSelector("a.logo > img:nth-child(1)");
    private By imagePpl = By.cssSelector(".block-promo-img > img:nth-child(1)");
    private By quickTourButton = By.cssSelector("a.btn:nth-child(1)");
    private By registerButton = By.cssSelector("a.btn:nth-child(2)");
    private By loginButton = By.cssSelector("li.last:nth-child(2) > a:nth-child(1)");
    private By about = By.cssSelector(".header-panel > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)");
    private By quickTourFeatures = By.cssSelector(".header-panel > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1) > span:nth-child(1)");
    private By nutritionProducts = By.cssSelector(".header-panel > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(3) > a:nth-child(1) > span:nth-child(1)");
    private By nhs = By.cssSelector(".header-panel > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(4) > a:nth-child(1) > span:nth-child(1)");
    private By resourceCentre = By.cssSelector(".header-panel > div:nth-child(1) > div:nth-child(1) > ul:nth-child(2) > li:nth-child(5) > a:nth-child(1) > span:nth-child(1)");
    private By hcpCarousel = By.cssSelector("dd.active > a:nth-child(1)");
    private By patientCarousel = By.cssSelector(".tabs > dd:nth-child(4) > a:nth-child(1)");
    private By institutionCarousel = By.cssSelector(".tabs > dd:nth-child(2) > a:nth-child(1)");
    private By hcpCarouselText = By.cssSelector("#panel2-2 > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)");
    private By patientCarouselText = By.cssSelector("#panel2-3 > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)");
    private By institutionCarouselText = By.cssSelector("#panel2-1 > div:nth-child(2) > div:nth-child(1) > p:nth-child(1)");
    private By productRangeText = By.cssSelector(".small-9 > p:nth-child(1)");
    private By productRageTitle = By.cssSelector(".block-featured > h2:nth-child(1)");
    private By moreProducts = By.cssSelector(".link-custom");
    private By product1 = By.cssSelector(".block-grid > li:nth-child(1)");
    private By product2 = By.cssSelector(".block-grid > li:nth-child(2)");
    private By product3 = By.cssSelector(".block-grid > li:nth-child(3)");
    private By product4 = By.cssSelector(".block-grid > li:nth-child(4)");
    private By footer = By.cssSelector(".footer-container");
    private By copyright = By.cssSelector(".footer-covidien");




    public HomepageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public void openHomepage(){
        getPage(URL);
        System.out.println("Opening the homepage...");
    }

    public void waitForPageToLoad (){
        waitForVisibilityOf(logo);
        waitForVisibilityOf(imagePpl);
        waitForVisibilityOf(quickTourButton);
        waitForVisibilityOf(registerButton);
        waitForVisibilityOf(loginButton);
        waitForVisibilityOf(about);
        waitForVisibilityOf(quickTourFeatures);
        waitForVisibilityOf(nutritionProducts);
        waitForVisibilityOf(nhs);
        waitForVisibilityOf(resourceCentre);
        waitForVisibilityOf(productRageTitle);
        waitForVisibilityOf(footer);
        System.out.println("Homepage loaded successfully !");
    }

    public String getHCPCarouselText(){
        clickOn(hcpCarousel);
        waitForVisibilityOf(hcpCarouselText);
        return getText(hcpCarouselText);
    }

    public String getPatienCarouselText(){
        clickOn(patientCarousel);
        waitForVisibilityOf(patientCarouselText);
        return getText(patientCarouselText);
    }

    public String getInstitutionCarouselText(){
        clickOn(institutionCarousel);
        waitForVisibilityOf(institutionCarouselText);
        return getText(institutionCarouselText);
    }

    public String getProductRangeText(){
        return getText(productRangeText);
    }

    public boolean productsVisibility(){
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0, 1200)");
        WebElement product1 = driver.findElement(By.cssSelector(".block-grid > li:nth-child(1)"));
        WebElement product2 = driver.findElement(By.cssSelector(".block-grid > li:nth-child(2)"));
//        WebElement product3 = driver.findElement(By.cssSelector(".block-grid > li:nth-child(3)"));
//        WebElement product4 = driver.findElement(By.cssSelector(".block-grid > li:nth-child(4)"));
        if(product1.isDisplayed() && product2.isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    public String getFooterCopyText(){
        return getText(copyright);
    }

    public LoginPageObject openLoginPage() throws IOException {
        clickOn(loginButton);
        return new LoginPageObject(driver);
    }


}
