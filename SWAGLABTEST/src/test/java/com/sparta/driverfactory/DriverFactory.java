package com.sparta.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public static WebDriver getDriver(String myDriver) {

        WebDriver driver = null;

        switch (myDriver) {
            case "chrome":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "edge":
                driver  = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            case "safari":
                driver  = new SafariDriver();
                driver.manage().window().maximize();
                break;
            case "opera":
                driver  = new OperaDriver();
                driver.manage().window().maximize();
                break;


        }
    return driver;
    }
}
