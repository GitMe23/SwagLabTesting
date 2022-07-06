package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends Page {

    private WebDriver webDriver;
    public InventoryPage(WebDriver webDriver){
      this.webDriver = webDriver;
    }
    // Options are 1, 2, 3, 4 for A-Z, Z-A, low-high, high-low
    public InventoryPage sortBy(int sortOption){
        WebElement container = webDriver.findElement(By.className("product_sort_container"));
        WebElement containerOption = container.findElement(By.cssSelector(".product_sort_container > option:nth-child(" + sortOption + ")"));

        container.click();
        containerOption.click();
        return this;
    }

    public String getProductName(int index){
        return getProducts(index).findElement(By.className("inventory_item_name")).getText();
    }
    public String getProductDescription(int index){
        return getProducts(index).findElement(By.className("inventory_item_desc")).getText();
    }
    public double getProductPrice(int index){
        String productPrice = getProducts(index).findElement(By.className("inventory_item_price")).getText();
        productPrice = productPrice.replaceAll("[$]", "");

        double price = Double.parseDouble(productPrice);
        return price;
    }

    public WebElement getProducts(int index){
        WebElement products = webDriver.findElements(By.className("inventory_item")).get(index);
        return products;
    }
    public int numberOfProducts(){
        List<WebElement> products = webDriver.findElements(By.className("inventory_item"));
        return products.size();
    }
    public boolean isSortedByNameAlphabetically(){
        boolean orderCorrect = true;
        String previousProduct = "";
        for (int i = 0; i < numberOfProducts(); i++) {
            String name = getProductName(i);
            if (name.compareTo(previousProduct) < 0){
                orderCorrect = false;
            }
            previousProduct = name;
        }
        return orderCorrect;
    }

    public boolean isSortedByNameAlphabeticallyReversed(){
        boolean orderCorrect = true;
        String previousProduct = "";
        for (int i = 0; i < numberOfProducts(); i++) {
            String name = getProductName(i);
            if (name.compareTo(previousProduct) < 0){
                orderCorrect = false;
            }
            previousProduct = name;
        }
        return orderCorrect;
    }

    public boolean isSortedByPriceLowToHigh(){
        boolean orderCorrect = true;
        double previousProduct = 0;

        for (int i=0; i < numberOfProducts(); i++){
            double price = getProductPrice(i);
            if (price < previousProduct){
                orderCorrect = false;
            }
            previousProduct = price;
        }
        return orderCorrect;
    }

    public boolean isSortedByPriceHighToLow(){
        boolean orderCorrect = true;
        double previousProduct = getProductPrice(0);

        for (int i=0; i < numberOfProducts(); i++){
            double price = getProductPrice(i);
            if (!(price <= previousProduct)){
                orderCorrect = false;
            }
            previousProduct = price;
        }
        return orderCorrect;
    }

    public boolean viewProductPageFromName(int index){
        getProducts(index).findElement(By.className("inventory_item_name")).click();

    }
    public boolean viewProductPageFromImage(int index){
        getProducts(index).findElement(By.className("inventory_item_name")).click();

    }












}
