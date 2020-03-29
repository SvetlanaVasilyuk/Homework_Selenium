package com.github.SvetlanaVasilyuk;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework_17_SiteTest2 {
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
    public void testSite() {
        webDriver.get("https://savkk.github.io/selenium-practice/select/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        selectValues("hero", "Alan Mathison Turing");
        selectValues("languages", "Java", "C++", "Golang");
        clickButton("go");
        Assert.assertEquals("Alan Mathison Turing", getSelectResult("hero"));
        Assert.assertEquals("Java, C++, Golang", getSelectResult("languages"));
        checkLink("Great! Return to menu");
        clickLink("Great! Return to menu");

        clickLink("Form");
        fillTextInput("First Name:", "John");
        fillTextInput("Last Name:", "Kassinton");
        fillTextInput("Email:", "John.Kassinton@google.com");
        fillRadio("Sex:", "Male");
        fillTextInput("Address:", "Great Britain, London, Abbey str. 119-8");
        fillTextArea("Tell me something about yourself", "I like dogs and football");
        String projectDirectory = System.getProperty("user.dir");
        uploadFile("Avatar:", projectDirectory + "/src/test/resources/images/picture1.png");
        clickSubmitButton();
        checkLink("Great! Return to menu");
        clickLink("Great! Return to menu");

        clickLink("IFrame");
        switchToFrame("code-frame");
        String code = getCode();
        webDriver.switchTo().defaultContent();
        fillTextInput("Enter code:", code);
        clickInputButton("Verify");
        checkLink("Great! Return to menu");
        clickLink("Great! Return to menu");
    }

    public void selectValues(String selectName, String... values) {
        WebElement select = webDriver.findElement(By.name(selectName));
        Select selectField = new Select(select);
        for (String i : values) {
            selectField.selectByVisibleText(i);
        }
    }

    public void clickButton(String id) {
        WebElement button = webDriver.findElement(By.id(id));
        button.click();
    }

    public String getSelectResult(String selectName) {
        return webDriver.findElement(By.xpath("//select[@name=\"" + selectName + "\"]/following::label[@name=\"result\"]")).getText();
    }

    public void checkLink(String linkText) {
        WebElement link = webDriver.findElement(By.linkText(linkText));
        Assert.assertTrue(link.isDisplayed());
    }

    public void clickLink(String linkText) {
        WebElement link = webDriver.findElement(By.linkText(linkText));
        link.click();
    }

    public void fillTextInput(String fieldName, String value) {
        WebElement field = webDriver.findElement(By.xpath("//label[.=\"" + fieldName + "\"]/following::input[1]"));
        field.sendKeys(value);
    }

    public void fillTextArea(String fieldName, String value) {
        WebElement field = webDriver.findElement(By.xpath("//label[.=\"" + fieldName + "\"]/following::textarea[1]"));
        field.sendKeys(value);
    }

    public void fillRadio(String fieldName, String value) {
        WebElement field = webDriver.findElement(By.xpath("//label[.=\"" + fieldName + "\"]/following::node()[normalize-space(.)=\"" + value + "\"]/preceding-sibling::input"));
        field.click();
    }

    public void uploadFile(String fieldName, String filePath) {
        WebElement field = webDriver.findElement(By.xpath("//label[.=\"" + fieldName + "\"]/following::input[1]"));
        field.sendKeys(filePath);
    }

    public void clickSubmitButton() {
        WebElement button = webDriver.findElement(By.xpath("//input[@type=\"submit\"]"));
        button.click();
    }

    public void switchToFrame(String frameId) {
        WebElement iframe = webDriver.findElement(By.id(frameId));
        webDriver.switchTo().frame(iframe);
    }

    public String getCode() {
        String codeString = webDriver.findElement(By.xpath("//node()[contains(text(), \"Your code is:\")]")).getText();
        return codeString.substring(14);
    }

    public void clickInputButton(String buttonValue) {
        WebElement button = webDriver.findElement(By.xpath("//input[@type=\"button\" and @value=\"" + buttonValue + "\"]"));
        button.click();
    }

    @AfterMethod
    public void closeBrowser() {
        webDriver.quit();
    }
}
