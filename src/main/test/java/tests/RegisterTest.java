package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulRegistrationTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.register("newuser" + System.currentTimeMillis() + "@yandex.ru", "password123", "TestUser");

        assertTrue("Ожидался переход на главную страницу после успешной регистрации",
                registerPage.isMainPageDisplayed());
    }

    @Test
    @DisplayName("Ошибка при вводе некорректного пароля")
    public void errorOnInvalidPasswordTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.register("newuser" + System.currentTimeMillis() + "@yandex.ru", "123", "TestUser");

        assertTrue("Ожидалось сообщение об ошибке для короткого пароля",
                registerPage.isPasswordErrorDisplayed());
    }
}
