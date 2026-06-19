package scooter;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import pageObject.MainPage;

public class BaseTest extends ExternalResource {

    private WebDriver driver;

    public  WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(MainPage.MAIN_PAGE_URL);
    }

    @Override
    protected void after() {
            driver.quit();
        }
    }
