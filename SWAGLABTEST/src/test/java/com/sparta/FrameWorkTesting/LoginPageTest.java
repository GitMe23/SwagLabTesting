package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {

    private WebDriver driver;
    private static ChromeDriverService service;

    private LoginPage loginPage;


    @BeforeAll
    static void setupAll() {
        service = new ChromeDriverService
                .Builder()
                .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(service);
        //driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Nested
    class PageTests {

        @Test
        @DisplayName("Check testing is setup correctly")
        void checkTestSetup() {
            Assertions.assertEquals(1,1);
        }

        @Test
        void checkThatWebDriverIsWorking() {
            assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Check getUrl method returns login page via web driver")
        void checkGetUrl() {
            Assertions.assertEquals("https://www.saucedemo.com/", loginPage.getUrl());
        }

        @Test
        @DisplayName("Check clickLogin button")
        void checkClickLoginButton() {
            loginPage.clickLogin();
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test=error]")).getText();
            Assertions.assertEquals("Epic sadface: Username is required", errorMsg);
        }

        @Test
        @DisplayName("Check enterPassword enters \"secret_sauce\"")
        void checkEnterPassword() {
            loginPage.enterPassword();
            driver.findElement(By.id("login-button")).click();
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test=error]")).getText();
            Assertions.assertEquals("Epic sadface: Username is required", errorMsg);
        }

        @Test
        @DisplayName("Check enterPassword with string argument enters argument correctly")
        void checkEnterPassWordWithArgument() {
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("FooBar");
            driver.findElement(By.id("login-button")).click();
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test=error]")).getText();
            Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
        }

    }

    @Nested
    class UserLoginTests {

        @Test
        @DisplayName("Check standard user can login")
        void checkStandardUserLogin() {
            loginPage.loginAsStandardUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Check locked out user can't login")
        void checkProblemUserCanLogin() {
            loginPage.loginAsProblemUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        }
        @Test
        @DisplayName("Check locked out user can't login")
        void checkLockedOutUserCantLogin() {
            loginPage.loginAsLockedOutUser();
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test=error]")).getText();
            Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.", errorMsg);
        }

        @Test
        @DisplayName("Check performance glitch user can login")
        void checkPerformanceGlitchUserCanLogin() {
            loginPage.loginAsPerformanceGlitchUser();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        }

    }

    @Nested
    class responsiveTests {

        @Test
        @DisplayName("Check setDeviceSize")
        void checkSetDeviceSize() {
            loginPage.setDeviceSize(1111, 500);
            Assertions.assertEquals(new Dimension(1111, 500), driver.manage().window().getSize());
        }

        @Test
        @DisplayName("Check setAsSmallTabletPortrait")
        void checkSetAsSmallTabletPortrait() {
            loginPage.setAsSmallTabletPortrait();
            Assertions.assertEquals(new Dimension(600, 800), driver.manage().window().getSize());
        }

        @Test
        @DisplayName("Check setAsSmallTabletLandscape")
        void checkSetAsSmallTabletLandscape() {
            loginPage.setAsSmallTabletLandscape();
            Assertions.assertEquals(new Dimension(800, 600), driver.manage().window().getSize());
        }

        @Test
        @DisplayName("Check setAsSmallTabletLandscape")
        void checkSetAsTabletPortrait() {
            loginPage.setAsTabletPortrait();
            Assertions.assertEquals(new Dimension(768, 1024), driver.manage().window().getSize());
        }

        @Test
        @DisplayName("Check setAsSmallTabletLandscape")
        void checkSetAsTabletLandscape() {
            loginPage.setAsTabletLandscape();
            Assertions.assertEquals(new Dimension(1024, 768), driver.manage().window().getSize());
        }

    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

    @AfterAll
    static void tearDownAll() {
        service.stop();
    }


}
