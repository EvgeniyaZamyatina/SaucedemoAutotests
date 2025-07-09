package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.LoginPage;

public class SauceDemoTest extends BaseTest {
    // логин - "standard_user",  пароль - "secret_sauce"
    // Given (дано)
    // When (когда)
    // Then (тогда)

    @Test(description = "Тест 2: автотест страницы логина c PageObject")
    public void loginWithPageObject() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class); // PageFactory.initElements — это команда, которая создаёт объект страницы и связывает все её элементы с реальными элементами в браузере.
        loginPage.login();
        System.out.println();
    }


    @Test(description = "Тест 1: страница логина")
    public void testLogin() {
        WebElement userNameField = driver.findElement(By.xpath("//*[@id='user-name']"));
        userNameField.sendKeys("standard_user");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordField.sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id = 'login-button']")).click();
    }
}
