package tests.ui;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.ui.LoginPage;
import pages.ui.ProductsPage;
import utils.ScreenshotVerifier;
import static com.codeborne.selenide.Selenide.page;

public class ProductsUITest extends BaseUITest {
    String successfullyLogin = "standard_user";
    String successfullyPassword = "secret_sauce";
    String successfullyResultText = "Products";

    @Test(description = "ProductsPage. Тест 1: Добавление товара в корзину.")
    public void addProductsToCart() throws Exception {
        LoginPage loginPage = page(LoginPage.class);
        ProductsPage productsPage = loginPage.login(successfullyLogin, successfullyPassword);
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Нет такого элемента")
                .isEqualTo(successfullyResultText);
        driver.get("https://www.saucedemo.com/inventory.html");

        productsPage.addToCart();
        Assertions.assertThat(productsPage.getTitleProducts())
                .as("Количество товаров изменилось")
                .isVisible();
        ScreenshotVerifier.verifyScreenshot("ProductsPage.Screenshots");
    }
}
