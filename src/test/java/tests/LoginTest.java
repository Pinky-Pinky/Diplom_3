package tests;

import io.qameta.allure.Description;
import model.CreatedUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import utils.ApiClient;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private ApiClient apiClient;
    private CreatedUser testUser;

    @Before
    public void setUp() {
        super.setUp();
        apiClient = new ApiClient();
        try {
            testUser = apiClient.createRandomUser();
            System.out.println("Создан пользователь: email=" + testUser.getEmail() + ", name=" + testUser.getName() + ", password=" + testUser.getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании тестового пользователя: " + e.getMessage(), e);
        }
    }

    @After
    public void tearDown() {
        try {
            if (testUser != null && testUser.getAccessToken() != null) {
                apiClient.deleteUser(testUser.getAccessToken());
            }
        } catch (Exception e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        } finally {
            super.tearDown();
        }
    }

    @Test
    @Description("Логин с главной страницы и проверка успешного входа")
    public void loginFromMainPageTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL);
        loginPage.loginFromMain(testUser.getEmail(), testUser.getPassword());
        assertTrue("Кнопка 'Оформить заказ' не видна после логина", loginPage.isOrderButtonVisible());
    }

    @Test
    @Description("Логин через форму регистрации")
    public void loginFromRegisterFormTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL + "register");
        loginPage.loginFromRegister(testUser.getEmail(), testUser.getPassword());
        assertTrue("Кнопка 'Оформить заказ' не видна после логина", loginPage.isOrderButtonVisible());
    }

    @Test
    @Description("Логин через форму восстановления пароля")
    public void loginFromForgotPasswordFormTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL + "forgot-password");
        loginPage.loginFromForgotPassword(testUser.getEmail(), testUser.getPassword());
        assertTrue("Кнопка 'Оформить заказ' не видна после логина", loginPage.isOrderButtonVisible());
    }
}