package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.*;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;

public class CheckoutStepTwoPageTests {

    private WebDriver driver;

    private static ChromeDriverService service;

    private CheckoutStepTwoPage checkoutStepTwoPage;

    private CheckoutStepOnePage checkoutStepOnePage;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    private CartPage cartPage;



    @BeforeAll
    static void setupAll() {
        service = new ChromeDriverService
                .Builder()
                .usingDriverExecutable(new File( "src/test/resources/chromedriver.exe"))
                .usingAnyFreePort()
                .build();

        try {
            service.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @BeforeEach
    void setup(){
        driver = new ChromeDriver(service);
        loginPage = new LoginPage(driver);
        //go from login page to checkoutsteptwopage
        //do the process of going from loginpage to checkoutsteptwopage
        //inventory page  =  inventorypage.standard user

        inventoryPage = loginPage.loginAsStandardUser();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        cartPage = inventoryPage.clickCartIcon();
        checkoutStepOnePage = cartPage.getCheckout();
        checkoutStepOnePage.enterAllData();
        checkoutStepTwoPage = checkoutStepOnePage.clickContinue();
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);

    }

    @Test
    @DisplayName("Check if subtotal is correct")
    void checkIfSubTotalIsCorrect() {
        Assertions.assertTrue(checkoutStepTwoPage.isSubTotalCorrect());
    }

    @Test
    @DisplayName("Check if total is correct")
    void checkIfFinalTotalIsCorrect() {
        Assertions.assertTrue(checkoutStepTwoPage.isFinalTotalCorrect());
    }

    @Test
    @DisplayName("Check if final total is at bottom of page")
    void checkFinalTotalIsAtBottomOfPage() {
        Assertions.assertTrue(checkoutStepTwoPage.hasFinalTotalAtBottom());
    }

    @Test
    @DisplayName("Check if tax is at bottom of page")
    void checkIfTaxIsAtBottomOfPage() {
        Assertions.assertTrue(checkoutStepTwoPage.hasTaxAtBottom());
    }

    @Test
    @DisplayName("Check prices only have 2 decimal places after point")
    void checkPricesHaveOnlyTwoDecimalPlaces() {
        Assertions.assertTrue(checkoutStepTwoPage.hasTwoNumsPastPoint(checkoutStepTwoPage.getTaxCost()));
    }

    @Test
    @DisplayName("Check that the cancel button takes you to the inventory page")
    void checkCancelButtonTakesYouToInterviewPage() {
        Assertions.assertEquals(InventoryPage.class, checkoutStepTwoPage.clickCancel().getClass());
    }

    @Test
    @DisplayName("Check that the finish button takes you to the checkoutComplete page")
    void checkFinishButtonTakesYouToCheckoutCompletePage() {
        Assertions.assertEquals(CheckoutCompletePage.class, checkoutStepTwoPage.clickFinish().getClass());
    }

    @AfterEach
    void tearDown() {driver.close();}

    @AfterAll
    static void tearDownAll() {service.stop();}
}
