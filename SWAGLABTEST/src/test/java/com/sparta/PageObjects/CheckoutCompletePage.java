package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends Page {
    public CheckoutCompletePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public InventoryPage backHome() {
        webDriver.findElement(By.id("back-to-products")).click();
        return new InventoryPage(webDriver);
    }


}