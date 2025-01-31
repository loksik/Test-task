import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSingleton {
    private static WebDriver instance;

    private WebDriverSingleton() {}

    public static WebDriver getInstance() {
        if (instance == null) {
            WebDriverManager.chromedriver().setup();
            instance = new ChromeDriver();
        }
        return instance;
    }

    public static void quitDriver() {
        if (instance != null) {
            instance.quit();
            instance = null;
        }
    }
}
