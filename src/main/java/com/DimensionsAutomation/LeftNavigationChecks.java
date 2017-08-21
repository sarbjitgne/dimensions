package com.DimensionsAutomation;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LeftNavigationChecks {
    private WebDriver driverToUse;

    public LeftNavigationChecks(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
    }

    public void welcomePageChecks(String loggedInUser) {
        LeftNavigationPaneObjects welObj = new LeftNavigationPaneObjects();
        //Check logged in username, will read logged in user name from input file.
        Assert.assertEquals(loggedInUser,driverToUse.findElement(welObj.loggedInUserName).getText());
        //Check Upload users button work
        driverToUse.findElement(welObj.uploadUsers).click();
        Assert.assertEquals("Upload Users:",driverToUse.findElement(welObj.uploadUsersPage).getText());
        //Check manage Users button works
        driverToUse.findElement(welObj.homePageLink).click();
        driverToUse.findElement(welObj.manageUsers).click();
        Assert.assertEquals("Users Manager",driverToUse.findElement(welObj.userManagerPage).getText());
    }
}

