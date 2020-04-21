package cucumber.stepdefinitions.sberbank;

import com.codeborne.selenide.*;
import io.cucumber.java.ru.Допустим;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;

public class CommonStepDef {

    @Допустим("название страницы {string}")
    public void pageTitleIs(String string) {
        Assert.assertEquals(Selenide.title(), string);
    }

    @Допустим("открылось новое окно")
    public void newWindowIsOpened() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
    }
}
