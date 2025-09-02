package tests;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.RegisterPage;
import utils.ApiClient;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private ApiClient.CreatedUser createdUser;

    @Before
    public void beforeEach() {
        // Не создаём пользователя через API, регистрируем через UI
    }

    @After
    public void afterEach() throws Exception {
        if (createdUser != null && createdUser.accessToken != null) {
            apiClient.deleteUser(createdUser.accessToken);
        }
    }

    @Test
    @Description("Успешная регистрация нового пользователя")
    public void successfulRegistrationTest() throws Exception {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        String email = "ui_" + System.currentTimeMillis() + "@mail.test";
        String password = "Password1!" + System.currentTimeMillis();
        String name = "UiUser" + System.currentTimeMillis();

        registerPage.register(name, email, password);
        assertTrue("Регистрация не прошла", driver.getPageSource().contains("Войти") || driver.getCurrentUrl().contains("/account"));

        String accessToken = apiClient.loginAndGetAccessToken(email, password);
        createdUser = new ApiClient.CreatedUser();
        createdUser.email = email;
        createdUser.password = password;
        createdUser.name = name;
        createdUser.accessToken = accessToken;

        attachText("Новый пользователь", "Email: " + email + ", Name: " + name);
    }

    @Test
    @Description("Регистрация с коротким паролем (ожидается ошибка)")
    public void registrationWithShortPasswordTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        String email = "ui_bad_" + System.currentTimeMillis() + "@mail.test";
        String name = "BadUser" + System.currentTimeMillis();
        String shortPwd = "123";

        registerPage.register(name, email, shortPwd);
        assertTrue("Ожидалось сообщение об ошибке для короткого пароля", registerPage.isErrorVisible());
    }
}

