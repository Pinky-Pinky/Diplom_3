package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Проверяем переход на вкладку 'Булки' и её активацию через className")
    public void bunsSectionTest() throws InterruptedException {
        ConstructorPage page = new ConstructorPage(driver);
        page.open();

        // Подождем чуть перед кликом, чтобы элементы точно прогрузились
        Thread.sleep(500);
        clickWhenReady(By.xpath("//span[text()='Булки']"));

        assertTrue("Булки не активны", page.isBunsActive());
    }

    @Test
    @Description("Проверяем переход на вкладку 'Соусы' и её активацию через className")
    public void saucesSectionTest() throws InterruptedException {
        ConstructorPage page = new ConstructorPage(driver);
        page.open();

        Thread.sleep(500);
        clickWhenReady(By.xpath("//span[text()='Соусы']"));

        assertTrue("Соусы не активны", page.isSaucesActive());
    }

    @Test
    @Description("Проверяем переход на вкладку 'Начинки' и её активацию через className")
    public void fillingsSectionTest() throws InterruptedException {
        ConstructorPage page = new ConstructorPage(driver);
        page.open();

        Thread.sleep(500);
        clickWhenReady(By.xpath("//span[text()='Начинки']"));

        assertTrue("Начинки не активны", page.isFillingsActive());
    }
}
