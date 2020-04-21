package cucumber.stepdefinitions.Sberbank;

import SberbankPages.ChoosingDepositTab;
import com.codeborne.selenide.*;
import io.cucumber.java.ru.Допустим;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoosingDepositTabStepDef {
    private ChoosingDepositTab tab = new ChoosingDepositTab();

    @Допустим("отобразились чек-боксы")
    public void checkboxesAreDisplayed(List<String> dataTable) {
        SelenideElement frame = tab.getFrame();
        Selenide.switchTo().frame(frame);

        HashMap<String, SelenideElement> checkboxes = tab.getCheckboxes();

        Assert.assertEquals(checkboxes.size(), dataTable.size());

        for (Map.Entry<String, SelenideElement> ch : checkboxes.entrySet()) {
            String str = checkboxes.keySet().toString();
            if (dataTable.contains(ch.getKey())) {
                ch.getValue().should(Condition.exist); // через visible почему-то не находит
            } else {
                ch.getValue().shouldNotBe(Condition.exist); // через visible почему-то не находит
            }
        }
    }

    @Допустим("установлены чек-боксы")
    public void checkboxesAreChecked(List<String> dataTable) {
        HashMap<String, SelenideElement> checkboxes = tab.getCheckboxes();

        for (Map.Entry<String, SelenideElement> ch : checkboxes.entrySet()) {
            if (dataTable.contains(ch.getKey())) {
                ch.getValue().shouldBe(Condition.checked);
            } else {
                ch.getValue().shouldNotBe(Condition.checked);
            }
        }
    }

    @Допустим("отображаются вклады")
    public void depositsAreDisplayed(List<String> dataTable) {
        ElementsCollection deposits = tab.getDepositsNames();
        deposits.shouldBe(CollectionCondition.exactTexts(dataTable));
    }

    @Допустим("пользователь выбирает чек-боксы")
    public void checkCheckboxes(List<String> dataTable) {
        HashMap<String, SelenideElement> checkboxes = tab.getCheckboxes();

        for (Map.Entry<String, SelenideElement> ch : checkboxes.entrySet()) {
            if (dataTable.contains(ch.getKey()) ^ ch.getValue().is(Condition.checked)) {
                tab.clickCheckbox(ch.getKey());
            }
        }

    }

    @Допустим("пользователь нажимает кнопку Подробнее вклада {string}")
    public void viewDepositDetails(String string) {
        SelenideElement deposit =
                tab
                        .getDeposits()
                        .find(Condition.matchesText(string));
        tab
                .detailButton(deposit)
                .click();
    }
}
