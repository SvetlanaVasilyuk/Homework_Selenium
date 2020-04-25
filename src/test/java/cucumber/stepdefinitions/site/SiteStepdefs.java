package cucumber.stepdefinitions.site;

import Homework_22_Selenide.LoginPage;
import Homework_22_Selenide.OTPPage;
import Homework_22_Selenide.OverviewPage;
import Homework_22_Selenide.WelcomePage;
import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static com.codeborne.selenide.Selenide.open;

public class SiteStepdefs {

    @Дано("открывается страница сайта для авторизации")
    public void открываетсяCтраницаCайтаДляАвторизации() {
        open("https://idemo.bspb.ru/");
    }

    @Дано("пользователь {string} авторизуется в ЛК с паролем {string}")
    public void пользовательАвторизуетсяВЛКСПаролем(String string, String string2) {
        LoginPage
                .openPage()
                .login(string, string2);
    }


    @Дано("вводит одноразовый пароль {string}")
    public void вводитОдноразовыйПароль(String string) {
        OTPPage
                .openPage()
                .enterOTP(string);
    }

    @Когда("переходит на вкладку {string}")
    public void переходитНаВкладкуОбзор(String string) {
        WelcomePage
                .openPage()
                .selectSection(string);
    }

    @Тогда("отображается блок с названием Финансовая свобода")
    public void отображаетсяБлокСНазваниемФинансоваяСвобода() {
        OverviewPage
                .openPage()
                .getFinanceTitle()
                .shouldHave(Condition.exactText("Финансовая свобода"));
    }

    @Тогда("отображается сумма финансов пользователя")
    public void отображаетсяСуммаФинансовПользователя() {
        OverviewPage
                .openPage()
                .getFinanceAmount()
                .shouldBe(Condition.matchesText("^(([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$"));
    }

    @Тогда("при наведении курсора отображается всплывающая информация о финансах")
    public void приНаведенииКурсораОтображаетсяВсплывающаяИнформацияОФинансах() {
        OverviewPage
                .openPage()
                .getFinanceInfo()
                .shouldBe(Condition.matchesText("^Моих средств (([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$"));
    }
}
