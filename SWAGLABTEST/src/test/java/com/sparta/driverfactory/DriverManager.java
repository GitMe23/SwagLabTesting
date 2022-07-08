package com.sparta.driverfactory;

import org.openqa.selenium.WebDriver;


public interface DriverManager {
    WebDriver createDriver();
    void closeTab();
    void stopService();
}
