package org.magneto.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AddressInformation {
    private ShippingAddress shipping_address;
    private BillingAddress billing_address;
    private String shipping_method_code;
    private String shipping_carrier_code;
    private ExtensionAttributes extension_attributes;
}
