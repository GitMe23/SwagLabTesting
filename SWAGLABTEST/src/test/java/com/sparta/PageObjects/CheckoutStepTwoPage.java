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

    public double convertElementToDouble(WebElement element) {
        return Double.parseDouble(element.getText().substring(1));
    }

    public List<WebElement> getPrices() {
        return webDriver.findElements(itemPrice);
    }


    public double getSubTotalCalc(List<WebElement> ls) {
//        List<WebElement> productPrices = webDriver.findElements(itemPrice);
        double total = 0;
        for (WebElement i : ls) {
            String prices = i.getText().substring(1);
            double doublePrices = Double.parseDouble(prices);
            total += doublePrices;
        }

        return total;
    }

    public double getFinalTotalCalc(double subTotal) {
        return subTotal + Double.parseDouble(webDriver.findElement(taxCost).getText().substring(6));
    }


    public boolean isSubTotalCorrect(double subTotal) {
        return subTotal == Double.parseDouble(webDriver.findElement(this.subTotal).getText().substring(13));
    }

    //Double.parseDouble(webDriver.findElement(this.taxCost).getText().substring(6))

    public boolean isFinalTotalCorrect(double finalTotal) {
        return finalTotal == Double.parseDouble(webDriver.findElement(this.total).getText().substring(8));
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

    public String convertByElementToString(By element) {
        return webDriver.findElement(element).getText();
    }

    public boolean hasTwoNumsPastPoint(String element) {

//        String subtotal = webDriver.findElement(element).getText();

        String subtotalCheck = element.substring(element.indexOf(".")+1);
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
