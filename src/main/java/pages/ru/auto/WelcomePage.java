package pages.ru.auto;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WelcomePage extends BasePage {
    public WelcomePage() {
        this.list = $$(By.className("IndexMarks__item-name"));
        this.button = $(By.xpath("div[@class='IndexSelector__submit']//button"));
    }
}
