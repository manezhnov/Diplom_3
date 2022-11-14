import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import supportClasses.RandomGenerator;
import supportClasses.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class UserRegistrationTest {

    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RegisterPage registerPage = page(RegisterPage.class);

    RandomGenerator random = new RandomGenerator();

    @Before
    public void setUp() {
        open(mainPage.Url);
        mainPage.clickToLoginButton();
        loginPage.clickToRegisterPageLink();
    }

    @After
    public void tearDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void userRegistrationWithValidCredentials() {
        User user = new User(random.String(),random.Email(), random.String());
        user.Registration();
        Assert.assertTrue(loginPage.isLogInHeaderDisplayed());
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле меньше шести символов")
    public void userRegistrationWithInvalidPassword() {
        User user = new User(random.String(),random.Email(),random.invalidString());
        user.Registration();
        Assert.assertTrue(registerPage.isPasswordErrorDisplayed());
    }
}