package tests;

import io.qameta.allure.Attachment;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ApiClient;

public class BaseTest {

    protected WebDriver driver;
    protected ApiClient apiClient;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

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
        apiClient = new ApiClient();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            attachScreenshot();
            driver.quit();
        }
    }

    // ============ Allure attachments ============

    @Attachment(value = "Скриншот", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String attachText(String name, String message) {
        return message;
    }
}
