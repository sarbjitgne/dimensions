package com.DimensionsAutomation;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TC4_UploadUsers_Test {
    private WebDriver baseDriver;
    private Map<String,String> inputDataValues = new HashMap<String,String>();
    private Properties propToUse = new Properties();
    @BeforeClass
    public void startWebDriver(){
        System.out.println("starting web driver");
        String serverTest = "Chrome";
        baseDriver = StartDriverServer.startDriver(serverTest);
        LoadTestData inputData = new LoadTestData("ChangeUserAccountStatus.csv");
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
        UploadUsers uploadNewUserList = new UploadUsers(baseDriver,"UploadUsers.txt");
        String areUsersAddedSuccessfully = uploadNewUserList.uploadUsers();
        if(areUsersAddedSuccessfully.equals("Users saved.")){
            Map<String,Boolean> usersUploaded = new HashMap<String,Boolean>();
            usersUploaded = uploadNewUserList.verifyUploadedUsers();
            for(Map.Entry<String, Boolean> entry:usersUploaded.entrySet()){
                boolean test = entry.getValue();
                if(test==true){
                    System.out.println(entry.getKey()+" - User addition successful");

                }else{
                    System.out.println(entry.getKey()+" - User addition failed");
                }
            }
        }else{
            System.out.println("User upload failed");
        }
    }
    @AfterClass
    public void stopWebDriver(){
        System.out.println("stopping and quitting web driver");
        baseDriver.close();
    }
}
