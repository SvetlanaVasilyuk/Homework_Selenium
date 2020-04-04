package com.github.SvetlanaVasilyuk.Homework_20_PageObject;

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

public class Test_Homework_20 {
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
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("demo", "demo");
        OTPPage otpPage = new OTPPage(webDriver);
        otpPage.enterOTP("0000");
        WelcomePage welcomePage = new WelcomePage(webDriver);
        welcomePage.getMenuBlock().selectSection("Обзор");
        OverviewPage overviewPage = new OverviewPage(webDriver);
        String finances = overviewPage.getFinances();
        Pattern pattern = Pattern.compile("^(([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$");
        Assert.assertTrue(pattern.matcher(finances).matches());
        String myFinances = overviewPage.getFinancesInfo();
        pattern = Pattern.compile("^Моих средств (([1-9](\\d{0,2})( \\d{3}){0,2})|0)\\.\\d{2} ₽$");
        System.out.println(myFinances);
        Assert.assertTrue(pattern.matcher(myFinances).matches());
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }
}
