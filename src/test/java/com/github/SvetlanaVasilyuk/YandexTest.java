package com.github.SvetlanaVasilyuk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class YandexTest {

    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test
    public void testYandex() throws InterruptedException {
        webDriver.get("https://yandex.ru");
        WebElement input = webDriver.findElement(By.id("text"));
        input.sendKeys("руддщ цщкдв");

        // Поиск через xpath
        WebElement button = webDriver.findElement(By.xpath("//child::div[@class=\"search2__button\"]"));

        // Поиск элемента через родительский по классу, затем по вложенному в него тегу
        //WebElement buttonDiv = webDriver.findElement(By.className("search2__button"));
        //WebElement button = buttonDiv.findElement(By.tagName("button"));

        button.click();
        WebElement newInput = webDriver.findElement(By.name("text"));
        Assert.assertEquals(newInput.getAttribute("value"), "hello world");
        String tab = webDriver.getTitle();
        Assert.assertTrue(tab.contains("hello world"));
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }

}
