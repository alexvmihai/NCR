package com.ncr.base;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.TimeZone;

/**
 * Created by alex.mihai on 6/26/2017.
 */

public class BasePageObject<T> extends BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;


    protected BasePageObject(WebDriver driver) throws IOException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    protected T getPage(String url){
        driver.get(url);
        return (T) this;
    }

    public boolean isAlertPresent(){
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch(NoAlertPresentException Ex){
            return false;
        }
    }
    public void acceptPrompt() throws InterruptedException {
        if (isAlertPresent()){
            driver.switchTo().alert().accept();
        }
        Thread.sleep(6000);
    }

    protected void type(String text, By element){
        find(element).sendKeys(text);
    }

    protected void deleteText(By element){
        find(element).clear();
    }

    protected void clickOn (By element){
        find(element).click();
    }

    private WebElement find(By element) {
        return driver.findElement(element);
    }

    protected void waitForVisibilityOf (By locator, Integer... timeOutInSeconds){
        int attempts = 0;
        while (attempts < 2){
            try{
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), (timeOutInSeconds.length >0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e){
            }
            attempts++;
        }
    }

    private void waitFor (ExpectedCondition<WebElement> condition, Integer timeOutInSeconds){
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    protected String getText(By element) {
        return find(element).getText();
    }

    public String getSource(){
        return driver.getPageSource();
    }

    public String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        return formatter.format(date);
    }

    public String getFutureDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 3);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("Australia/Sidney"));
        return formatter.format(date);
    }

    public void mouseOver(WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    public String[] getCredentials() throws IOException {
        if (setEnv() == "PPRD"){
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:\\Access Credentials\\ncare_login.txt"));
            String userName = properties.getProperty("username_pprd");
            String password = properties.getProperty("password_pprd");
            String[] credentials ={userName, password};
            return credentials;
        } else if (setEnv() == "PROD"){
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:\\Access Credentials\\ncare_login.txt"));
            String userName = properties.getProperty("username_prod");
            String password = properties.getProperty("password_prod");
            System.out.println("PROD !!!!");
            String[] credentials ={userName, password};
            return credentials;
        }else{
            return null;
        }
    }

    public String[] getAdminCredentials() throws IOException {
        if (setEnv() == "PPRD"){
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:\\Access Credentials\\ncare_login.txt"));
            String userName = properties.getProperty("username_admin_pprd");
            String password = properties.getProperty("password_admin_pprd");
            String[] credentials ={userName, password};
            return credentials;
        } else if (setEnv() == "PROD"){
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:\\Access Credentials\\ncare_login.txt"));
            String userName = properties.getProperty("username_admin_prod");
            String password = properties.getProperty("password_admin_prod");
            System.out.println("PROD !!!!");
            String[] credentials ={userName, password};
            return credentials;
        }else{
            return null;
        }
    }

    public String getURL(String path, String name) throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        String url = properties.getProperty(name);
        return url;
    }

    public String BaseURL() throws IOException {
        if(setEnv()== "PPRD"){
            return getURL("D:\\Access Credentials\\ncare_url.txt", "url_preprod");
        } else if (setEnv() == "PROD"){
            return getURL("D:\\Access Credentials\\ncare_url.txt", "url_prod");
        } else {
            return null;
        }
    }

    public String AdminURL() throws IOException{
        if(setEnv()== "PPRD"){
            return getURL("D:\\Access Credentials\\ncare_url.txt", "url_admin_preprod");
        } else if (setEnv() == "PROD"){
            return getURL("D:\\Access Credentials\\ncare_url.txt", "url_admin_prod");
        } else {
            return null;
        }

    }

    //write the customer to a file
    public void writeCustomer(String customer, String fileTitle, String pathName, String variableName){
        String date = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        File fileName = new File( pathName + fileTitle + date +".txt");
        try{
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(variableName + "=" + customer);
            fw.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    //get the last modified file from a folder
    public File lastModified(String dir){
        File directory = new File(dir);
        if(!directory.exists()){
            System.out.println(String.format("Directory %s does not exist !", dir));
        }
        File[] files = directory.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        long lastModifiedFile = Long.MIN_VALUE;
        File choice = null;
        for (File file :files){
            if (file.lastModified() > lastModifiedFile){
                choice = file;
                lastModifiedFile = file.lastModified();
            }
        }
        return choice;
    }

    public String readCustomer(File file, String variable) throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        String email = properties.getProperty(variable);
        return email;
    }


    public String[] getMemberCredentials(String filePath) throws IOException{
        Properties properties = new Properties();
        properties.load(new FileInputStream(filePath));
        String email = properties.getProperty("member_email_pprd");
        String pass = properties.getProperty("member_pass_pprd");
        String[] credentials = {email, pass};
        return credentials;
    }












}

