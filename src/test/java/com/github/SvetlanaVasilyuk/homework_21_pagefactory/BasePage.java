package com.github.SvetlanaVasilyuk.homework_21_pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {
    protected final WebDriver webDriver;
    private final MenuBlock menuBlock;
    protected final List<WebElement> navBarItems;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.menuBlock = new MenuBlock(webDriver);
        this.navBarItems = webDriver.findElements(By.xpath("//ul[@class='navigation-menu nav']/li"));
        PageFactory.initElements(webDriver, this);
    }

    public void selectSection(String sectionName){
        boolean sectionFound = false;
        for (WebElement item : navBarItems){
            if (item.getText().equals(sectionName.toUpperCase())){
                item.click();
                sectionFound = true;
                break;
            }
        }
        if (!sectionFound){
            throw new IllegalArgumentException(String.format("Нет кнопки меню с текстом \"%s\"", sectionName));
        }
    }

    public MenuBlock getMenuBlock() {
        return menuBlock;
    }
}
