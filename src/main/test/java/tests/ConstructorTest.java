package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.ConstructorPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход в раздел «Булки»")
    public void bunsSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();
        constructorPage.clickBunsSection();
        assertTrue("Ожидалось отображение раздела Булки", constructorPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход в раздел «Соусы»")
    public void saucesSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();
        constructorPage.clickSaucesSection();
        assertTrue("Ожидалось отображение раздела Соусы", constructorPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход в раздел «Начинки»")
    public void fillingsSectionTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openConstructorPage();
        constructorPage.clickFillingsSection();
        assertTrue("Ожидалось отображение раздела Начинки", constructorPage.isFillingsSectionActive());
    }
}
