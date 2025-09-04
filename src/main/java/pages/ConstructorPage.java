package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage extends BasePage {

    // Локаторы вкладок
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    // Локатор для поиска родительского контейнера вкладки
    private final By tabParent = By.xpath("./ancestor::div[1]");

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

    // 🔑 Вынесла повторяющуюся логику в приватный метод
    private boolean isTabActive(By tabLocator) {
        WebElement el = driver.findElement(tabLocator);
        WebElement parent = el.findElement(tabParent);
        String cls = parent.getAttribute("class");
        return cls != null && cls.contains("tab_tab_type_current");
    }
}
