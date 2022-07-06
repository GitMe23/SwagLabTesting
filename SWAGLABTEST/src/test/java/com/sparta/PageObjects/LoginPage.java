package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver webDriver;

    String password = "secret_sauce";

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
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

    public String getUsernameField() {
        return webDriver.findElement(By.id("user-name")).getText();
    }

    public String getPasswordField() {
        return webDriver.findElement(By.id("password")).getText();
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
