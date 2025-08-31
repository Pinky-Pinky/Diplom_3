package tests;

import org.junit.Test;
import org.openqa.selenium.By;

public class ConstructorTest extends BaseTest {

    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    @Test
    public void bunsSectionTest() {
        driver.get(BASE_URL);
        clickWhenReady(By.xpath("//span[text()='Булки']"));
        waitForVisible(By.xpath("//h2[text()='Булки']")); // проверка, что блок отобразился
    }

    @Test
    public void saucesSectionTest() {
        driver.get(BASE_URL);
        clickWhenReady(By.xpath("//span[text()='Соусы']"));
        waitForVisible(By.xpath("//h2[text()='Соусы']"));
    }

    @Test
    public void fillingsSectionTest() {
        driver.get(BASE_URL);
        clickWhenReady(By.xpath("//span[text()='Начинки']"));
        waitForVisible(By.xpath("//h2[text()='Начинки']"));
    }
}
