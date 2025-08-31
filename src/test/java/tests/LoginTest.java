package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private final String VALID_EMAIL = "pinky-pinky@yandex.ru";
    private final String VALID_PASSWORD = "password";

    @Test
    @DisplayName("Логин с главной страницы")
    public void loginFromMainPageTest() {
        driver.get(BASE_URL);
        clickWhenReady(By.xpath("//button[text()='Войти в аккаунт']"));
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), VALID_EMAIL);
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), VALID_PASSWORD);
        clickWhenReady(By.xpath("//button[text()='Войти']"));
        waitForVisible(By.xpath("//*[text()='Оформить заказ']")); // успешный вход
    }

    @Test
    @DisplayName("Логин через форму регистрации")
    public void loginFromRegisterFormTest() {
        driver.get(BASE_URL + "register");
        clickWhenReady(By.xpath("//a[text()='Войти']"));
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), VALID_EMAIL);
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), VALID_PASSWORD);
        clickWhenReady(By.xpath("//button[text()='Войти']"));
        waitForVisible(By.xpath("//*[text()='Оформить заказ']"));
    }

    @Test
    @DisplayName("Логин через форму восстановления пароля")
    public void loginFromForgotPasswordFormTest() {
        driver.get(BASE_URL + "forgot-password");
        clickWhenReady(By.xpath("//a[text()='Войти']"));
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), VALID_EMAIL);
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), VALID_PASSWORD);
        clickWhenReady(By.xpath("//button[text()='Войти']"));
        waitForVisible(By.xpath("//*[text()='Оформить заказ']"));
    }
}
