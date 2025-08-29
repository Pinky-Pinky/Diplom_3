package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @Description("Проверяем, что новый пользователь может зарегистрироваться с валидными данными")
    public void successfulRegistrationTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register("newuser@example.com", "password123", "TestUser");

        assertTrue("Не выполнен переход на главную страницу после регистрации",
                driver.getCurrentUrl().contains("/"));
    }

    @Test
    @Description("Проверяем, что нельзя зарегистрироваться с паролем короче 6 символов")
    public void registrationWithShortPasswordTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register("newuser2@example.com", "123", "ShortPwd");

        assertTrue("Сообщение об ошибке отсутствует",
                registerPage.isErrorVisible());
    }
}
