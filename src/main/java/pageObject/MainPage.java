package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

//главная страница
public class MainPage {
    //веб-драйвер
    private final WebDriver driver;
    private final WebDriverWait wait;
    //Константа с URL главной страницы
    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    //локатор на Куки
    public final By cookieButton = By.id("rcc-confirm-button");
    //динамические локаторы для вопросов и ответов
    private final String questionTemplateId = "accordion__heading-%d";
    private final String answerTemplateId = "accordion__panel-%d";
    //локатор для верхней кнопки "Заказать"
    private final By upperOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //локатор для нижней кнопки "Заказать"
    private final By lowerOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //локатор логотипа Самокат
    private final By logoScooter = By.className("Header_LogoScooter__3lsAR");
    //локатор логотипа Яндекс
    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver,5);
    }
    //методы
    // Принять куки
    public void acceptCookies() {
        if (driver.findElements(cookieButton).size() > 0) {
            driver.findElement(cookieButton).click();
        }
    }
    // скролл
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    // Кликнуть по вопросу по его индексу (0-7)
    public void clickQuestion(int index) {
        // подставляем индекс в динамический id
        By questionLocator = By.id(String.format(questionTemplateId, index));
        WebElement element = driver.findElement(questionLocator);
        scrollToElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    // Получить текст ответа по его номеру (от 0 до 7)
    public String getAnswerText(int index) {
        By answerLocator = By.id(String.format(answerTemplateId, index));
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }
    //кликаем на верхнюю кнопку "Заказать"
    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }
    //кликаем по нижней кнопки "Заказать"
    public void clickLowerOrderButton() {
        WebElement element = driver.findElement(lowerOrderButton);
        scrollToElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    //кликаем на логотип "Самоката"
    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
    }
    //кликаем на логотип "Яндекса"
    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }
    // Метод для ожидания загрузки главной страницы
    public void waitForMainPageToLoad() {
        wait.until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));
    }
    // Метод для ожидания загрузки главной страницы Яндекса
    public void waitForYandexPageToLoad() {
        wait.until(ExpectedConditions.urlContains("dzen.ru"));
    }
    // Метод для переключения на новую вкладку
    public void switchToNewWindow(String originalWindow) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
