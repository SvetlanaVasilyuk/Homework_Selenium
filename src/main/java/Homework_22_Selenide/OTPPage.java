package Homework_22_Selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class OTPPage {

    private SelenideElement codeInput = $(byId("otp-code"));
    private SelenideElement loginButton = $(byId("login-otp-button"));

    public static OTPPage openPage() {
        return new OTPPage();
    }

    @Step("Ввод одноразового пароля")
    public WelcomePage enterOTP(String otp) {
        codeInput.clear();
        codeInput.setValue(otp);
        loginButton.click();
        return WelcomePage.openPage();
    }
}
