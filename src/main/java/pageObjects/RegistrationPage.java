package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";

    // поле "Имя"
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement nameField;

    // поле "Email"
    @FindBy(how = How.XPATH, using = ".//fieldset[2]//input")
    private SelenideElement emailField;

    // поле "Пароль"
    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    // кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrationBtn;

    // кнопка "Войти"
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj")
    private SelenideElement enterBtn;

    // кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Конструктор')]")
    private SelenideElement constructorBtn;

    // текст об ошибке "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Некорректный пароль')]")
    private SelenideElement errorPasswordText;


    public RegistrationPage clickNameField() {
        nameField.click();
        return this;
    }

    public RegistrationPage setNameField(String name) {
        nameField.setValue(name);
        return this;
    }

    public RegistrationPage clickEmailField() {
        emailField.click();
        return this;
    }

    public RegistrationPage setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }

    public RegistrationPage clickPasswordField() {
        passwordField.click();
        return this;
    }

    public RegistrationPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }


    public LoginPage сlickRegistrationBtn() {
        this.registrationBtn.click();
        return page(LoginPage.class);
    }


    public LoginPage clickEnterBtn() {
        enterBtn.click();
        return page(LoginPage.class);
    }

    public MainPage clickConstructorBtn() {
        constructorBtn.click();
        return page(MainPage.class);
    }

    public boolean ErrorPasswordFieldTextIsDisplayed() {
        return errorPasswordText.isDisplayed();
    }
}