package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//главная страница
public class MainPage {
    //веб-драйвер
    private WebDriver driver;
    //локатор на Куки
    public final By cookieButton = By.id("rcc-confirm-button");
    //"Вопросы о важном"
    //локаторы на вопросы
    private final By question_0 = By.id("accordion__heading-0");
    private final By question_1 = By.id("accordion__heading-1");
    private final By question_2 = By.id("accordion__heading-2");
    private final By question_3 = By.id("accordion__heading-3");
    private final By question_4 = By.id("accordion__heading-4");
    private final By question_5 = By.id("accordion__heading-5");
    private final By question_6 = By.id("accordion__heading-6");
    private final By question_7 = By.id("accordion__heading-7");
    //локаторы на ответы
    private final By answer_0 = By.id("accordion__panel-0");
    private final By answer_1 = By.id("accordion__panel-1");
    private final By answer_2 = By.id("accordion__panel-2");
    private final By answer_3 = By.id("accordion__panel-3");
    private final By answer_4 = By.id("accordion__panel-4");
    private final By answer_5 = By.id("accordion__panel-5");
    private final By answer_6 = By.id("accordion__panel-6");
    private final By answer_7 = By.id("accordion__panel-7");
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
        // Собираем локаторы вопросов в массив для быстрого доступа по индексу
        By[] questions = {question_0, question_1, question_2, question_3, question_4, question_5,
                question_6, question_7};
        WebElement element = driver.findElement(questions[index]);
        scrollToElement(element);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    // Получить текст ответа по его номеру (от 0 до 7)
    public String getAnswerText(int index) {
        By[] answers = {answer_0, answer_1, answer_2, answer_3, answer_4, answer_5, answer_6, answer_7};
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(answers[index]));
        return driver.findElement(answers[index]).getText();
    }
    //кликаем на верхнюю кнопку "Заказать"
    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='Для кого самокат']")));
    }
    //кликаем по нижней кнопки "Заказать"
    public void clickLowerOrderButton() {
        WebElement element = driver.findElement(lowerOrderButton);
        scrollToElement(element);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='Для кого самокат']")));
    }
    //кликаем на логотип "Самоката"
    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
    }
    //кликаем на логотип "Яндекса"
    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }
}
