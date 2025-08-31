package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @Description("Проверяем вход через кнопку 'Войти в аккаунт' на главной странице")
    public void loginFromMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("pinky-pinky@yandex.ru", "password");

        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    @Description("Проверяем вход через кнопку 'Личный кабинет'")
    public void loginFromProfileButtonTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openFromProfile();
        loginPage.login("pinky-pinky@yandex.ru", "password");

        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    @Description("Проверяем вход со страницы регистрации")
    public void loginFromRegisterFormTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openFromRegister();
        loginPage.login("pinky-pinky@yandex.ru", "password");

        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    @Description("Проверяем вход через форму восстановления пароля")
    public void loginFromForgotPasswordFormTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openFromForgotPassword();
        loginPage.login("pinky-pinky@yandex.ru", "password");

        assertTrue(driver.getCurrentUrl().contains("/"));
    }
}
