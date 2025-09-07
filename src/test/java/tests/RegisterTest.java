package tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import model.CreatedUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.RegisterPage;
import utils.ApiClient;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private ApiClient apiClient;
    private CreatedUser testUser;

    @Before
    public void setUp() {
        super.setUp();
        apiClient = new ApiClient();
        try {
            testUser = apiClient.createRandomUser();
            attachText("Созданный тестовый пользователь",
                    "Email: " + testUser.getEmail() +
                            "\nName: " + testUser.getName() +
                            "\nPassword: " + testUser.getPassword());
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать тестового пользователя: " + e.getMessage(), e);
        }
    }

    @After
    public void tearDown() {
        try {
            if (testUser != null && testUser.getAccessToken() != null) {
                apiClient.deleteUser(testUser.getAccessToken());
                attachText("Удаление пользователя", "Пользователь с email " + testUser.getEmail() + " удалён");
            }
        } catch (Exception e) {
            attachText("Ошибка при удалении пользователя", "Ошибка: " + e.getMessage());
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
        } finally {
            super.tearDown();
        }
    }

    @Test
    @Description("Регистрация с валидными данными")
    public void registrationWithValidDataTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        // Проверяем, что отображается ошибка, так как пользователь уже создан через API
        boolean errorVisible = registerPage.isUserExistsErrorVisible();
        attachText("Статус проверки ошибки 'пользователь уже существует'",
                "Ошибка отображается: " + errorVisible);
        assertTrue("Ошибка 'Такой пользователь уже существует' не отображается", errorVisible);
    }

    @Test
    @Description("Регистрация с коротким паролем (невалидный сценарий)")
    public void registrationWithShortPasswordTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        String shortPassword = "123";
        registerPage.register(testUser.getName(), testUser.getEmail(), shortPassword);

        boolean passwordErrorVisible = registerPage.isErrorVisible("Некорректный пароль");
        attachText("Статус проверки ошибки короткого пароля",
                "Ошибка 'Некорректный пароль' отображается: " + passwordErrorVisible);
        assertTrue("Ошибка 'Некорректный пароль' не отображается", passwordErrorVisible);
    }

    @Attachment(value = "Текстовый аттачмент", type = "text/plain")
    public static String attachText(String title, String content) {
        return content;
    }
}