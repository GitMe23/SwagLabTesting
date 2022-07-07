package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver webDriver;

    String password = "secret_sauce";

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get("https://www.saucedemo.com/");
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void enterUsername(String userName) {
        webDriver.findElement(By.id("user-name")).sendKeys(userName);
    }

    public void enterPassword() {
        webDriver.findElement(By.id("password")).sendKeys(password);
    }

    public void enterPassword(String userPassword) {
        webDriver.findElement(By.id("password")).sendKeys(userPassword);
    }

    public void clickLogin() {
        webDriver.findElement(By.id("login-button")).click();
    }


    public InventoryPage loginAsStandardUser() {
        enterUsername("standard_user");
        enterPassword();
        clickLogin();
        return new InventoryPage(webDriver);
    }

    public void loginAsLockedOutUser() {
        enterUsername("locked_out_user");
        enterPassword();
        clickLogin();
    }

    public InventoryPage loginAsProblemUser() {
        enterUsername("problem_user");
        enterPassword();
        clickLogin();
        return new InventoryPage(webDriver);
    }

    public InventoryPage loginAsPerformanceGlitchUser() {
        enterUsername("performance_glitch_user");
        enterPassword();
        clickLogin();
        return new InventoryPage(webDriver);
    }

    public void setDeviceSize(int width, int height) {
        webDriver.manage().window().setSize(new Dimension(width, height));
    }


    public void setAsMobilePortrait() {
        setDeviceSize(320, 480);
    }

    public void setAsMobileLandscape() {
        setDeviceSize(480, 320);
    }

    public void setAsSmallTabletPortrait() {
        setDeviceSize(600,800);
    }

    public void setAsSmallTabletLandscape() {
        setDeviceSize(800,600);
    }

    public void setAsTabletPortrait() {
        setDeviceSize(768,1024);
    }

    public void setAsTabletLandscape() {
        setDeviceSize(1024,768);
    }

    public void setAsMaximumWindowSize() {
        webDriver.manage().window().maximize();
    }

    public void setFullScreen() {
        webDriver.manage().window().fullscreen();
    }


}
