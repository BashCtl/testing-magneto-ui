import org.magneto.dto.AddressInformation;
import org.magneto.dto.ShippingAddress;
import org.magneto.steps.BagsSteps;
import org.magneto.steps.CheckoutSteps;
import org.magneto.steps.HomeSteps;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.magneto.utils.FileHandler.COLLECTED_DATA_DIR;
import static org.magneto.utils.FileHandler.writeCollectedData;

public class E2ETest extends BaseTest {
    private HomeSteps homeSteps;
    private BagsSteps bagsSteps;
    private CheckoutSteps checkoutSteps;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context) {
        super.beforeMethod(context);
        homeSteps = new HomeSteps();
        bagsSteps = new BagsSteps();
        checkoutSteps = new CheckoutSteps();
    }

    @Test()
    public void placeOrderTest() {
        homeSteps.openPage()
                .navigateToBags()
                .addBagToCart("Overnight Duffle");
        String alertText = bagsSteps.getAlertText();
        Assert.assertTrue(alertText.contains("You added"));

        bagsSteps.proceedToCheckout();
        AddressInformation addressInformation = checkoutSteps.fillOutCheckoutForm(checkout)
                .proceedToPayment();
        ShippingAddress shippingAddress = addressInformation.getShipping_address();

        Assert.assertEquals(shippingAddress.getRegion(), checkout.getState());
        Assert.assertEquals(shippingAddress.getStreet().get(0), checkout.getAddress1());
        Assert.assertEquals(shippingAddress.getStreet().get(1), checkout.getAddress2());
        Assert.assertEquals(shippingAddress.getStreet().get(2), checkout.getAddress3());
        Assert.assertEquals(shippingAddress.getCompany(), checkout.getCompany());
        Assert.assertEquals(shippingAddress.getTelephone(), checkout.getPhoneNumber());
        Assert.assertEquals(shippingAddress.getPostcode(), checkout.getZipCode());
        Assert.assertEquals(shippingAddress.getCity(), checkout.getCity());
        Assert.assertEquals(shippingAddress.getFirstname(), checkout.getFirstName());
        Assert.assertEquals(shippingAddress.getLastname(), checkout.getLastName());


        checkoutSteps.confirmOrder();
        String orderId = checkoutSteps.getOrderId();
        writeCollectedData(orderId, COLLECTED_DATA_DIR + "test_data.txt");
    }
}
