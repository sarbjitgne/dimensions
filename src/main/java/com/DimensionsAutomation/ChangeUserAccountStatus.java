package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class ChangeUserAccountStatus extends BasePageNavigation{
    private WebDriver driverForUse;
//    private String userStatusVerify = null;
    private SelectUserForOperation selectRow;
    private ObjectsUserManager userManagerButtons = new ObjectsUserManager();
//    public Wait wait = new FluentWait(driverForUse)
//            .withTimeout(60, TimeUnit.SECONDS)
//            .pollingEvery(500,TimeUnit.MILLISECONDS);

    public ChangeUserAccountStatus(WebDriver driverForUse) {
        super(driverForUse);
        this.driverForUse = driverForUse;
        selectRow = new SelectUserForOperation(driverForUse);
    }
    public String changeUserStatus(String columnName, String rollNumber, String actionRequired){
        selectRow.selectRequiredRow(columnName, rollNumber);
        if(actionRequired.equalsIgnoreCase("active")){
            driverForUse.findElement(userManagerButtons.activateUser).click();
        }
        if(actionRequired.equalsIgnoreCase("blocked")){
            driverForUse.findElement(userManagerButtons.blockUser).click();
        }
        alertHandle();
        wait(60,500).until(ExpectedConditions.invisibilityOfElementLocated(basePage.processingWindow));
        clickHomeButton();
        clickManageUsers();
        return selectRow.verifyRequiredStatus(columnName, rollNumber);
    }
}
