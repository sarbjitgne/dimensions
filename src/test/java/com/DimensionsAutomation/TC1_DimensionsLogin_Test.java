package com.DimensionsAutomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC1_DimensionsLogin_Test {
    private WebDriver baseDriver;
    @BeforeClass
    public void startWebDriver(){
        System.out.println("starting web driver");
        String serverTest = "Mozilla";
        baseDriver = StartDriverServer.startDriver(serverTest);
//        LoadTestData inputData = new LoadTestData("MyText.csv");
//        inputData.LoadCSV();
//        inputDataValues = inputData.getToUseDataList().get(0);
    }
    @Test
    public void dimensionsLogin(){
        LoginDimensions loginPage = new LoginDimensions(baseDriver);
        CaptureScreenShot cprSrcShots = new CaptureScreenShot(baseDriver);
        loginPage.loginApp("http://courses.learnobytes.com/", "admin", "password");
        Assert.assertEquals("Dimension", loginPage.getWindowTitle());
        cprSrcShots.captureSrcShot("Home_Page");
        WelcomePageObjects homePage = new WelcomePageObjects();
        WelcomeChecks welChecks = new WelcomeChecks(baseDriver);
        welChecks.welcomePageChecks();
    }
    @AfterClass
    public void stopWebDriver(){
        System.out.println("stopping and quitting web driver");
        baseDriver.close();
    }
}
