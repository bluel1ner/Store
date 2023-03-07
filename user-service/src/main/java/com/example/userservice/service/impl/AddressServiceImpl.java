package com.example.userservice.service.impl;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressResponse addAddress(Address address) {
//        addressRepository.save(address);
//        addressRepository.
//        return AddressResponse.builder()
//                .id()
//                .street()
//                .country()
//                .house()
//                .city()
//                .apartment("")
//                .build();
        return null;
    }

}
