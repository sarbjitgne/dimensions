package com.DimensionsAutomation;

import org.openqa.selenium.By;

public class ObjectUploadUsers {
    By uploadUsersText = By.xpath("//div[@id='divUploadUsers']//h1[contains(text(), 'Upload')]");
    By userInputArea = By.cssSelector("textarea.auto-style1");
    By userSubmit = By.cssSelector("button.btn.btn-primary");
    By userSavedConfirmation = By.xpath("");
}
