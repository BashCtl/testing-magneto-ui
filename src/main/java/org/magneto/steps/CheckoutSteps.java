package org.magneto.steps;

import com.google.gson.Gson;
import lombok.Getter;
import org.magneto.dto.AddressInformation;
import org.magneto.dto.Root;
import org.magneto.entities.Checkout;
import org.magneto.pages.CheckoutPage;
import org.openqa.selenium.devtools.v118.network.model.Request;
import org.openqa.selenium.devtools.v118.network.Network;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CheckoutSteps extends BaseSteps{
    @Getter
    private  final AtomicReference<String> capturedData = new AtomicReference<>();
    private CheckoutPage checkoutPage;
    public CheckoutSteps() {
        checkoutPage = new CheckoutPage();
    }

    public CheckoutSteps fillOutCheckoutForm(Checkout checkout){

        checkoutPage
                .waitUntilLoading()
                .enterEmail(checkout.getEmail())
                .enterFirstName(checkout.getFirstName())
                .enterLastName(checkout.getLastName())
                .enterCompany(checkout.getCompany())
                .enterStreetFirstLine(checkout.getAddress1())
                .enterStreetSecondLine(checkout.getAddress2())
                .enterStreetThirdLine(checkout.getAddress3())
                .enterCity(checkout.getCity())
                .selectCountry(checkout.getCountry())
                .enterZipCode(checkout.getZipCode())
                .selectState(checkout.getState())
                .enterPhoneNumber(checkout.getPhoneNumber())
                .selectShippingMethod(checkout.isFixedMethod());
        return this;
    }


    public AddressInformation  proceedToPayment(){
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),
                request -> {
                    Request req = request.getRequest();
                    if(req.getUrl().contains("shipping-information")){
                         capturedData.set(req.getPostData().get());
                    }
                });


        checkoutPage.clickNextButton();
        checkoutPage.waitUntilLoading();

        devTools.send(Network.disable());
        devTools.disconnectSession();

        return new Gson().fromJson(capturedData.get(), Root.class).getAddressInformation();
    }


    public CheckoutSteps confirmOrder(){
        checkoutPage.clickPlaceOrderButton();
        return this;
    }

    public String getOrderId(){
        return checkoutPage.getOrderId();
    }

}
