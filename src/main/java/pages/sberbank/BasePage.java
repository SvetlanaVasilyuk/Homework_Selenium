package pages.sberbank;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected final ElementsCollection navBarItems = $$(By.xpath("//ul[@class='lg-menu__list']/li"));
    protected ElementsCollection subMenuItems;
    protected ElementsCollection tabItems = $$(By.xpath("//ul[@class='tabs-container__nav-tabs']/li"));
    protected SelenideElement header = $$(By.xpath("//h2[@class='kit-heading kit-heading_l product-teaser-full-width__header']")).findBy(Condition.visible);
    protected SelenideElement cookieWarning = $(By.xpath("//a[@class='cookie-warning__close']"));

    public BasePage hoverSection(String sectionName){
        boolean sectionFound = false;
        for (SelenideElement item : navBarItems) {
            if (item.getText().equals(sectionName.toUpperCase())) {
                item.click();
                sectionFound = true;
                subMenuItems = item.$$(By.xpath(".//ul[@class='lg-menu__sub-list']/li/a"));
                break;
            }
        }
        if (!sectionFound) {
            throw new IllegalArgumentException(String.format("Нет кнопки меню с текстом \"%s\"", sectionName));
        }
        return this;
    }

    public void selectSubSection(String subSectionName) {
        boolean sectionFound = false;
        for (SelenideElement item : subMenuItems) {
            if (item.getText().equals(subSectionName)) {
                item.hover();
                item.click();
                sectionFound = true;
                break;
            }
        }
        if (!sectionFound) {
            throw new IllegalArgumentException(String.format("Нет подраздела с текстом \"%s\"", subSectionName));
        }
    }

    public void selectTab(String sectionName) {
        boolean sectionFound = false;
        for (SelenideElement item : tabItems) {
            if (item.getText().equals(sectionName)) {
                new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(item));
                item.click();
                sectionFound = true;
                break;
            }
        }
        if (!sectionFound) {
            throw new IllegalArgumentException(String.format("Нет вкладки с текстом \"%s\"", sectionName));
        }
    }

    public void closeCookieWarning() {
        cookieWarning.click();
    }

    public boolean cookieWarningAppears() {
        return cookieWarning.is(Condition.exist);
    }

    public SelenideElement getHeader() {
        return header;
    }
}
