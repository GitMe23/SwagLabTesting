package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractPageTests {
    private WebDriver driver;
    private static ChromeDriverService service;
    private Page page;

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
        page = new LoginPage(driver).loginAsStandardUser();
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
    @DisplayName("check: getUrl method")
    void checkGetUrlMethod() {
        assertEquals("https://www.saucedemo.com/inventory.html", page.getUrl());
    }

    @Test
    @DisplayName("check: clickCartIcon")
    void checkClickCartIcon() {
        assertEquals(CartPage.class, page.clickCartIcon().getClass());
    }

    @DisplayName("setDimensions/getDimensions")
    @Disabled
    @Nested
    class setDimensionsGetDimensions {
        @Test
        @DisplayName("check: setDimensions and getDimensions")
        void checkSetDimensionsAndGetDimensions() {
            page.setDimensions(600, 600);
            assertEquals(new Dimension(600, 600), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimensions to iPhone SE")
        void checkSetDimensionsToIPhoneSe() {
            page.changeDimensionsToIPhoneSE();
            assertEquals(new Dimension(375, 667), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimension to iPhone XR")
        void checkSetDimensionToIPhoneXr() {
            page.changeDimensionToIPhoneXR();
            assertEquals(new Dimension(414, 896), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimension to Samsung Galaxy SE8+")
        void checkSetDimensionToSamsungGalaxySe8() {
            page.changeDimensionToSamsungGalaxyS8Plus();
            assertEquals(new Dimension(360, 740), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimension to iPad Air")
        void checkSetDimensionToIPadAir() {
            page.changeDimensionToIPadAir();
            assertEquals(new Dimension(820, 1180), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimension to Surface Pro 7")
        void checkSetDimensionToSurfacePro7() {
            page.changeDimensionToSurfaceProSeven();
            assertEquals(new Dimension(912, 1368), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimension to Galaxy Fold")
        void checkSetDimensionToGalaxyFold() {
            page.changeDimensionToGalaxyFold();
            assertEquals(new Dimension(280, 653), page.getDimensions());
        }

        @Test
        @DisplayName("check: setDimension to Nest Hub")
        void checkSetDimensionToNestHub() {
            page.changeDimensionToNestHub();
            assertEquals(new Dimension(1024, 600), page.getDimensions());
        }
    }

    @DisplayName("Social Media Links")
    @Nested
    class socialMediaLinks {
        @Test
        @DisplayName("check: Twitter")
        void checkTwitter() {
            page.clickTwitterLink();
            assertTrue(page.getUrl().contains("twitter"));
        }

        @Test
        @DisplayName("check: Facebook")
        void checkFacebook() {
            page.clickFacebookLink();
            assertTrue(page.getUrl().contains("facebook"));
        }

        @Test
        @DisplayName("check: LinkedIn")
        void checkLinkedIn() {
            page.clickLinkedInLink();
            assertTrue(page.getUrl().contains("linkedin"));
        }
    }

    @DisplayName("Sidebar")
    @Nested
    class sidebar {
        @Test
        @DisplayName("check: return to inventory page")
        void checkReturnToInventoryPage() {
            assertEquals("https://www.saucedemo.com/inventory.html", page.clickCartIcon().getCheckout().clickSidebarInventory().getUrl());
        }

        @Test
        @DisplayName("check: go to About page")
        void checkGoToAboutPage() {
            assertEquals("https://saucelabs.com/", page.clickSidebarAbout().getUrl());
        }

        @Test
        @DisplayName("check: logout")
        void checkLogout() {
            assertEquals("https://www.saucedemo.com/", page.clickSidebarLogout().getUrl());
        }

        @Test
        @DisplayName("check: reset state")
        void checkResetState() {
            InventoryPage inventoryPage = (InventoryPage) page;
            inventoryPage.addToCart(0);
            inventoryPage.addToCart(1);
            inventoryPage.addToCart(2);
            inventoryPage.clickSidebarReset();
            assertThrowsExactly(NoSuchElementException.class, inventoryPage::viewItemsInCart);
        }
    }
}
