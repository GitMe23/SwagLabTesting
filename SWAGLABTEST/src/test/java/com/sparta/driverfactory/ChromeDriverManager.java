package com.sparta.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;


public class ChromeDriverManager implements DriverManager {

    private WebDriver webDriver;
    private ChromeDriverService service;
    private ChromeOptions options;

    //@Override
    public void createDriver() {
        service = new ChromeDriverService
                .Builder()
                .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(service, options);
    }
}