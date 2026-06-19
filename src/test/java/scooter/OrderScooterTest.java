package scooter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.MainPage;
import pageObject.OrderPage;

import static org.junit.Assert.assertTrue;

// Тест на Заказ самоката
@RunWith(Parameterized.class)
public class OrderScooterTest {

    @Rule
    public final BaseTest baseTest = new BaseTest();

    // Параметры теста
    private final String orderButtonLocation; // "Верхняя" или "Нижняя" кнопки
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String orderDate;
    private final String orderDuration;
    private final String color;
    private final String comment;

    public OrderScooterTest(String orderButtonLocation, String name, String surname, String address,
                            String metroStation, String phone, String orderDate, String orderDuration,
                            String color, String comment) {
        this.orderButtonLocation = orderButtonLocation;
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

    @Parameterized.Parameters(name = "Заказ через кнопку: {0}, пользователь: {1} {2}")
    public static Object[][] getParams() {
        return new Object[][]{
                {"Верхняя", "Александр", "Пушкин", "Тульская, 5", "Тульская", "+79163456678",
                        "Choose пятница, 19-е июня 2026 г.", "сутки", "серая безысходность", "Позвонить на месте"},
                {"Нижняя", "Мария", "Ивановна", "Павелецкая, 36", "Павелецкая", "+79181234567",
                        "Choose суббота, 20-е июня 2026 г.", "семеро суток", "серая безысходность", "Не звонить, только смс"}
        };
    }

    @Test
    public void scooterOrderFlowTest() {
        MainPage mainPage = new MainPage(baseTest.getDriver());
        mainPage.acceptCookies();
        // Кликаем по нужной кнопке в зависимости от переданного параметра
        if ("Верхняя".equalsIgnoreCase(orderButtonLocation)) {
            mainPage.clickUpperOrderButton();
        } else if ("Нижняя".equalsIgnoreCase(orderButtonLocation)) {
            mainPage.clickLowerOrderButton();
        }
        OrderPage orderPage = new OrderPage(baseTest.getDriver());
        // Заполняем форму "Для кого самокат"
        orderPage.fillWhoIsScooterForm(name, surname, address, metroStation, phone);
        // Заполняем форму "Про аренду"
        orderPage.fillAboutRentForm(orderDate, orderDuration, color, comment);
        // Проверяем появление окна об успешном оформлении заказа
        assertTrue("Модальное окно 'Заказ оформлен' не появилось!",
                orderPage.isSuccessOrderModalDisplayed());
    }
}
