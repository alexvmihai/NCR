package com.ncr.base;

import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by alex.mihai on 10/10/2017.
 */
public class TestListener extends TestListenerAdapter implements IAnnotationTransformer{
    protected WebDriver driver;
    @Override
    public void onTestSuccess(ITestResult tr){
        System.out.println(tr.getTestContext().getCurrentXmlTest().getName() + " Test Success !");
    }

    @Override
    public void onTestFailure(ITestResult tr){
        System.out.println(tr.getTestContext().getCurrentXmlTest().getName() + " Test Failure !");
        driver.close();
    }

    @Override
    public void onStart(ITestContext testContext){
        System.out.println(testContext.getCurrentXmlTest().getName() + " Test Start !");
    }

    @Override
    public void onFinish(ITestContext testContext){
        System.out.println(testContext.getCurrentXmlTest().getName() + " Test Finish !");
    }

    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
