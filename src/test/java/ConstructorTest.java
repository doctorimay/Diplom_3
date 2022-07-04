
import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.Test;
import pageObjects.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        Configuration.browser = "Chrome";
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
    }

    @Test // работает переход на раздел булки
    public void bunTransitionTest() {
        mainPage.clickSouceBtn();
        mainPage.clickBunBtn();
        assertTrue(mainPage.titleCategoryBunIsDisplayed());
    }

    @Test // Работает переход на соусы
    public void sauceTransitionTest() {
        mainPage.clickSouceBtn();
        assertTrue(mainPage.titleCategorySauceIsDisplayed());
    }

    @Test // Работает переход на начинки
    public void fillingTransitionTest() {
        mainPage.clickFillingBtn();
        assertTrue(mainPage.titleCategoryFillingIsDisplayed());
    }

}