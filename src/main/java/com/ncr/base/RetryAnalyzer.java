package com.ncr.base;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by alex.mihai on 10/10/2017.
 */
public class RetryAnalyzer implements IRetryAnalyzer{
    int counter = 0;
    int retryLimit = 6;
    protected WebDriver driver;

    public boolean retry(ITestResult iTestResult) {
        if(counter < retryLimit){
            counter++;
            return true;
        }
        return false;
    }
}
