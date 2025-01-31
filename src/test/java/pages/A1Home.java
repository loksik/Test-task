package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class A1Home {
    protected WebDriver driver;
    private WebDriverWait wait;

    private By emailForm = By.id("news-subscribe-form");
    private By inputEmailForm = By.id("i-subscribe-input");
    private By emailButton = By.xpath("//*[@id=\"news-subscribe-form\"]/fieldset/div/label[1]/button");
    private By cancelCookies = By.xpath("//*[@id=\"CookiesStickyPanel\"]//button[2]");
    private By cookiesBanner = By.id("CookiesStickyPanel");
    private By successfulSubscriptionTitle = By.xpath("//div[@class='iziToast-wrapper iziToast-target']//p//div[@class='toast-content-title']");
    private By successfulSubscriptionText = By.xpath("//div[@class='iziToast-wrapper iziToast-target']//p//div[@class='toast-content-text']");


    public A1Home(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void targetCookies() {
        try {
            WebElement cookieBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(cookiesBanner));
            if (cookieBanner.isDisplayed()) {
                System.out.println("Куки отображаются!");
                driver.findElement(cancelCookies).click();
            }
        }
        catch (Exception e) {
            System.out.println("Куки не требуются!");
        }
    }

    public void scrollEmailSubscriptionForm (WebElement emailSubscriptionForm) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailSubscriptionForm);
    }

    public boolean visibleEmailSubscriptionForm () {
        WebElement emailSubscriptionForm = driver.findElement(emailForm);
        scrollEmailSubscriptionForm(emailSubscriptionForm);
        if (!emailSubscriptionForm.isDisplayed()) {
         scrollEmailSubscriptionForm(emailSubscriptionForm);
        }
        return emailSubscriptionForm.isDisplayed();
    }

    public void inputEmailForm(String email) {
        WebElement inputEmail = wait.until(ExpectedConditions.elementToBeClickable(inputEmailForm));
        inputEmail.sendKeys(email);
    }

    public void clickEmailButton () {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(emailButton));
        scrollEmailSubscriptionForm(button);
        button.click();
    }

    public String getSuccessfulSubscription () {
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successfulSubscriptionTitle));
        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(successfulSubscriptionText));

        String result = titleElement.getText();
        result += "\n" + textElement.getText();
        return  result;
    }
}

