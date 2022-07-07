package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.InventoryItemPage;
import com.sparta.PageObjects.InventoryPage;
import com.sparta.PageObjects.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;


public class InventoryTesting {
    private WebDriver driver;
    private static ChromeDriverService service;
    private static ChromeOptions options;
    private LoginPage loginPage;
    private static InventoryPage inventoryPage;
    private InventoryItemPage inventoryItemPage;

    private InventoryPage sortedInventoryPage;

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

    }

    @DisplayName("Inventory Page Tests")
    @Nested
    class InventoryPageTests {
        @Test
        @DisplayName("Given I am on the inventory page when I look at the items available then I should be able to view 6 different items")
        void sixItemsOnPage() {
            Assertions.assertEquals(6, inventoryPage.numberOfProducts());
        }

        @ParameterizedTest
        @DisplayName("Given I am on the inventory page when I look at each product then I should be able to see the correct product name")
        @MethodSource("individualProductNames")
        void productNames(String actual, String expected) {
            Assertions.assertEquals(expected, actual);

        }

        private static Stream<Arguments> individualProductNames() {
            return Stream.of(
                    Arguments.arguments(inventoryPage.getProductName(0), "Sauce Labs Backpack"),
                    Arguments.arguments(inventoryPage.getProductName(1), "Sauce Labs Bike Light"),
                    Arguments.arguments(inventoryPage.getProductName(2), "Sauce Labs Bolt T-Shirt"),
                    Arguments.arguments(inventoryPage.getProductName(3), "Sauce Labs Fleece Jacket"),
                    Arguments.arguments(inventoryPage.getProductName(4), "Sauce Labs Onesie"),
                    Arguments.arguments(inventoryPage.getProductName(5), "Test.allTheThings() T-Shirt (Red)"));
        }

        @Test
        @DisplayName("Given I am on the inventory page when I look at each product then I should be able to see the correct product description")
        void correctDescription() {
            String backpackDescription = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
            Assertions.assertEquals(inventoryPage.getProductDescription(0), backpackDescription);
        }

        @ParameterizedTest
        @DisplayName("Given I am on the inventory page when I look at each product then I should be able to see the correct product price")
        @MethodSource("individualProductPrice")
        void itemPrice(double actual, double expected) {
            Assertions.assertEquals(expected, actual);
        }

        private static Stream<Arguments> individualProductPrice() {
            return Stream.of(
                    Arguments.arguments(inventoryPage.getProductPrice(0), 29.99),
                    Arguments.arguments(inventoryPage.getProductPrice(1), 9.99),
                    Arguments.arguments(inventoryPage.getProductPrice(2), 15.99),
                    Arguments.arguments(inventoryPage.getProductPrice(3), 49.99),
                    Arguments.arguments(inventoryPage.getProductPrice(4), 7.99),
                    Arguments.arguments(inventoryPage.getProductPrice(5), 15.99));
        }


        @Test
        @DisplayName("Given I am on the inventory page when I click remove from cart then it should remove that item from my basket")
        void addToBasket() {
            Assertions.assertTrue(inventoryPage.addedToCart(1));
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click remove from cart then it should remove that item from my basket")
        void removedFromBasket() {
            Assertions.assertFalse(inventoryPage.removedFromCart(1));
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click on the name of an item it should take me to that items page ")
        void goToItemPageViaPosition() {
            inventoryItemPage = inventoryPage.goToItemPageBasedOnSortedOrderPosition(0);
            Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=4", inventoryItemPage.getUrl());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click on the name of an item it should take me to that items page ")
        void goToItemPageViaName() {
            inventoryItemPage = inventoryPage.goToItemPageBasedOnNameOfItem("Test.allTheThings() T-Shirt (Red)");
            Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=3", inventoryItemPage.getUrl());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click on the image of an item it should take me to that items page ")
        void goToItemPageViaImage() {
            inventoryItemPage = inventoryPage.goToItemPageByImageBasedOnPosition(3);
            Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=5", inventoryItemPage.getUrl());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click sort by name (A-Z) then it should sort the items in descending order alphabetically ")
        void sortAlphabetically() {
            driver.findElement((By.cssSelector(".product_sort_container > option:nth-child(2)"))).click();
            sortedInventoryPage = inventoryPage.sortBy(1);
            Assertions.assertTrue(sortedInventoryPage.isSortedByNameAlphabetically());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click sort by name (Z-A) then it should sort the items in ascending order alphabetically")
        void sortAlphabeticallyReverse() {
            sortedInventoryPage = inventoryPage.sortBy(2);
            Assertions.assertTrue(sortedInventoryPage.isSortedByNameAlphabeticallyReversed());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click sort by price (low to high) then it should sort the items in ascending order by price")
        void sortedByPriceLowHigh() {
            sortedInventoryPage = inventoryPage.sortBy(3);
            Assertions.assertTrue(sortedInventoryPage.isSortedByPriceLowToHigh());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I click sort by price (high to low) then it should sort the items in descending order by price")
        void sortedPriceHighToLow() {
            sortedInventoryPage = inventoryPage.sortBy(4);
            Assertions.assertTrue(sortedInventoryPage.isSortedByPriceHighToLow());
        }

        @Test
        @DisplayName("Given I am on the inventory page when I look at the cart symbol then I should be able to see how many items are in my cart")
        void itemNumber() {
            inventoryPage.getProducts(0).findElement(By.className("btn_inventory")).click();
            inventoryPage.getProducts(1).findElement(By.className("btn_inventory")).click();
            inventoryPage.getProducts(2).findElement(By.className("btn_inventory")).click();
            Assertions.assertEquals(3, inventoryPage.viewItemsInCart());
        }
    }
}


