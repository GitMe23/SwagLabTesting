package com.sparta.FrameWorkTesting;

import com.sparta.PageObjects.*;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        List<WebElement> elements = checkoutStepTwoPage.getPrices();
        double subTotal = checkoutStepTwoPage.getSubTotalCalc(elements);
        boolean correctSubTotal = checkoutStepTwoPage.isSubTotalCorrect(subTotal);


        Assertions.assertTrue(correctSubTotal);
    }

    @Test
    @DisplayName("Check if subtotal is correct with correct value")
    void checkIfSubTotalIsCorrectWithCorrectValue() {
        Assertions.assertTrue(checkoutStepTwoPage.isSubTotalCorrect(45.98));
    }

    @Test
    @DisplayName("Check if subtotal is correct with incorrect value")
    void checkIfSubTotalIsCorrectWithIncorrectValue() {
        Assertions.assertFalse(checkoutStepTwoPage.isSubTotalCorrect(0));
    }

    @Test
    @DisplayName("Check if total is correct")
    void checkIfFinalTotalIsCorrect() {

        List<WebElement> elements = checkoutStepTwoPage.getPrices();
        double finalTotal = checkoutStepTwoPage.getFinalTotalCalc(checkoutStepTwoPage.getSubTotalCalc(elements));
        boolean currentTotal = checkoutStepTwoPage.isFinalTotalCorrect(finalTotal);

        Assertions.assertTrue(currentTotal);
    }

    @Test
    @DisplayName("check if total is correct with incorrect value")
    void checkIfTotalIsCorrectWithIncorrectValue() {
        Assertions.assertFalse(checkoutStepTwoPage.isFinalTotalCorrect(9.23));
    }

    @Test
    @DisplayName("check if total is correct with correct value")
    void checkIfTotalIsCorrectWithCorrectValue() {
        Assertions.assertTrue(checkoutStepTwoPage.isFinalTotalCorrect(49.66));
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
    @DisplayName("Check prices only have 2 decimal places after point with proper number")
    void checkPricesHaveOnlyTwoDecimalPlaces() {
        Assertions.assertTrue(checkoutStepTwoPage.hasTwoNumsPastPoint("762233.90"));
    }

    @Test
    @DisplayName("Check prices only have 2 decimal places after point with weird number")
    void checkPricesHaveOnlyTwoDecimalPlacesWithWeirdNumber() {
        Assertions.assertFalse(checkoutStepTwoPage.hasTwoNumsPastPoint("43.4209"));
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
