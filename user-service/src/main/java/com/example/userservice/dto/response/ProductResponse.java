package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

@Builder
public record ProductResponse(
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
