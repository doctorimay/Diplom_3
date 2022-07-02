package pageObjects;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class MainPage {
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";

    //Кнопка Личный кабинет
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement personalAccountBtn;
    //Кнопка Войти в аккаунт
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Войти в аккаунт')]")//
    private SelenideElement loginAccountBtn;
    //Кнопка Оформить заказ
    @FindBy(how = How.XPATH, using = ".//*[contains (text(), 'Оформить заказ')]")//
    private SelenideElement createOrderBtn;
    //Раздел Булки
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Булки')]")
    private SelenideElement bunBtn;
    //Раздел Соусы
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Соусы')]")
    private SelenideElement sauceBtn;
    //Раздел Начинки
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Начинки')]")
    private SelenideElement fillingBtn;
    //заголовок раздела Булок
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Булки')]")
    private SelenideElement titleBunSection;
    //Заголовок раздела Соусов
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Соусы')]")
    private SelenideElement titleSauceSection;
    //заголовок раздела Начинок
    @FindBy(how = How.XPATH, using = ".//h2[contains(text(),'Начинки')]")
    private SelenideElement titleFillingSection;

    //заголовок Главной страницы
    @FindBy(how = How.XPATH, using = ".//h1[contains(text(),'Соберите бургер')]")
    private SelenideElement titleMainPage;
    //выбранный раздел
    @FindBy(how = How.CSS, using = ".tab_tab_type_current__2BEPc")
    public SelenideElement activeSection;



    public LoginPage clickPersonalAccountBtnWithoutAuth() {
        personalAccountBtn.click();
        return page(LoginPage.class);
    }


    public ProfilePage clickPersonalAccountBtnWithAuth() {
        personalAccountBtn.click();
        return page(ProfilePage.class);
    }


    public LoginPage clickLoginAccountBtn() {
        loginAccountBtn.click();
        return page(LoginPage.class);
    }


    public MainPage clickBunBtn() {
        bunBtn.click();
        return this;
    }

    public boolean titleCategoryBunIsDisplayed() {
        return titleBunSection.shouldBe(visible).isDisplayed();
    }


    public MainPage clickSouceBtn() {
        sauceBtn.click();
        return this;
    }


    public boolean titleCategorySauceIsDisplayed() {
        return titleSauceSection.shouldBe(visible).isDisplayed();
    }


    public MainPage clickFillingBtn() {
        fillingBtn.click();
        return this;
    }


    public boolean titleCategoryFillingIsDisplayed() {
        return titleFillingSection.shouldBe(visible).isDisplayed();
    }


    public boolean CreateOrderBtnIsDisplayed() {
        return createOrderBtn.shouldBe(visible).isDisplayed();
    }

    public boolean titleMainPageIsDisplayed() {
        return titleMainPage.shouldBe(visible).isDisplayed();
    }

    public String getActiveSectionText() {
        return activeSection.getText();
    }


    public boolean loginAccountBtnIsDisplayed() {
        return loginAccountBtn.isDisplayed();
    }
}
