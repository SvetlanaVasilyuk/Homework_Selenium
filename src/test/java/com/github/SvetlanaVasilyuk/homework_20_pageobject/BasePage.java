package com.github.SvetlanaVasilyuk.homework_20_pageobject;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected final WebDriver webDriver;
    private final MenuBlock menuBlock;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.menuBlock = new MenuBlock(webDriver);
    }

    public MenuBlock getMenuBlock() {
        return menuBlock;
    }
}
