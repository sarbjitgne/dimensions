package com.DimensionsAutomation;

import org.openqa.selenium.By;

public class LoginDimObjects {
    By username = By.id("LoginID");
    By password = By.id("loginPass");
    By loginButton = By.cssSelector("button.btn.btn-primary[type=submit]");
//    By loginButton = By.cssSelector("button[type=submit]");
//    By loginButton = By.className("btn btn-primary");
}
