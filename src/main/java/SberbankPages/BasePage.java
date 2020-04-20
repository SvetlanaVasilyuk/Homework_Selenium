package SberbankPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
    protected final ElementsCollection navBarItems;
    protected ElementsCollection subMenuItems;
    protected ElementsCollection tabItems;
    protected SelenideElement header;
    protected SelenideElement cookieWarning;

    public BasePage() {
        this.navBarItems = $$(By.xpath("//ul[@class='lg-menu__list']/li"));
        this.tabItems = $$(By.xpath("//ul[@class='tabs-container__nav-tabs']/li"));
        this.header = $(By.xpath("//div[@class='product-teaser-full-width__image product-teaser-full-width__image_md']//h2"));
        this.cookieWarning = $(By.xpath("//a[@class='cookie-warning__close']"));
    }

    // Следующие 3 метода похожи друг на друга, но не полностью, поэтому не стала пытаться слить их в один
    public BasePage hoverSection(String sectionName) {
        boolean sectionFound = false;
        for (SelenideElement item : navBarItems) {
            if (item.getText().equals(sectionName.toUpperCase())) {
                item.click();
                sectionFound = true;
                this.subMenuItems = item.$$(By.xpath(".//ul[@class='lg-menu__sub-list']/li/a"));
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
