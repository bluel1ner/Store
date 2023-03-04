package com.example.userservice.dto;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

@Builder
public record ProductDto(
        Integer id,
        Double price,
        String brand,
        String name,
        ProductType productType,
        String description,
        String color,
        String originCountry,
        Integer amount,
        Float averageRate
) {
}
