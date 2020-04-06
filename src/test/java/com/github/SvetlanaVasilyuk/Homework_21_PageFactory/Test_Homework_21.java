package com.github.SvetlanaVasilyuk.Homework_21_PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Test_Homework_21 {
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void testSetup() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://idemo.bspb.ru/");
    }

    @Test
    public void testCheckFinances() {
        LoginPage
                .open(webDriver)
                .login("demo", "demo")
                .enterOTP("0000")
                .selectSection("Обзор");
        OverviewPage overviewPage = OverviewPage.open(webDriver);
        String financesTitle = overviewPage.getFinanceBlockTitle();
        Assert.assertEquals(financesTitle, "Финансовая свобода");
        String financesAmount = overviewPage.getFinances();
        Pattern pattern = Pattern.compile("^(([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$");
        Assert.assertTrue(pattern.matcher(financesAmount).matches());
        String myFinances = overviewPage.getFinancesInfo();
        pattern = Pattern.compile("^Моих средств (([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$");
        Assert.assertTrue(pattern.matcher(myFinances).matches());
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }
}
