package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RestorePage {
    public static final String FORGOT_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    //кнопка "Войти"
    @FindBy(how = How.CSS, using = ".Auth_link__1fOlj")
    private SelenideElement enterBtn;

    public LoginPage clickEnterBtn() {
        enterBtn.click();
        return page(LoginPage.class);
    }
}