package SberbankPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DepositDetailsPage extends DepositsPage {
    private String header = $(By.xpath("//div[@class='product-teaser-full-width__image product-teaser-full-width__image_md']")).getText();

    public static DepositDetailsPage openPage() {
        return new DepositDetailsPage();
    }

    public SelenideElement getHeader(){
        SelenideElement h = $(By.xpath("//div[@class='product-teaser-full-width__image product-teaser-full-width__image_md']//h2[.='Вклад Управляй']"));
        String str = h.getText();
        String str2 = h.getValue();
        String str3 = h.text();
        String str4 = h.innerText();
        return h;
    }
}
