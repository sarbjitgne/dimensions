package com.DimensionsAutomation;

//import com.sun.webpane.platform.Pasteboard;
//import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.*;

public class TesterClass extends BasePageNavigation {
    private static WebDriver driverToUse;
    private static Map<String,String> inputDataValues = new HashMap<String,String>();
    private static Properties propToUse = new Properties();

    public TesterClass(WebDriver driverToUse) {
        super(driverToUse);
        this.driverToUse = driverToUse;
    }

    public static void main(String[] args) {
        //Code to invoke browser
        String serverTest = "Chrome";
        driverToUse = StartDriverServer.startDriver(serverTest);
        LoadTestData inputData = new LoadTestData("AdminLogin.csv");
        inputData.LoadCSV();
        inputDataValues = inputData.getToUseDataList().get(0);
        ReadPropertyFile loadProperty = new ReadPropertyFile();
        loadProperty.readPropertyFile();
        propToUse = loadProperty.getPropFile();
        LoginDimensions loginPage = new LoginDimensions(driverToUse);
        CaptureScreenShot cprSrcShots = new CaptureScreenShot(driverToUse);
        String url = propToUse.getProperty("url");
        String userName = inputDataValues.get("userName");
        String userPassword = inputDataValues.get("userPassword");
        String loggedInUserName = inputDataValues.get("loggedInUserName");
        loginPage.loginApp(url, userName, userPassword);
       loginPage.clickManageUsers();
        AddNewUser newUserAddition = new AddNewUser(driverToUse);
        String fName = inputDataValues.get("firstName");
        String lName = inputDataValues.get("lastName");
        String loginID = inputDataValues.get("loginID");
        String uType = inputDataValues.get("userType");
        String password = inputDataValues.get("password");
        newUserAddition.addUser(fName,lName,loginID,uType,password);
    }


}
