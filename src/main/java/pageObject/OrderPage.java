package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//страница заказа с заполнением форм заказа
public class OrderPage {
    //веб-драйвер
    private WebDriver driver;
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
    }
    //методы
    //заполняем форму заказа "Для кого самокат"
    public void fillWhoIsScooterForm(String name, String surname, String address, String metroStation, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(metroStationInput).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(metroSearchOptions));
        driver.findElement(By.xpath(".//*[text()='"+metroStation+"']")).click();
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(dateInput));
    }

    //заполняем форму заказа "Про аренду"
    public void fillAboutRentForm(String orderDate, String orderDuration, String color, String comment){
        driver.findElement(dateInput).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendar));
        driver.findElement(By.xpath(".//div[@aria-label='" + orderDate + "']")).click();
        driver.findElement(rentPeriodInput).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rentPeriodList));
        driver.findElement(By.xpath(".//div[text()='" + orderDuration + "']")).click();
        if(color.equals("чёрный жемчуг")) {
            driver.findElement(blackColorCheckbox).click();
        } else if(color.equals("серая безысходность")) {
            driver.findElement(greyColorCheckbox).click();
        }
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmOrderModal));
        driver.findElement(yesButton).click();
    }
    //проверяем, что появилось окно "Заказ оформлен"
    public boolean isSuccessOrderModalDisplayed() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(successOrderModal));
        return driver.findElement(successOrderModal).isDisplayed();
    }
}
