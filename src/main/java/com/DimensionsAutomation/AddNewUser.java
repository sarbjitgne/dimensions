package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewUser extends LeftNavigationButtonChecks {
    private WebDriver driverToUse;

    private ObjectsAddNewUser baseUser = new ObjectsAddNewUser();

    public AddNewUser(WebDriver driverToUse) {
        super(driverToUse);
    }
    public void addUser(String firstName, String lastName, String loginID, String userType, String password){
        waitForPageToLoad();
        verifyManageUsers();
        driverToUse.findElement(baseUser.addNewUser).click();
        driverToUse.findElement(baseUser.userFirstName).sendKeys(firstName);
        driverToUse.findElement(baseUser.userLastName).sendKeys(lastName);
        driverToUse.findElement(baseUser.userLoginId).sendKeys(loginID);
        WebElement userTypeDD = driverToUse.findElement(baseUser.userType);
        Select uType = new Select(userTypeDD);
        uType.selectByValue(userType);
        driverToUse.findElement(baseUser.userPassword).sendKeys(password);
        driverToUse.findElement(baseUser.userSave).click();
    }
}
