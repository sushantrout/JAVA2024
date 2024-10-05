package com.tech.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}