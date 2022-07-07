package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutStepTwoPage extends Page {


    //private WebDriver webDriver;
    private By itemdDiv = By.className("cart_list");
    private By itemPrice = By.className("inventory_item_price");
    private By taxCost = By.cssSelector("#checkout_summary_container > div > div.summary_info > div.summary_tax_label");
    private By total = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]");

    private By subTotal = By.className("summary_subtotal_label");

    private By cancelButton = By.id("cancel");

    private By finishButton = By.id("finish");



    public CheckoutStepTwoPage(WebDriver webDriver) {

        this.webDriver = webDriver;
    }

    public double subTotalCalc() {
        List<WebElement> productPrices = webDriver.findElements(itemPrice);
        double total = 0;
        for (WebElement i : productPrices) {
            String prices = i.getText().substring(1);
            double doublePrices = Double.parseDouble(prices);
            total += doublePrices;
        }
//        System.out.println(total);
//        System.out.println(Double.parseDouble(webDriver.findElement(this.taxCost).getText().substring(6)));
//        System.out.println( Double.parseDouble(webDriver.findElement(this.total).getText().substring(8)));
        return total;
    }

    public double finalTotalCalc() {
        return subTotalCalc() + Double.parseDouble(webDriver.findElement(taxCost).getText().substring(6));
    }


    public boolean isSubTotalCorrect() {
        return subTotalCalc() == Double.parseDouble(webDriver.findElement(this.subTotal).getText().substring(13));
    }

    //Double.parseDouble(webDriver.findElement(this.taxCost).getText().substring(6))

    public boolean isFinalTotalCorrect() {
        return finalTotalCalc() == Double.parseDouble(webDriver.findElement(this.total).getText().substring(8));
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


    public boolean hasTwoNumsPastPoint(By element) {

        String subtotal = webDriver.findElement(element).getText();
        String subtotalCheck = subtotal.substring(subtotal.indexOf(".")+1);
        System.out.println(subtotalCheck);
        return subtotalCheck.length() == 2;
    }

    //getters for private By elements
    public By getItemdDiv() {
        return itemdDiv;
    }

    public By getItemPrice() {
        return itemPrice;
    }

    public By getTaxCost() {
        return taxCost;
    }

    public By getTotal() {
        return total;
    }

    public By getSubTotal() {
        return subTotal;
    }

    public By getCancelButton() {
        return cancelButton;
    }

    public By getFinishButton() {
        return finishButton;
    }
}
