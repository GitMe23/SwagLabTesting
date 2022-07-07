package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class FrameTestAhmed {

    private WebDriver driver;
    private static ChromeDriverService service;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutCompletePage completePage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;

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
        driver.get("https://www.saucedemo.com/");
        inventoryPage = new LoginPage(driver).loginAsStandardUser();
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
    @DisplayName("checkout from cart")
    void checkoutFromCart() {
        cartPage = inventoryPage.clickCartIcon();
        cartPage.getCheckout();
    }

    @Test
    @DisplayName("removed item from cart")
    void removedItemFromCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = inventoryPage.clickCartIcon();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        cartPage.checkItemRemoved();
    }

    @Test
    @DisplayName("check item in cart")
    void checkItemInCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = inventoryPage.clickCartIcon();
        cartPage.checkItemFromCart();
    }

    @Test
    @DisplayName("check the continue shopping btn")
    void checkContinueShopping() {
        cartPage = inventoryPage.clickCartIcon();
        cartPage.checkContinueShopBtn();
    }

    @Test
    @DisplayName("check that checkout complete works")
    void completeCheckout() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = inventoryPage.clickCartIcon();
        checkoutStepOnePage = cartPage.getCheckout();
        checkoutStepOnePage.enterAllData();
        checkoutStepTwoPage = checkoutStepOnePage.clickContinue();
        completePage = checkoutStepTwoPage.clickFinish();
        completePage.backHome();
    }

    @Test
    @DisplayName("check multiple items removed")
    void checkMultipleItemsRemoved() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        cartPage = inventoryPage.clickCartIcon();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        cartPage.checkItemRemoved();
    }
}
