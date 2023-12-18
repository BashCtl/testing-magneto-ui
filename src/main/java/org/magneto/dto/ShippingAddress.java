package org.magneto.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
public class ShippingAddress {
    private String countryId;
    private String regionId;
    private String regionCode;
    private String region;
    private ArrayList<String> street;
    private String company;
    private String telephone;
    private String postcode;
    private String city;
    private String firstname;
    private String lastname;
}
