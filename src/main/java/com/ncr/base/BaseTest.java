package com.ncr.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

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

    //Selenium Grid - Docker
//    @Parameters({"browser"})
//    @BeforeClass
//    public void methodSetUp(String browser) throws MalformedURLException {
//        System.out.println("Test set up !");
//        DesiredCapabilities cap = DesiredCapabilities.firefox();
////        switch (browser)
////        {
////            case "firefox":
////                cap = DesiredCapabilities.firefox();
////                break;
////            case "ie":
////                cap = DesiredCapabilities.internetExplorer();
////                break;
////            case "chrome":
////                cap = DesiredCapabilities.chrome();
////                break;
////            default:
////                cap = DesiredCapabilities.firefox();
////                break;
////        }
//
//        cap.setPlatform(Platform.WIN10);
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);

//    }

    @BeforeClass
    public String setEnv(){
        String env = "PPRD";
        return env;
    }

//    @AfterClass
//    public void methodTearDown(){
//        System.out.println("Test clean up !");
//        driver.quit();
//    }
}
