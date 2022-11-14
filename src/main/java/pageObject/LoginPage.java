package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    public final String Url = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Войти')]")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//*[@name='name']")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = "//*[@name='Пароль']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = "//*[@href='/register']")
    private SelenideElement registerPageLink;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Восстановить пароль')]")
    private SelenideElement forgotPasswordLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement logInHeader;


    @Step("Нажать на кнопку 'Войти' на странице авторизации")
    public void clickToLoginButton() {
        loginButton.click();
    }

    @Step("Нажать на ссылку 'Зарегистрироваться' на странице авторизации")
    public void clickToRegisterPageLink() {
        registerPageLink.shouldBe(Condition.visible).click();
    }

    @Step("Нажать на ссылку'Восстановить пароль'")
    public void clickToForgotPasswordLink(){
        forgotPasswordLink.click();
    }

    @Step("Ввести почту пользователя в поле 'Email'")
    public void setEmailField(String userEmail) {
        emailField.shouldBe(Condition.visible).setValue(userEmail);
    }
    @Step("Ввести пароль пользователя в поле 'Пароль'")
    public void setPasswordField(String userPassword) {
        passwordField.shouldBe(Condition.visible).setValue(userPassword);
    }
    @Step("Заголовок 'Регистрация' отображается на странице")
    public boolean isLogInHeaderDisplayed() {
        return logInHeader.shouldBe(Condition.visible).isDisplayed();
    }
}