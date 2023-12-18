package org.magneto.entities;

import lombok.Data;

@Data
public class Checkout implements Entity{

    private String email;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    private String phoneNumber;
    private boolean isTableRateMethod;
    private boolean isFixedMethod;

}
