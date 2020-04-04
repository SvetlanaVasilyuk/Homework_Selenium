package com.github.SvetlanaVasilyuk.Homework_20_PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OTPPage {
    private final WebDriver webDriver;
    private By codeInput = By.id("otp-code");
    private By loginButton = By.id("login-otp-button");

    public OTPPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterOTP(String otp) {
        WebElement otpField = webDriver.findElement(codeInput);
        otpField.clear();
        otpField.sendKeys(otp);
        webDriver.findElement(loginButton).click();
    }
}
