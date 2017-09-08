package com.DimensionsAutomation;

import com.sun.webpane.platform.Pasteboard;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.*;

public class TestClass_BlockUser extends BasePageNavigation {
    private static WebDriver driverToUse;
    private static Map<String,String> inputDataValues = new HashMap<String,String>();
    private static Properties propToUse = new Properties();

    public TestClass_BlockUser(WebDriver driverToUse) {
        super(driverToUse);
        this.driverToUse = driverToUse;
    }

    public static void main(String[] args) {
        //Code to invoke browser
        String serverTest = "Mozilla";
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
        Scanner fileRead = null;
        StringBuffer rowList = new StringBuffer();
        try{
            fileRead = new Scanner(new FileReader("C:\\Sarbjit\\Dimensions_Automation\\UploadUsers.txt"));
            while(fileRead.hasNext()){
                String row = fileRead.nextLine();
                rowList.append(row);
                rowList.append("\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fileRead != null){
                fileRead.close();
            }
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipBoard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(rowList.toString());
        clipBoard.setContents(strSel, null);
        loginPage.clickUploadUsers();
        WebElement textArea = driverToUse.findElement(By.cssSelector("textarea.auto-style1"));
        textArea.click();
        ((HasInputDevices) driverToUse).getKeyboard().sendKeys(Keys.CONTROL + "v");
        cprSrcShots.captureSrcShot("DataEntered");
        driverToUse.findElement(By.cssSelector("button.btn.btn-primary")).click();
        loginPage.clickHomeButton();
        loginPage.clickManageUsers();
        SelectUserForOperation checkUploadedUsers = new SelectUserForOperation(driverToUse);
        String isUserExist = checkUploadedUsers.verifyRequiredStatus("Roll Number","8021");
        if(isUserExist.equals("Active")){
            System.out.println("User added successfully");
        }else{
            System.out.println("There is some issue with user uploading");
        }
        driverToUse.close();
    }


}
