package AutoruPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected ElementsCollection list;
    protected SelenideElement button;
    protected SelenideElement modalCloser = $(By.xpath("//div[@class='PromoPopupHoliday__buttons']//a[.='Понятно, спасибо']"));

//    public BasePage() {
//        this.list = $$(By.ByClassName("ListingPopularMMM-module__item"));
//    }

    public String getSalesCountInList(String item){
        return list.findBy(Condition.exactText(item)).sibling(0).getText();
    }

    public ElementsCollection getItemList(){
        return list;
    }

    public SelenideElement getButton(){
        return button;
    }

    public void closeModal() {
        modalCloser.click();
    }

    public boolean modalAppears() throws InterruptedException {
        //Thread.sleep(3000);
        return modalCloser.is(Condition.exist);
    }

}
