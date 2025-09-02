package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим email для восстановления: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Нажимаем 'Восстановить'")
    public void clickRestoreButton() {
        driver.findElement(restoreButton).click();
    }
}