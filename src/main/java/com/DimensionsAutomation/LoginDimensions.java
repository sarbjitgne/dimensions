package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginDimensions {
    private WebDriver driverToUse;
    private String windowTitle;

    public LoginDimensions(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
    }

    public String getWindowTitle() {
        return windowTitle;
    }
    public void loginApp(String URL, String uName, String uPassword){
        driverToUse.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driverToUse.get(URL);
        driverToUse.manage().window().maximize();
        LoginDimObjects loginObj = new LoginDimObjects();
        driverToUse.findElement(loginObj.username).sendKeys(uName);
        driverToUse.findElement(loginObj.password).sendKeys(uPassword);
        driverToUse.findElement(loginObj.loginButton).click();
        windowTitle = driverToUse.getTitle();
    }
}
