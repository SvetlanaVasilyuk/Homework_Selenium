package Homework_22_Selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement usernameInput = $(byName("username"));
    private SelenideElement passwordInput = $(byName("password"));
    private SelenideElement loginButton = $(byId("login-button"));

    public static LoginPage openPage() {
        return new LoginPage();
    }

    @Step("Авторизация под пользователем {username}")
    public OTPPage login(String username, String password) {
        usernameInput.clear();
        usernameInput.setValue(username);
        passwordInput.clear();
        passwordInput.setValue(password);
        loginButton.click();
        return OTPPage.openPage();
    }
}
