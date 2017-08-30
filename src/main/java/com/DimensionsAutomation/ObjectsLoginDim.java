package com.DimensionsAutomation;

import org.openqa.selenium.By;

public class ObjectsLoginDim {
    By username = By.cssSelector("input.form-control[name=LoginID]");
    By password = By.cssSelector("input.form-control[name=loginPass]");
    By loginButton = By.cssSelector("button.btn.btn-primary[type=submit]");
    By loginText = By.cssSelector("span.panel-title");
//    By loginButton = By.cssSelector("button[type=submit]");
//    By loginButton = By.className("btn btn-primary");
}
