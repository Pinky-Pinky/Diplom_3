package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage extends BasePage {

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    // локатор для проверки активного состояния вкладки
    private final By activeParent = By.xpath("./parent::div[contains(@class,'tab_tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу конструктора")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Переходим на вкладку 'Булки'")
    public void goToBuns() {
        driver.findElement(bunsTab).click();
    }

    @Step("Переходим на вкладку 'Соусы'")
    public void goToSauces() {
        driver.findElement(saucesTab).click();
    }

    @Step("Переходим на вкладку 'Начинки'")
    public void goToFillings() {
        driver.findElement(fillingsTab).click();
    }

    private boolean isTabActive(By tabLocator) {
        WebElement tab = driver.findElement(tabLocator);
        return !tab.findElements(activeParent).isEmpty();
    }

    @Step("Проверяем, что вкладка 'Булки' активна")
    public boolean isBunsActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверяем, что вкладка 'Соусы' активна")
    public boolean isSaucesActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверяем, что вкладка 'Начинки' активна")
    public boolean isFillingsActive() {
        return isTabActive(fillingsTab);
    }
}
