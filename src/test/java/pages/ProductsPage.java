package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class ProductsPage {
    @FindBy(xpath = "//*[@data-test = 'title']")
    private SelenideElement titleProducts;

    @FindBy(xpath = "//*[@data-test = 'add-to-cart-sauce-labs-backpack']")
    private SelenideElement addToCartBackpack;

    @FindBy(xpath = "//*[@data-test = 'add-to-cart-sauce-labs-bolt-t-shirt']")
    private SelenideElement addToCartTShirt;

    @FindBy(xpath = "//*[@data-test = 'add-to-cart-sauce-labs-fleece-jacket']")
    private SelenideElement addToCartJacket;

    @FindBy(xpath = "//*[@data-test = 'shopping-cart-link']")
    private SelenideElement cartLink;

    @FindBy(xpath = "//*[@data-test = 'shopping-cart-badge']")
    private SelenideElement cartBadge;

    public ProductsPage() {
    }

    @Step("Найти заголовок")
    public String getTitleProducts(){
        return titleProducts.getText();
    }

    @Step("Добавить товар в корзину")
    public void addToCart() {
        addToCartJacket.click();
    }

    @Step("Найти значок корзины покупок")
    public String getCartBadge(){
        return cartBadge.getText();
    }
}
