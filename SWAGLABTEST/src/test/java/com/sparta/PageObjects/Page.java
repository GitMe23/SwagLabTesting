package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Page {
    protected WebDriver webDriver;
    private By sideBarLink = new By.ById("react-burger-menu-btn");
    private By inventorySideBarLink = new By.ById("inventory_sidebar_link");
    private By aboutSidebarLink = new By.ById("about_sidebar_link");
    private By logoutSidebarLink = new By.ById("logout_sidebar_link");
    private By resetSidebarLink = new By.ById("reset_sidebar_link");
    private By cartIcon = new By.ByClassName("shopping_cart_link");
    private By twitterLink = new By.ByLinkText("twitter");
    private By facebookLink = new By.ByLinkText("facebook");
    private By linkedInLink = new By.ByLinkText("LinkedIn");

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
    public ExternalPage clickTwitterLink() {
        webDriver.findElement(twitterLink).click();
        return new ExternalPage(webDriver);
    }
    public ExternalPage clickFacebookLink() {
        webDriver.findElement(facebookLink).click();
        return new ExternalPage(webDriver);
    }
    public ExternalPage clickLinkedInLink() {
        webDriver.findElement(linkedInLink).click();
        return new ExternalPage(webDriver);
    }
    public CartPage clickCartIcon() {
        webDriver.findElement(cartIcon).click();
        return new CartPage(webDriver);
    }
    public InventoryPage clickSidebarInventory() {
        openDropDownMenu();
        webDriver.findElement(inventorySideBarLink).click();
        return new InventoryPage(webDriver);
    }
    public ExternalPage clickSidebarAbout() {
        openDropDownMenu();
        webDriver.findElement(aboutSidebarLink).click();
        return new ExternalPage(webDriver);
    }
    public LoginPage clickSidebarLogout() {
        openDropDownMenu();
        webDriver.findElement(logoutSidebarLink).click();
        return new LoginPage(webDriver);
    }
    public void clickSidebarReset() {
        openDropDownMenu();
        webDriver.findElement(resetSidebarLink).click();
    }
    private void openDropDownMenu() {
        webDriver.findElement(sideBarLink).click();
    }
}
