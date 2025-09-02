package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private final String VALID_EMAIL = "pinky-pinky@yandex.ru";
    private final String VALID_PASSWORD = "password";

    @Test
    @Description("Логин с главной страницы и проверка успешного входа")
    public void loginFromMainPageTest() {
        driver.get(BASE_URL);
        clickWhenReady(By.xpath("//button[text()='Войти в аккаунт']"));
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), VALID_EMAIL);
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), VALID_PASSWORD);
        clickWhenReady(By.xpath("//button[text()='Войти']"));
        waitForVisible(By.xpath("//*[text()='Оформить заказ']"));
        attachText("Текущий URL после логина", driver.getCurrentUrl());
    }

    @Test
    @Description("Логин через форму регистрации")
    public void loginFromRegisterFormTest() {
        driver.get(BASE_URL + "register");
        clickWhenReady(By.xpath("//a[text()='Войти']"));
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), VALID_EMAIL);
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), VALID_PASSWORD);
        clickWhenReady(By.xpath("//button[text()='Войти']"));
        waitForVisible(By.xpath("//*[text()='Оформить заказ']"));
        attachText("Текущий URL после логина", driver.getCurrentUrl());
    }

    @Test
    @Description("Логин через форму восстановления пароля")
    public void loginFromForgotPasswordFormTest() {
        driver.get(BASE_URL + "forgot-password");
        clickWhenReady(By.xpath("//a[text()='Войти']"));
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), VALID_EMAIL);
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), VALID_PASSWORD);
        clickWhenReady(By.xpath("//button[text()='Войти']"));
        waitForVisible(By.xpath("//*[text()='Оформить заказ']"));
        attachText("Текущий URL после логина", driver.getCurrentUrl());
    }
}
