package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage extends BasePage {

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By tabContainer = By.xpath("./ancestor::div[contains(@class, 'tab_tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу конструктора")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    private void clickTab(By tabLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(tabLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(tabLocator));
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("Не удалось кликнуть на элемент, пробуем через JavaScript: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    @Step("Переходим на вкладку 'Булки'")
    public void goToBuns() {
        clickTab(bunsTab);
    }

    @Step("Переходим на вкладку 'Соусы'")
    public void goToSauces() {
        clickTab(saucesTab);
    }

    @Step("Переходим на вкладку 'Начинки'")
    public void goToFillings() {
        clickTab(fillingsTab);
    }

    private boolean isTabActive(By tabLocator) {
        try {
            WebElement el = driver.findElement(tabLocator);
            WebElement parent = el.findElement(tabContainer);
            String cls = parent.getAttribute("class");
            return cls != null && cls.contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
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