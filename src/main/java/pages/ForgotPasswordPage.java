package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By emailInput = By.name("email");
    private final By resetButton = By.xpath("//button[text()='Восстановить']");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу восстановления пароля")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Вводим email и отправляем форму восстановления")
    public void resetPassword(String email) {
        typeWhenVisible(emailInput, email);
        clickWhenReady(resetButton);
    }

    @Step("Переходим на страницу логина")
    public void goToLogin() {
        clickWhenReady(loginLink);
    }
}