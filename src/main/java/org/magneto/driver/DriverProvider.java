package org.magneto.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.magneto.driver.DriverOptions.*;

public class DriverProvider {

    public final String BROWSER_KEY = "browser";
    @Getter
    private static final DriverProvider instance = new DriverProvider();


    private DriverProvider() {

    }

    private ThreadLocal<Pair<WebDriver, DevTools>> driver = ThreadLocal.withInitial(() ->
            getLocalDriver(System.getProperty(BROWSER_KEY)));


    private Pair<WebDriver, DevTools> getLocalDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeDriver chromeDriver = new ChromeDriver(getChromeOptions());
                DevTools chromeDevTools = chromeDriver.getDevTools();
                return Pair.of(chromeDriver, chromeDevTools);
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeDriver edgeDriver = new EdgeDriver(getEdgeOptions());
                DevTools edgeDevTools = edgeDriver.getDevTools();
                return Pair.of(edgeDriver, edgeDevTools);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                WebDriver firefoxDriver = new FirefoxDriver(getFirefoxOptions());
                DevTools firefoxDevTools = ((HasDevTools)firefoxDriver).getDevTools();
                return Pair.of(firefoxDriver, firefoxDevTools);
            default:
                throw new RuntimeException(String.format("Unknown wed driver type: %s", browser));
        }
    }

    public WebDriver getDriver() {
        return driver.get().getKey();
    }

    public DevTools getDevTools(){
        return driver.get().getValue();
    }

    public void quit() {
        driver.get().getKey().quit();
        driver.remove();
    }

    public void close() {
        driver.get().getKey().close();
        driver.remove();
    }

}
