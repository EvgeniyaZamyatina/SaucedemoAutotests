package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@data-test = 'title']")
    private WebElement titleProducts;

    @FindBy(xpath = "//*[@data-test = 'add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBackpack;

    @FindBy(xpath = "//*[@data-test = 'add-to-cart-sauce-labs-bolt-t-shirt']")
    private WebElement addToCartTShirt;

    @FindBy(xpath = "//*[@data-test = 'add-to-cart-sauce-labs-fleece-jacket']")
    private WebElement addToCartJacket;

    @FindBy(xpath = "//*[@data-test = 'shopping-cart-link']")
    private WebElement cartLink;

    @FindBy(xpath = "//*[@data-test = 'shopping-cart-badge']")
    private WebElement cartBadge;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
