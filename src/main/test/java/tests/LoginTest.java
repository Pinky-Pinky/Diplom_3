package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.RegisterPage;
import pages.ForgotPasswordPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private final String EMAIL = "pinky-pinky@yandex.ru";
    private final String PASSWORD = "password";

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromMainPage(EMAIL, PASSWORD);
        assertTrue("Ожидался успешный вход через главную страницу", loginPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginFromProfilePageTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromProfile(EMAIL, PASSWORD);
        assertTrue("Ожидался успешный вход через личный кабинет", loginPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    public void loginFromRegisterPageTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);
        assertTrue("Ожидался успешный вход через форму регистрации", loginPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(EMAIL, PASSWORD);
        assertTrue("Ожидался успешный вход через форму восстановления пароля", loginPage.isMainPageDisplayed());
    }
}
