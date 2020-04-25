package pages.ru.auto;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected ElementsCollection list;
    protected ElementsCollection buttons = $$(By.tagName("button"));
    protected SelenideElement modalCloser = $(By.xpath("//div[@class='PromoPopupHoliday__buttons']//a[.='Понятно, спасибо']"));

    public ElementsCollection getItemList() {
        return list;
    }

    public SelenideElement getListItemCount(String itemName) {
        SelenideElement listItemCount =
                list.find(Condition.exactText(itemName))
                        .sibling(0);
        return listItemCount;
    }

    public ElementsCollection getButtons() {
        return buttons;
    }

    public void closeModal() {
        modalCloser.click();
    }

    public boolean modalAppears() {
        return modalCloser.is(Condition.exist);
    }

}