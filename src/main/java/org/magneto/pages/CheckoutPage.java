package org.magneto.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage<CheckoutPage> {

    @FindBy(id = "customer-email")
    private WebElement emailField;

    @FindBy(css = "input[name='firstname']")
    private WebElement firstNameField;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameField;

    @FindBy(css = "input[name='company']")
    private WebElement companyField;

    @FindBy(css = "input[name='street[0]']")
    private WebElement addressField1;

    @FindBy(css = "input[name='street[1]']")
    private WebElement addressField2;

    @FindBy(css = "input[name='street[2]']")
    private WebElement addressField3;

    @FindBy(css = "input[name='city']")
    private WebElement cityField;

    @FindBy(css = "select[name='region_id']")
    private WebElement stateDropdown;

    @FindBy(css = "input[name='postcode']")
    private WebElement zipCodeField;

    @FindBy(css = "select[name='country_id']")
    private WebElement countryDropdown;

    @FindBy(css = "input[name='telephone']")
    private WebElement phoneNumberField;



    @FindBy(css = "input[value='flatrate_flatrate']")
    private WebElement fixedShippingRadioBtn;

    @FindBy(css = "input[value='tablerate_bestway']")
    private WebElement tableRateShippingRadioBtn;

    @FindBy(css = "button[data-role='opc-continue']")
    private WebElement nextBtn;

    @FindBy(css="button.checkout")
    private WebElement placeOrderBtn;

    @FindBy(id = "checkout-loader")
    private WebElement checkoutLoader;

    @FindBy(css = ".checkout-success p span")
    private WebElement orderIdEl;


    public CheckoutPage waitUntilLoading(){
        waitUntilNotVisible(checkoutLoader);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        waitUntilClickable(emailField).clear();
        emailField.sendKeys(email);
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterCompany(String company) {
        companyField.clear();
        companyField.sendKeys(company);
        return this;
    }

    public CheckoutPage enterStreetFirstLine(String address) {
        addressField1.clear();
        addressField1.sendKeys(address);
        return this;
    }

    public CheckoutPage enterStreetSecondLine(String address) {
        addressField2.clear();
        addressField2.sendKeys(address);
        return this;
    }

    public CheckoutPage enterStreetThirdLine(String address) {
        addressField3.clear();
        addressField3.sendKeys(address);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String state) {
        scrollToElement(stateDropdown);
        waitUntilClickable(stateDropdown);
        Select drpState = new Select(stateDropdown);
        drpState.selectByVisibleText(state);
        return this;
    }

    public CheckoutPage enterZipCode(String zipCode) {
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    public CheckoutPage selectCountry(String country) {
        scrollToElement(countryDropdown);
        waitUntilClickable(countryDropdown);
        Select drpCountry = new Select(countryDropdown);
        drpCountry.selectByVisibleText(country);
        return this;
    }


    public CheckoutPage enterPhoneNumber(String number) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(number);
        return this;
    }

    public CheckoutPage selectShippingMethod(boolean isFixed) {
        if (isFixed) {
//            waitForElementToBeClickable(fixedShippingRadioBtn);
//            fixedShippingRadioBtn.click();
            clickElement(fixedShippingRadioBtn);
        } else {
            waitForElementToBeClickable(tableRateShippingRadioBtn);
            tableRateShippingRadioBtn.click();
        }
        return this;
    }

    public CheckoutPage clickNextButton(){
        waitForElementToBeClickable(nextBtn);
        nextBtn.click();
        return this;
    }

    public CheckoutPage clickPlaceOrderButton(){
        waitUntilLoading();
        waitForElementToBeClickable(placeOrderBtn);
        clickElement(placeOrderBtn);
        return this;
    }

    public String getOrderId(){
        waitUntilPageLoaded();
        waitUntilElementVisible(orderIdEl);
        return orderIdEl.getText();
    }
}
