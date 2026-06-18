import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;
import pageObject.OrderPage;

import static org.junit.Assert.assertTrue;

//тест на Заказ самоката
@RunWith(Parameterized.class)
public class OrderScooterTest {
    //веб-драйвер
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String orderDate;
    private final String orderDuration;
    private final String color;
    private final String comment;

    public OrderScooterTest(String name, String surname, String address, String metroStation, String phone,
                            String orderDate, String orderDuration, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.orderDate = orderDate;
        this.orderDuration = orderDuration;
        this.color = color;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] getParams() {
        return new Object[][]{
                {"Александр", "Пушкин", "Тульская, 5" , "Тульская", "+79163456678", "Choose пятница, " +
                        "19-е июня 2026 г.", "сутки", "серая безысходность","Позвонить на месте"},
                {"Мария", "Ивановна", "Павелецкая, 36" ,"Павелецкая", "+79181234567", "Choose четверг, " +
                        "2-е июля 2026 г.", "семеро суток", "серая безысходность", "Не звонить, только смс"}
        };
    }
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //заказ по верхней кнопке
    @Test
    public void UpperButtonOrderTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickUpperOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillWhoIsScooterForm(name,surname, address, metroStation, phone);
        orderPage.fillAboutRentForm(orderDate, orderDuration, color, comment);
        assertTrue("Модальное окно Заказ оформлен не появилось!",
                orderPage.isSuccessOrderModalDisplayed());
    }
    //заказ по нижней кнопке
    @Test
    public void LowerButtonOrderTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickLowerOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillWhoIsScooterForm(name,surname, address, metroStation, phone);
        orderPage.fillAboutRentForm(orderDate, orderDuration, color, comment);
        assertTrue("Модальное окно Заказ оформлен не появилось!",
                orderPage.isSuccessOrderModalDisplayed());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
