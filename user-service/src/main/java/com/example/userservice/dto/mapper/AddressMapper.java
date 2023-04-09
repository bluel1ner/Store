package com.example.userservice.dto.mapper;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/9/2023 11:56 AM
 */
@Component
@NoArgsConstructor

public class AddressMapper {

    public AddressResponse toResponseDto(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .status(address.getStatus())
                .state(address.getState())
                .apartment(address.getApartment())
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .house(address.getHouse())
                .build();
    }

}
