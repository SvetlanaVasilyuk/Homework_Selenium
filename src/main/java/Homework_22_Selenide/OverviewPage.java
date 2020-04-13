package Homework_22_Selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class OverviewPage extends BasePage {
    private SelenideElement financeBLock = $(byId("can-spend"));
    private SelenideElement financeTitle = financeBLock.$(By.className("text"));
    private SelenideElement financeAmount = financeBLock.$(By.className("amount"));
    private SelenideElement financeInfo = financeBLock.$(By.className("my-assets"));

    @Step("Получение заголовка блока с финансами")
    public SelenideElement getFinanceTitle() {
        return financeTitle;
    }

    @Step("Получение информации об объеме средств")
    public SelenideElement getFinanceAmount() {
        return financeAmount;
    }

    @Step("Получение всплывающей информации о финансах")
    public SelenideElement getFinanceInfo() {
        financeTitle.hover();
        return financeInfo;
    }

    public static OverviewPage openPage() {
        return new OverviewPage();
    }
}
