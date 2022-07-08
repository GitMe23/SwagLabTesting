package com.sparta.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public static DriverManager getDriverManager(WebDrivers myDriver) {

        DriverManager manager = null;

        switch (myDriver) {
            case CHROME:
                manager = new ChromeDriverManager();
                break;
//            case FIREFOX:
//                manager = new FirefoxDriverManager();
//                break;
//            case EDGE:
//                manager = new EdgeDriverManager();
//                break;
//            case SAFARI:
//                manager = new SafariDriverManager();
//                break;
//            case OPERA:
//                manager = new OperaDriverManager();
//                break;
        }
        return manager;
    }
}
