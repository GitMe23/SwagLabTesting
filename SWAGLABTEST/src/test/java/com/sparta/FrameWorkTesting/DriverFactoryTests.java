package com.sparta.FrameWorkTesting;

import com.sparta.driverfactory.DriverFactory;
import com.sparta.driverfactory.DriverManager;
import com.sparta.driverfactory.WebDrivers;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class DriverFactoryTests {
    static DriverManager manager;
    static WebDriver webDriver;

    @BeforeAll
    static void setupAll() {
        manager = DriverFactory.getDriverManager(WebDrivers.CHROME);
    }

    @BeforeEach
    void setup() {
        webDriver = manager.createDriver();
    }

    @AfterEach
    void tearDown() {
        manager.closeTab();
    }

    @AfterAll
    static void tearDownAll() {
        manager.stopService();
    }

    @Test
    @DisplayName("check: get Chrome Driver from DriverFactory")
    void checkGetChromeDriverFromDriverFactory() {
        assertEquals(ChromeDriver.class, webDriver.getClass());
    }
}
