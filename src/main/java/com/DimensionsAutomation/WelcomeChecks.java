package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;

public class WelcomeChecks {
    private WebDriver driverToUse;

    public WelcomeChecks(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
    }

    public void welcomePageChecks() {
        LeftNavigationPane welObj = new LeftNavigationPane();
        System.out.println(driverToUse.findElement(welObj.loggedInUserName).getText());
        System.out.println(driverToUse.findElement(welObj.homePageLink).getSize());
        driverToUse.findElement(welObj.uploadUsers).click();
        System.out.println("Upload users screen loaded as: "+driverToUse.findElement(welObj.uploadUsersPage).getText());
        driverToUse.findElement(welObj.homePageLink).click();
        driverToUse.findElement(welObj.manageUsers).click();
        System.out.println("Manage users screen loaded as: "+driverToUse.findElement(welObj.userManagerPage).getText());
    }
}
