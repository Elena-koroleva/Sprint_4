package scooter;

import org.junit.Rule;
import org.junit.Test;
import pageObject.MainPage;

import static org.junit.Assert.assertEquals;

//Если нажать на логотип Самоката, попадёшь на главную страницу Самоката
public class LogoScooterTest {
        @Rule
        public final BaseTest baseTest = new BaseTest();

        @Test
        public void testLogoScooterRedirectsToMainPage() {
            MainPage mainPage = new MainPage(baseTest.getDriver());
            mainPage.acceptCookies();
            mainPage.clickUpperOrderButton(); // Уходим на страницу заказа
            mainPage.clickLogoScooter();     // Кликаем по логотипу
            mainPage.waitForMainPageToLoad(); // Ждем возвращения на главную страницу
            String actualUrl = baseTest.getDriver().getCurrentUrl();
            assertEquals("Клик по логотипу Самоката не вернул на главную страницу!",
                    MainPage.MAIN_PAGE_URL, actualUrl);
        }
}
