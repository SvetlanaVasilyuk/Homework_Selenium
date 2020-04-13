package Homework_22_Selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected final ElementsCollection navBarItems;

    public BasePage() {
        this.navBarItems = $$(By.xpath("//ul[@class='navigation-menu nav']/li"));
    }

    @Step("Переход в раздел меню {sectionName}")
    public void selectSection(String sectionName) {
        boolean sectionFound = false;
        for (SelenideElement item : navBarItems) {
            if (item.getText().equals(sectionName.toUpperCase())) {
                item.click();
                sectionFound = true;
                break;
            }
        }
        if (!sectionFound) {
            throw new IllegalArgumentException(String.format("Нет кнопки меню с текстом \"%s\"", sectionName));
        }
    }
}
