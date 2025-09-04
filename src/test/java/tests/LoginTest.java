package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private final String VALID_EMAIL = "pinky-pinky@yandex.ru";
    private final String VALID_PASSWORD = "password";

    @Test
    @Description("Логин с главной страницы и проверка успешного входа")
    public void loginFromMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL);
        loginPage.loginFromMain(VALID_EMAIL, VALID_PASSWORD);

        // Проверка успешного входа
        boolean isOrderButtonVisible = driver.findElements(loginPage.getOrderButtonLocator()).size() > 0;
        assertTrue("Кнопка 'Оформить заказ' не видна после логина", isOrderButtonVisible);

        attachText("Текущий URL после логина", driver.getCurrentUrl());
    }

    @Test
    @Description("Логин через форму регистрации")
    public void loginFromRegisterFormTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL + "register");
        loginPage.loginFromRegister(VALID_EMAIL, VALID_PASSWORD);

        boolean isOrderButtonVisible = driver.findElements(loginPage.getOrderButtonLocator()).size() > 0;
        assertTrue("Кнопка 'Оформить заказ' не видна после логина", isOrderButtonVisible);

        attachText("Текущий URL после логина", driver.getCurrentUrl());
    }

    @Test
    @Description("Логин через форму восстановления пароля")
    public void loginFromForgotPasswordFormTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL + "forgot-password");
        loginPage.loginFromForgotPassword(VALID_EMAIL, VALID_PASSWORD);

        boolean isOrderButtonVisible = driver.findElements(loginPage.getOrderButtonLocator()).size() > 0;
        assertTrue("Кнопка 'Оформить заказ' не видна после логина", isOrderButtonVisible);

        attachText("Текущий URL после логина", driver.getCurrentUrl());
    }
}
