package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    static WebDriver webDriver;

    String username;
    String password = "secret_sauce";
    
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void enterPassword() {
        webDriver.findElement(By.id("password")).sendKeys(password);
    }
    public void clickLogin() {
        webDriver.findElement(By.id("login-button")).click();
    }

    public void loginAsStandardUser() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        enterPassword();
        clickLogin();
    }

    public void loginAsLockedOutUser() {
        webDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        enterPassword();
        clickLogin();
    }

    public void loginAsProblemUser() {
        webDriver.findElement(By.id("user-name")).sendKeys("problem_user");
        enterPassword();
        clickLogin();
    }

    public void loginAsPerformanceGlitchUser() {
        webDriver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        enterPassword();
        clickLogin();
    }






}
