package tests;

import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {
    // логин - "standard_user",  пароль - "secret_sauce"
    // Given (дано)
    // When (когда)
    // Then (тогда)
    String successfullyLogin = "standard_user";
    String successfullyPassword = "secret_sauce";

    @Test(description = "Тест 1: Успешная авторизация")
    public void loginWithPageObject() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin(successfullyLogin);
        loginPage.enterPassword(successfullyPassword);
        ProductsPage productsPage = loginPage.clickLoginButton();
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Нет такого элемента")
                .isEqualTo("Products");
    }

    @Test(description = "Тест 2: Успешная авторизация. Рефакт")
    public void newLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(successfullyLogin, successfullyPassword);
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Нет такого элемента")
                .isEqualTo("Products");
    }

    @Test(description = "Тест 3: Не успешная авторизация. Неверный логин")
    public void unSuccessfullyLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin("");
        loginPage.enterPassword(successfullyPassword);
        loginPage.clickLoginButton();
        Assertions.assertThat(loginPage.getErrorMessage())
                .as("Сообщение об ошибке")
                .isNotNull();
    }
}
