package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    // логин - "standard_user",  пароль - "secret_sauce"

    // Аннотация @FindBy указывает Selenium, как найти элемент на странице.
    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id = 'login-button']")
    private WebElement submitButton;

    public void login (){
        loginField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        submitButton.click();
    }
}
