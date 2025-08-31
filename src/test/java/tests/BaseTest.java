package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome"); // chrome или yandex

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("yandex")) {
            String yandexBinary = "/Applications/Yandex.app/Contents/MacOS/Yandex";
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary(yandexBinary);
            driver = new ChromeDriver(options);
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            attachScreenshot();
            driver.quit();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // ==============================
    // УНИВЕРСАЛЬНЫЕ ХЕЛПЕРЫ
    // ==============================

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickWhenReady(By locator) {
        try {
            WebElement el = waitForClickable(locator);
            el.click();
        } catch (ElementClickInterceptedException e) {
            // если элемент перекрыт, жмём через JS
            WebElement el = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    protected void typeWhenVisible(By locator, String text) {
        WebElement el = waitForVisible(locator);
        el.clear();
        el.sendKeys(text);
    }
}
