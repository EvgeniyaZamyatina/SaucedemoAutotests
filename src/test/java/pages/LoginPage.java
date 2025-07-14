package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    // логин - "standard_user",  пароль - "secret_sauce"

    private WebDriver driver;

    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id = 'login-button']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        return new ProductsPage(driver);
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
