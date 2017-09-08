package com.DimensionsAutomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Tester_Class_For_Any_Class {
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
    public void testerClass(){
        LoginDimensions loginPage = new LoginDimensions(baseDriver);
        CaptureScreenShot cprSrcShots = new CaptureScreenShot(baseDriver);
        String url = propToUse.getProperty("url");
        String userName = inputDataValues.get("userName");
        String userPassword = inputDataValues.get("userPassword");
        loginPage.loginApp(url, userName, userPassword);
        Assert.assertEquals("Dimension", loginPage.getWindowTitle());
        cprSrcShots.captureSrcShot("Home_Page");
        loginPage.clickManageUsers();
//        baseDriver.findElement(By.xpath("button.btn.btn-primary[data-bind='click: OnManageUsers']")).click();
//        SelectUserForOperation selectUser = new SelectUserForOperation(baseDriver);
//        selectUser.selectRequiredRow("Roll Number","8005");
        ChangeUserAccountStatus userAction = new ChangeUserAccountStatus(baseDriver);
        String test = userAction.changeUserStatus("Roll Number", "8005", "active");
        Assert.assertEquals("active", test);
    }
    @AfterClass
    public void stopWebDriver(){
        System.out.println("stopping and quitting web driver");
        baseDriver.close();
    }
}
