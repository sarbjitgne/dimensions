package com.DimensionsAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class TestClass_BlockUser {
    private static WebDriver driverToUse;
    private static Map<String,String> inputDataValues = new HashMap<String,String>();
    private static Properties propToUse = new Properties();
    private static List<WebElement> headerList = new ArrayList<WebElement>();
    private static List<WebElement> elementsList = new ArrayList<WebElement>();
    private static List<WebElement> gridTableDetails = new ArrayList<WebElement>();

    public TestClass_BlockUser(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
    }

    public static void main(String[] args) {
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
        driverToUse.findElement(By.cssSelector("button.btn.btn-primary[data-bind='click: OnManageUsers']")).click();
        List<WebElement> table = new ArrayList<WebElement>();
        WebElement gridObject = driverToUse.findElement(By.cssSelector("div.jsgrid-grid-header.jsgrid-header-scrollbar"));
        table = gridObject.findElements(By.cssSelector("th.jsgrid-header-cell.jsgrid-header-sortable"));
        Iterator<WebElement> itr = table.iterator();
        while(itr.hasNext()){
            WebElement tables = itr.next();
            headerList.add(tables);
//            System.out.println("itr text: " + tables.getText());
        }
//        WebElement gridObject = driverToUse.findElement(By.cssSelector("th.jsgrid-header-cell.jsgrid-header-sortable"));
//        System.out.println(headerList);
//        System.out.println("attribute: "+gridObject.);
//        System.out.println("Tag Name: "+gridObject.getTagName());
//        System.out.println("Text: "+gridObject.getText());
        Iterator<WebElement> itr2 = headerList.iterator();
        while(itr2.hasNext()){
            WebElement test = itr2.next();
            System.out.println("index is: "+headerList.indexOf(test)+" list text: "+test.getText());
//            System.out.println("list text: "+test.getText());
        }
//        for(int i=0;i<headerList.size();i++){
//            System.out.println("index is: "+headerList.indexOf(headerList.indexOf(headerList.get(i)))+" element text is: "+headerList.get(i).getText());
//        }
        //Code to read grid data:
        WebElement gridData = driverToUse.findElement(By.cssSelector("div.jsgrid-grid-body"));
        List<WebElement> gridTable = gridData.findElements(By.cssSelector("tr"));
        Iterator<WebElement> gridTB = gridTable.iterator();
        while(gridTB.hasNext()){
            WebElement record = gridTB.next();
            elementsList.add(record);
        }
        Iterator<WebElement> itr3 = elementsList.iterator();
        while(itr3.hasNext()){
            WebElement test1 = itr3.next();
            System.out.println("index is: "+elementsList.indexOf(test1)+" list text: "+test1.getText());
//            System.out.println("list text: "+test.getText());
        }
//        for(int i=0;i<elementsList.size();i++){
//            gridTableDetails = gridTable.get(i).findElements(By.cssSelector("td.jsgrid-cell"));
//        }
//        for(int i=0;i<gridTableDetails.size();i++){
//            System.out.println("index is: "+gridTableDetails.indexOf(gridTableDetails.indexOf(gridTableDetails.get(i)))+" element text is: "+gridTableDetails.get(i).getText());
//        }


//        List<WebElement> gridTableDetails = gridTable.get(1).findElements(By.cssSelector("td.jsgrid-cell"));


        driverToUse.close();
    }
}
