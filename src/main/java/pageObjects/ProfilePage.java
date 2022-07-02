package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {
    public static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Кнопка конструктор
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Конструктор')]")//
    private SelenideElement headerConstructorBtn;
    //Лого проекта
    @FindBy(how = How.CSS, using = ".AppHeader_header__logo__2D0X2")
    private SelenideElement headerBurgerLogo;
    // кнопка "Выйти"
    @FindBy(how = How.XPATH, using = ".//button[contains (text(), 'Выход')]")
    private SelenideElement exitBtn;


    public MainPage clickConstructorBtn() {
        headerConstructorBtn.click();
        return page(MainPage.class);
    }

    public MainPage clickHeaderBurgerLogo() {
        headerBurgerLogo.click();
        return page(MainPage.class);
    }

    public LoginPage cleckExitBtn() {
        exitBtn.click();
        return page(LoginPage.class);
    }

    public boolean exitBtnIsDisplayed() {
        return exitBtn.shouldBe(visible).isDisplayed();
    }

}
