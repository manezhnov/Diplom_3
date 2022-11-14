import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;
import supportClasses.RandomGenerator;
import supportClasses.User;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class UserLoginTest {

    Header header = page(Header.class);
    MainPage mainPage = page(MainPage.class);
    LoginPage loginPage = page(LoginPage.class);
    RegisterPage registerPage = page(RegisterPage.class);
    ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);

    RandomGenerator random = new RandomGenerator();
    User user = new User(random.String(), random.Email(), random.String());

    @Before
    public void setUp() {
        open(registerPage.Url);
        user.Registration();
    }

    @After
    public void tearDown() {
        WebDriverRunner.clearBrowserCache();
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void userLogInFromMainPageLogInButton() {
        open(mainPage.Url);
        mainPage.clickToLoginButton();
        user.LogIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void userLogInFromHeaderUserAccountButton() {
        open(mainPage.Url);
        header.clickToAccountButton();
        user.LogIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void userLogInFromRegisterPage(){
        open(registerPage.Url);
        registerPage.clickToLoginLink();
        user.LogIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void userLogInFromForgotPasswordPage() {
        open(loginPage.Url);
        loginPage.clickToForgotPasswordLink();
        forgotPasswordPage.clickToLoginLink();
        user.LogIn();
        Assert.assertTrue(mainPage.isMakeOrderButtonDisplayed());
    }

}