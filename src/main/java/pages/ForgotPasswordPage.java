package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By restoreButton = By.xpath("//button[text()='Восстановить']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickRestoreButton() {
        driver.findElement(restoreButton).click();
    }
}
