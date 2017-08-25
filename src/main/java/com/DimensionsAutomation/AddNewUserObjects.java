package com.DimensionsAutomation;

import org.openqa.selenium.By;

public class AddNewUserObjects {
    By addNewUser = By.cssSelector("btn.btn-primary[data-bind='click: OnAddNewUser']");
    By userFirstName = By.cssSelector("input[data-bind='value:FirstName']");
    By userLastName = By.cssSelector("input[data-bind='value:LastName']");
    By userLoginId = By.cssSelector("input[data-bind='value:LoginID']");
    By userType = By.cssSelector("select.data-bind");
    By userPassword = By.cssSelector("input[data-bind='value: Password, enable: OpenMode == ViewOpenModeEnum.New']");
    By userSave = By.cssSelector("btn.btn-primary[data-bind='click: OnSave']");
}
