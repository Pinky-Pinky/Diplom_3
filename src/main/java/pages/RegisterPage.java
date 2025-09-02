package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath("//p[@class='input__error text_type_main-default']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу регистрации")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Регистрируем пользователя Name:{name} Email:{email}")
    public void register(String name, String email, String password) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Проверяем, что появилось сообщение об ошибке")
    public boolean isErrorVisible() {
        return driver.findElements(errorMessage).size() > 0;
    }
}