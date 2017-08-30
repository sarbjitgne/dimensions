package com.DimensionsAutomation;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class TestClass_BlockUser {
    private static WebDriver driverToUse;
    private static Map<String,String> inputDataValues = new HashMap<String,String>();
    private static Properties propToUse = new Properties();

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
//        System.out.println(provideCellValue(driverToUse,"Last Name","Mo"));
        clickOnRequiredRow(driverToUse,"Last Name","Samar");
        CaptureScreenShot cprSrcShots1 = new CaptureScreenShot(driverToUse);
        cprSrcShots1.captureSrcShot("userClicked");
        driverToUse.close();
    }

    //Method to return Name of grid headers
    public static List<String> readColumnHeaders(WebDriver driver){
        List<String> headerColumnList = new ArrayList<String>();
        List<WebElement> headerColumnName = new ArrayList<WebElement>();
        WebElement gridHeaderObject = driver.findElement(By.cssSelector("div.jsgrid-grid-header.jsgrid-header-scrollbar"));
        headerColumnName = gridHeaderObject.findElements(By.cssSelector("th.jsgrid-header-cell.jsgrid-header-sortable"));
        Iterator<WebElement> itr = headerColumnName.iterator();
        while(itr.hasNext()){
            WebElement tables = itr.next();
            headerColumnList.add(tables.getText());
        }
        return headerColumnList;
    }

    //method to return column index
    public static int provideColumnIndex(WebDriver driver, String columnNameToSearch){
        int index=-1;
        List<String> columnListToUse = readColumnHeaders(driver);
        Iterator<String> itr = columnListToUse.iterator();
        while(itr.hasNext()){
            String checkText = itr.next();
            if(checkText.equals(columnNameToSearch)){
                index = columnListToUse.indexOf(checkText);
            }
        }
        return index;
    }

    //Method to read all grid data
    public static List<WebElement> readGridRecords(WebDriver driver){
        List<WebElement> recordList = new ArrayList<WebElement>();
        WebElement gridColumnData = driver.findElement(By.cssSelector("div.jsgrid-grid-body"));
        List<WebElement> gridRecordTable = gridColumnData.findElements(By.cssSelector("tr"));
        Iterator<WebElement> gridTB = gridRecordTable.iterator();
        while(gridTB.hasNext()){
            WebElement record = gridTB.next();
            recordList.add(record);
        }
        return recordList;
    }

    //Method to read each all elements of a row
    public static String readCellsOfRow(WebDriver driver, WebElement row, int index){
        List<WebElement> cellValuesList = row.findElements(By.cssSelector("td"));
        return cellValuesList.get(index).getText();
    }
    //method to click on desired row.
    public static void clickOnRequiredRow(WebDriver driver,String columnNameToSearch, String valueToSearch){
        List<WebElement> providedRecordList = readGridRecords(driver);
        int index = provideColumnIndex(driver,columnNameToSearch);
        Iterator<WebElement> clickRecord = providedRecordList.iterator();
//        for(int i=0;i<providedRecordList.size();i++){
//            WebElement clickRow = providedRecordList.get(i);
//        }
//        for(WebElement clickRow:providedRecordList){
//            String cellValueToSearch = readCellsOfRow(driver, clickRow,index);
//        }
        while(clickRecord.hasNext()){
            WebElement clickRow = clickRecord.next();
            String cellValueToSearch = readCellsOfRow(driver, clickRow,index);
            if(cellValueToSearch.equals(valueToSearch)){
                clickRow.click();
                break;
            }

        }
    }
}
