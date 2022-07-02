package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    // поле "Email"
    @FindBy(how = How.XPATH, using = ".//input[@name ='name']")
    private SelenideElement emailField;
    // поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@name ='Пароль']")
    private SelenideElement passwordField;
    // кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Войти')]")
    private SelenideElement enterBtn;
    // кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrationBtn;
    // кнопка "Восстановить пароль"
    @FindBy(how = How.XPATH, using = ".//a[contains(text(),'Восстановить пароль')]")
    private SelenideElement restorePasswordBtn;
    // Заголовок страницы входа в аккаунт
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Вход')]")
    private SelenideElement headerLogoBtn;


    // нажать на поле email
    public LoginPage clickEmailField() {
        emailField.click();
        return this;
    }

    // ввести в поле email
    public LoginPage enterEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    // нажать на поле пароль
    public LoginPage clickPasswordField() {
        passwordField.click();
        return this;
    }

    // ввести в поле пароль
    public LoginPage enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    // нажать на кнопку войти
    public MainPage clickEnterBtn() {
        enterBtn.click();
        return page(MainPage.class);
    }

    // нажать на кнопку зарегистрироваться
    public RegistrationPage clickRegistrationBtn() {
        registrationBtn.click();
        return page(RegistrationPage.class);
    }

    // нажать на кнопку восстановить пароль
    public RestorePage clickRestorePasswordBtn() {
        restorePasswordBtn.click();
        return page(RestorePage.class);
    }

    // лого бургеров в шапке
    public boolean headerLogoIsDisplayed() {
        return headerLogoBtn.shouldBe(visible).isDisplayed();
    }
}