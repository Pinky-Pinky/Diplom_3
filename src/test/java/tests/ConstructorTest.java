package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Проверяем переход на вкладку 'Булки' и её активацию")
    public void bunsSectionTest() {
        ConstructorPage page = new ConstructorPage(driver);
        page.open();
        page.goToBuns();
        assertTrue("Вкладка 'Булки' не активна", page.isBunsActive());
    }

    @Test
    @Description("Проверяем переход на вкладку 'Соусы' и её активацию")
    public void saucesSectionTest() {
        ConstructorPage page = new ConstructorPage(driver);
        page.open();
        page.goToSauces();
        assertTrue("Вкладка 'Соусы' не активна", page.isSaucesActive());
    }

    @Test
    @Description("Проверяем переход на вкладку 'Начинки' и её активацию")
    public void fillingsSectionTest() {
        ConstructorPage page = new ConstructorPage(driver);
        page.open();
        page.goToFillings();
        assertTrue("Вкладка 'Начинки' не активна", page.isFillingsActive());
    }
}
