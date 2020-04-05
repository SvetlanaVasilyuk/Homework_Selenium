package com.github.SvetlanaVasilyuk.Homework_21_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected final WebDriver webDriver;
    private final MenuBlock menuBlock;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.menuBlock = new MenuBlock(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public MenuBlock getMenuBlock() {
        return menuBlock;
    }
}
