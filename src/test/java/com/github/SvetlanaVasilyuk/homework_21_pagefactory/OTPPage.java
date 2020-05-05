package com.github.SvetlanaVasilyuk.homework_21_pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OTPPage {
    private final WebDriver webDriver;

    @FindBy(id = "otp-code")
    private WebElement codeInput;

    @FindBy(id = "login-otp-button")
    private WebElement loginButton;

    public OTPPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public static OTPPage open(WebDriver webDriver) {
        return new OTPPage(webDriver);
    }

    public WelcomePage enterOTP(String otp) {
        codeInput.clear();
        codeInput.sendKeys(otp);
        loginButton.click();
        return WelcomePage.open(webDriver);
    }
}
