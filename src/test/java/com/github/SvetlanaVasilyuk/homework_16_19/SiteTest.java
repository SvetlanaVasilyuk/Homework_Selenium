package com.github.SvetlanaVasilyuk.homework_16_19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SiteTest {
    protected WebDriver webDriver;

    protected void clickButtonById(String id) {
        WebElement button = webDriver.findElement(By.id(id));
        button.click();
    }

    protected void clickButtonByClass(String buttonClass) {
        WebElement button = webDriver.findElement(By.className(buttonClass));
        button.click();
    }

    protected void clickSubmitButton() {
        WebElement button = webDriver.findElement(By.xpath("//input[@type=\"submit\"]"));
        button.click();
    }

    protected void fillTextInput(String fieldName, String value) {
        WebElement field = webDriver.findElement(By.xpath("//label[.=\"" + fieldName + "\"]/following::input[1]"));
        field.sendKeys(value);
    }

    protected void checkLink(String linkText) {
        WebElement link = webDriver.findElement(By.linkText(linkText));
        Assert.assertTrue(link.isDisplayed());
    }

    protected void clickLink(String linkText) {
        WebElement link = webDriver.findElement(By.linkText(linkText));
        link.click();
    }
}
