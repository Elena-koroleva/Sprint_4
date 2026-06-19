package scooter;

import org.junit.Rule;
import org.junit.Test;
import pageObject.MainPage;

import static org.junit.Assert.assertTrue;
//Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса
public class LogoYandexTest {

    @Rule
    public final BaseTest baseTest = new BaseTest();

    @Test
    public void testLogoYandexOpensMainPageYandex() {
        MainPage mainPage = new MainPage(baseTest.getDriver());
        mainPage.acceptCookies();
        // Запоминаем имя текущей вкладки
        String originalWindow = baseTest.getDriver().getWindowHandle();
        mainPage.clickLogoYandex();
        mainPage.switchToNewWindow(originalWindow); // метод работы со вкладками
        mainPage.waitForYandexPageToLoad();
        String actualUrl = baseTest.getDriver().getCurrentUrl();
        assertTrue("Клик по логотипу Яндекс не открыл главную страницу Яндекса!",
                actualUrl.contains("dzen.ru"));
    }
}
