package com.ncr.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

/**
 * Created by alex.mihai on 6/26/2017.
 */
public class BaseTest  {
    protected WebDriver driver;
    ProfilesIni profile = new ProfilesIni();
    FirefoxProfile qa = profile.getProfile("JavaSelenium");

    @BeforeClass
    public void methodSetUp(){
        System.out.println("Test set up !");
        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver(qa);
        driver.manage().deleteAllCookies();
    }

    @BeforeClass
    public String setEnv(){
        String env = "PPRD";
        return env;
    }

    @AfterClass
    public void methodTearDown(){
        System.out.println("Test clean up !");
        driver.quit();
    }
}
