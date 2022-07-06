package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryItemPage extends Page {



    public InventoryItemPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }
    public String getName(){
        return webDriver.findElement(By.className("inventory_details_name")).getText();
    }
    public String getPrice(){
        return webDriver.findElement(By.className("inventory_details_price")).getText();
    }

    public boolean isProductInCart(){
        boolean isItemInCart = false;
        if(webDriver.findElement(By.className("btn_inventory")).getText().equals("Add to cart")){
            return isItemInCart;
        }
        else if(webDriver.findElement(By.className("btn_inventory")).getText().equals("Remove")){
            return isItemInCart = true;
        }
        return isItemInCart;
    }

    public InventoryItemPage addOrRemoveProductToCart() {
        webDriver.findElement(By.id("btn_inventory")).click();
        return this;
    }
    public InventoryPage goToInventoryPage(){
        webDriver.findElement(By.id("back-to-products")).click();
        return new InventoryPage(webDriver);
    }

}
