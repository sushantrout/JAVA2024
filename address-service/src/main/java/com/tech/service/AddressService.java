package com.tech.service;

import com.tech.entity.Address;
import com.tech.repo.AddressRepository;
import com.tech.service.external.TaskClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final TaskClient taskClient;

    // Retrieve all addresses
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Retrieve an address by its ID
    public Optional<Address> getAddressById(Long id) {
        String task = taskClient.getTask();
        log.info("Task: {}", task);
        return addressRepository.findById(id);
    }

    // Create a new address
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // Update an existing address
    public Address updateAddress(Long id, Address address) {
        if (addressRepository.existsById(id)) {
            return addressRepository.save(address);
        } else {
            throw new IllegalArgumentException("Address with id " + id + " does not exist.");
        }
    }

    // Delete an address by its ID
    public void deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Address with id " + id + " does not exist.");
        }
    }
}
