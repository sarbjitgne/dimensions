package com.DimensionsAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;

import java.awt.*;
//import java.awt.List;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class UploadUsers extends BasePageNavigation {
    private WebDriver driverToUse;
    private Scanner fileRead = null;
    private StringBuffer rowList = new StringBuffer();
    private String usersList;
    private Map<String,Boolean> uploadUserStatus = new HashMap<String,Boolean>();

    public UploadUsers(WebDriver driverToUse, String userList) {
        super(driverToUse);
        this.driverToUse = driverToUse;
//        this.usersList = userList;
        this.usersList = System.getProperty("user.dir")+"\\src\\main\\resources\\test_data\\"+userList;
    }

    public String uploadUsers(){
    try{
        fileRead = new Scanner(new FileReader(usersList));
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
    clickUploadUsers();
    ObjectUploadUsers loadUsers = new ObjectUploadUsers();
    WebElement textArea = driverToUse.findElement(loadUsers.userInputArea);//driverToUse.findElement(By.cssSelector("textarea.auto-style1"));
    textArea.click();
    ((HasInputDevices) driverToUse).getKeyboard().sendKeys(Keys.CONTROL+"v");
    driverToUse.findElement(loadUsers.userSubmit).click();
    waitToPageToLoad();
    return driverToUse.findElement(loadUsers.userSavedConfirmation).getText();
    }
    public Map<String,Boolean> verifyUploadedUsers(){
        clickManageUsers();
//        List<Map<String,Boolean>> uploadUserReport = new ArrayList<Map<String,Boolean>>();
        boolean uploadUser = false;
        int counter = 0;
        String[] rowDataArray;
        List<String> rowDataList = new ArrayList<String>();
        try{
            fileRead = new Scanner(new FileReader(usersList));
            while(fileRead.hasNext()){
                String row = fileRead.nextLine();
                if(counter>0){
                    rowDataArray = row.split("\t");
                    rowDataList = Arrays.asList(rowDataArray);
                    String userRollNumberToVerify = rowDataList.get(2);//Index of Roll Number in upload user list is 2
                    SelectUserForOperation checkUploadedUsers = new SelectUserForOperation(driverToUse);
                    String isUserExist = checkUploadedUsers.verifyRequiredStatus("Roll Number",userRollNumberToVerify);
                    if(isUserExist == null || !isUserExist.isEmpty()){
                        uploadUser = true;
                     }else{
                            uploadUser = false;
                    }
                    uploadUserStatus.put(userRollNumberToVerify,uploadUser);
                }
                counter++;
             }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fileRead != null){
                fileRead.close();
            }
        }
        return uploadUserStatus;
    }
}
