package com.github.SvetlanaVasilyuk.homework_16_19;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Homework_19_SiteTest4 extends SiteTest {

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
    public void testSiteDragAndDrop() {
        webDriver.get("https://savkk.github.io/selenium-practice/dnd/");

        WebElement blackSquare1 = webDriver.findElement(By.xpath("//div[@class=\"square black\"][position()=1]"));
        WebElement redSquare = webDriver.findElement(By.id("red_square"));
        WebElement blackSquare2 = webDriver.findElement(By.xpath("//div[@class=\"square black\"][position()=2]"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

        //Assert.assertEquals(webDriver.manage().getCookieNamed("dnd").getValue(), "done");
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }
}
