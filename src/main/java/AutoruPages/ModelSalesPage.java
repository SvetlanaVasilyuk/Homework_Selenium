package AutoruPages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ModelSalesPage extends BasePage {
    public ModelSalesPage() {
        this.list = $$(By.xpath("//div[@class='ListingPopularMMM-module__item']//a"));
        this.button = $(By.xpath("//div[@class='ListingCarsFilters-module__actions']//button"));
    }
}
