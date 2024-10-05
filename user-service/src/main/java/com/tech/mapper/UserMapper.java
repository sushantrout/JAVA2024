package com.tech.mapper;

import com.tech.dto.AddressDTO;
import com.tech.dto.UserDTO;
import com.tech.entity.ApplicationUser;
import com.tech.service.external.AddressServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final AddressServiceClient addressClient;

    public UserDTO toDTO(ApplicationUser user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        Long addressId = user.getAddressId();
        if(Objects.nonNull(addressId)) {
            AddressDTO address = addressClient.getAddress(addressId);
            userDTO.setAddressDTO(address);
        }
        return userDTO;
    }

    public ApplicationUser toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        ApplicationUser user = new ApplicationUser();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        if(Objects.nonNull(userDTO.getAddressDTO())) {
            user.setAddressId(userDTO.getAddressDTO().getId());
        }
        return user;
    }
}
