package com.DimensionsAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UploadUsers extends BasePageNavigation {
    private WebDriver driverToUse;
    private Scanner fileRead = null;
    private StringBuffer rowList = new StringBuffer();

    public UploadUsers(WebDriver driverToUse) {
        super(driverToUse);
        this.driverToUse = driverToUse;
    }

    public String uploadUsers(String usersList){
    try{
        fileRead = new Scanner(new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\test_data\\"+usersList));
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
    return driverToUse.findElement(loadUsers.userSavedConfirmation).getText();
    }
    public void verifyUploadedUsers(String userList){

    }
}
