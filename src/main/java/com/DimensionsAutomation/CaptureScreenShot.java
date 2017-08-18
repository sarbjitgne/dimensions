package com.DimensionsAutomation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by 1530155 on 8/8/2017.
 */
public class CaptureScreenShot {
    private WebDriver driverToUse;

    public CaptureScreenShot(WebDriver driverToUse) {
        this.driverToUse = driverToUse;
    }

    public void captureSrcShot(String fileName) {
        try {
            TakesScreenshot srcShot = ((TakesScreenshot) driverToUse);
            File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
//            String folder = "C:\\Sarbjit\\MVNLearn\\ScreenShots\\";
//            String firstStep = folder.concat(fileName);
            //String secondStep = firstStep.concat(".jpg");
            File destinationFile = new File(System.getProperty("user.dir")+"\\target\\ScreenShots\\"+fileName+System.currentTimeMillis()+".jpg");
            FileUtils.copyFile(srcFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
