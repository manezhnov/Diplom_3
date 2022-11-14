import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import static com.codeborne.selenide.Selenide.*;

public class BurgerConstructorTest {

    MainPage mainPage = page(MainPage.class);

    @Before
    public void setUp()  {
        open(mainPage.Url);
    }

    @Test
    @DisplayName("Открыть раздел 'Начинки' по клику на название раздела")
    public void openIngredientsSectionFromSectionNameLink() {
        mainPage.openIngredientSection();
        Assert.assertTrue(mainPage.isIngredientsSectionOpen());
    }

    @Test
    @DisplayName("Открыть раздел 'Начинки' по скроллу к элементу")
    public void openIngredientsSectionByScrollToIngredientImage() {
        mainPage.scrollToExoPlantagonSalad();
        Assert.assertTrue(mainPage.isIngredientsSectionOpen());
    }

    @Test
    @DisplayName("Открыть раздел 'Соусы' по клику на название раздела")
    public void openSaucesSectionFromSectionNameLink(){
        mainPage.openSaucesSection();
        Assert.assertTrue(mainPage.isSaucesSectionOpen());
    }

    @Test
    @DisplayName("Открыть раздел 'Соусы' по скроллу к элементу")
    public void openSaucesSectionByScrollToSauceImage() {
        mainPage.scrollToSpicyXSauce();
        Assert.assertTrue(mainPage.isSaucesSectionOpen());
    }

    @Test
    @DisplayName("Открыть раздел 'Булки' по клику на название раздела")
    public void openBunsSectionFromSectionNameLink(){
        mainPage.openIngredientSection();
        mainPage.openBunsSection();
        Assert.assertTrue(mainPage.isBunsSectionOpen());
    }

    @Test
    @DisplayName("Открыть раздел 'Булки' по скроллу к элементу")
    public void openBunsSectionByScrollToBunsImage() {
        mainPage.scrollToCardboardBun();
        Assert.assertTrue(mainPage.isBunsSectionOpen());
    }
}