package com.example.userservice.controller;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.service.AddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @PostMapping
    public AddressResponse createAddress(@RequestBody Address address) {
        addressService.addAddress(address);
        return null;
    }
}
