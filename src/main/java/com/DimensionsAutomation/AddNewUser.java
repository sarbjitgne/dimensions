package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewUser extends LeftNavigationChecks {
    private WebDriver driverToUse;

    private AddNewUserObjects baseUser = new AddNewUserObjects();

    public AddNewUser(WebDriver driverToUse) {
        super(driverToUse);
    }
    public void addUser(){
        waitForPageToLoad();
        verifyManageUsers();
        driverToUse.findElement(baseUser.addNewUser).click();
        driverToUse.findElement(baseUser.userFirstName).sendKeys("Aman");
        driverToUse.findElement(baseUser.userLastName).sendKeys("Jain2");
        driverToUse.findElement(baseUser.userLoginId).sendKeys("aman_jain2");
        WebElement userType = driverToUse.findElement(baseUser.userType);
        Select uType = new Select(userType);
        uType.selectByValue("Student");
        driverToUse.findElement(baseUser.userPassword).sendKeys("Amanjain123");
        driverToUse.findElement(baseUser.userSave).click();
    }
}
