package cucumber.StepDefinitions.Autoru;

import AutoruPages.BasePage;
import AutoruPages.ModelSalesPage;
import AutoruPages.WelcomePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import data.DataStorage;
import io.cucumber.java.ru.Допустим;
import org.testng.Assert;

public class BasePageStepDef {
    private BasePage welcomePage = new WelcomePage();
    private BasePage modelSalesPage = new ModelSalesPage();

    @Допустим("открывается страница авто.ру {string}")
    public void открываетсяСтраницаАвтоРу(String string) throws InterruptedException {
        Selenide.open(string);
        if (welcomePage.modalAppears()) {
            welcomePage.closeModal();
        }
    }

    @Допустим("^в списке марок отображается количество объявлений по марке (.*)$")
    public void checkMarkListCount(String string) {
        String listCountStr = welcomePage.getListItemCount(string).getText();
        DataStorage.setValue("listCount", Integer.parseInt(listCountStr));
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
        Assert.assertEquals(buttonCount, DataStorage.getValue("listCount"));
    }

    @Допустим("^в списке моделей отображается количество объявлений по модели (.*)$")
    public void checkModelListCount(String string) {
        String listCountStr = modelSalesPage.getListItemCount(string).getText();
        DataStorage.setValue("listCount", Integer.parseInt(listCountStr));
    }

    @Допустим("^пользователь переходит на страницу с объявлениями по модели (.*)$")
    public void openModelPage(String string) {
        modelSalesPage
                .getItemList()
                .find(Condition.exactText(string))
                .click();
    }
}
