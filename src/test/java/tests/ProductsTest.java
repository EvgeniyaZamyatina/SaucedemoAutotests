package tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest{
    String successfullyLogin = "standard_user";
    String successfullyPassword = "secret_sauce";
    String successfullyResultText = "Products";

    @Test(description = "ProductsPage. Тест 1: Добавление товара в корзину.")
    public void addProductsToCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(successfullyLogin, successfullyPassword);
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Нет такого элемента")
                .isEqualTo(successfullyResultText);
        driver.get("https://www.saucedemo.com/inventory.html");

        productsPage.addToCart();
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Количество товаров изменилось")
                .isVisible();
    }


}
