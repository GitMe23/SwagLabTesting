package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutStepTwoPage {


    WebDriver webDriver;
    By itemdDiv = By.className("cart_list");
    By itemPrice = By.className("inventory_item_price");
    By total = By.className("summary_total_label");
    By taxCost = By.className("summary_tax_label");

    By button = By.id("cancel");



    public CheckoutStepTwoPage(WebDriver webDriver) {
    
        this.webDriver = webDriver;
    }

    public boolean isTotalCorrect() {
        List<WebElement> productPrices = webDriver.findElement(itemdDiv).findElements(itemPrice);
        double total = 0;
        for (WebElement i : productPrices) {
            String prices = i.getText();
            double doublePrices = Double.parseDouble(prices);
            total += doublePrices;
        }
        return total == Double.parseDouble(this.total.toString()) + Double.parseDouble(taxCost.toString());
    }

    public boolean hasTotalAtBottom() {
        String swagTotal = webDriver.findElement(this.total).getText();
        return swagTotal != null;

    }

    public boolean isInventoryPage() {
        webDriver.findElement(button).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return webDriver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");

    }


}
