package org.magneto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCartPage extends BasePage<MiniCartPage> {

    @FindBy(id = "top-cart-btn-checkout")
    private WebElement checkoutBtn;

    @FindBy(id="minicart-content-wrapper")
    private WebElement miniCartContent;


    public CheckoutPage clickCheckoutBtn() {
        sleep(500);
        waitForElementToBeClickable(checkoutBtn);
        checkoutBtn.click();
        return new CheckoutPage();
    }
}
