package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу логина")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step("Выполняем вход: {email}/***")
    public void login(String email, String password) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Открываем страницу регистрации (через ссылку)")
    public void openFromRegister() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Открываем страницу восстановления пароля")
    public void openFromForgotPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
}
