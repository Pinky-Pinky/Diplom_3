package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConstructorPage extends BasePage {

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

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
        WebElement el = driver.findElement(bunsTab);
        WebElement parent = el.findElement(By.xpath("./ancestor::div[1]"));
        String cls = parent.getAttribute("class");
        return cls != null && cls.contains("tab_tab_type_current");
    }

    @Step("Проверяем, что вкладка 'Соусы' активна")
    public boolean isSaucesActive() {
        WebElement el = driver.findElement(saucesTab);
        WebElement parent = el.findElement(By.xpath("./ancestor::div[1]"));
        String cls = parent.getAttribute("class");
        return cls != null && cls.contains("tab_tab_type_current");
    }

    @Step("Проверяем, что вкладка 'Начинки' активна")
    public boolean isFillingsActive() {
        WebElement el = driver.findElement(fillingsTab);
        WebElement parent = el.findElement(By.xpath("./ancestor::div[1]"));
        String cls = parent.getAttribute("class");
        return cls != null && cls.contains("tab_tab_type_current");
    }
}