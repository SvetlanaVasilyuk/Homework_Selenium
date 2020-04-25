package pages.ru.auto;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class ModelSalesPage extends BasePage {
    public ModelSalesPage() {
        list = $$(By.xpath("//div[@class='ListingPopularMMM-module__item']//a"));
    }
}
