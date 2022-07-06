package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends Page {

    public CartPage (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    WebDriver webDriver;

    public CheckoutStepOnePage getCheckout() {
        webDriver.findElement(By.id("checkout")).click();
        return new CheckoutStepOnePage(webDriver);
    }

    public boolean checkItemRemoved() {
        return webDriver.findElement(By.className("removed_cart_item")) != null;
    }

    public InventoryItemPage checkItemFromCart() {
        webDriver.findElement(By.className("inventory_item_name")).click();
        return new InventoryItemPage(webDriver);
    }

    public InventoryPage checkContinueShopBtn() {
        webDriver.findElement(By.id("continue-shopping"));
        return new InventoryPage(webDriver);
    }
}
