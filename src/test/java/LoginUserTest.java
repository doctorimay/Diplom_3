import Api.User;
import Api.UserClient;

import com.codeborne.selenide.Selenide;
import io.restassured.response.ValidatableResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.RegistrationPage;
import pageObjects.RestorePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class LoginUserTest {
    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test // Авторизация используя кнопку "Войти в аккаунт" на главной
    public void userAuthorizationWithBodyLoginBtnTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickLoginAccountBtn();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        assertTrue(mainPage.CreateOrderBtnIsDisplayed());
    }

    @Test // Авторизация через кнопку "Личный кабинет"
    public void userAuthorizationWithHeaderLoginBtnTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickPersonalAccountBtnWithoutAuth();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        assertTrue(mainPage.CreateOrderBtnIsDisplayed());
    }

    @Test // Авторизация через кнопку в форме регистрации
    public void userAuthorizationWithRegistrationFormTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickPersonalAccountBtnWithoutAuth();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationBtn();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickEnterBtn();

        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();

        assertTrue(mainPage.CreateOrderBtnIsDisplayed());
    }

    @Test // Авторизация пользователя в форме восстановления пароля
    public void userAuthorizationWithRecoveryPasswordFormTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickLoginAccountBtn();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRestorePasswordBtn();
        RestorePage restorePage = page(RestorePage.class);
        restorePage.clickEnterBtn();
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        assertTrue(mainPage.CreateOrderBtnIsDisplayed());
    }
}
