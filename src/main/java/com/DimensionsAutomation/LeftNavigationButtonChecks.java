package com.DimensionsAutomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class LeftNavigationButtonChecks {
    private WebDriver driverToUse;
    private ObjectsLeftNavigationPane basePage = new ObjectsLeftNavigationPane();
    private Wait wait;

    public LeftNavigationButtonChecks(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
    }

    public void waitForPageToLoad(){
        wait = new FluentWait(driverToUse)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(500,TimeUnit.MILLISECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(basePage.uploadUsers));
    }

   public void verifyLoggedInUserName(String UserName) {
       wait = new FluentWait(driverToUse)
               .withTimeout(60, TimeUnit.SECONDS)
               .pollingEvery(500,TimeUnit.MILLISECONDS);
       wait.until(ExpectedConditions.visibilityOfElementLocated(basePage.loggedInUserName));
        Assert.assertEquals(UserName, driverToUse.findElement(basePage.loggedInUserName).getText());
    }
    public void verifyUploadUsers() {
        driverToUse.findElement(basePage.uploadUsers).click();
        wait = new FluentWait(driverToUse)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(500,TimeUnit.MILLISECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(basePage.uploadUsersPage));
        Assert.assertEquals("Upload Users:",driverToUse.findElement(basePage.uploadUsersPage).getText());
    }
    public void verifyManageUsers() {
        driverToUse.findElement(basePage.homePageLink).click();
        wait = new FluentWait(driverToUse)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(500,TimeUnit.MILLISECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(basePage.manageUsers));
        driverToUse.findElement(basePage.manageUsers).click();
        Assert.assertEquals("Users Manager",driverToUse.findElement(basePage.userManagerPage).getText());
    }
    public void verifyChangePassword() {
        driverToUse.findElement(basePage.homePageLink).click();
        driverToUse.findElement(basePage.changePasswordLink).click();
        Assert.assertEquals("Change Password",driverToUse.findElement(basePage.changePasswordPage).getText());
    }
    public void verifyLogout() {
        driverToUse.findElement(basePage.homePageLink).click();
        driverToUse.findElement(basePage.logoutLink).click();
        Assert.assertEquals("Please Login Here",driverToUse.findElement(basePage.loginText).getText());
    }
//      //Check logged in username, will read logged in user name from input file.
//        Assert.assertEquals(loggedInUserName, baseDriver.findElement(welObj.loggedInUserName).getText());
//        //Check Upload users button work
//        wait.until(ExpectedConditions.visibilityOfElementLocated(welObj.uploadUsers));
//        baseDriver.findElement(welObj.uploadUsers).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(welObj.uploadUsersPage));
//        Assert.assertEquals("Upload Users:",baseDriver.findElement(welObj.uploadUsersPage).getText());
//        //Check manage Users button works
//        baseDriver.findElement(welObj.homePageLink).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(welObj.manageUsers));
//        baseDriver.findElement(welObj.manageUsers).click();
//        Assert.assertEquals("Users Manager",baseDriver.findElement(welObj.userManagerPage).getText());
//        //Check Change Password button
//        baseDriver.findElement(welObj.homePageLink).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(welObj.changePasswordLink));
//        baseDriver.findElement(welObj.changePasswordLink).click();
//        Assert.assertEquals("Change Password",baseDriver.findElement(welObj.changePasswordPage).getText());
//        //Check Logout button
//        baseDriver.findElement(welObj.homePageLink).click();
//        baseDriver.findElement(welObj.logoutLink).click();
//        Assert.assertEquals("Please Login Here",baseDriver.findElement(welObj.loginText).getText());
//        LeftNavigationPaneObjects welObj = new LeftNavigationPaneObjects();
//        //Check logged in username, will read logged in user name from input file.
//        Assert.assertEquals(loggedInUser,driverToUse.findElement(welObj.loggedInUserName).getText());
//        //Check Upload users button work
//        driverToUse.findElement(welObj.uploadUsers).click();
//        Assert.assertEquals("Upload Users:",driverToUse.findElement(welObj.uploadUsersPage).getText());
//        //Check manage Users button works
//        driverToUse.findElement(welObj.homePageLink).click();
//        driverToUse.findElement(welObj.manageUsers).click();
//        Assert.assertEquals("Users Manager",driverToUse.findElement(welObj.userManagerPage).getText());
}

