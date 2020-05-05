package com.github.SvetlanaVasilyuk.homework_16_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework_18_SiteTest3 extends SiteTest {

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testSitePositive() {
        webDriver.get("https://savkk.github.io/selenium-practice/alerts/");

        clickButtonByClass("get");
        Alert alert = webDriver.switchTo().alert();
        String password = alert.getText().substring(15);
        alert.accept();
        webDriver.switchTo().defaultContent();
        clickButtonByClass("set");
        Alert prompt = webDriver.switchTo().alert();
        prompt.sendKeys(password);
        prompt.accept();
        webDriver.switchTo().defaultContent();
        Assert.assertTrue(webDriver.findElement(By.xpath("//label[.=\"Great!\"]")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.className("return")).isDisplayed());
        clickButtonByClass("return");
        Alert confirm = webDriver.switchTo().alert();
        confirm.accept();
        webDriver.switchTo().defaultContent();

        Assert.assertEquals(webDriver.manage().getCookieNamed("alerts").getValue(), "done");
    }

    @Test
    public void testSiteNegative() {
        webDriver.get("https://savkk.github.io/selenium-practice/alerts/");

        String password = "falsePassword";
        clickButtonByClass("set");
        Alert prompt = webDriver.switchTo().alert();
        prompt.sendKeys(password);
        prompt.accept();
        webDriver.switchTo().defaultContent();

        // Метод elementExists проверяет наличие элемента без ожидания в 5 секунд
        // Добавила </button> в начало первой проверки и </div> конце второй проверки, чтобы тест прошел,
        // так как в исходном коде эти элементы есть в блоке <script>
        Assert.assertFalse(elementExists("</button><label>Great!</label>"));
        Assert.assertFalse(elementExists("<button class='return'>Return to menu</button></div>"));

        // Вместо предыдущих двух строк можно использовать второй метод для проверки отсутствия элементов на странице,
        // но этот метод будет искать элементы по 5 секунд из-за установленного implicitlyWait
        //Assert.assertFalse(elementExists2("//label[.=\"Great!\"]"));
        //Assert.assertFalse(elementExists2("//button[@class=\"return\"]"));

        //Дополнительные проверки: что мы остались на той же странице и что кнопки остались доступными
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://savkk.github.io/selenium-practice/alerts/");
        Assert.assertTrue(webDriver.findElement(By.className("get")).isEnabled());
        Assert.assertTrue(webDriver.findElement(By.className("set")).isEnabled());
    }

    @Test
    public void testSiteTable() {
        webDriver.get("https://savkk.github.io/selenium-practice/table/");

        addRow("Magazzini Alimentari Riuniti", "Giovanni Rovelli", "Italy");

        List<WebElement> rowsToDelete = new ArrayList<>();
        rowsToDelete.addAll(findRows("Island Trading", "Helen Bennett", "UK"));
        rowsToDelete.addAll(findRows("Magazzini Alimentari Riuniti", "Giovanni Rovelli", "Italy"));
        deleteRows(rowsToDelete);
        List<WebElement> rowsAfterDelete = new ArrayList<>();
        rowsAfterDelete.addAll(findRows("Island Trading", "Helen Bennett", "UK"));
        rowsAfterDelete.addAll(findRows("Magazzini Alimentari Riuniti", "Giovanni Rovelli", "Italy"));
        Assert.assertEquals(rowsAfterDelete.size(), 0);

        WebElement link = webDriver.findElement(By.linkText("Great! Return to menu"));

        addRow("Fabulous machines", "Anthony MacCorner", "USA");

        link.click();

        Assert.assertEquals(webDriver.manage().getCookieNamed("table").getValue(), "done");

    }

    public boolean elementExists(String element) {
        return webDriver.getPageSource().contains(element);
    }

    public boolean elementExists2(String element) {
        try {
            webDriver.findElement(By.xpath(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Возвращаем список элементов, а не один элемент, на случай, если есть дубликаты
    public List<WebElement> findRows(String companyName, String contact, String country) {
        List<WebElement> rowsList = webDriver.findElements(By.xpath("//tr[td[position()=2 and text()=\"" + companyName + "\"] and td[position()=3 and text()=\"" + contact + "\"] and td[position()=4 and text()=\"" + country + "\"]]"));
        return rowsList;
    }

    public void deleteRows(List<WebElement> rowsList) {
        for (WebElement i : rowsList) {
            i.findElement(By.xpath("./td[position()=1]/input")).click();
        }
        webDriver.findElement(By.xpath("//input[@value=\"Delete\"]")).click();
    }

    public void addRow(String company, String contact, String country) {
        int sizeBeforeAddition = findRows(company, contact, country).size();
        fillTextInput("Company", company);
        fillTextInput("Contact", contact);
        fillTextInput("Country", country);
        webDriver.findElement(By.xpath("//input[@value=\"Add\"]")).click();
        int sizeAfterAddition = findRows(company, contact, country).size();
        Assert.assertEquals(sizeAfterAddition - sizeBeforeAddition, 1);
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }
}
