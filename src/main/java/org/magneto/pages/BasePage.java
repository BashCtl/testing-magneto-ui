package org.magneto.pages;

import org.magneto.driver.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.magneto.utils.PropsLoader.loadProperties;

public abstract class BasePage<T extends BasePage<T>> {


    protected final String ENV_URL = loadProperties()
            .getProperty(System.getProperty("env", "QA"));
    public WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected JavascriptExecutor executor;
    private final int WAIT_SECONDS = 30;


    public BasePage() {
        driver = DriverProvider.getInstance().getDriver();
        driver.manage().deleteAllCookies();
        actions = new Actions(driver);
        executor = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public WebElement waitUntilElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUnitElementPresence(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public  boolean waitUntilEnabled(WebElement element){
        return wait.until(d->element.isEnabled());
    }

    public WebElement waitUntilElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilRefreshedVisibility(WebElement element){
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }

    public WebElement waitUntilRefreshedVisibility(By locator){
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public void scrollToElement(WebElement element) {
        this.executor.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});", element);
    }

    public void  waitUntilNotVisible(WebElement element){
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)));
    }

    public void clickElement(WebElement element){
        this.executor.executeScript("arguments[0].click();", element);
    }


    public  void waitUntilPageLoaded() {
        ExpectedCondition<Boolean> pageLoadCondition = driver -> executor.executeScript("return document.readyState").equals("complete");
        wait.until(pageLoadCondition);
    }


    public  void hoverUsingJavaScript(WebElement element) {

        String script = "var element = arguments[0];"
                + "var mouseEvent = document.createEvent('MouseEvents');"
                + "mouseEvent.initEvent('mouseover', true, true);"
                + "element.dispatchEvent(mouseEvent);";

        executor.executeScript(script, element);
    }

    public void waitUntilElementContainsText(By locator, String text){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator,text));
    }

    public  WebElement waitForElementToBeClickable(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20)) // Maximum wait time of 20 seconds
                .pollingEvery(Duration.ofMillis(500)) // Polling interval of 500 milliseconds
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void  sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
