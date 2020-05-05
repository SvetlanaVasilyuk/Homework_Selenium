package com.github.SvetlanaVasilyuk.homework_20_pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuBlock {
    private final WebDriver webDriver;

    public MenuBlock(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void selectSection(String sectionName) {
        WebElement section = webDriver.findElement(By.xpath("//a[text()=\"" + sectionName + "\"]"));
        section.click();

    }
}