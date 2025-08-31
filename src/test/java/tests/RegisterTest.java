package tests;

import org.junit.Test;
import org.openqa.selenium.By;

public class RegisterTest extends BaseTest {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @Test
    public void successfulRegistrationTest() {
        driver.get(BASE_URL + "register");
        typeWhenVisible(By.xpath("//label[text()='Имя']/following-sibling::input"), "Тест");
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), "test" + System.currentTimeMillis() + "@mail.ru");
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), "123456");
        clickWhenReady(By.xpath("//button[text()='Зарегистрироваться']"));
        waitForVisible(By.xpath("//button[text()='Войти']")); // попадаем на форму входа
    }

    @Test
    public void registrationWithShortPasswordTest() {
        driver.get(BASE_URL + "register");
        typeWhenVisible(By.xpath("//label[text()='Имя']/following-sibling::input"), "Тест");
        typeWhenVisible(By.xpath("//label[text()='Email']/following-sibling::input"), "bad" + System.currentTimeMillis() + "@mail.ru");
        typeWhenVisible(By.xpath("//label[text()='Пароль']/following-sibling::input"), "123");
        clickWhenReady(By.xpath("//button[text()='Зарегистрироваться']"));
        waitForVisible(By.xpath("//p[text()='Некорректный пароль']")); // проверка ошибки
    }
}
