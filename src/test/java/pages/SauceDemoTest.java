package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTest {
    // логин - "standard_user",  пароль - "secret_sauce"
    // Given (дано)
    // When (когда)
    // When (когда)

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void cleanup(){
        driver.quit();
    }

    @Test(description = "Тест 2: автотест страницы логина c PageObject")
    public void loginWithPageObject(){
        driver.navigate().to("https://www.saucedemo.com/");
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login();
    }

    @Test(description = "Тест 1: страница логина")
    public void testLogin() {
        driver.navigate().to("https://www.saucedemo.com/");
        WebElement userNameField = driver.findElement(By.xpath("//*[@id='user-name']"));
        userNameField.sendKeys("standard_user");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordField.sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id = 'login-button']")).click();
    }
}
