package tests.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUITest {
    protected WebDriver driver;

    @BeforeMethod(description = "Открыть браузер и страницу")
    public void setup() {
        // Регистрируем слушатель, который автоматически прикрепляет скриншоты и HTML страницы в Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)  // включить скриншоты
                .savePageSource(true));  // сохранить html страницы вместе

        Selenide.open("/");
        driver = WebDriverRunner.getWebDriver();
    }

    @AfterMethod(description = "Закрыть браузер и отписываемся от слушателя")
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
        SelenideLogger.removeListener("AllureSelenide"); // Удаляем слушатель после каждого теста
    }

    //  скриншот текущего состояния экрана браузера во время выполнения теста
    @Attachment(value = "Screenshot test", type = "image/png")
    public byte[] makeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
