package cucumber.StepDefinitions;

import SberbankPages.ChoosingDepositTab;
import SberbankPages.DepositDetailsPage;
import SberbankPages.DepositsPage;
import SberbankPages.WelcomePage;
import com.codeborne.selenide.*;
import io.cucumber.java.ru.Допустим;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SberbankStepDefinitions {
    private static String currentWindowHandle;

    @Допустим("открывается страница сбербанка")
    public void открываетсяСтраницаСбербанка() {
        Selenide.open("https://www.sberbank.ru/");
        WelcomePage
                .openPage()
                .closeCookieWarning();
    }

    @Допустим("название страницы {string}")
    public void названиеСтраницы(String string) {
        Assert.assertEquals(Selenide.title(), string);
    }

    @Допустим("пользователь переходит в раздел {string} и подраздел {string}")
    public void пользовательПереходитВРаздел(String string, String string2) throws InterruptedException {
        Thread.sleep(3000);
        WelcomePage
                .openPage()
                .hoverSection(string)
                .selectSubSection(string2);
    }

//    @Допустим("пользователь переходит в подраздел {string}")
//    public void пользовательПереходитВПодРаздел(String string) throws InterruptedException {
//        Thread.sleep(5000);
//        WelcomePage
//                .openPage()
//                .selectSubSection(string);
//    }

    @Допустим("пользователь переходит на вкладку {string}")
    public void пользовательПереходитНаВкладку(String string) {
        DepositsPage
                .openPage()
                .selectTab(string);
        SelenideElement frame =
                ChoosingDepositTab
                    .openPage()
                    .getFrame();
        Selenide.switchTo().frame(frame);
    }

    @Допустим("отобразились чек-боксы")
    public void отобразилисьЧекБоксы(List<String> dataTable) {
        HashMap<String, SelenideElement> checkboxes =
                ChoosingDepositTab
                        .openPage()
                        .getCheckboxes();

        Assert.assertEquals(checkboxes.size(), dataTable.size());

        for (Map.Entry<String, SelenideElement> ch : checkboxes.entrySet()){
            String str = checkboxes.keySet().toString();
            if (dataTable.contains(ch.getKey())){
                ch.getValue().should(Condition.exist);
            }
            else{
                ch.getValue().shouldNotBe(Condition.exist);
            }
        }

    }

    @Допустим("установлены чек-боксы")
    public void установленыЧекБоксы(List<String> dataTable) {
        HashMap<String, SelenideElement> checkboxes =
                ChoosingDepositTab
                        .openPage()
                        .getCheckboxes();

        for (Map.Entry<String, SelenideElement> ch : checkboxes.entrySet()){
            if (dataTable.contains(ch.getKey())){
                ch.getValue().shouldBe(Condition.checked);
            }
            else{
                ch.getValue().shouldNotBe(Condition.checked);
            }
        }
    }

//    ArrayList<String> checkboxesNames = new ArrayList<>();
////    int i = 0;
////        for (SelenideElement c : checkboxes){
////        c.shouldHave(Condition.value(dataTable.get(i)));
////        i++;
////    }

    @Допустим("отображаются вклады")
    public void отображаютсяВклады(List<String> dataTable) {
        ElementsCollection deposits =
                ChoosingDepositTab
                        .openPage()
                        .getDepositsNames();

        //deposits.shouldHaveSize(dataTable.size());

        deposits.shouldBe(CollectionCondition.exactTexts(dataTable));

//        for (SelenideElement d : deposits){
//            if (dataTable.contains(d.getValue())){
//                d.should(Condition.exist);
//            }
//            else{
//                d.shouldNotBe(Condition.exist);
//            }
//        }
    }

    @Допустим("пользователь выбирает чек-боксы")
    public void пользовательВыбираетЧекБоксы(List<String> dataTable) {
        HashMap<String, SelenideElement> checkboxes =
                ChoosingDepositTab
                        .openPage()
                        .getCheckboxes();

        for (Map.Entry<String, SelenideElement> ch : checkboxes.entrySet()){
            if (dataTable.contains(ch.getKey())^ch.getValue().is(Condition.checked)){
                ChoosingDepositTab
                        .openPage()
                        .clickCheckbox(ch.getKey());
            }
        }

    }

    @Допустим("пользователь нажимает кнопку Подробнее вклада {string}")
    public void пользовательНажимаетКнопкуПодробнееВклада(String string) {
        ChoosingDepositTab page = ChoosingDepositTab.openPage();

        SelenideElement deposit =
                page
                    .getDeposits()
                    .find(Condition.matchesText(string));
        page
                .detailButton(deposit)
                .click();
    }

    @Допустим("открылось новое окно")
    public void открылосьНовоеОкно() {
        WebDriver driver =  WebDriverRunner.getWebDriver();
        ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }

    @Допустим("отображается надпись {string}")
    public void отображаетсяНадпись(String string) throws InterruptedException {
        Thread.sleep(10000);
        DepositDetailsPage
                .openPage()
                .getHeader()
                .shouldHave(Condition.matchesText(string));
    }

}
