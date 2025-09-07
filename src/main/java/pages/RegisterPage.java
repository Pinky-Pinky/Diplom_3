package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage {

    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By errorMessage = By.xpath("//p[contains(@class, 'input__error')]");
    private final By userExistsError = By.xpath("//p[text()='Такой пользователь уже существует']");
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Заполнить форму регистрации и нажать 'Зарегистрироваться'")
    public void register(String name, String email, String password) {
        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("Имя, email или пароль не могут быть null");
        }
        System.out.println("Регистрация: name=" + name + ", email=" + email + ", password=" + password);
        typeWhenVisible(nameInput, name);
        typeWhenVisible(emailInput, email);
        typeWhenVisible(passwordInput, password);
        clickWhenReady(registerButton);
    }

    @Step("Проверка видимости ошибки с текстом: {expectedMessage}")
    public boolean isErrorVisible(String expectedMessage) {
        try {
            WebElement error = driver.findElement(errorMessage);
            String errorText = error.getText();
            System.out.println("Ошибка на странице: " + errorText);
            return error.isDisplayed() && errorText.contains(expectedMessage);
        } catch (Exception e) {
            System.out.println("Ошибка не найдена: " + e.getMessage());
            return false;
        }
    }

    @Step("Проверка видимости ошибки 'Такой пользователь уже существует'")
    public boolean isUserExistsErrorVisible() {
        try {
            WebElement error = driver.findElement(userExistsError);
            String errorText = error.getText();
            System.out.println("Ошибка на странице: " + errorText);
            return error.isDisplayed();
        } catch (Exception e) {
            System.out.println("Ошибка 'Такой пользователь уже существует' не найдена: " + e.getMessage());
            return false;
        }
    }

    @Step("Проверяем видимость кнопки 'Оформить заказ'")
    public boolean isOrderButtonVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
            boolean isVisible = driver.findElement(orderButton).isDisplayed();
            System.out.println("Кнопка 'Оформить заказ' видна: " + isVisible);
            return isVisible;
        } catch (Exception e) {
            System.out.println("Ошибка при проверке кнопки 'Оформить заказ': " + e.getMessage());
            return false;
        }
    }
}