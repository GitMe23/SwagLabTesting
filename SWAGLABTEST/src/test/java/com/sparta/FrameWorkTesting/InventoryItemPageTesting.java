package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.InventoryItemPage;
import com.sparta.PageObjects.InventoryPage;
import com.sparta.PageObjects.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class InventoryItemPageTesting {
    private WebDriver driver;
    private static ChromeDriverService service;
    private static ChromeOptions options;
    private LoginPage loginPage;
    private static InventoryPage inventoryPage;

    private InventoryItemPage inventoryItemPage;


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
        options = new ChromeOptions();
        options.addArguments("headless");
    }


    @BeforeEach
    void setup() {
        driver = new ChromeDriver(service, options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = loginPage.loginAsStandardUser();
        inventoryItemPage = inventoryPage.goToItemPageBasedOnNameOfItem("Sauce Labs Backpack");
    }

    @DisplayName("Inventory Item Page Tests")
    @Nested
    class InventoryItemPageTests {
        @Test
        @DisplayName("Given that I am on a specific items page the name of the item should be correct")
        void itemNameCorrect() {
            Assertions.assertEquals("Sauce Labs Backpack",inventoryItemPage.getName());
        }
        @Test
        @DisplayName("Given that I am on a specific items page the description should be correct")
        void correctDescription(){
            String description = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
            Assertions.assertEquals(description, inventoryItemPage.getDescription());
        }
        @Test
        @DisplayName("Given that I am on a specific items page the price should be correct")
        void CorrectPrice(){
            Assertions.assertEquals(29.99, inventoryItemPage.getPrice());
        }
        @Test
        @DisplayName("Given I am on a specific item page when I click remove from cart then it should remove that item from my basket")
        void removeFromBasket(){
            Assertions.assertFalse(inventoryItemPage.removedFromCart());
        }
        @Test
        @DisplayName("Given I am on a specific item page when I click add to cart then it should add that item to my basket")
        void addToBasket(){
            Assertions.assertTrue(inventoryItemPage.addedToCart());
        }
        @Test
        @DisplayName("Given I am on a specific items page when I click back to products it should return me to the inventory page")
        void returnToInventory(){
            inventoryPage = inventoryItemPage.goToInventoryPage();
            Assertions.assertEquals("https://www.saucedemo.com/inventory.html",inventoryPage.getUrl());
        }

    }
}
