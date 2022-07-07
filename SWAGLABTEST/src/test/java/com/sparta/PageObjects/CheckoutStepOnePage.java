package com.sparta.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends Page {
    private final By firstNameField = new By.ById("first-name");
    private final By lastNameField = new By.ById("last-name");
    private final By postCodeField = new By.ById("postal-code");
    private final By cancelButton = new By.ById("cancel");
    private final By continueButton = new By.ById("continue");

    public CheckoutStepOnePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
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
    public String getFirstNameField() {
        explicitWaitForClickable(10, firstNameField);
        return webDriver.findElement(firstNameField).getAttribute("value");
    }
    public String getLastNameField() {
        explicitWaitForClickable(10, lastNameField);
        return webDriver.findElement(lastNameField).getAttribute("value");
    }
    public String getPostCodeField() {
        explicitWaitForClickable(10, postCodeField);
        return webDriver.findElement(postCodeField).getAttribute("value");
    }
    public String[] getAllFields() {
        return new String[] {getFirstNameField(), getLastNameField(), getPostCodeField()};
    }
    public CartPage clickCancel() {
        fluentWaitToClick(10, cancelButton);
        return new CartPage(webDriver);
    }
    public CheckoutStepTwoPage clickContinue() {
        fluentWaitToClick(10, continueButton);
        return new CheckoutStepTwoPage(webDriver);
    }
}
