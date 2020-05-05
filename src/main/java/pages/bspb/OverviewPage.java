package pages.bspb;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class OverviewPage extends BasePage {
    private SelenideElement financeBLock = $(byId("can-spend"));
    private SelenideElement financeTitle = financeBLock.$(By.className("text"));
    private SelenideElement financeAmount = financeBLock.$(By.className("amount"));
    private SelenideElement financeInfo = financeBLock.$(By.className("my-assets"));

    public SelenideElement getFinanceTitle() {
        return financeTitle;
    }

    public SelenideElement getFinanceAmount() {
        return financeAmount;
    }

    public SelenideElement getFinanceInfo() {
        financeTitle.hover();
        return financeInfo;
    }

    public static OverviewPage openPage() {
        return new OverviewPage();
    }
}
