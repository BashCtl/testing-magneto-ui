package org.magneto.steps;

import org.magneto.driver.DriverProvider;
import org.magneto.pages.HomePage;
import org.openqa.selenium.devtools.DevTools;

public abstract class BaseSteps {


    protected HomePage homePage;

    protected DevTools devTools;
    public BaseSteps() {
        homePage = new HomePage();
        devTools = DriverProvider.getInstance().getDevTools();

    }

    public CheckoutSteps proceedToCheckout(){
        homePage.clickCartIcon()
                .clickCheckoutBtn();
        return new CheckoutSteps();
    }
}
