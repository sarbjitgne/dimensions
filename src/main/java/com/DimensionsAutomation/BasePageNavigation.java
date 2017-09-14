package com.DimensionsAutomation;

import com.gargoylesoftware.htmlunit.AlertHandler;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class BasePageNavigation {
    private WebDriver driverToUse;
    public ObjectsLeftNavigationPane basePage = new ObjectsLeftNavigationPane();
//    public Wait wait;

    public BasePageNavigation(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
//        wait = new FluentWait(driverToUse)
//                .withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(500,TimeUnit.MILLISECONDS);
    }
    public Wait wait(int secondsToWait, int secondsToPoll){
       return new FluentWait(driverToUse)
                .withTimeout(secondsToWait, TimeUnit.SECONDS)
                .pollingEvery(secondsToPoll,TimeUnit.MILLISECONDS);
    }

    public void waitToPageToLoad(){
        wait(20,500).until(ExpectedConditions.invisibilityOfElementLocated(basePage.processingWindow));
    }
    public void clickHomeButton() {
        driverToUse.findElement(basePage.homePageLink).click();
        wait(20,500).until(ExpectedConditions.visibilityOfElementLocated(basePage.welcomeMessage));
    }

    public void clickUploadUsers() {
        clickHomeButton();
        wait(20,500).until(ExpectedConditions.elementToBeClickable(basePage.uploadUsers));
        driverToUse.findElement(basePage.uploadUsers).click();
        wait(20,500).until(ExpectedConditions.visibilityOfElementLocated(basePage.uploadUsersPage));
    }
    public void clickManageUsers() {
        clickHomeButton();
        wait(20,500).until(ExpectedConditions.elementToBeClickable(basePage.manageUsers));
        driverToUse.findElement(basePage.manageUsers).click();
        wait(60,500).until(ExpectedConditions.invisibilityOfElementLocated(basePage.processingWindow));
    }
    public void clickChangePassword() {
        driverToUse.findElement(basePage.changePasswordLink).click();
        wait(20,500).until(ExpectedConditions.visibilityOfElementLocated(basePage.changePasswordPage));
    }
    public void clickLogout() {
        driverToUse.findElement(basePage.logoutLink).click();
        wait(20,500).until(ExpectedConditions.visibilityOfElementLocated(basePage.loginText));
    }
    public void alertHandle(){
        try{
            Alert handleAlert = driverToUse.switchTo().alert();
            handleAlert.accept();
        }catch(Exception e){

        }
    }
}
