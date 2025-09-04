package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage extends BasePage {

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By tabContainer = By.xpath("./ancestor::div[contains(@class,'tab_tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу конструктора")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Переходим на вкладку 'Булки'")
    public void goToBuns() {
        clickWhenReady(bunsTab);
    }

    @Step("Переходим на вкладку 'Соусы'")
    public void goToSauces() {
        clickWhenReady(saucesTab);
    }

    @Step("Переходим на вкладку 'Начинки'")
    public void goToFillings() {
        clickWhenReady(fillingsTab);
    }

    private boolean isTabActive(By tabLocator) {
        WebElement el = driver.findElement(tabLocator);
        WebElement parent = el.findElement(tabContainer);
        String cls = parent.getAttribute("class");
        return cls != null && cls.contains("tab_tab_type_current");
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
