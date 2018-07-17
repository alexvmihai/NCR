package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class NutritionProductsPageObject extends BasePageObject<NutritionProductsPageObject> {
    private By header = By.cssSelector(".page-title > h1:nth-child(1)");
    private By categTitle = By.cssSelector(".category-name");
    private By searchBox = By.cssSelector("#search");
    private By searchButton = By.cssSelector(".btn-search");
    private By tree = By.cssSelector(".category6 > span:nth-child(1)");
    //Navigation menu
    private By dysphagia = By.cssSelector("#narrow-by-list > li:nth-child(1) > a:nth-child(1)");
    private By optifast = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(2) > a:nth-child(1)");
    private By oral = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(3) > a:nth-child(1)");
    private By paediatrics = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(4) > a:nth-child(1)");
    private By sustagen = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(5) > a:nth-child(1)");
    private By tube = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(6) > a:nth-child(1)");
    private By consumables = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(7) > a:nth-child(1)");
    private By gut = By.cssSelector(".block-content > ol:nth-child(1) > li:nth-child(8) > a:nth-child(1)");
    //Filters
    private By view = By.cssSelector("div.toolbar:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > select:nth-child(1)");
    private By position = By.cssSelector("div.toolbar:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > select:nth-child(1)");
    private By viewAll = By.cssSelector("div.toolbar:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(3)");
    private By flavour = By.cssSelector("#narrow-by-list > dt:nth-child(1)");
    private By apple = By.cssSelector("dd.sln-type-checkbox:nth-child(2) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)");
    private By apple_prune = By.cssSelector("dd.sln-type-checkbox:nth-child(2) > ol:nth-child(1) > li:nth-child(2) > a:nth-child(1)");
    private By apple_straw = By.cssSelector("dd.sln-type-checkbox:nth-child(2) > ol:nth-child(1) > li:nth-child(3) > a:nth-child(1)");
    private By neutral = By.cssSelector("dd.sln-type-checkbox:nth-child(2) > ol:nth-child(1) > li:nth-child(4) > a:nth-child(1)");
    private By orange = By.cssSelector("dd.sln-type-checkbox:nth-child(2) > ol:nth-child(1) > li:nth-child(5) > a:nth-child(1)");
    private By unit = By.cssSelector("dt.active:nth-child(3) > span:nth-child(1)");
    private By caseF = By.cssSelector("dd.sln-type-checkbox:nth-child(4) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)");
    private By each = By.cssSelector("dd.sln-type-checkbox:nth-child(4) > ol:nth-child(1) > li:nth-child(2) > a:nth-child(1)");



    protected NutritionProductsPageObject(WebDriver driver) throws IOException {
        super(driver);
    }

    public String getPageTitle(){
        return getTitle();
    }

    public void waitForNutritionPageToLoad(){
        waitForVisibilityOf(header);
        waitForVisibilityOf(categTitle);
        waitForVisibilityOf(searchBox);
        waitForVisibilityOf(searchButton);
        System.out.println("Nutrition page loaded successfully !");
    }

    public String getCategTitleText(){
        return getText(categTitle);
    }

    public void clickDysphagia() throws InterruptedException {
        clickOn(dysphagia);
        Thread.sleep(10000);
    }

    public void clickOptifast() throws InterruptedException{
        clickOn(optifast);
        Thread.sleep(10000);
    }

    public void clickOral() throws InterruptedException{
        clickOn(oral);
        Thread.sleep(10000);
    }

    public void clickPaediatrics() throws InterruptedException{
        clickOn(paediatrics);
        Thread.sleep(10000);
    }

    public void clickSustagen() throws InterruptedException{
        clickOn(sustagen);
        Thread.sleep(10000);
    }

    public void clickTube() throws InterruptedException{
        clickOn(tube);
        Thread.sleep(10000);
    }

    public void clickConsumables() throws InterruptedException{
        clickOn(consumables);
        Thread.sleep(10000);
    }

    public void clickGut() throws InterruptedException{
        clickOn(gut);
        Thread.sleep(10000);
    }


}
