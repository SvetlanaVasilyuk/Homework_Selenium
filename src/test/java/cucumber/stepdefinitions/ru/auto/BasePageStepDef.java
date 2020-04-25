package cucumber.stepdefinitions.ru.auto;

import pages.ru.auto.BasePage;
import pages.ru.auto.ModelSalesPage;
import pages.ru.auto.WelcomePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import data.DataStorage;
import io.cucumber.java.ru.Допустим;
import org.testng.Assert;

public class BasePageStepDef {
    private BasePage page;

    @Допустим("открывается страница авто.ру {string}")
    public void открываетсяСтраницаАвтоРу(String string) {
        Selenide.open(string);
        page = new WelcomePage();
        if (page.modalAppears()) {
            page.closeModal();
        }
    }

    @Допустим("^в списке (?:марок|моделей) отображается количество объявлений по (?:марке|модели) (.*)$")
    public void checkItemListCount(String string) {
        String listCountStr =
                page.getListItemCount(string)
                        .getText();
        DataStorage.getInstance().setCount(Integer.parseInt(listCountStr));
    }

    @Допустим("^пользователь переходит на страницу с объявлениями по (?:марке|модели) (.*)$")
    public void openItemPage(String string) {
        page.getItemList()
                .find(Condition.exactText(string))
                .click();
        page = new ModelSalesPage();
    }

    @Допустим("ожидается появление кнопки {string} с отображаемым количеством объявлений с погрешностью {int}")
    public void checkCountInButton(String string, int error) {
        String buttonText =
                page.getButtons()
                        .find(Condition.matchText(string))
                        .getText();
        String buttonCountText = buttonText.replaceAll("[^0-9]+", "");
        int buttonCount = Integer.parseInt(buttonCountText);
        Assert.assertEquals(buttonCount, (int) DataStorage.getInstance().getCount(), error);
    }
}
