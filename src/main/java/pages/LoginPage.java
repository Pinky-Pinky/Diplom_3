package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    public void openLoginPageFromProfile() {
        driver.get("https://stellarburgers.nomoreparties.site/account");
    }

    public void openLoginPageFromRegister() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    public void openLoginPageFromForgotPassword() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
