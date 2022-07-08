package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutStepOneTests {
    private WebDriver driver;
    private static ChromeDriverService service;
    private CheckoutStepOnePage checkoutStepOnePage;

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
        Page page = new LoginPage(driver).loginAsStandardUser();
        InventoryPage inventoryPage = (InventoryPage) page;
        inventoryPage.addToCart(0);
        inventoryPage.addToCart(1);
        inventoryPage.addToCart(2);
        page = inventoryPage.clickCartIcon().getCheckout();
        checkoutStepOnePage = (CheckoutStepOnePage) page;
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

    @AfterAll
    static void tearDownAll() {
        service.stop();
    }

    @DisplayName("Navigation")
    @Nested
    class navigation {
        @Test
        @DisplayName("check: Click continue")
        void checkClickContinue() {
            assertEquals(CheckoutStepTwoPage.class, checkoutStepOnePage.clickContinue().getClass());
        }

        @Test
        @DisplayName("check: Click cancel")
        void checkClickCancel() {
            assertEquals(CartPage.class, checkoutStepOnePage.clickCancel().getClass());
        }
    }

    @DisplayName("Entering Data")
    @Nested
    class enteringData {
        @Test
        @DisplayName("check: Enter first name")
        void checkEnterFirstName() {
            checkoutStepOnePage.enterFirstName("Hello");
            assertEquals("Hello", checkoutStepOnePage.getFirstNameField());
        }

        @Test
        @DisplayName("check: Enter last name")
        void checkEnterLastName() {
            checkoutStepOnePage.enterLastName("Hello");
            assertEquals("Hello", checkoutStepOnePage.getLastNameField());
        }

        @Test
        @DisplayName("check: Enter post code")
        void checkEnterPostCode() {
            checkoutStepOnePage.enterPostCode("Hello");
            assertEquals("Hello", checkoutStepOnePage.getPostCodeField());
        }

        @Test
        @DisplayName("check: Enter all data at once")
        void checkEnterAllDataAtOnce() {
            checkoutStepOnePage.enterAllData();
            assertEquals(Arrays.toString(new String[]{"Princess", "Daisy", "Sarasaland"}), Arrays.toString(checkoutStepOnePage.getAllFields()));
        }
    }
}
