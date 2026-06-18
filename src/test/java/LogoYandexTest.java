import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.MainPage;

import java.util.Set;

import static org.junit.Assert.assertEquals;

//Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
public class LogoYandexTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void testLogoYandexOpensMainPageYandex() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        //Запоминаем имя нашей текущей вкладки
        String originalWindow = driver.getWindowHandle();
        //Кликаем по логотипу Яндекса
        mainPage.clickLogoYandex();
        //Ждем, пока в браузере физически появится вторая вкладка
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        //запрашиваем у браузера все открытые вкладки
        Set<String> allWindows = driver.getWindowHandles();
        // перебираем их в цикле
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        String expectedUrl = "https://dzen.ru/?yredirect=true";
        //ждем, пока URL станет равен главной странице
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlToBe(expectedUrl));
        //получаем URL страницы, на которой оказались
        String actualUrl = driver.getCurrentUrl();
        //Проверяем, что вернулись на главную
        assertEquals("Клик по логотипу Яндекс, не открыл новое окно главной страницы Яндекса!",
                expectedUrl, actualUrl);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
