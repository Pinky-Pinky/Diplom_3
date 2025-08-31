package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage extends BasePage {

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void goToBuns() {
        driver.findElement(bunsTab).click();
    }

    public void goToSauces() {
        driver.findElement(saucesTab).click();
    }

    public void goToFillings() {
        driver.findElement(fillingsTab).click();
    }

    public boolean isBunsVisible() {
        return driver.findElement(bunsTab).isDisplayed();
    }

    public boolean isSaucesVisible() {
        return driver.findElement(saucesTab).isDisplayed();
    }

    public boolean isFillingsVisible() {
        return driver.findElement(fillingsTab).isDisplayed();
    }
}
