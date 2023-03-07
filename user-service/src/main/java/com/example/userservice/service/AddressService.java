package com.example.userservice.service;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;

import java.util.List;

public interface AddressService {
    AddressResponse addAddress(Address address);

    List<AddressResponse> getAllAddresses();
}

