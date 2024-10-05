package com.tech.controller;

import com.tech.dto.AddressDTO;
import com.tech.entity.Address;
import com.tech.mapper.AddressMapper;
import com.tech.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/addresses")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses().stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable Long id) {
        AddressDTO addressDTO = addressService.getAddressById(id)
                .map(AddressMapper::toDTO)
                .orElse(null);
        log.info("Address: {}", addressDTO);
        return addressDTO;
    }

    @PostMapping
    public AddressDTO createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = AddressMapper.toEntity(addressDTO);
        Address createdAddress = addressService.createAddress(address);
        return AddressMapper.toDTO(createdAddress);
    }

    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        Address address = AddressMapper.toEntity(addressDTO);
        address.setId(id);
        Address updatedAddress = addressService.updateAddress(id, address);
        return AddressMapper.toDTO(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
