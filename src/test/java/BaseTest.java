import org.magneto.driver.DriverProvider;
import org.magneto.entities.Checkout;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import static org.magneto.entities.EntityProvider.getEntity;
import static org.magneto.entities.EntityType.CHECKOUT;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Checkout checkout;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void beforeClass(@Optional("chrome") String browser) {
        System.setProperty(DriverProvider.getInstance().BROWSER_KEY, browser);

    }

    @BeforeMethod
    public void beforeMethod(ITestContext context) {
        context.setAttribute("WebDriver", DriverProvider.getInstance().getDriver());
        checkout = getEntity(CHECKOUT);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DriverProvider.getInstance().quit();
    }
}
