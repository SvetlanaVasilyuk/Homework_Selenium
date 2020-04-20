package SberbankPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ChoosingDepositTab extends DepositsPage {

    private ElementsCollection checkboxes = $$(By.xpath("//div[@id='depositSelection']//div[contains(@class, 'settings-menu__block-checkboxes')]//input"));
    private ElementsCollection checkboxesNames = $$(By.xpath("//div[contains(@class, 'settings-menu__block-checkboxes')]//input"));
    private ElementsCollection deposits = $$(By.xpath("//div[@class='offered-products__item']"));
    private ElementsCollection depositNames = $$(By.xpath("//h3[@data-test-id='DepositSelection_ProductCard-title']"));
    //private SelenideElement frame = $(By.id("iFrameResizer0"));


    public static ChoosingDepositTab openPage(){
        return new ChoosingDepositTab();
    }

//    public ElementsCollection getCheckboxes(){
//        return checkboxes;
//    }

    public HashMap<String, SelenideElement> getCheckboxes(){
        HashMap<String, SelenideElement> checkboxesMap = new HashMap<>();
        for (SelenideElement ch : checkboxes){
            String label = ch.$(By.xpath("./following::div")).getText();
            checkboxesMap.put(label, ch);
        }
        return checkboxesMap;

    }

    public ElementsCollection getDeposits(){
        deposits.exclude(Condition.hidden);
        return deposits;
    }

    public ElementsCollection getDepositsNames(){
      //  getDepositsNames().exclude(Condition.disabled);
        return depositNames;
    }

    public SelenideElement detailButton(SelenideElement deposit){
        return deposit.
                find(By.xpath(".//a[.='Подробнее']"));
    }

    public SelenideElement getFrame(){
        return $(By.id("iFrameResizer0"));
    }

    public void clickCheckbox(String checkboxName){
        $(By.xpath("//div[@id='depositSelection']//div[contains(@class, 'settings-menu__block-checkboxes')]//div[text()='" + checkboxName + "']")).click();
    }
}