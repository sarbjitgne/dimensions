package com.DimensionsAutomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TC1_DimensionsLogin_Test {
    private WebDriver baseDriver;
    private Map<String,String> inputDataValues = new HashMap<String,String>();
    private Properties propToUse = new Properties();
    @BeforeClass
    public void startWebDriver(){
        System.out.println("starting web driver");
        String serverTest = "Mozilla";
        baseDriver = StartDriverServer.startDriver(serverTest);
        LoadTestData inputData = new LoadTestData("AdminLogin.csv");
        inputData.LoadCSV();
        inputDataValues = inputData.getToUseDataList().get(0);
        ReadPropertyFile loadProperty = new ReadPropertyFile();
        loadProperty.readPropertyFile();
        propToUse = loadProperty.getPropFile();
    }
    @Test
    public void dimensionsLogin(){
        LoginDimensions loginPage = new LoginDimensions(baseDriver);
        CaptureScreenShot cprSrcShots = new CaptureScreenShot(baseDriver);
        String url = propToUse.getProperty("url");
        String userName = inputDataValues.get("userName");
        String userPassword = inputDataValues.get("userPassword");
        loginPage.loginApp(url, userName, userPassword);
        Assert.assertEquals("Dimension", loginPage.getWindowTitle());
        cprSrcShots.captureSrcShot("Home_Page");
//        LeftNavigationChecks welChecks = new LeftNavigationChecks(baseDriver);
//        welChecks.welcomePageChecks("Sarbjit Singh");
    }
    @Test
    public void leftNavigationPaneChecks(){
        LoginDimensions loginPage = new LoginDimensions(baseDriver);
        CaptureScreenShot cprSrcShots = new CaptureScreenShot(baseDriver);
        String url = propToUse.getProperty("url");
        String userName = inputDataValues.get("userName");
        String userPassword = inputDataValues.get("userPassword");
        String loggedInUserName = inputDataValues.get("loggedInUserName");
        loginPage.loginApp(url, userName, userPassword);
        LeftNavigationPaneObjects welObj = new LeftNavigationPaneObjects();
        //Check logged in username, will read logged in user name from input file.
        Assert.assertEquals(loggedInUserName,baseDriver.findElement(welObj.loggedInUserName).getText());
        //Check Upload users button work
        baseDriver.findElement(welObj.uploadUsers).click();
        Assert.assertEquals("Upload Users:",baseDriver.findElement(welObj.uploadUsersPage).getText());
        //Check manage Users button works
        baseDriver.findElement(welObj.homePageLink).click();
        baseDriver.findElement(welObj.manageUsers).click();
        Assert.assertEquals("Users Manager",baseDriver.findElement(welObj.userManagerPage).getText());
        //Check Change Password button
        baseDriver.findElement(welObj.homePageLink).click();
        baseDriver.findElement(welObj.changePasswordLink).click();
        Assert.assertEquals("Change Password",baseDriver.findElement(welObj.changePasswordPage).getText());
        //Check Logout button
        baseDriver.findElement(welObj.homePageLink).click();
        baseDriver.findElement(welObj.logoutLink).click();
        Assert.assertEquals("Please Login Here",baseDriver.findElement(welObj.loginText).getText());
    }
    @AfterClass
    public void stopWebDriver(){
        System.out.println("stopping and quitting web driver");
        baseDriver.close();
    }
}
