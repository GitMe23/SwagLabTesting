package com.sparta.driverfactory;

import org.openqa.selenium.WebDriver;


public abstract class DriverManager {

    protected WebDriver webDriver;

    public abstract void createDriver();

    public void quitDriver(){
        if (webDriver != null){
            webDriver.quit();
            webDriver = null;
        }
    }

    public WebDriver getDriver(){
        if (webDriver == null){
            createDriver();
        }
        return webDriver;
    }

}
