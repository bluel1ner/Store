package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Configuration;
import com.example.userservice.entity.mongo.Product;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        String id,
        String name,
        ProductType type,
        Integer price,
        String display,
        String powerAndBattery,
        List<Configuration> configurations,
        List<Color> colors,
        String video
) {
}
