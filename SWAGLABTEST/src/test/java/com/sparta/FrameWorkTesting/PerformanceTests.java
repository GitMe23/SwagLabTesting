package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.LoginPage;
import com.sparta.PageObjects.Page;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class PerformanceTests {
    private WebDriver driver;
    private static ChromeDriverService service;
    private LoginPage loginPage;
    private Page page;
    private double startTime;
    private double timeTaken;

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
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        startTime = getTime();
        page = loginPage.loginAsPerformanceGlitchUser();
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

    @AfterAll
    static void tearDownAll() {
        service.stop();
    }

    @Test
    @DisplayName("check: time taken to log in")
    void checkTimeTakenToLogIn() {
        //TODO: Make performance tests >.<
        System.out.println("" + (timeTaken = getTime() - startTime));
    }
    private double getTime() {
        return System.nanoTime() / 1_000_000.00;
    }
}
