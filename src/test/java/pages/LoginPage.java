package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    // логин - "standard_user",  пароль - "secret_sauce"

    @FindBy(id="user-name")
    private SelenideElement loginField;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private SelenideElement passwordField;

    @FindBy(xpath = "//*[@id = 'login-button']")
    private SelenideElement submitButton;

    @FindBy(xpath = "//*[@data-test='error']")
    private SelenideElement errorMessage;

    public LoginPage() {
    }

    @Step("Ввести логин")
    public void enterLogin (String loginString){
        loginField.sendKeys(loginString);
    }

    @Step("Ввести пароль")
    public void enterPassword (String passwordString){
        passwordField.sendKeys(passwordString);
    }

    @Step("Нажать Login")
    public ProductsPage clickLoginButton(){
        submitButton.click();
        return page(ProductsPage.class);
    }

    @Step("Получить сообщение об ошибке")
    public boolean getError() {
        return errorMessage.isDisplayed();
    }

    public ProductsPage login(String loginString, String passwordString){
        enterLogin(loginString);
        enterPassword(passwordString);
        return clickLoginButton();
    }
}
