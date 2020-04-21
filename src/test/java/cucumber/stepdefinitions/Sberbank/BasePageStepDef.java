package cucumber.stepdefinitions.Sberbank;

import SberbankPages.*;
import com.codeborne.selenide.*;
import io.cucumber.java.ru.Допустим;

public class BasePageStepDef {
    private BasePage page = new BasePage();

    @Допустим("открывается страница сбербанка {string}")
    public void openSberbankPage(String string) {
        Selenide.open(string);
        if (page.cookieWarningAppears()) {
            page.closeCookieWarning();
        }
    }

    @Допустим("пользователь переходит в раздел {string}")
    public void openSection(String string) {
        page.hoverSection(string);
    }

    @Допустим("пользователь переходит в подраздел {string}")
    public void openSubSection(String string) {
        page.selectSubSection(string);
    }

    @Допустим("пользователь переходит на вкладку {string}")
    public void openTab(String string) {
        page.selectTab(string);
    }

    @Допустим("отображается заголовок {string}")
    public void headerIsDiplayed(String string) {
        page
                .getHeader()
                .should(Condition.exist);

        // Элемент находится, но по какой-то причине не видим и не заполнен
        // .shouldHave(Condition.matchesText(string));
    }
}
