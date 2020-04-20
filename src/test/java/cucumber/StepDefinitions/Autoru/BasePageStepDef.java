package cucumber.StepDefinitions.Autoru;

import AutoruPages.BasePage;
import AutoruPages.ModelSalesPage;
import AutoruPages.WelcomePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Допустим;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasePageStepDef {
    private BasePage welcomePage = new WelcomePage();
    private BasePage modelSalesPage = new ModelSalesPage();
    private int listCount;

    @Допустим("открывается страница авто.ру {string}")
    public void открываетсяСтраницаАвтоРу(String string) throws InterruptedException {
        Selenide.open(string);
        if (welcomePage.modalAppears()) {
            welcomePage.closeModal();
        }
    }

    @Допустим("^в списке марок отображается количество объявлений по марке (.*)$")
    public void checkMarkListCount(String string) {
            String listCountStr =
                    welcomePage
                        .getItemList()
                        .find(Condition.exactText(string))
                        .sibling(0)
                        .getText();
        listCount = Integer.parseInt(listCountStr);
    }

    @Допустим("^пользователь переходит на страницу с объявлениями по марке (.*)$")
    public void openMarkPage(String string) {
        welcomePage
                .getItemList()
                .find(Condition.exactText(string))
                .click();
    }

    @Допустим("отображается то же количество объявлений на кнопке")
    public void checkCountInButton() {
        String buttonText =
                modelSalesPage
                .getButton()
                .getText();
        String str  = buttonText.replaceAll("[^0-9]+", "");
        int buttonCount = Integer.parseInt(str);
        Assert.assertEquals(buttonCount, listCount);
    }

    @Допустим("^в списке моделей отображается количество объявлений по модели (.*)$")
    public void checkModelListCount(String string) {
        String listCountStr =
                modelSalesPage
                        .getItemList()
                        .find(Condition.matchesText(string))
                        .sibling(0)
                        .getText();
        listCount = Integer.parseInt(listCountStr);
    }

    @Допустим("^пользователь переходит на страницу с объявлениями по модели (.*)$")
    public void openModelPage(String string) {
        modelSalesPage
                .getItemList()
                .find(Condition.exactText(string))
                .click();
    }
}
