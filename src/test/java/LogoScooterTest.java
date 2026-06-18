import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.MainPage;

import static org.junit.Assert.assertEquals;

//Если нажать на логотип Самоката, попадёшь на главную страницу Самоката
public class LogoScooterTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void testLogoScooterRedirectsToMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickUpperOrderButton(); //уходим с главной страницы
        mainPage.clickLogoScooter(); //нажимаем на логотип Самокат
        //ожидаемый url главной страницы
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        //ждем, пока URL станет равен главной странице
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlToBe(expectedUrl));
        //получаем URL страницы, на которой оказались
        String actualUrl = driver.getCurrentUrl();
        //Проверяем, что вернулись на главную
        assertEquals("Клик по логотипу Самоката не вернул на главную страницу!",
                expectedUrl, actualUrl);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
