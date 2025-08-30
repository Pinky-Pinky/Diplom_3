package tests;

import io.qameta.allure.Description;
import org.junit.Test;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @Description("Проверяем переход к разделу 'Булки'")
    public void bunsSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();
        constructorPage.goToBuns();

        assertTrue(constructorPage.isBunsVisible());
    }

    @Test
    @Description("Проверяем переход к разделу 'Соусы'")
    public void saucesSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();
        constructorPage.goToSauces();

        assertTrue(constructorPage.isSaucesVisible());
    }

    @Test
    @Description("Проверяем переход к разделу 'Начинки'")
    public void fillingsSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();
        constructorPage.goToFillings();

        assertTrue(constructorPage.isFillingsVisible());
    }
}
