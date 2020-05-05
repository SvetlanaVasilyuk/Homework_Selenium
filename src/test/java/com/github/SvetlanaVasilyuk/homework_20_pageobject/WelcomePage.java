package com.github.SvetlanaVasilyuk.homework_20_pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePage extends BasePage {
    public WelcomePage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.titleContains("Старт"));
    }
}
