package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class LoginDimensions extends BasePageNavigation {
    private WebDriver driverForUse;
    private String windowTitle;

    public LoginDimensions(WebDriver driverForUse) {
        super(driverForUse);
        this.driverForUse = driverForUse;
    }

//    public LoginDimensions(WebDriver driverToUse) {
//        super(driverToUse);
//        this.driverToUse = driverToUse;
//    }

    public String getWindowTitle() {
        return windowTitle;
    }
    public void loginApp(String URL, String uName, String uPassword){
        driverForUse.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driverForUse.get(URL);
        driverForUse.manage().window().maximize();
        ObjectsLoginDim loginObj = new ObjectsLoginDim();
        driverForUse.findElement(loginObj.username).sendKeys(uName);
        driverForUse.findElement(loginObj.password).sendKeys(uPassword);
        driverForUse.findElement(loginObj.loginButton).click();
        waitToPageToLoad();
//        wait(60,500).until(ExpectedConditions.invisibilityOfElementLocated(basePage.processingWindow));
        windowTitle = driverForUse.getTitle();
        waitToPageToLoad();
    }
}
