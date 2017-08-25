package com.DimensionsAutomation;

import org.openqa.selenium.By;

/**
 * Created by Sarb on 8/20/2017.
 */
public class UserManagerObjects {
    By addNewUser = By.cssSelector("btn.btn-primary[data-bind='click: OnAddNewUser']");
    By blockUser = By.cssSelector("btn.btn-primary[data-bind='click:OnBlockUser']");
    By activateUser = By.cssSelector("btn.btn-primary[data-bind='click:OnActivateUser']");
    By resetPassword = By.cssSelector("btn.btn-primary[data-bind='click:OnResetPassword']");
    By viewPerformance = By.cssSelector("btn.btn-primary[data-bind='click:OnViewPerformance']");
    By usersGridTable = By.cssSelector("div[id='grdUsersList']");
    By gridHeaders = By.cssSelector("div.jsgrid-grid-header.jsgrid-header-scrollbar");
}
