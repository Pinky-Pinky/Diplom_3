package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By loginFromMainButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу")
    public void open(String url) {
        driver.get(url);
    }

    @Step("Логин с главной страницы")
    public void loginFromMain(String email, String password) {
        clickWhenReady(loginFromMainButton);
        login(email, password);
    }

    @Step("Логин через форму регистрации")
    public void loginFromRegister(String email, String password) {
        clickWhenReady(loginLink);
        login(email, password);
    }

    @Step("Логин через форму восстановления пароля")
    public void loginFromForgotPassword(String email, String password) {
        clickWhenReady(loginLink);
        login(email, password);
    }

    @Step("Выполнить логин")
    private void login(String email, String password) {
        typeWhenVisible(emailInput, email);
        typeWhenVisible(passwordInput, password);
        clickWhenReady(loginButton);
        waitForVisible(orderButton);
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        try {
            waitForVisible(orderButton);
            return driver.findElement(orderButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}