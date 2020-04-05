package com.github.SvetlanaVasilyuk.Homework_21_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage extends BasePage {
    @FindBy(xpath = "//div[@id=\"can-spend\"]/span[@class=\"text\"]")
    private WebElement financeBLock;

    //@FindBys({@FindBy(id = "can-spend"), @FindBy(xpath = ".//span[@class=\"amount\"]")})  ---- не работает почему-то
    @FindBy(xpath = "//div[@id=\"can-spend\"]//span[@class=\"amount\"]")
    private WebElement financeAmount;


    @FindBy(xpath = "//div[@id=\"can-spend\"]//small[@class=\"my-assets\"]")
    private WebElement financeInfo;

    public OverviewPage(WebDriver webDriver) {
        super(webDriver);
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.titleContains("Обзор"));
    }

    public String getFinanceBlockTitle() {
        return financeBLock.getText();
    }

    public String getFinances() {
        return financeAmount.getText();
    }

    public String getFinancesInfo() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(financeAmount).build().perform();
        return financeInfo.getText();
    }

    public static OverviewPage open(WebDriver webDriver) {
        return new OverviewPage(webDriver);
    }
}
