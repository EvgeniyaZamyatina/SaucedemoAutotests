package tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import static com.codeborne.selenide.Selenide.page;

public class LoginTest extends BaseTest {
    // логин - "standard_user",  пароль - "secret_sauce"
    // Given (дано)
    // When (когда)
    // Then (тогда)

    String successfullyLogin = "standard_user";
    String successfullyPassword = "secret_sauce";
    String successfullyResultText = "Products";

    @Test(description = "LoginPage. Тест 1: Успешная авторизация")
    public void loginWithPageObject() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.enterLogin(successfullyLogin);
        loginPage.enterPassword(successfullyPassword);
        ProductsPage productsPage = loginPage.clickLoginButton();
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Нет такого элемента")
                .isEqualTo(successfullyResultText);
    }

    @Test(description = "LoginPage. Тест 2: Успешная авторизация. Рефакт")
    public void newLogin() {
        LoginPage loginPage = page(LoginPage.class);
        ProductsPage productsPage = loginPage.login(successfullyLogin, successfullyPassword);
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Нет такого элемента")
                .isEqualTo(successfullyResultText);
    }

    @Test(description = "LoginPage. Тест 3: Не успешная авторизация. Неверный логин")
    public void unSuccessfullyLogin() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.enterLogin("");
        loginPage.enterPassword(successfullyPassword);
        loginPage.clickLoginButton();
        Assertions.assertThat(loginPage.getError())
                .as("Сообщение об ошибке")
                .isTrue();
    }

    @Test(description = "LoginPage. Тест 4: Не успешная авторизация. Неверный пароль")
    public void unSuccessfullyPassword() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(successfullyLogin, "");
        Assertions.assertThat(loginPage.getError())
                .as("Сообщение об ошибке")
                .isTrue();
    }
}
