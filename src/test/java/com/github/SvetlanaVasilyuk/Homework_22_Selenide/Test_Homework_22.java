package com.github.SvetlanaVasilyuk.Homework_22_Selenide;

import Homework_22_Selenide.LoginPage;
import Homework_22_Selenide.OverviewPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class Test_Homework_22 {

    @BeforeMethod
    public void initDriver() {
        Configuration.browser = "firefox";
        open("https://idemo.bspb.ru/");
    }

    @Test
    public void testCheckFinances() {
        LoginPage
                .openPage()
                .login("demo", "demo")
                .enterOTP("0000")
                .selectSection("Обзор");

        OverviewPage overviewPage = OverviewPage.openPage();

        overviewPage
                .getFinanceTitle()
                .shouldHave(Condition.exactText("Финансовая свобода"));

        overviewPage
                .getFinanceAmount()
                .shouldBe(Condition.matchesText("^(([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$"));

        overviewPage
                .getFinanceInfo()
                .shouldBe(Condition.matchesText("^Моих средств (([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$"));
    }
}
