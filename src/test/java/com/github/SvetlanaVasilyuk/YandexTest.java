package com.github.SvetlanaVasilyuk;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class YandexTest {

    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void testSite(){
        webDriver.get("https://savkk.github.io/selenium-practice/button/");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        webDriver.findElement(By.id("first")).click();
        List<WebElement> text1 = webDriver.findElements(By.id("result"));
        Assert.assertEquals(text1.get(1).getText(), "Excellent!");
        WebElement buttonClickMeToo = webDriver.findElement(By.className("button-primary"));
        Assert.assertTrue(buttonClickMeToo.isDisplayed());

        buttonClickMeToo.click();
        WebElement link1 = webDriver.findElement(By.linkText("Great! Return to menu"));
        Assert.assertTrue(link1.isDisplayed());
        link1.click();

        webDriver.findElement(By.id("checkbox")).click();
        WebElement checkbox1 = webDriver.findElement(By.id("one"));
        checkbox1.click();
        WebElement checkbox2 = webDriver.findElement(By.id("three"));
        checkbox2.click();
        webDriver.findElement(By.id("go")).click();
        WebElement checkboxResult = webDriver.findElement(By.id("result"));
        String expResult = checkbox1.getAttribute("value") + " " + checkbox2.getAttribute("value");
        Assert.assertEquals(checkboxResult.getText(), expResult);

        WebElement radio = webDriver.findElement(By.id("radio_two"));
        radio.click();
        webDriver.findElement(By.id("radio_go")).click();
        WebElement radioResult = webDriver.findElement(By.id("radio_result"));
        Assert.assertEquals(radioResult.getText(), radio.getAttribute("value"));
        WebElement link2 = webDriver.findElement(By.linkText("Great! Return to menu"));
        Assert.assertTrue(link2.isDisplayed());
        link2.click();
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }

}
