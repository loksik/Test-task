import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.A1Home;

import java.time.Duration;

public class A1Tests {

    private WebDriver driver;
    private WebDriverWait wait;
    private A1Home a1Home;

    @BeforeEach
    public void setUp() {
        driver = WebDriverSingleton.getInstance();
        driver.get("https://www.a1.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        a1Home = new A1Home(driver, wait);
        a1Home.targetCookies();
    }

    @Test
    @Description("Проверка подписания на рассылку")
    public void SubscribeToNewsletterTest() {
        Assertions.assertTrue(a1Home.visibleEmailSubscriptionForm(), "Форма ввода email-а в не поля видимости");
        a1Home.inputEmailForm("test@test.test");
        a1Home.clickEmailButton();
        Assertions.assertEquals("Вы подписались\nВы успешно подписались на нашу новостную рассылку.", a1Home.getSuccessfulSubscription());
    }

    @AfterEach
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }
}
