package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends Page {

    public CartPage (WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    WebDriver webDriver;

    public CheckoutStepOnePage getCheckout() {
        webDriver.findElement(By.id("checkout")).click();
;
        return new CheckoutStepOnePage(webDriver);
    }

    public boolean checkItemRemoved() {
        if (webDriver.findElement(By.className("removed_cart_item")) != null) {
            return true;
        }
        return false;
    }
}
