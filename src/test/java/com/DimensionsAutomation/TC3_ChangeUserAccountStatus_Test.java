package com.DimensionsAutomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TC3_ChangeUserAccountStatus_Test {
    private WebDriver baseDriver;
    private Map<String,String> inputDataValues = new HashMap<String,String>();
    private Properties propToUse = new Properties();
    @BeforeClass
    public void startWebDriver(){
        System.out.println("starting web driver");
        String serverTest = "Mozilla";
        baseDriver = StartDriverServer.startDriver(serverTest);
        LoadTestData inputData = new LoadTestData("ChangeUserAccountStatus.csv");
        inputData.LoadCSV();
        inputDataValues = inputData.getToUseDataList().get(0);
        ReadPropertyFile loadProperty = new ReadPropertyFile();
        loadProperty.readPropertyFile();
        propToUse = loadProperty.getPropFile();
    }
    @Test
    public void changeStatusUser(){
        LoginDimensions loginPage = new LoginDimensions(baseDriver);
        CaptureScreenShot cprSrcShots = new CaptureScreenShot(baseDriver);
        String url = propToUse.getProperty("url");
        String userName = inputDataValues.get("userName");
        String userPassword = inputDataValues.get("userPassword");
        loginPage.loginApp(url, userName, userPassword);
        loginPage.clickManageUsers();
        cprSrcShots.captureSrcShot("ManageUsersPage");
        String userSelectionBasedOnColumn = inputDataValues.get("userSelectionBasedOnColumn");
        String userIdentification = inputDataValues.get("columnValue");
        String requiredStatus = inputDataValues.get("newUserStatus");
        ChangeUserAccountStatus userAction = new ChangeUserAccountStatus(baseDriver);
        String changedStatus = userAction.changeUserStatus(userSelectionBasedOnColumn, userIdentification, requiredStatus);
        cprSrcShots.captureSrcShot("UserStatusChanged");
        Assert.assertEquals(requiredStatus, changedStatus);
        userAction.clickLogout();
    }
    @AfterClass
    public void stopWebDriver(){
        System.out.println("stopping and quitting web driver");
        baseDriver.close();
    }
}
