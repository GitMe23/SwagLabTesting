package com.sparta.FrameWorkTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class FrameWorkTest {
    private WebDriver driver;
    private static ChromeDriverService service;
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
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

    @AfterAll
    static void tearDownAll() {
        service.stop();
    }

    @DisplayName("Generic Page Methods")
    @Nested
    class genericPageMethods {

    }

    @DisplayName("Login Page Methods")
    @Nested
    class loginPageMethods {

    }

    @DisplayName("Checkout Step Two Page Methods")
    @Nested
    class checkoutStepTwoPageMethods {

    }

    @DisplayName("Checkout Complete Page Methods")
    @Nested
    class checkoutCompletePageMethods {

    }

    @DisplayName("Cart Page Methods")
    @Nested
    class cartPageMethods {

    }

    @DisplayName("Inventory Page Methods")
    @Nested
    class inventoryPageMethods {

    }

    @DisplayName("Inventory Item Page Methods")
    @Nested
    class inventoryItemPageMethods {

    }

    @DisplayName("Checkout Step One Page Methods")
    @Nested
    class checkoutStepOnePageMethods {

    }
}
