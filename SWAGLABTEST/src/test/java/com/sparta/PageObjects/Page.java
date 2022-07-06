package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
    public void setDimensions(int width, int height) {
        webDriver.manage().window().setSize(new Dimension(width, height));
    }
    public void changeDimensionsToIPhoneSE() {
        setDimensions(375, 667);
    }
    public void changeDimensionToIPhoneXR() {
        setDimensions(414, 896);
    }
    public void changeDimensionToSamsungGalaxyS8Plus() {
        setDimensions(360, 740);
    }
    public void changeDimensionToIPadAir() {
        setDimensions(820, 1180);
    }
    public void changeDimensionToSurfaceProSeven() {
        setDimensions(912, 1368);
    }
    public void changeDimensionToGalaxyFold() {
        setDimensions(280, 653);
    }
    public void changeDimensionToNestHub() {
        setDimensions(1024, 600);
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
