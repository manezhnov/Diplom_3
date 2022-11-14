import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.Header;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import pageObject.UserAccount;
import supportClasses.RandomGenerator;
import supportClasses.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class UserAccountTest {

    UserAccount userAccount = page(UserAccount.class);
    RegisterPage registerPage = page(RegisterPage.class);
    LoginPage loginPage = page(LoginPage.class);
    Header header = page(Header.class);

    RandomGenerator random = new RandomGenerator();
    User user = new User(random.String(), random.Email(), random.String());

    @Before
    public void setUp() throws InterruptedException {
        open(registerPage.Url);
        user.Registration();
        open(loginPage.Url);
        user.LogIn();
        header.clickToAccountButton();
    }

    @After
    public void tearDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет».")
    public void openUserAccount() {
        boolean isPersonalAreaElementDisplayed = userAccount.isExitButtonDisplayed();
        Assert.assertTrue(isPersonalAreaElementDisplayed);
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void exitFromUserAccount() throws InterruptedException {
        userAccount.clickToExitButton();
        boolean isExitSuccess = loginPage.isLogInHeaderDisplayed();
        Assert.assertTrue(isExitSuccess);
    }

    @Test
    @DisplayName("Перейти в конструктор бургеров из лого Stellar Burgers")
    public void switchFromAccountToConstructorByLogo() {
        header.clickToLogo();
        Assert.assertTrue(header.isConstructorButtonEnabled());
    }

    @Test
    @DisplayName("Перейти в конструктор бургеров по кнопке Конструктор")
    public void switchFromAccountToConstructorByConstructorButton() {
        header.clickToConstructorButton();
        Assert.assertTrue(header.isConstructorButtonEnabled());
    }
}