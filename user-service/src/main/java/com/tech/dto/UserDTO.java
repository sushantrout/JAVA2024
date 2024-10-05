package com.tech.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private AddressDTO addressDTO;
}
