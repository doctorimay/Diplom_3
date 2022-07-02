import Api.User;
import Api.UserClient;

import com.codeborne.selenide.Selenide;

import io.restassured.response.ValidatableResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertTrue;

public class ProfileActionTest {
    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String accessToken;
    private LoginPage loginPage;
    private ProfilePage profilePage;


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


    @Test // Переход в личный кабинет
    public void userTransitionPersonalAccountTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickLoginAccountBtn();
        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        mainPage.clickPersonalAccountBtnWithAuth();
        profilePage = page(ProfilePage.class);
        assertTrue(profilePage.exitBtnIsDisplayed());
    }

    @Test // Переход на вкладку "Конструктор"
    public void userTransitionConstructorFromPersonalAccountTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickLoginAccountBtn();
        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        mainPage.clickPersonalAccountBtnWithAuth();
        profilePage = page(ProfilePage.class);
        profilePage.clickConstructorBtn();
        assertTrue(mainPage.titleMainPageIsDisplayed());
    }

    @Test //Клик по лого
    public void userTransitionLogoFromPersonalAccountTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickLoginAccountBtn();
        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        mainPage.clickPersonalAccountBtnWithAuth();
        profilePage = page(ProfilePage.class);
        profilePage.clickHeaderBurgerLogo();
        assertTrue(mainPage.titleMainPageIsDisplayed());
    }

    @Test // выход из авторизованного аккаунта
    public void userLogoutFromPersonalAccountTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickLoginAccountBtn();
        loginPage = page(LoginPage.class);
        loginPage.clickEmailField()
                .enterEmail(user.email)
                .clickPasswordField()
                .enterPassword(user.password)
                .clickEnterBtn();
        mainPage.clickPersonalAccountBtnWithAuth();
        profilePage = page(ProfilePage.class);
        profilePage.cleckExitBtn();
        assertTrue(loginPage.headerLogoIsDisplayed());
    }
}
