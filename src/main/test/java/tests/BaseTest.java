package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {

    @Rule
    public AllureJunit4 allure = new AllureJunit4();

    protected WebDriver driver;

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
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
}
