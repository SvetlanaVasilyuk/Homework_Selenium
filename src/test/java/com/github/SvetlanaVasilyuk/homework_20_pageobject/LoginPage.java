package com.github.SvetlanaVasilyuk.homework_20_pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver webDriver;
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(String username, String password) {
        WebElement usernameField = webDriver.findElement(usernameInput);
        usernameField.clear();
        usernameField.sendKeys(username);
        WebElement passwordField = webDriver.findElement(passwordInput);
        passwordField.clear();
        passwordField.sendKeys(password);
        webDriver.findElement(loginButton).click();
    }
}
