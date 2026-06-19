package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//страница заказа с заполнением форм заказа
public class OrderPage {
    //веб-драйвер
    private final WebDriver driver;
    private final WebDriverWait wait;
    //локаторы для формы заказа "Для кого самокат"
    //поле имя
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    //поле фамилия
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле адрес: куда привезти заказ
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле Станция метро
    private final By metroStationInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //выпадающий список станций метро
    private final By metroSearchOptions = By.className("select-search__select");
    //поле телефон:на него позвонит курьер
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //локаторы для формы заказа "Про аренду"
    //поле Когда привезти самокат
    private final By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //календарь
    private final By calendar = By.className("react-datepicker");
    //поле срок аренды
    private final By rentPeriodInput = By.className("Dropdown-placeholder");
    //выпадающий список срока аренды
    private final By rentPeriodList = By.xpath(".//div[@class='Dropdown-menu']");
    //чекбокс Чёрный жемчуг
    private final By blackColorCheckbox = By.xpath(".//input[@id='black']");
    //чекбокс Серая безысходность
    private final By greyColorCheckbox = By.xpath(".//input[@id='grey']");
    //поле комментарий для курьера
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //всплывающее окно: Хотите оформить заказ?
    private final By confirmOrderModal = By.xpath(".//div[text()='Хотите оформить заказ?']");
    //кнопка Да
    private final By yesButton = By.xpath(".//button[text()='Да']");
    //локатор для всплывающего окна "Заказ оформлен"
    private final By successOrderModal = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver,5);
    }
    // методы "Для кого самокат"

    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    public void enterSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void enterAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void selectMetroStation(String metroStation) {
        driver.findElement(metroStationInput).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroSearchOptions));
        driver.findElement(By.xpath(".//*[text()='" + metroStation + "']")).click();
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));
    }

    // методы формы "Про аренду"

    public void selectDate(String orderDate) {
        driver.findElement(dateInput).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendar));
        driver.findElement(By.xpath(".//div[@aria-label='" + orderDate + "']")).click();
    }

    public void selectRentDuration(String orderDuration) {
        driver.findElement(rentPeriodInput).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentPeriodList));
        driver.findElement(By.xpath(".//div[text()='" + orderDuration + "']")).click();
    }

    public void selectColor(String color) {
        if ("чёрный жемчуг".equalsIgnoreCase(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("серая безысходность".equalsIgnoreCase(color)) {
            driver.findElement(greyColorCheckbox).click();
        }
    }

    public void enterComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmOrderModal));
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    // Методы-сценарии (шаги)

    public void fillWhoIsScooterForm(String name, String surname, String address, String metroStation, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        selectMetroStation(metroStation);
        enterPhone(phone);
        clickNextButton();
    }

    public void fillAboutRentForm(String orderDate, String orderDuration, String color, String comment) {
        selectDate(orderDate);
        selectRentDuration(orderDuration);
        selectColor(color);
        enterComment(comment);
        clickOrderButton();
        clickYesButton();
    }

    // Проверка появления окна успешного заказа
    public boolean isSuccessOrderModalDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successOrderModal));
        return driver.findElement(successOrderModal).isDisplayed();
    }
}
