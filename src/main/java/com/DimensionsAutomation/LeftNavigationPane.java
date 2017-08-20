package com.DimensionsAutomation;

import org.openqa.selenium.By;

public class LeftNavigationPane {
    By loggedInUserName = By.xpath("//h3[@class='profile-title' and text()='Sarbjit Singh']");
    By homePageLink = By.cssSelector("div.main-navigation i.fa-home");
    By logoutLink =By.cssSelector("div.main-navigation i.fa-sign-out");
    By changePasswordLink = By.cssSelector("div.main-navigation i.fa-pencil");
    By uploadUsers = By.cssSelector("button.btn.btn-primary[data-bind='click: OnUploadUsers']");
    By manageUsers = By.cssSelector("button.btn.btn-primary[data-bind='click: OnManageUsers']");
    By uploadUsersPage = By.xpath("//div[@id='MainChildContent']//div[@id='divUploadUsers']//h1[contains(text(),'Upload')]");
    By userManagerPage = By.xpath("//div[@id='divManageUsers']//div[@class='ChildPageHeader']//h1[contains(text(),'Users')]");
}
