package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }

    @AfterAll
    static void tearDownAll() {
        service.stop();
    }

    @DisplayName("Standard User")
    @Nested
    class standardUser {
        @Test
        @DisplayName("check: time taken to log in")
        void checkTimeTakenToLogIn() {
            doLogin(false);
        }

        @Test
        @DisplayName("check: time taken to put 6 items in the cart")
        void checkTimeTakenToPut6ItemsInTheCart() {
            doPut6ItemsIntoCart(false);
        }

        @Test
        @DisplayName("check: time taken to get back to inventory page from cart")
        void checkTimeTakenToGetBackToInventoryPageFromCart() {
            doGoToCartAndBack(false);
        }

        @Test
        @DisplayName("check: log in, add 6 items to cart, checkout")
        void checkInventoryPageAdd6ItemsToCartCheckout() {
            doWholeShoppingProcess(false);
        }

        @Test
        @DisplayName("check: from inventory, go to item page and then go back to inventory page")
        void checkFromInventoryGoToItemPageAndThenGoBackToInventoryPage() {
            doInvPageToItemPageAndBackTest(false);
        }
    }

    @DisplayName("PerformanceGlitchUser")
    @Nested
    class performanceGlitchUser {
        @Test
        @DisplayName("check: time taken to log in")
        void checkTimeTakenToLogIn() {
            doLogin(true);
        }

        @Test
        @DisplayName("check: time taken to put 6 items in the cart")
        void checkTimeTakenToPut6ItemsInTheCart() {
            doPut6ItemsIntoCart(true);
        }

        @Test
        @DisplayName("check: time taken to get back to inventory page from cart")
        void checkTimeTakenToGetBackToInventoryPageFromCart() {
            doGoToCartAndBack(true);
        }

        @Test
        @DisplayName("check: log in, add 6 items to cart, checkout")
        void checkInventoryPageAdd6ItemsToCartCheckout() {
            doWholeShoppingProcess(true);
        }

        @Test
        @DisplayName("check: from inventory, go to item page and then go back to inventory page")
        void checkFromInventoryGoToItemPageAndThenGoBackToInventoryPage() {
            doInvPageToItemPageAndBackTest(true);
        }
    }

    private void doLogin(boolean glitch) {
        startTheClock();
        loginToInventoryPage(glitch);
        stopTheCLock();
        System.out.println("Time from Login Page to Inventory page: " +
                format2Decimal(timeTaken) +
                " seconds.");
        assertTrue(timeTaken < 7);
    }

    private void doPut6ItemsIntoCart(boolean glitch) {
        InventoryPage inventoryPage = loginToInventoryPage(glitch);
        startTheClock();
        for (int i = 0; i < 6; i++) {
            inventoryPage.addToCart(i);
        }
        stopTheCLock();
        System.out.println("Time taken to add log in and add 6 items to cart: " +
                format2Decimal(timeTaken) +
                " seconds.");
        assertTrue(timeTaken < 5);
    }

    private void doGoToCartAndBack(boolean glitch) {
        InventoryPage inventoryPage = loginToInventoryPage(glitch);
        startTheClock();
        inventoryPage.clickCartIcon().checkContinueShopBtn();
        stopTheCLock();
        System.out.println("Time taken to go to the cart page and then return to the inventory page: " +
                format2Decimal(timeTaken) +
                " seconds.");
        assertTrue(timeTaken < 5);
    }

    private void doWholeShoppingProcess(boolean glitch) {
        startTheClock();
        InventoryPage inventoryPage = loginToInventoryPage(glitch);
        for (int i = 0; i < 6; i++) {
            inventoryPage.addToCart(i);
        }
        CheckoutStepOnePage checkoutStepOnePage = inventoryPage.clickCartIcon().getCheckout();
        checkoutStepOnePage.enterAllData();
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.clickContinue();
        checkoutStepTwoPage.clickFinish();
        stopTheCLock();
        System.out.println("Time taken to log in, add 6 items to cart, " +
                "go to cart, go to checkout, complete checkout process and finish: " +
                format2Decimal(timeTaken) +
                " seconds.");
        assertTrue(timeTaken < 10);
    }

    private void doInvPageToItemPageAndBackTest(boolean glitch) {
        InventoryPage inventoryPage = loginToInventoryPage(glitch);
        startTheClock();
        InventoryItemPage itemPage = inventoryPage.goToItemPageByImageBasedOnPosition(0);
        itemPage.goToInventoryPage();
        stopTheCLock();
        System.out.println("Time taken to go into an item page, and then go back to the inventory page: " +
                format2Decimal(timeTaken) +
                " seconds.");
        assertTrue(timeTaken < 3);
    }

    private InventoryPage loginToInventoryPage(boolean glitch) {
        InventoryPage inventoryPage;
        if (glitch)
            inventoryPage = loginPage.loginAsPerformanceGlitchUser();
        else
            inventoryPage = loginPage.loginAsStandardUser();
        return inventoryPage;
    }

    private void startTheClock() {
        startTime = getTime();
    }

    private void stopTheCLock() {
        timeTaken = getTime() - startTime;
    }

    private String format2Decimal(double number) {
        return String.format("%.2f", number);
    }
    private double getTime() {
        return System.nanoTime() / 1_000_000_000.00;
    }
}
