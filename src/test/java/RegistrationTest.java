import Api.User;
import Api.UserClient;
import Api.UserCredentials;
import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import pageObjects.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    private User user;
    private UserClient userClient;
    private MainPage mainPage;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @Test // Успешная регистрация
    public void successfulRegistrationTest() {
        mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickPersonalAccountBtnWithoutAuth();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationBtn();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickNameField()
                .setNameField(user.name)
                .clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(user.password)
                .ClickRegistrationBtn();
        ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.builder().email(user.email).password(user.password).build());
        accessToken = loginResponse.extract().path("accessToken");
        assertThat( accessToken, notNullValue());
    }

    @Test //Ошибка для некорректного пароля. Минимальный пароль — шесть символов.
    public void failRegistrationTest() {
        MainPage mainPage = open(MainPage.MAIN_URL, MainPage.class);
        mainPage.clickPersonalAccountBtnWithoutAuth();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationBtn();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickNameField()
                .setNameField(user.name)
                .clickEmailField()
                .setEmailField(user.email)
                .clickPasswordField()
                .setPasswordField(RandomStringUtils.randomAlphabetic(5))
                .ClickRegistrationBtn();
        assertTrue(registrationPage.ErrorPasswordFieldTextIsDisplayed());
    }
}
