package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public abstract class Page {
    protected WebDriver webDriver;
    private final By sideBarOpen = new By.ById("react-burger-menu-btn");
    private final By inventorySideBarLink = new By.ById("inventory_sidebar_link");
    private final By aboutSidebarLink = new By.ById("about_sidebar_link");
    private final By logoutSidebarLink = new By.ById("logout_sidebar_link");
    private final By resetSidebarLink = new By.ById("reset_sidebar_link");
    private final By cartIcon = new By.ByClassName("shopping_cart_link");
    private final By twitterLink = new By.ByLinkText("Twitter");
    private final By facebookLink = new By.ByLinkText("Facebook");
    private final By linkedInLink = new By.ByLinkText("LinkedIn");
    private final By sideBarCross = new By.ById("react-burger-cross-btn");

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }
    public void setDimensions(int width, int height) {
        webDriver.manage().window().setSize(new Dimension(width, height));
    }
    public Dimension getDimensions() {
        return webDriver.manage().window().getSize();
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
        fluentWaitToClick(10, twitterLink);
        changeToNextTab();
        return new ExternalPage(webDriver);
    }

    public ExternalPage clickFacebookLink() {
        fluentWaitToClick(10, facebookLink);
        changeToNextTab();
        return new ExternalPage(webDriver);
    }

    public ExternalPage clickLinkedInLink() {
        fluentWaitToClick(10, linkedInLink);
        changeToNextTab();
        return new ExternalPage(webDriver);
    }
    public CartPage clickCartIcon() {
        fluentWaitToClick(10, cartIcon);
        return new CartPage(webDriver);
    }
    public InventoryPage clickSidebarInventory() {
        openDropDownMenu();
        explicitWaitToClick(10, inventorySideBarLink);
        return new InventoryPage(webDriver);
    }
    public ExternalPage clickSidebarAbout() {
        openDropDownMenu();
        explicitWaitToClick(10, aboutSidebarLink);
        return new ExternalPage(webDriver);
    }
    public LoginPage clickSidebarLogout() {
        openDropDownMenu();
        explicitWaitToClick(10, logoutSidebarLink);
        return new LoginPage(webDriver);
    }
    public void clickSidebarReset() {
        openDropDownMenu();
        explicitWaitToClick(10, resetSidebarLink);
        closeDropDownMenu();
    }
    public void changeToNextTab() {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1));
        wait.until((driver) -> driver.getWindowHandles().size() > 1);
        Set<String> allWindows = webDriver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(webDriver.getWindowHandle())) {
                webDriver.close();
                webDriver.switchTo().window(window);
                break;
            }
        }
    }
    public void explicitWaitForClickable(int seconds, By element) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void explicitWaitToClick(int seconds, By element) {
        explicitWaitForClickable(seconds, element);
        webDriver.findElement(element).click();
    }
    public void fluentWaitToClick(int seconds, By element) {
        Wait<WebDriver> wait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(1));
        wait.until((driver) -> driver.findElement(element)).click();
    }
    private void openDropDownMenu() {
        fluentWaitToClick(5, sideBarOpen);
    }
    private void closeDropDownMenu() { fluentWaitToClick(5, sideBarCross); }
}
