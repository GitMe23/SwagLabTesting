package com.sparta.PageObjects;

import io.cucumber.java.bs.I;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends Page {

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


    public InventoryItemPage goToItemPageBasedOnSortedOrderPosition(int index){
        String productName = getProductName(index);
        getProducts(index).findElement(By.className("inventory_item_name")).click();
        return new InventoryItemPage(webDriver);
    }

    public InventoryItemPage goToItemPageBasedOnName (String name){
        webDriver.findElement(By.linkText(name)).click();
        return new InventoryItemPage(webDriver);
    }

    public InventoryItemPage goToItemPageBYImage(int index){
        String productName = getProductName(index);
        getProducts(index).findElement(By.className("inventory_item_img")).click();
        return new InventoryItemPage(webDriver);
    }
    // might need to combine find elements for the below method
    public int viewItemsInCart(){
        String shoppingCartNumber = "";
        try {
            shoppingCartNumber = webDriver.findElement(By.className("shopping_cart_badge")).getText();
        }catch (Exception e) {
            return 0;
        }
        return Integer.parseInt(shoppingCartNumber);
    }
}
