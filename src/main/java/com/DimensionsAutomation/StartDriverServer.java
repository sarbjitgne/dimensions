package com.DimensionsAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;

/**
 * Created by 1530155 on 7/19/2017.
 */
public class StartDriverServer {
    private static WebDriver startDriverServer;
//    public StartDriverServer(WebDriver driverServer){
//        this.startDriverServer = driverServer;
//      }

    public static WebDriver startDriver(String browserName){
        if(browserName.equals("IE")){
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\IEDriverServer.exe");
            return startDriverServer = new InternetExplorerDriver();
        }
        if(browserName.equals("Chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
//            return startDriverServer = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments(Arrays.asList("--start-maximized"));
            options.addArguments(Arrays.asList("--ssl-protocol=any"));
            options.addArguments(Arrays.asList("--ignore-ssl-errors=true"));
            options.addArguments(Arrays.asList("--disable-extensions"));
            options.addArguments(Arrays.asList("--ignore-certificate-errors"));
            DesiredCapabilities crcapabilities = DesiredCapabilities.chrome();
//            crcapabilities.setCapability("chrome.binary","C:/Sarbjit/MVNLearn/src/main/resources/chromedriver.exe");
//            crcapabilities.setCapability("Project","MVNLearn");
            crcapabilities.setCapability(ChromeOptions.CAPABILITY, options);
            crcapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            return startDriverServer = new ChromeDriver(crcapabilities);

        }
        if(browserName.equals("Mozilla")){
            return startDriverServer = new FirefoxDriver();
        }
        return null;
    }
}
