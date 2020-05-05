package com.github.SvetlanaVasilyuk.homework_20_pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage extends BasePage {
    private By financeBlock = By.xpath("//div[span[normalize-space(text())=\"Финансовая свобода\"]]");
    private By financeAmount = By.xpath("//div[@id=\"can-spend\"]//span[@class=\"amount\"]");
    private By financeInfo = By.xpath("//div[@id=\"can-spend\"]//small[@class=\"my-assets\"]");

    public OverviewPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.titleContains("Обзор"));
    }

    public String getFinances() {
        webDriver.findElement(financeBlock);
        WebElement amount = webDriver.findElement(financeAmount);
        return amount.getText();
    }

    public String getFinancesInfo() {
        WebElement amount = webDriver.findElement(financeAmount);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amount).build().perform();
        WebElement myFinances = webDriver.findElement(financeInfo);
        return myFinances.getText();
    }
}
