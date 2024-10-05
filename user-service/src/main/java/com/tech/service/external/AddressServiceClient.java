package com.tech.service.external;

import com.tech.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service")
public interface AddressServiceClient {
    @GetMapping(value = "addresses/{id}")
    AddressDTO getAddress(@PathVariable Long id);
}
