package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends Page {
    private By firstNameField = new By.ById("first-name");
    private By lastNameField = new By.ById("last-name");
    private By postCodeField = new By.ById("postal-code");
    private By cancelButton = new By.ById("cancel");
    private By continueButton = new By.ById("continue");

    private void enterStringIntoField(String value, By field) {
        webDriver.findElement(field).sendKeys(value);
    }
    public void enterFirstName(String value) {
        enterStringIntoField(value, firstNameField);
    }
    public void enterLastName(String value) {
        enterStringIntoField(value, lastNameField);
    }
    public void enterPostCode(String value) {
        enterStringIntoField(value, postCodeField);
    }
    public void enterAllData() {
        enterFirstName("Princess");
        enterLastName("Daisy");
        enterPostCode("Sarasaland");
    }
    public CartPage clickCancel() {
        webDriver.findElement(cancelButton).click();
        return new CartPage(webDriver);
    }
    public CheckoutStepTwoPage clickContinue() {
        webDriver.findElement(continueButton).click();
        return new CheckoutStepTwoPage(webDriver);
    }
    public CheckoutStepOnePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
