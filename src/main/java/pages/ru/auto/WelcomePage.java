package pages.ru.auto;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class WelcomePage extends BasePage {
    public WelcomePage() {
        list = $$(By.className("IndexMarks__item-name"));
    }
}
