package com.DimensionsAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class SelectUserForOperation {
    private WebDriver driverToUse;
    private static List<String> headersList = new ArrayList<String>();

    public SelectUserForOperation(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
        headersList = readColumnHeaders(driverToUse);
    }

    //method to click on desired row.
    public void selectRequiredRow(String columnNameToSearch, String valueToSearch){
        List<WebElement> providedRecordList = readGridRecords(driverToUse);
        int index = provideColumnIndex(columnNameToSearch);
        Iterator<WebElement> clickRecord = providedRecordList.iterator();
        while(clickRecord.hasNext()){
            WebElement clickRow = clickRecord.next();
            String cellValueToSearch = readCellsOfRow(driverToUse, clickRow,index);
            if(cellValueToSearch.equals(valueToSearch)){
                clickRow.click();
                break;
            }

        }
/*  Different types of Iterator examples
                for(int i=0;i<providedRecordList.size();i++){
            WebElement clickRow = providedRecordList.get(i);
        }
        for(WebElement clickRow:providedRecordList){
            String cellValueToSearch = readCellsOfRow(driver, clickRow,index);
        }*/
    }

    //method to read the account status of any user on desired row.
    public String verifyRequiredStatus(String columnNameToSearch, String valueToSearch){
        String recordStatus=null;
        List<WebElement> providedRecordList = readGridRecords(driverToUse);
        int index = provideColumnIndex(columnNameToSearch);
        Iterator<WebElement> clickRecord = providedRecordList.iterator();
        while(clickRecord.hasNext()){
            WebElement clickRow = clickRecord.next();
            String cellValueToSearch = readCellsOfRow(driverToUse, clickRow,index);
            if(cellValueToSearch.equals(valueToSearch)){
                int internalIndex = provideColumnIndex("Account Status");
                recordStatus = readCellsOfRow(driverToUse,clickRow,internalIndex);
                break;
            }
        }
        return recordStatus;
    }

    //Method to return Name of grid headers
    private static List<String> readColumnHeaders(WebDriver driver){
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
    private static int provideColumnIndex(String columnNameToSearch){
        int index=-1;
        List<String> columnListToUse = headersList;
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
    private static List<WebElement> readGridRecords(WebDriver driver){
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
    private static String readCellsOfRow(WebDriver driver, WebElement row, int index){
        List<WebElement> cellValuesList = row.findElements(By.cssSelector("td"));
        return cellValuesList.get(index).getText();
    }

}
