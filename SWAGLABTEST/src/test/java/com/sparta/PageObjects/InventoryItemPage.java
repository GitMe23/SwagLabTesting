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
    public String getDescription(){return webDriver.findElement(By.className("inventory_details_desc")).getText();}
    public double getPrice(){
        String itemPrice = webDriver.findElement(By.className("inventory_details_price")).getText();
        itemPrice = itemPrice.replaceAll("[$]", "");
        double price = Double.parseDouble(itemPrice);
        return price;
    }

    public boolean addedToCart(){
        boolean isItemInCart = false;
        if(webDriver.findElement(By.className("btn_inventory")).getText().equals("ADD TO CART")){
            webDriver.findElement(By.className("btn_inventory")).click();
            return isItemInCart = true;
        }
        else if(webDriver.findElement(By.className("btn_inventory")).getText().equals("REMOVE")){
            return isItemInCart = true;
        }
        return isItemInCart;
    }
    public boolean removedFromCart(){
        webDriver.findElement(By.className("btn_inventory")).click();
        boolean isItemInCart = true;
        if(webDriver.findElement(By.className("btn_inventory")).getText().equals("REMOVE")){
            webDriver.findElement(By.className("btn_inventory")).click();
            return isItemInCart = false;
        }
        else if(webDriver.findElement(By.className("btn_inventory")).getText().equals("ADD TO CART")){
            return isItemInCart = true;
        }
        return isItemInCart;
    }


    public InventoryPage goToInventoryPage(){
        webDriver.findElement(By.id("back-to-products")).click();
        return new InventoryPage(webDriver);
    }

}
