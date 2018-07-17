package com.ncr.pages;

import com.ncr.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class NutritionProductsPageObject extends BasePageObject<NutritionProductsPageObject> {
    private By header = By.cssSelector(".page-title > h1:nth-child(1)");
    private By categTitle = By.cssSelector(".category-name > p:nth-child(1)");
    private By searchBox = By.cssSelector("#search");
    private By searchButton = By.cssSelector(".btn-search");
    private By tree = By.cssSelector(".category6 > span:nth-child(1)");
    //Navigation menu
    private By dysphagia = By.cssSelector("li.active:nth-child(1) > a:nth-child(1)");
    private By optifast = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(2) > a:nth-child(1)");
    private By oral = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(3) > a:nth-child(1)");
    private By paediatrics = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(4) > a:nth-child(1)");
    private By sustagen = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(5) > a:nth-child(1)");
    private By tube = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(6) > a:nth-child(1)");
    private By consumables = By.cssSelector("div.block:nth-child(1) > div:nth-child(2) > ol:nth-child(1) > li:nth-child(7) > a:nth-child(1)");
    private By gut = By.cssSelector("li.active:nth-child(8) > a:nth-child(1)");


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
}
