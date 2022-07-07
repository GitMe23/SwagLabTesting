package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutStepTwoPage extends Page {


    //private WebDriver webDriver;
    private By itemdDiv = By.className("cart_list");
    private By itemPrice = By.className("inventory_item_price");
    private By total = By.className("summary_total_label");
    private By taxCost = By.className("summary_tax_label");

    private By subTotal = By.className("summary_subtotal_label");

    private By cancelButton = By.id("cancel");

    private By finishButton = By.id("finish");



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
        String swagSubTotal = webDriver.findElement(this.subTotal).getText();
        return swagSubTotal != null;

    }

    public boolean hasTaxAtBottom() {
        String swagTax = webDriver.findElement(this.taxCost).getText();
        return swagTax != null;

    }

    public boolean hasFinalTotalAtBottom() {
        String swagTotal = webDriver.findElement(this.total).getText();
        return swagTotal != null;

    }

    //could be depreciated in next version of MVP
    public boolean isInventoryPage() {
        webDriver.findElement(cancelButton).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return webDriver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");

    }

    //could be depreciated in next version of MVP
    public boolean isConfirmationPage() {
        webDriver.findElement(finishButton).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return webDriver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");

    }

    public CheckoutCompletePage clickFinish() {
        webDriver.findElement(finishButton).click();
        return new CheckoutCompletePage(webDriver);
    }

    public InventoryPage clickCancel() {
        webDriver.findElement(cancelButton).click();
        return new InventoryPage(webDriver);
    }


    public boolean hasTwoNumsPastPoint() {

        String subtotal = webDriver.findElement(subTotal).getText();
        String subtotalCheck = subtotal.substring(subtotal.indexOf("."));

        return subtotalCheck.length() < 2;
    }


}
